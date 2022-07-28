/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.layout.admin.web.internal.display.context;

import com.liferay.client.extension.constants.ClientExtensionEntryConstants;
import com.liferay.client.extension.type.item.selector.CETItemSelectorReturnType;
import com.liferay.client.extension.type.item.selector.criterion.CETItemSelectorCriterion;
import com.liferay.client.extension.type.manager.CETManager;
import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.criteria.FileEntryItemSelectorReturnType;
import com.liferay.item.selector.criteria.file.criterion.FileItemSelectorCriterion;
import com.liferay.layout.admin.constants.LayoutAdminPortletKeys;
import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutServiceUtil;
import com.liferay.portal.kernel.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.kernel.service.permission.GroupPermissionUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portlet.layoutsadmin.display.context.GroupDisplayContextHelper;
import com.liferay.staging.StagingGroupHelper;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jürgen Kappler
 */
public class BaseLayoutsAdminDisplayContext {

	public BaseLayoutsAdminDisplayContext(
		CETManager cetManager, ItemSelector itemSelector,
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		StagingGroupHelper stagingGroupHelper) {

		this.cetManager = cetManager;
		this.itemSelector = itemSelector;
		this.liferayPortletRequest = liferayPortletRequest;
		this.liferayPortletResponse = liferayPortletResponse;
		this.stagingGroupHelper = stagingGroupHelper;

		httpServletRequest = PortalUtil.getHttpServletRequest(
			this.liferayPortletRequest);

		groupDisplayContextHelper = new GroupDisplayContextHelper(
			httpServletRequest);

		themeDisplay = (ThemeDisplay)liferayPortletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public String getBackURL() {
		PortletURL portletURL = PortalUtil.getControlPanelPortletURL(
			liferayPortletRequest, getGroup(),
			LayoutAdminPortletKeys.GROUP_PAGES, 0, 0,
			PortletRequest.RENDER_PHASE);

		return portletURL.toString();
	}

	public String getFileEntryItemSelectorURL() {
		FileItemSelectorCriterion itemSelectorCriterion =
			new FileItemSelectorCriterion();

		itemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			new FileEntryItemSelectorReturnType());

		if (!GetterUtil.getBoolean(PropsUtil.get("feature.flag.LPS-153457"))) {
			return String.valueOf(
				itemSelector.getItemSelectorURL(
					RequestBackedPortletURLFactoryUtil.create(
						httpServletRequest),
					getSelectFaviconEventName(), itemSelectorCriterion));
		}

		CETItemSelectorCriterion cetItemSelectorCriterion =
			new CETItemSelectorCriterion();

		cetItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			new CETItemSelectorReturnType());
		cetItemSelectorCriterion.setType(
			ClientExtensionEntryConstants.TYPE_THEME_FAVICON);

		return String.valueOf(
			itemSelector.getItemSelectorURL(
				RequestBackedPortletURLFactoryUtil.create(httpServletRequest),
				getSelectFaviconEventName(), itemSelectorCriterion,
				cetItemSelectorCriterion));
	}

	public Group getGroup() {
		return groupDisplayContextHelper.getGroup();
	}

	public Long getGroupId() {
		return groupDisplayContextHelper.getGroupId();
	}

	public Group getLiveGroup() {
		return groupDisplayContextHelper.getLiveGroup();
	}

	public Long getLiveGroupId() {
		return groupDisplayContextHelper.getLiveGroupId();
	}

	public String getRedirect() {
		if (_redirect != null) {
			return _redirect;
		}

		_redirect = ParamUtil.getString(
			liferayPortletRequest, "redirect", getBackURL());

		return _redirect;
	}

	public PortletURL getRedirectURL() {
		return PortletURLBuilder.createRenderURL(
			liferayPortletResponse
		).setRedirect(
			getRedirect()
		).setParameter(
			"groupId", getSelGroupId()
		).buildPortletURL();
	}

	public String getSelectFaviconEventName() {
		return liferayPortletResponse.getNamespace() + "selectImage";
	}

	public Group getSelGroup() {
		return groupDisplayContextHelper.getSelGroup();
	}

	public long getSelGroupId() {
		Group selGroup = getSelGroup();

		if (selGroup != null) {
			return selGroup.getGroupId();
		}

		return 0;
	}

	public Layout getSelLayout() {
		if (_selLayout != null) {
			return _selLayout;
		}

		if (getSelPlid() != LayoutConstants.DEFAULT_PLID) {
			_selLayout = LayoutLocalServiceUtil.fetchLayout(getSelPlid());
		}

		return _selLayout;
	}

	public LayoutSet getSelLayoutSet() {
		if (_selLayoutSet != null) {
			return _selLayoutSet;
		}

		Group group = groupDisplayContextHelper.getStagingGroup();

		if (group == null) {
			group = getLiveGroup();
		}

		_selLayoutSet = LayoutSetLocalServiceUtil.fetchLayoutSet(
			group.getGroupId(), isPrivateLayout());

		return _selLayoutSet;
	}

	public Long getSelPlid() {
		if (_selPlid != null) {
			return _selPlid;
		}

		_selPlid = ParamUtil.getLong(
			liferayPortletRequest, "selPlid", LayoutConstants.DEFAULT_PLID);

		return _selPlid;
	}

	public Long getStagingGroupId() {
		return groupDisplayContextHelper.getStagingGroupId();
	}

	public boolean isPrivateLayout() {
		if (_privateLayout != null) {
			return _privateLayout;
		}

		Group selGroup = getSelGroup();

		if (selGroup.isLayoutSetPrototype()) {
			_privateLayout = true;

			return _privateLayout;
		}

		if (getSelLayout() != null) {
			Layout selLayout = getSelLayout();

			_privateLayout = selLayout.isPrivateLayout();

			return _privateLayout;
		}

		Layout layout = themeDisplay.getLayout();

		if (!layout.isTypeControlPanel()) {
			_privateLayout = layout.isPrivateLayout();

			return _privateLayout;
		}

		String privateLayoutString = liferayPortletRequest.getParameter(
			"privateLayout");

		if (Validator.isNotNull(privateLayoutString)) {
			_privateLayout = GetterUtil.getBoolean(privateLayoutString);

			return _privateLayout;
		}

		Boolean privateLayout = false;

		if ((getLayoutsCount(true) > 0) && (getLayoutsCount(false) <= 0)) {
			privateLayout = true;
		}

		_privateLayout = privateLayout;

		return _privateLayout;
	}

	protected int getLayoutsCount(boolean privateLayouts) {
		try {
			if (GroupPermissionUtil.contains(
					themeDisplay.getPermissionChecker(), getSelGroupId(),
					ActionKeys.MANAGE_LAYOUTS)) {

				return LayoutLocalServiceUtil.getLayoutsCount(
					getSelGroup(), privateLayouts, 0);
			}

			return LayoutServiceUtil.getLayoutsCount(
				getSelGroupId(), privateLayouts, 0);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}
		}

		return 0;
	}

	protected final CETManager cetManager;
	protected final GroupDisplayContextHelper groupDisplayContextHelper;
	protected final HttpServletRequest httpServletRequest;
	protected final ItemSelector itemSelector;
	protected final LiferayPortletRequest liferayPortletRequest;
	protected final LiferayPortletResponse liferayPortletResponse;
	protected final StagingGroupHelper stagingGroupHelper;
	protected final ThemeDisplay themeDisplay;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseLayoutsAdminDisplayContext.class);

	private Boolean _privateLayout;
	private String _redirect;
	private Layout _selLayout;
	private LayoutSet _selLayoutSet;
	private Long _selPlid;

}