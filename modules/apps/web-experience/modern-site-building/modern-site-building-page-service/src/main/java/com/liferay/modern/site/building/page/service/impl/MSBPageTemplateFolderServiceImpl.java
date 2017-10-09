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

package com.liferay.modern.site.building.page.service.impl;

import com.liferay.modern.site.building.page.model.MSBPageTemplateFolder;
import com.liferay.modern.site.building.page.service.base.MSBPageTemplateFolderServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Pavel Savinov
 */
public class MSBPageTemplateFolderServiceImpl
	extends MSBPageTemplateFolderServiceBaseImpl {

	@Override
	public void deleteMSBPageTemplates(long msbPageTemplateFolderId) {
		msbPageTemplateFolderLocalService.deleteMSBPageTemplates(
			msbPageTemplateFolderId);
	}

	@Override
	public MSBPageTemplateFolder deleteMSBPageTemplateFolder(
			long msbPageTemplateFolderId)
		throws  PortalException {

		return msbPageTemplateFolderLocalService.deleteMSBPageTemplateFolder(
			msbPageTemplateFolderId);
	}

	@Override
	public MSBPageTemplateFolder addMSBPageTemplateFolder(
			long groupId, String name, String description,
			ServiceContext serviceContext)
		throws PortalException {

		return msbPageTemplateFolderLocalService.addMSBPageTemplateFolder(
			groupId, name, description, serviceContext);
	}

	@Override
	public MSBPageTemplateFolder getMSBPageTemplateFolder(long msbPageTemplateFolderId)
		throws PortalException {

		return msbPageTemplateFolderLocalService.fetchMSBPageTemplateFolder(
			msbPageTemplateFolderId);
	}

	@Override
	public MSBPageTemplateFolder fetchMSBPageTemplateFolder(long msbPageTemplateFolderId)
		throws PortalException {

		return msbPageTemplateFolderLocalService.fetchMSBPageTemplateFolder(
			msbPageTemplateFolderId);
	}

	@Override
	public List<MSBPageTemplateFolder> getMSBPageTemplateFolders(
		long groupId, int start, int end, OrderByComparator obc) {

		return msbPageTemplateFolderLocalService.getMSBPageTemplateFolders(
			groupId, start, end, obc);
	}

	@Override
	public int getMSBPageTemplateFoldersCount(long groupId) {
		return msbPageTemplateFolderLocalService.getMSBPageTemplateFoldersCount(
			groupId);
	}

	@Override
	public List<MSBPageTemplateFolder> searchMSBPageTemplateFolders(
		long groupId, String keywords, int start, int end,
		OrderByComparator obc) {

		return msbPageTemplateFolderLocalService.searchMSBPageTemplateFolders(
			groupId, keywords, start, end, obc);
	}

	@Override
	public int searchMSBPageTemplateFoldersCount(long groupId, String keywords) {
		return msbPageTemplateFolderLocalService.
			searchMSBPageTemplateFoldersCount(groupId, keywords);
	}

	@Override
	public MSBPageTemplateFolder updateMSBPageTemplateFolder(
		long msbPageTemplateFolderId, String name, String description,
		ServiceContext serviceContext)
		throws PortalException {

		return msbPageTemplateFolderLocalService.updateMSBPageTemplateFolder(
			msbPageTemplateFolderId, name, description, serviceContext);
	}

}