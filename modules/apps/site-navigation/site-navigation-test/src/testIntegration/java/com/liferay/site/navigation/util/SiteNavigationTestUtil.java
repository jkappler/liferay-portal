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

package com.liferay.site.navigation.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.site.navigation.menu.item.layout.constants.SiteNavigationMenuItemTypeConstants;
import com.liferay.site.navigation.model.SiteNavigationMenu;
import com.liferay.site.navigation.model.SiteNavigationMenuItem;
import com.liferay.site.navigation.service.SiteNavigationMenuItemLocalServiceUtil;
import com.liferay.site.navigation.service.SiteNavigationMenuLocalServiceUtil;

/**
 * @author Kyle Miho
 */
public class SiteNavigationTestUtil {

	public static SiteNavigationMenu addSiteNavigationMenu(long groupId)
		throws PortalException {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		return SiteNavigationMenuLocalServiceUtil.addSiteNavigationMenu(
			TestPropsValues.getUserId(), groupId, RandomTestUtil.randomString(),
			serviceContext);
	}

	public static SiteNavigationMenuItem addSiteNavigationMenuItem(
			long siteNavigationMenuId, String typeSettings)
		throws PortalException {

		SiteNavigationMenu siteNavigationMenu =
			SiteNavigationMenuLocalServiceUtil.getSiteNavigationMenu(
				siteNavigationMenuId);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				siteNavigationMenu.getGroupId());

		return SiteNavigationMenuItemLocalServiceUtil.addSiteNavigationMenuItem(
			TestPropsValues.getUserId(), serviceContext.getScopeGroupId(),
			siteNavigationMenuId, 1, SiteNavigationMenuItemTypeConstants.LAYOUT,
			typeSettings, serviceContext);
	}

}