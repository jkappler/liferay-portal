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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for MSBPageTemplateFolder. This utility wraps
 * {@link com.liferay.modern.site.building.page.service.impl.MSBPageTemplateFolderServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see MSBPageTemplateFolderService
 * @see com.liferay.modern.site.building.page.service.base.MSBPageTemplateFolderServiceBaseImpl
 * @see com.liferay.modern.site.building.page.service.impl.MSBPageTemplateFolderServiceImpl
 * @generated
 */
@ProviderType
public class MSBPageTemplateFolderServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.modern.site.building.page.service.impl.MSBPageTemplateFolderServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.modern.site.building.page.model.MSBPageTemplateFolder addMSBPageTemplateFolder(
		long groupId, java.lang.String name, java.lang.String description,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addMSBPageTemplateFolder(groupId, name, description,
			serviceContext);
	}

	public static com.liferay.modern.site.building.page.model.MSBPageTemplateFolder deleteMSBPageTemplateFolder(
		long msbPageTemplateFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteMSBPageTemplateFolder(msbPageTemplateFolderId);
	}

	public static void deleteMSBPageTemplates(long msbPageTemplateFolderId) {
		getService().deleteMSBPageTemplates(msbPageTemplateFolderId);
	}

	public static com.liferay.modern.site.building.page.model.MSBPageTemplateFolder fetchMSBPageTemplateFolder(
		long msbPageTemplateFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().fetchMSBPageTemplateFolder(msbPageTemplateFolderId);
	}

	public static com.liferay.modern.site.building.page.model.MSBPageTemplateFolder getMSBPageTemplateFolder(
		long msbPageTemplateFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getMSBPageTemplateFolder(msbPageTemplateFolderId);
	}

	public static java.util.List<com.liferay.modern.site.building.page.model.MSBPageTemplateFolder> getMSBPageTemplateFolders(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService().getMSBPageTemplateFolders(groupId, start, end, obc);
	}

	public static int getMSBPageTemplateFoldersCount(long groupId) {
		return getService().getMSBPageTemplateFoldersCount(groupId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.modern.site.building.page.model.MSBPageTemplateFolder> searchMSBPageTemplateFolders(
		long groupId, java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService()
				   .searchMSBPageTemplateFolders(groupId, keywords, start, end,
			obc);
	}

	public static int searchMSBPageTemplateFoldersCount(long groupId,
		java.lang.String keywords) {
		return getService().searchMSBPageTemplateFoldersCount(groupId, keywords);
	}

	public static com.liferay.modern.site.building.page.model.MSBPageTemplateFolder updateMSBPageTemplateFolder(
		long msbPageTemplateFolderId, java.lang.String name,
		java.lang.String description,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateMSBPageTemplateFolder(msbPageTemplateFolderId, name,
			description, serviceContext);
	}

	public static MSBPageTemplateFolderService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MSBPageTemplateFolderService, MSBPageTemplateFolderService> _serviceTracker =
		ServiceTrackerFactory.open(MSBPageTemplateFolderService.class);
}