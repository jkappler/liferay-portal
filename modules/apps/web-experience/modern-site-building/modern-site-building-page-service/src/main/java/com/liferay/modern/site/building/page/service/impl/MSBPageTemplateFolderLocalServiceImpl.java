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
import com.liferay.modern.site.building.page.service.base.MSBPageTemplateFolderLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.Date;
import java.util.List;

/**
 * @author Pavel Savinov
 */
public class MSBPageTemplateFolderLocalServiceImpl
	extends MSBPageTemplateFolderLocalServiceBaseImpl {

	@Override
	public void deleteMSBPageTemplates(long msbPageTemplateFolderId) {
		msbPageTemplatePersistence.removeByMSBPageTemplateFolderId(
			msbPageTemplateFolderId);
	}

	@Override
	public MSBPageTemplateFolder deleteMSBPageTemplateFolder(
		long msbPageTemplateFolderId)
	throws PortalException {

		MSBPageTemplateFolder msbPageTemplateFolder =
			msbPageTemplateFolderPersistence.findByPrimaryKey(
				msbPageTemplateFolderId);

		return deleteMSBPageTemplateFolder(msbPageTemplateFolder);
	}

	@Override
	public MSBPageTemplateFolder deleteMSBPageTemplateFolder(
		MSBPageTemplateFolder msbPageTemplateFolder) {

		deleteMSBPageTemplates(
			msbPageTemplateFolder.getMsbPageTemplateFolderId());

		return msbPageTemplateFolderPersistence.remove(msbPageTemplateFolder);
	}

	@Override
	public MSBPageTemplateFolder addMSBPageTemplateFolder(
			long groupId, String name, String description,
			ServiceContext serviceContext)
		throws PortalException {

		long msbPageTemplateFolderId = counterLocalService.increment();

		MSBPageTemplateFolder msbPageTemplateFolder =
			msbPageTemplateFolderPersistence.create(msbPageTemplateFolderId);

		User user = userLocalService.getUser(serviceContext.getUserId());

		Date now = new Date();

		msbPageTemplateFolder.setCompanyId(serviceContext.getCompanyId());
		msbPageTemplateFolder.setGroupId(groupId);
		msbPageTemplateFolder.setCreateDate(serviceContext.getCreateDate(now));
		msbPageTemplateFolder.setModifiedDate(serviceContext.getModifiedDate(now));
		msbPageTemplateFolder.setUserId(serviceContext.getUserId());
		msbPageTemplateFolder.setUserName(user.getFullName());

		msbPageTemplateFolder.setName(name);
		msbPageTemplateFolder.setDescription(description);

		msbPageTemplateFolderPersistence.update(msbPageTemplateFolder);

		resourceLocalService.addModelResources(
			msbPageTemplateFolder, serviceContext);

		return msbPageTemplateFolder;
	}

	@Override
	public List<MSBPageTemplateFolder> getMSBPageTemplateFolders(
		long groupId, int start, int end, OrderByComparator obc) {

		return msbPageTemplateFolderPersistence.findByGroupId(
			groupId, start, end, obc);
	}

	@Override
	public int getMSBPageTemplateFoldersCount(long groupId) {
		return msbPageTemplateFolderPersistence.countByGroupId(groupId);
	}

	@Override
	public List<MSBPageTemplateFolder> searchMSBPageTemplateFolders(
		long groupId, String keywords, int start, int end,
		OrderByComparator obc) {

		return msbPageTemplateFolderPersistence.findByG_LikeN(
			groupId, keywords, start, end, obc);
	}

	@Override
	public int searchMSBPageTemplateFoldersCount(long groupId, String keywords) {
		return msbPageTemplateFolderPersistence.countByG_LikeN(groupId, keywords);
	}

	@Override
	public MSBPageTemplateFolder updateMSBPageTemplateFolder(
			long msbPageTemplateFolderId, String name, String description,
			ServiceContext serviceContext)
		throws PortalException {

		MSBPageTemplateFolder msbPageTemplateFolder = getMSBPageTemplateFolder(
			msbPageTemplateFolderId);

		msbPageTemplateFolder.setModifiedDate(
			serviceContext.getModifiedDate(new Date()));

		msbPageTemplateFolder.setName(name);
		msbPageTemplateFolder.setDescription(description);

		return msbPageTemplateFolder;
	}

}