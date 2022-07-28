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
import com.liferay.client.extension.model.ClientExtensionEntryRel;
import com.liferay.client.extension.service.ClientExtensionEntryRelLocalServiceUtil;
import com.liferay.client.extension.type.manager.CETManager;
import com.liferay.item.selector.ItemSelector;
import com.liferay.layout.admin.web.internal.util.FaviconUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.RobotsUtil;
import com.liferay.staging.StagingGroupHelper;

import java.io.IOException;

import java.util.TreeMap;

/**
 * @author Jürgen Kappler
 */
public class EditLayoutSetDisplayContext
	extends BaseLayoutsAdminDisplayContext {

	public EditLayoutSetDisplayContext(
		CETManager cetManager, ItemSelector itemSelector,
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		StagingGroupHelper stagingGroupHelper) {

		super(
			cetManager, itemSelector, liferayPortletRequest,
			liferayPortletResponse, stagingGroupHelper);
	}

	public String getFaviconTitle() {
		return FaviconUtil.getFaviconTitle(
			getSelLayoutSet(), themeDisplay.getLocale());
	}

	public String getFaviconURL() {
		String faviconURL = FaviconUtil.getFaviconURL(
			cetManager, getSelLayoutSet());

		if (Validator.isNotNull(faviconURL)) {
			return faviconURL;
		}

		return themeDisplay.getPathThemeImages() + "/" +
			PropsUtil.get(PropsKeys.THEME_SHORTCUT_ICON);
	}

	public UnicodeProperties getGroupTypeSettingsUnicodeProperties() {
		return groupDisplayContextHelper.getGroupTypeSettings();
	}

	public LayoutSet getGuestGroupLayoutSet(long companyId)
		throws PortalException {

		Group group = GroupLocalServiceUtil.getGroup(
			companyId, GroupConstants.GUEST);

		return LayoutSetLocalServiceUtil.getLayoutSet(
			group.getGroupId(), isPrivateLayout());
	}

	public String getRobots() {
		return ParamUtil.getString(
			httpServletRequest, "robots", _getStrictRobots());
	}

	public String getThemeFaviconCETExternalReferenceCode() {
		LayoutSet setLayoutSet = getSelLayoutSet();

		ClientExtensionEntryRel clientExtensionEntryRel =
			ClientExtensionEntryRelLocalServiceUtil.
				fetchClientExtensionEntryRel(
					PortalUtil.getClassNameId(LayoutSet.class),
					setLayoutSet.getLayoutSetId(),
					ClientExtensionEntryConstants.TYPE_THEME_FAVICON);

		if (clientExtensionEntryRel != null) {
			return clientExtensionEntryRel.getCETExternalReferenceCode();
		}

		return StringPool.BLANK;
	}

	public String getVirtualHostname() {
		LayoutSet layoutSet = getSelLayoutSet();

		if (layoutSet == null) {
			return StringPool.BLANK;
		}

		String virtualHostname = null;

		TreeMap<String, String> virtualHostnames =
			PortalUtil.getVirtualHostnames(layoutSet);

		if (!virtualHostnames.isEmpty()) {
			virtualHostname = virtualHostnames.firstKey();
		}

		Group scopeGroup = themeDisplay.getScopeGroup();

		if (Validator.isNull(virtualHostname) && scopeGroup.isStagingGroup()) {
			Group liveGroup = scopeGroup.getLiveGroup();

			LayoutSet liveGroupLayoutSet = liveGroup.getPublicLayoutSet();

			if (layoutSet.isPrivateLayout()) {
				liveGroupLayoutSet = liveGroup.getPrivateLayoutSet();
			}

			virtualHostname = null;

			virtualHostnames = PortalUtil.getVirtualHostnames(
				liveGroupLayoutSet);

			if (!virtualHostnames.isEmpty()) {
				virtualHostname = virtualHostnames.firstKey();
			}
		}

		return virtualHostname;
	}

	public boolean isClearFaviconButtonEnabled() {
		LayoutSet selLayoutSet = getSelLayoutSet();

		if (selLayoutSet.getFaviconFileEntryId() > 0) {
			return true;
		}

		ClientExtensionEntryRel clientExtensionEntryRel =
			ClientExtensionEntryRelLocalServiceUtil.
				fetchClientExtensionEntryRel(
					PortalUtil.getClassNameId(LayoutSet.class),
					selLayoutSet.getLayoutSetId(),
					ClientExtensionEntryConstants.TYPE_THEME_FAVICON);

		if (clientExtensionEntryRel != null) {
			return true;
		}

		return false;
	}

	private String _getStrictRobots() {
		LayoutSet layoutSet = getSelLayoutSet();

		if (layoutSet != null) {
			try {
				return GetterUtil.getString(
					layoutSet.getSettingsProperty(
						layoutSet.isPrivateLayout() + "-robots.txt"),
					StringUtil.read(
						RobotsUtil.class.getClassLoader(),
						PropsValues.ROBOTS_TXT_WITH_SITEMAP));
			}
			catch (IOException ioException) {
				_log.error(
					"Unable to read the content for " +
						PropsValues.ROBOTS_TXT_WITH_SITEMAP);
			}
		}

		try {
			return StringUtil.read(
				RobotsUtil.class.getClassLoader(),
				PropsValues.ROBOTS_TXT_WITHOUT_SITEMAP);
		}
		catch (IOException ioException) {
			_log.error(
				"Unable to read the content for " +
					PropsValues.ROBOTS_TXT_WITHOUT_SITEMAP);

			return null;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditLayoutSetDisplayContext.class);

}