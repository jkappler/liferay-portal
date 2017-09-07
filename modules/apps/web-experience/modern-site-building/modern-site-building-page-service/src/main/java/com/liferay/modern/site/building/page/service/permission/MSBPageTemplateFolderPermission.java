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

import com.liferay.modern.site.building.page.model.MSBPageTemplateFolder;
import com.liferay.modern.site.building.page.service.MSBPageTemplateFolderLocalService;
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
	property = {"model.class.name=com.liferay.modern.site.building.page.model.MSBPageTemplateFolder"},
	service = BaseModelPermissionChecker.class
)
public class MSBPageTemplateFolderPermission implements BaseModelPermissionChecker {

	public static void check(
			PermissionChecker permissionChecker, MSBPageTemplateFolder folder,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, folder, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, MSBPageTemplateFolder.class.getName(),
				folder.getMsbPageTemplateFolderId(), actionId);
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long groupId, long folderId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, groupId, folderId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, MSBPageTemplateFolder.class.getName(), folderId,
				actionId);
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, MSBPageTemplateFolder folder,
		String actionId) {

		return _hasPermission(permissionChecker, folder, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long groupId, long folderId,
		String actionId) {

		MSBPageTemplateFolder folder =
			_msbPageTemplateFolderLocalService.fetchMSBPageTemplateFolder(
				folderId);

		if (folder == null) {
			_log.error("Unable to get page template folder " + folderId);

			return false;
		}

		return contains(permissionChecker, folder, actionId);

	}

	@Override
	public void checkBaseModel(
			PermissionChecker permissionChecker, long groupId, long primaryKey,
			String actionId)
		throws PortalException {

		check(permissionChecker, groupId, primaryKey, actionId);
	}

	@Reference(unbind = "-")
	protected void setMSBPageTemplateFolderLocalService(
		MSBPageTemplateFolderLocalService msbPageTemplateFolderLocalService) {

		_msbPageTemplateFolderLocalService = msbPageTemplateFolderLocalService;
	}

	private static boolean _hasPermission(
		PermissionChecker permissionChecker, MSBPageTemplateFolder folder,
		String actionId) {

		if (permissionChecker.hasOwnerPermission(
				folder.getCompanyId(), MSBPageTemplateFolder.class.getName(),
				folder.getMsbPageTemplateFolderId(), folder.getUserId(), actionId)
			|| permissionChecker.hasPermission(
				folder.getGroupId(), MSBPageTemplateFolder.class.getName(),
				folder.getMsbPageTemplateFolderId(), actionId)) {

			return true;
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MSBPageTemplateFolderPermission.class);

	private static MSBPageTemplateFolderLocalService
		_msbPageTemplateFolderLocalService;

}