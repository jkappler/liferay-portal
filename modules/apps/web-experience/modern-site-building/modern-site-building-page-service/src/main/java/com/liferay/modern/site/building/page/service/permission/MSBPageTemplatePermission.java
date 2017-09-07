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

package com.liferay.modern.site.building.page.service.permission;

import com.liferay.modern.site.building.page.model.MSBPageTemplate;
import com.liferay.modern.site.building.page.service.MSBPageTemplateLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(
	property = {"model.class.name=com.liferay.modern.site.building.page.model.MSBPageTemplate"},
	service = BaseModelPermissionChecker.class
)
public class MSBPageTemplatePermission implements BaseModelPermissionChecker {

	public static void check(
			PermissionChecker permissionChecker,
			MSBPageTemplate msbPageTemplate, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, msbPageTemplate, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, MSBPageTemplate.class.getName(),
				msbPageTemplate.getMsbPageTemplateFolderId(), actionId);
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long groupId,
			long msbPageTemplateId, String actionId)
		throws PortalException {

		if (!contains(
			permissionChecker, groupId, msbPageTemplateId, actionId)) {

			throw new PrincipalException.MustHavePermission(
				permissionChecker, MSBPageTemplate.class.getName(),
				msbPageTemplateId, actionId);
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, MSBPageTemplate msbPageTemplate,
		String actionId) {

		return _hasPermission(permissionChecker, msbPageTemplate, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long groupId,
		long msbPageTemplateId, String actionId) {

		MSBPageTemplate msbPageTemplate =
			_msbPageTemplateLocalService.fetchMSBPageTemplate(
				msbPageTemplateId);

		if (msbPageTemplate == null) {
			_log.error("Unable to get page template msbPageTemplate " +
			   	msbPageTemplateId);

			return false;
		}

		return contains(permissionChecker, msbPageTemplate, actionId);
	}

	@Override
	public void checkBaseModel(
			PermissionChecker permissionChecker, long groupId, long primaryKey,
			String actionId)
		throws PortalException {

		check(permissionChecker, groupId, primaryKey, actionId);
	}

	@Reference(unbind = "-")
	protected void setMSBPageTemplateLocalService(
		MSBPageTemplateLocalService msbPageTemplateLocalService) {

		_msbPageTemplateLocalService = msbPageTemplateLocalService;
	}

	private static boolean _hasPermission(
		PermissionChecker permissionChecker, MSBPageTemplate msbPageTemplate,
		String actionId) {

		if (permissionChecker.hasOwnerPermission(
				msbPageTemplate.getCompanyId(), MSBPageTemplate.class.getName(),
				msbPageTemplate.getMsbPageTemplateFolderId(),
				msbPageTemplate.getUserId(), actionId) ||
			permissionChecker.hasPermission(
				msbPageTemplate.getGroupId(), MSBPageTemplate.class.getName(),
				msbPageTemplate.getMsbPageTemplateFolderId(), actionId)) {

			return true;
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MSBPageTemplatePermission.class);

	private static MSBPageTemplateLocalService _msbPageTemplateLocalService;

}