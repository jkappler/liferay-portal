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

package com.liferay.modern.site.building.page.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MSBPageTemplateFolderService}.
 *
 * @author Brian Wing Shun Chan
 * @see MSBPageTemplateFolderService
 * @generated
 */
@ProviderType
public class MSBPageTemplateFolderServiceWrapper
	implements MSBPageTemplateFolderService,
		ServiceWrapper<MSBPageTemplateFolderService> {
	public MSBPageTemplateFolderServiceWrapper(
		MSBPageTemplateFolderService msbPageTemplateFolderService) {
		_msbPageTemplateFolderService = msbPageTemplateFolderService;
	}

	@Override
	public com.liferay.modern.site.building.page.model.MSBPageTemplateFolder addMSBPageTemplateFolder(
		long groupId, java.lang.String name, java.lang.String description,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _msbPageTemplateFolderService.addMSBPageTemplateFolder(groupId,
			name, description, serviceContext);
	}

	@Override
	public com.liferay.modern.site.building.page.model.MSBPageTemplateFolder deleteMSBPageTemplateFolder(
		long msbPageTemplateFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _msbPageTemplateFolderService.deleteMSBPageTemplateFolder(msbPageTemplateFolderId);
	}

	@Override
	public void deleteMSBPageTemplates(long msbPageTemplateFolderId) {
		_msbPageTemplateFolderService.deleteMSBPageTemplates(msbPageTemplateFolderId);
	}

	@Override
	public com.liferay.modern.site.building.page.model.MSBPageTemplateFolder fetchMSBPageTemplateFolder(
		long msbPageTemplateFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _msbPageTemplateFolderService.fetchMSBPageTemplateFolder(msbPageTemplateFolderId);
	}

	@Override
	public com.liferay.modern.site.building.page.model.MSBPageTemplateFolder getMSBPageTemplateFolder(
		long msbPageTemplateFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _msbPageTemplateFolderService.getMSBPageTemplateFolder(msbPageTemplateFolderId);
	}

	@Override
	public java.util.List<com.liferay.modern.site.building.page.model.MSBPageTemplateFolder> getMSBPageTemplateFolders(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return _msbPageTemplateFolderService.getMSBPageTemplateFolders(groupId,
			start, end, obc);
	}

	@Override
	public int getMSBPageTemplateFoldersCount(long groupId) {
		return _msbPageTemplateFolderService.getMSBPageTemplateFoldersCount(groupId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _msbPageTemplateFolderService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.modern.site.building.page.model.MSBPageTemplateFolder> searchMSBPageTemplateFolders(
		long groupId, java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return _msbPageTemplateFolderService.searchMSBPageTemplateFolders(groupId,
			keywords, start, end, obc);
	}

	@Override
	public int searchMSBPageTemplateFoldersCount(long groupId,
		java.lang.String keywords) {
		return _msbPageTemplateFolderService.searchMSBPageTemplateFoldersCount(groupId,
			keywords);
	}

	@Override
	public com.liferay.modern.site.building.page.model.MSBPageTemplateFolder updateMSBPageTemplateFolder(
		long msbPageTemplateFolderId, java.lang.String name,
		java.lang.String description,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _msbPageTemplateFolderService.updateMSBPageTemplateFolder(msbPageTemplateFolderId,
			name, description, serviceContext);
	}

	@Override
	public MSBPageTemplateFolderService getWrappedService() {
		return _msbPageTemplateFolderService;
	}

	@Override
	public void setWrappedService(
		MSBPageTemplateFolderService msbPageTemplateFolderService) {
		_msbPageTemplateFolderService = msbPageTemplateFolderService;
	}

	private MSBPageTemplateFolderService _msbPageTemplateFolderService;
}