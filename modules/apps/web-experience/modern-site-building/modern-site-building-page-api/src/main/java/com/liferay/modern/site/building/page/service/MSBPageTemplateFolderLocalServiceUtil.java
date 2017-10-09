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
 * Provides the local service utility for MSBPageTemplateFolder. This utility wraps
 * {@link com.liferay.modern.site.building.page.service.impl.MSBPageTemplateFolderLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see MSBPageTemplateFolderLocalService
 * @see com.liferay.modern.site.building.page.service.base.MSBPageTemplateFolderLocalServiceBaseImpl
 * @see com.liferay.modern.site.building.page.service.impl.MSBPageTemplateFolderLocalServiceImpl
 * @generated
 */
@ProviderType
public class MSBPageTemplateFolderLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.modern.site.building.page.service.impl.MSBPageTemplateFolderLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.modern.site.building.page.model.MSBPageTemplateFolder addMSBPageTemplateFolder(
		long groupId, java.lang.String name, java.lang.String description,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addMSBPageTemplateFolder(groupId, name, description,
			serviceContext);
	}

	/**
	* Adds the msb page template folder to the database. Also notifies the appropriate model listeners.
	*
	* @param msbPageTemplateFolder the msb page template folder
	* @return the msb page template folder that was added
	*/
	public static com.liferay.modern.site.building.page.model.MSBPageTemplateFolder addMSBPageTemplateFolder(
		com.liferay.modern.site.building.page.model.MSBPageTemplateFolder msbPageTemplateFolder) {
		return getService().addMSBPageTemplateFolder(msbPageTemplateFolder);
	}

	/**
	* Creates a new msb page template folder with the primary key. Does not add the msb page template folder to the database.
	*
	* @param msbPageTemplateFolderId the primary key for the new msb page template folder
	* @return the new msb page template folder
	*/
	public static com.liferay.modern.site.building.page.model.MSBPageTemplateFolder createMSBPageTemplateFolder(
		long msbPageTemplateFolderId) {
		return getService().createMSBPageTemplateFolder(msbPageTemplateFolderId);
	}

	/**
	* Deletes the msb page template folder with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param msbPageTemplateFolderId the primary key of the msb page template folder
	* @return the msb page template folder that was removed
	* @throws PortalException if a msb page template folder with the primary key could not be found
	*/
	public static com.liferay.modern.site.building.page.model.MSBPageTemplateFolder deleteMSBPageTemplateFolder(
		long msbPageTemplateFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteMSBPageTemplateFolder(msbPageTemplateFolderId);
	}

	/**
	* Deletes the msb page template folder from the database. Also notifies the appropriate model listeners.
	*
	* @param msbPageTemplateFolder the msb page template folder
	* @return the msb page template folder that was removed
	*/
	public static com.liferay.modern.site.building.page.model.MSBPageTemplateFolder deleteMSBPageTemplateFolder(
		com.liferay.modern.site.building.page.model.MSBPageTemplateFolder msbPageTemplateFolder) {
		return getService().deleteMSBPageTemplateFolder(msbPageTemplateFolder);
	}

	public static void deleteMSBPageTemplates(long msbPageTemplateFolderId) {
		getService().deleteMSBPageTemplates(msbPageTemplateFolderId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.modern.site.building.page.model.impl.MSBPageTemplateFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.modern.site.building.page.model.impl.MSBPageTemplateFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.modern.site.building.page.model.MSBPageTemplateFolder fetchMSBPageTemplateFolder(
		long msbPageTemplateFolderId) {
		return getService().fetchMSBPageTemplateFolder(msbPageTemplateFolderId);
	}

	/**
	* Returns the msb page template folder matching the UUID and group.
	*
	* @param uuid the msb page template folder's UUID
	* @param groupId the primary key of the group
	* @return the matching msb page template folder, or <code>null</code> if a matching msb page template folder could not be found
	*/
	public static com.liferay.modern.site.building.page.model.MSBPageTemplateFolder fetchMSBPageTemplateFolderByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService()
				   .fetchMSBPageTemplateFolderByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the msb page template folder with the primary key.
	*
	* @param msbPageTemplateFolderId the primary key of the msb page template folder
	* @return the msb page template folder
	* @throws PortalException if a msb page template folder with the primary key could not be found
	*/
	public static com.liferay.modern.site.building.page.model.MSBPageTemplateFolder getMSBPageTemplateFolder(
		long msbPageTemplateFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getMSBPageTemplateFolder(msbPageTemplateFolderId);
	}

	/**
	* Returns the msb page template folder matching the UUID and group.
	*
	* @param uuid the msb page template folder's UUID
	* @param groupId the primary key of the group
	* @return the matching msb page template folder
	* @throws PortalException if a matching msb page template folder could not be found
	*/
	public static com.liferay.modern.site.building.page.model.MSBPageTemplateFolder getMSBPageTemplateFolderByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getMSBPageTemplateFolderByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the msb page template folders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.modern.site.building.page.model.impl.MSBPageTemplateFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of msb page template folders
	* @param end the upper bound of the range of msb page template folders (not inclusive)
	* @return the range of msb page template folders
	*/
	public static java.util.List<com.liferay.modern.site.building.page.model.MSBPageTemplateFolder> getMSBPageTemplateFolders(
		int start, int end) {
		return getService().getMSBPageTemplateFolders(start, end);
	}

	public static java.util.List<com.liferay.modern.site.building.page.model.MSBPageTemplateFolder> getMSBPageTemplateFolders(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService().getMSBPageTemplateFolders(groupId, start, end, obc);
	}

	/**
	* Returns all the msb page template folders matching the UUID and company.
	*
	* @param uuid the UUID of the msb page template folders
	* @param companyId the primary key of the company
	* @return the matching msb page template folders, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.modern.site.building.page.model.MSBPageTemplateFolder> getMSBPageTemplateFoldersByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService()
				   .getMSBPageTemplateFoldersByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of msb page template folders matching the UUID and company.
	*
	* @param uuid the UUID of the msb page template folders
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of msb page template folders
	* @param end the upper bound of the range of msb page template folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching msb page template folders, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.modern.site.building.page.model.MSBPageTemplateFolder> getMSBPageTemplateFoldersByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.modern.site.building.page.model.MSBPageTemplateFolder> orderByComparator) {
		return getService()
				   .getMSBPageTemplateFoldersByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of msb page template folders.
	*
	* @return the number of msb page template folders
	*/
	public static int getMSBPageTemplateFoldersCount() {
		return getService().getMSBPageTemplateFoldersCount();
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

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
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

	/**
	* Updates the msb page template folder in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param msbPageTemplateFolder the msb page template folder
	* @return the msb page template folder that was updated
	*/
	public static com.liferay.modern.site.building.page.model.MSBPageTemplateFolder updateMSBPageTemplateFolder(
		com.liferay.modern.site.building.page.model.MSBPageTemplateFolder msbPageTemplateFolder) {
		return getService().updateMSBPageTemplateFolder(msbPageTemplateFolder);
	}

	public static MSBPageTemplateFolderLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MSBPageTemplateFolderLocalService, MSBPageTemplateFolderLocalService> _serviceTracker =
		ServiceTrackerFactory.open(MSBPageTemplateFolderLocalService.class);
}