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

package com.liferay.content.dashboard.web.internal.portlet.action;

import com.liferay.content.dashboard.web.internal.constants.ContentDashboardPortletKeys;
import com.liferay.content.dashboard.web.internal.item.ContentDashboardItem;
import com.liferay.content.dashboard.web.internal.item.ContentDashboardItemFactory;
import com.liferay.content.dashboard.web.internal.item.ContentDashboardItemFactoryTracker;
import com.liferay.info.item.InfoItemReference;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;
import java.util.Locale;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Stefan Tanasie
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + ContentDashboardPortletKeys.CONTENT_DASHBOARD_ADMIN,
		"mvc.command.name=/content_dashboard/get_content_dashboard_pagination_info"
	},
	service = MVCResourceCommand.class
)
public class GetContentDashboardPaginationInfoMVCResourceCommand
	extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		HttpServletRequest httpServletRequest = _portal.getHttpServletRequest(
			resourceRequest);

		Locale locale = _portal.getLocale(resourceRequest);

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		try {
			String className = ParamUtil.getString(
				resourceRequest, "className");

			ContentDashboardItemFactory<?> contentDashboardItemFactory =
				_contentDashboardItemFactoryTracker.
					getContentDashboardItemFactory(className);

			if (contentDashboardItemFactory == null) {
				JSONPortletResponseUtil.writeJSON(
					resourceRequest, resourceResponse,
					JSONFactoryUtil.createJSONArray());

				return;
			}

			long classPK = GetterUtil.getLong(
				ParamUtil.getLong(resourceRequest, "classPK"));

			ContentDashboardItem<?> contentDashboardItem =
				contentDashboardItemFactory.create(classPK);

			JSONPortletResponseUtil.writeJSON(
				resourceRequest, resourceResponse,
				JSONUtil.put(
					"allVersions",
					_getAllVersionsJSONArray(contentDashboardItem, themeDisplay)
				).put(
					"className", _getClassName(contentDashboardItem)
				).put(
					"classPK", _getClassPK(contentDashboardItem)
				));
		}
		catch (Exception exception) {
			if (_log.isInfoEnabled()) {
				_log.info(exception);
			}

			JSONPortletResponseUtil.writeJSON(
				resourceRequest, resourceResponse,
				JSONUtil.put(
					"error",
					ResourceBundleUtil.getString(
						ResourceBundleUtil.getBundle(locale, getClass()),
						"an-unexpected-error-occurred")));
		}
	}

	private JSONArray _getAllVersionsJSONArray(
		ContentDashboardItem contentDashboardItem, ThemeDisplay themeDisplay) {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<ContentDashboardItem.Version> allVersions =
			contentDashboardItem.getAllVersions(themeDisplay);

		for (ContentDashboardItem.Version version : allVersions) {
			jsonArray.put(version.toJSONObject());
		}

		return jsonArray;
	}

	private String _getClassName(ContentDashboardItem<?> contentDashboardItem) {
		InfoItemReference infoItemReference =
			contentDashboardItem.getInfoItemReference();

		return infoItemReference.getClassName();
	}

	private long _getClassPK(ContentDashboardItem<?> contentDashboardItem) {
		InfoItemReference infoItemReference =
			contentDashboardItem.getInfoItemReference();

		return infoItemReference.getClassPK();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		GetContentDashboardPaginationInfoMVCResourceCommand.class);

	@Reference
	private ContentDashboardItemFactoryTracker
		_contentDashboardItemFactoryTracker;

	@Reference
	private Portal _portal;

}