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
 * Provides the local service utility for MSBPageTemplate. This utility wraps
 * {@link com.liferay.modern.site.building.page.service.impl.MSBPageTemplateLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see MSBPageTemplateLocalService
 * @see com.liferay.modern.site.building.page.service.base.MSBPageTemplateLocalServiceBaseImpl
 * @see com.liferay.modern.site.building.page.service.impl.MSBPageTemplateLocalServiceImpl
 * @generated
 */
@ProviderType
public class MSBPageTemplateLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.modern.site.building.page.service.impl.MSBPageTemplateLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the msb page template to the database. Also notifies the appropriate model listeners.
	*
	* @param msbPageTemplate the msb page template
	* @return the msb page template that was added
	*/
	public static com.liferay.modern.site.building.page.model.MSBPageTemplate addMSBPageTemplate(
		com.liferay.modern.site.building.page.model.MSBPageTemplate msbPageTemplate) {
		return getService().addMSBPageTemplate(msbPageTemplate);
	}

	/**
	* Creates a new msb page template with the primary key. Does not add the msb page template to the database.
	*
	* @param msbPageTemplateId the primary key for the new msb page template
	* @return the new msb page template
	*/
	public static com.liferay.modern.site.building.page.model.MSBPageTemplate createMSBPageTemplate(
		long msbPageTemplateId) {
		return getService().createMSBPageTemplate(msbPageTemplateId);
	}

	/**
	* Deletes the msb page template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param msbPageTemplateId the primary key of the msb page template
	* @return the msb page template that was removed
	* @throws PortalException if a msb page template with the primary key could not be found
	*/
	public static com.liferay.modern.site.building.page.model.MSBPageTemplate deleteMSBPageTemplate(
		long msbPageTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteMSBPageTemplate(msbPageTemplateId);
	}

	/**
	* Deletes the msb page template from the database. Also notifies the appropriate model listeners.
	*
	* @param msbPageTemplate the msb page template
	* @return the msb page template that was removed
	*/
	public static com.liferay.modern.site.building.page.model.MSBPageTemplate deleteMSBPageTemplate(
		com.liferay.modern.site.building.page.model.MSBPageTemplate msbPageTemplate) {
		return getService().deleteMSBPageTemplate(msbPageTemplate);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.modern.site.building.page.model.impl.MSBPageTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.modern.site.building.page.model.impl.MSBPageTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.modern.site.building.page.model.MSBPageTemplate fetchMSBPageTemplate(
		long msbPageTemplateId) {
		return getService().fetchMSBPageTemplate(msbPageTemplateId);
	}

	/**
	* Returns the msb page template matching the UUID and group.
	*
	* @param uuid the msb page template's UUID
	* @param groupId the primary key of the group
	* @return the matching msb page template, or <code>null</code> if a matching msb page template could not be found
	*/
	public static com.liferay.modern.site.building.page.model.MSBPageTemplate fetchMSBPageTemplateByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchMSBPageTemplateByUuidAndGroupId(uuid, groupId);
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
	* Returns the msb page template with the primary key.
	*
	* @param msbPageTemplateId the primary key of the msb page template
	* @return the msb page template
	* @throws PortalException if a msb page template with the primary key could not be found
	*/
	public static com.liferay.modern.site.building.page.model.MSBPageTemplate getMSBPageTemplate(
		long msbPageTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getMSBPageTemplate(msbPageTemplateId);
	}

	/**
	* Returns the msb page template matching the UUID and group.
	*
	* @param uuid the msb page template's UUID
	* @param groupId the primary key of the group
	* @return the matching msb page template
	* @throws PortalException if a matching msb page template could not be found
	*/
	public static com.liferay.modern.site.building.page.model.MSBPageTemplate getMSBPageTemplateByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getMSBPageTemplateByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the msb page templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.modern.site.building.page.model.impl.MSBPageTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of msb page templates
	* @param end the upper bound of the range of msb page templates (not inclusive)
	* @return the range of msb page templates
	*/
	public static java.util.List<com.liferay.modern.site.building.page.model.MSBPageTemplate> getMSBPageTemplates(
		int start, int end) {
		return getService().getMSBPageTemplates(start, end);
	}

	public static java.util.List<com.liferay.modern.site.building.page.model.MSBPageTemplate> getMSBPageTemplates(
		long msbPageTemplateFolderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService()
				   .getMSBPageTemplates(msbPageTemplateFolderId, start, end, obc);
	}

	/**
	* Returns all the msb page templates matching the UUID and company.
	*
	* @param uuid the UUID of the msb page templates
	* @param companyId the primary key of the company
	* @return the matching msb page templates, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.modern.site.building.page.model.MSBPageTemplate> getMSBPageTemplatesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService()
				   .getMSBPageTemplatesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of msb page templates matching the UUID and company.
	*
	* @param uuid the UUID of the msb page templates
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of msb page templates
	* @param end the upper bound of the range of msb page templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching msb page templates, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.modern.site.building.page.model.MSBPageTemplate> getMSBPageTemplatesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.modern.site.building.page.model.MSBPageTemplate> orderByComparator) {
		return getService()
				   .getMSBPageTemplatesByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of msb page templates.
	*
	* @return the number of msb page templates
	*/
	public static int getMSBPageTemplatesCount() {
		return getService().getMSBPageTemplatesCount();
	}

	public static int getMSBPageTemplatesCount(long msbPageTemplateFolderId) {
		return getService().getMSBPageTemplatesCount(msbPageTemplateFolderId);
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

	public static java.util.List<com.liferay.modern.site.building.page.model.MSBPageTemplate> searchMSBPageTemplates(
		long msbPageTemplateFolderId, java.lang.String keywords, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService()
				   .searchMSBPageTemplates(msbPageTemplateFolderId, keywords,
			start, end, obc);
	}

	public static int searchMSBPageTemplatesCount(
		long msbPageTemplateFolderId, java.lang.String keywords) {
		return getService()
				   .searchMSBPageTemplatesCount(msbPageTemplateFolderId,
			keywords);
	}

	/**
	* Updates the msb page template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param msbPageTemplate the msb page template
	* @return the msb page template that was updated
	*/
	public static com.liferay.modern.site.building.page.model.MSBPageTemplate updateMSBPageTemplate(
		com.liferay.modern.site.building.page.model.MSBPageTemplate msbPageTemplate) {
		return getService().updateMSBPageTemplate(msbPageTemplate);
	}

	public static MSBPageTemplateLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MSBPageTemplateLocalService, MSBPageTemplateLocalService> _serviceTracker =
		ServiceTrackerFactory.open(MSBPageTemplateLocalService.class);
}