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
 * Provides a wrapper for {@link MSBPageTemplateFolderLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MSBPageTemplateFolderLocalService
 * @generated
 */
@ProviderType
public class MSBPageTemplateFolderLocalServiceWrapper
	implements MSBPageTemplateFolderLocalService,
		ServiceWrapper<MSBPageTemplateFolderLocalService> {
	public MSBPageTemplateFolderLocalServiceWrapper(
		MSBPageTemplateFolderLocalService msbPageTemplateFolderLocalService) {
		_msbPageTemplateFolderLocalService = msbPageTemplateFolderLocalService;
	}

	@Override
	public com.liferay.modern.site.building.page.model.MSBPageTemplateFolder addMSBPageTemplateFolder(
		long groupId, java.lang.String name, java.lang.String description,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _msbPageTemplateFolderLocalService.addMSBPageTemplateFolder(groupId,
			name, description, serviceContext);
	}

	/**
	* Adds the msb page template folder to the database. Also notifies the appropriate model listeners.
	*
	* @param msbPageTemplateFolder the msb page template folder
	* @return the msb page template folder that was added
	*/
	@Override
	public com.liferay.modern.site.building.page.model.MSBPageTemplateFolder addMSBPageTemplateFolder(
		com.liferay.modern.site.building.page.model.MSBPageTemplateFolder msbPageTemplateFolder) {
		return _msbPageTemplateFolderLocalService.addMSBPageTemplateFolder(msbPageTemplateFolder);
	}

	/**
	* Creates a new msb page template folder with the primary key. Does not add the msb page template folder to the database.
	*
	* @param msbPageTemplateFolderId the primary key for the new msb page template folder
	* @return the new msb page template folder
	*/
	@Override
	public com.liferay.modern.site.building.page.model.MSBPageTemplateFolder createMSBPageTemplateFolder(
		long msbPageTemplateFolderId) {
		return _msbPageTemplateFolderLocalService.createMSBPageTemplateFolder(msbPageTemplateFolderId);
	}

	/**
	* Deletes the msb page template folder with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param msbPageTemplateFolderId the primary key of the msb page template folder
	* @return the msb page template folder that was removed
	* @throws PortalException if a msb page template folder with the primary key could not be found
	*/
	@Override
	public com.liferay.modern.site.building.page.model.MSBPageTemplateFolder deleteMSBPageTemplateFolder(
		long msbPageTemplateFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _msbPageTemplateFolderLocalService.deleteMSBPageTemplateFolder(msbPageTemplateFolderId);
	}

	/**
	* Deletes the msb page template folder from the database. Also notifies the appropriate model listeners.
	*
	* @param msbPageTemplateFolder the msb page template folder
	* @return the msb page template folder that was removed
	*/
	@Override
	public com.liferay.modern.site.building.page.model.MSBPageTemplateFolder deleteMSBPageTemplateFolder(
		com.liferay.modern.site.building.page.model.MSBPageTemplateFolder msbPageTemplateFolder) {
		return _msbPageTemplateFolderLocalService.deleteMSBPageTemplateFolder(msbPageTemplateFolder);
	}

	@Override
	public void deleteMSBPageTemplates(long msbPageTemplateFolderId) {
		_msbPageTemplateFolderLocalService.deleteMSBPageTemplates(msbPageTemplateFolderId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _msbPageTemplateFolderLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _msbPageTemplateFolderLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _msbPageTemplateFolderLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _msbPageTemplateFolderLocalService.dynamicQuery(dynamicQuery,
			start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _msbPageTemplateFolderLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _msbPageTemplateFolderLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _msbPageTemplateFolderLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.modern.site.building.page.model.MSBPageTemplateFolder fetchMSBPageTemplateFolder(
		long msbPageTemplateFolderId) {
		return _msbPageTemplateFolderLocalService.fetchMSBPageTemplateFolder(msbPageTemplateFolderId);
	}

	/**
	* Returns the msb page template folder matching the UUID and group.
	*
	* @param uuid the msb page template folder's UUID
	* @param groupId the primary key of the group
	* @return the matching msb page template folder, or <code>null</code> if a matching msb page template folder could not be found
	*/
	@Override
	public com.liferay.modern.site.building.page.model.MSBPageTemplateFolder fetchMSBPageTemplateFolderByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _msbPageTemplateFolderLocalService.fetchMSBPageTemplateFolderByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _msbPageTemplateFolderLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _msbPageTemplateFolderLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _msbPageTemplateFolderLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the msb page template folder with the primary key.
	*
	* @param msbPageTemplateFolderId the primary key of the msb page template folder
	* @return the msb page template folder
	* @throws PortalException if a msb page template folder with the primary key could not be found
	*/
	@Override
	public com.liferay.modern.site.building.page.model.MSBPageTemplateFolder getMSBPageTemplateFolder(
		long msbPageTemplateFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _msbPageTemplateFolderLocalService.getMSBPageTemplateFolder(msbPageTemplateFolderId);
	}

	/**
	* Returns the msb page template folder matching the UUID and group.
	*
	* @param uuid the msb page template folder's UUID
	* @param groupId the primary key of the group
	* @return the matching msb page template folder
	* @throws PortalException if a matching msb page template folder could not be found
	*/
	@Override
	public com.liferay.modern.site.building.page.model.MSBPageTemplateFolder getMSBPageTemplateFolderByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _msbPageTemplateFolderLocalService.getMSBPageTemplateFolderByUuidAndGroupId(uuid,
			groupId);
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
	@Override
	public java.util.List<com.liferay.modern.site.building.page.model.MSBPageTemplateFolder> getMSBPageTemplateFolders(
		int start, int end) {
		return _msbPageTemplateFolderLocalService.getMSBPageTemplateFolders(start,
			end);
	}

	@Override
	public java.util.List<com.liferay.modern.site.building.page.model.MSBPageTemplateFolder> getMSBPageTemplateFolders(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return _msbPageTemplateFolderLocalService.getMSBPageTemplateFolders(groupId,
			start, end, obc);
	}

	/**
	* Returns all the msb page template folders matching the UUID and company.
	*
	* @param uuid the UUID of the msb page template folders
	* @param companyId the primary key of the company
	* @return the matching msb page template folders, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.modern.site.building.page.model.MSBPageTemplateFolder> getMSBPageTemplateFoldersByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _msbPageTemplateFolderLocalService.getMSBPageTemplateFoldersByUuidAndCompanyId(uuid,
			companyId);
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
	@Override
	public java.util.List<com.liferay.modern.site.building.page.model.MSBPageTemplateFolder> getMSBPageTemplateFoldersByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.modern.site.building.page.model.MSBPageTemplateFolder> orderByComparator) {
		return _msbPageTemplateFolderLocalService.getMSBPageTemplateFoldersByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of msb page template folders.
	*
	* @return the number of msb page template folders
	*/
	@Override
	public int getMSBPageTemplateFoldersCount() {
		return _msbPageTemplateFolderLocalService.getMSBPageTemplateFoldersCount();
	}

	@Override
	public int getMSBPageTemplateFoldersCount(long groupId) {
		return _msbPageTemplateFolderLocalService.getMSBPageTemplateFoldersCount(groupId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _msbPageTemplateFolderLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _msbPageTemplateFolderLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<com.liferay.modern.site.building.page.model.MSBPageTemplateFolder> searchMSBPageTemplateFolders(
		long groupId, java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return _msbPageTemplateFolderLocalService.searchMSBPageTemplateFolders(groupId,
			keywords, start, end, obc);
	}

	@Override
	public int searchMSBPageTemplateFoldersCount(long groupId,
		java.lang.String keywords) {
		return _msbPageTemplateFolderLocalService.searchMSBPageTemplateFoldersCount(groupId,
			keywords);
	}

	@Override
	public com.liferay.modern.site.building.page.model.MSBPageTemplateFolder updateMSBPageTemplateFolder(
		long msbPageTemplateFolderId, java.lang.String name,
		java.lang.String description,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _msbPageTemplateFolderLocalService.updateMSBPageTemplateFolder(msbPageTemplateFolderId,
			name, description, serviceContext);
	}

	/**
	* Updates the msb page template folder in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param msbPageTemplateFolder the msb page template folder
	* @return the msb page template folder that was updated
	*/
	@Override
	public com.liferay.modern.site.building.page.model.MSBPageTemplateFolder updateMSBPageTemplateFolder(
		com.liferay.modern.site.building.page.model.MSBPageTemplateFolder msbPageTemplateFolder) {
		return _msbPageTemplateFolderLocalService.updateMSBPageTemplateFolder(msbPageTemplateFolder);
	}

	@Override
	public MSBPageTemplateFolderLocalService getWrappedService() {
		return _msbPageTemplateFolderLocalService;
	}

	@Override
	public void setWrappedService(
		MSBPageTemplateFolderLocalService msbPageTemplateFolderLocalService) {
		_msbPageTemplateFolderLocalService = msbPageTemplateFolderLocalService;
	}

	private MSBPageTemplateFolderLocalService _msbPageTemplateFolderLocalService;
}