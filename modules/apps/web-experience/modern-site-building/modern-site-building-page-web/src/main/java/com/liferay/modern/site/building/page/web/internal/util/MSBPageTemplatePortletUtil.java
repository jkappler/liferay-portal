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

package com.liferay.modern.site.building.page.web.internal.util;

import com.liferay.modern.site.building.page.model.MSBPageTemplate;
import com.liferay.modern.site.building.page.model.MSBPageTemplateFolder;
import com.liferay.modern.site.building.page.util.comparator.MSBPageTemplateCreateDateComparator;
import com.liferay.modern.site.building.page.util.comparator.MSBPageTemplateFolderCreateDateComparator;
import com.liferay.modern.site.building.page.util.comparator.MSBPageTemplateFolderNameComparator;
import com.liferay.modern.site.building.page.util.comparator.MSBPageTemplateNameComparator;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Pavel Savinov
 */
public class MSBPageTemplatePortletUtil {

	public static void addPortletBreadcrumbEntries(
			MSBPageTemplateFolder msbPageTemplateFolder, HttpServletRequest request,
			PortletURL portletURL)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		portletURL.setParameter("toolbarItem", "view-page-templates");

		PortalUtil.addPortletBreadcrumbEntry(
			request, themeDisplay.translate("home"), portletURL.toString());

		if (msbPageTemplateFolder != null) {
			portletURL.setParameter(
				"msbPageTemplateFolderId",
				String.valueOf(msbPageTemplateFolder.
					getMsbPageTemplateFolderId()));

			PortalUtil.addPortletBreadcrumbEntry(
				request, msbPageTemplateFolder.getName(), portletURL.toString());
		}
	}

	public static OrderByComparator<MSBPageTemplateFolder>
		getMSBPageTemplateFolderOrderByComparator(
			String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator<MSBPageTemplateFolder> orderByComparator = null;

		if (orderByCol.equals("create-date")) {
			orderByComparator = new MSBPageTemplateFolderCreateDateComparator(
				orderByAsc);
		}
		else if (orderByCol.equals("name")) {
			orderByComparator = new MSBPageTemplateFolderNameComparator(
				orderByAsc);
		}

		return orderByComparator;
	}

	public static OrderByComparator<MSBPageTemplate>
		getMSBPageTemplateOrderByComparator(
			String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator<MSBPageTemplate> orderByComparator = null;

		if (orderByCol.equals("create-date")) {
			orderByComparator = new MSBPageTemplateCreateDateComparator(
				orderByAsc);
		}
		else if (orderByCol.equals("name")) {
			orderByComparator = new MSBPageTemplateNameComparator(orderByAsc);
		}

		return orderByComparator;
	}

}