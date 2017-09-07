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

package com.liferay.modern.site.building.page.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.modern.site.building.page.model.MSBPageTemplate;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the msb page template service. This utility wraps {@link com.liferay.modern.site.building.page.service.persistence.impl.MSBPageTemplatePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MSBPageTemplatePersistence
 * @see com.liferay.modern.site.building.page.service.persistence.impl.MSBPageTemplatePersistenceImpl
 * @generated
 */
@ProviderType
public class MSBPageTemplateUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(MSBPageTemplate msbPageTemplate) {
		getPersistence().clearCache(msbPageTemplate);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<MSBPageTemplate> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MSBPageTemplate> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MSBPageTemplate> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MSBPageTemplate> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MSBPageTemplate update(MSBPageTemplate msbPageTemplate) {
		return getPersistence().update(msbPageTemplate);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MSBPageTemplate update(MSBPageTemplate msbPageTemplate,
		ServiceContext serviceContext) {
		return getPersistence().update(msbPageTemplate, serviceContext);
	}

	/**
	* Returns all the msb page templates where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching msb page templates
	*/
	public static List<MSBPageTemplate> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the msb page templates where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of msb page templates
	* @param end the upper bound of the range of msb page templates (not inclusive)
	* @return the range of matching msb page templates
	*/
	public static List<MSBPageTemplate> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the msb page templates where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of msb page templates
	* @param end the upper bound of the range of msb page templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching msb page templates
	*/
	public static List<MSBPageTemplate> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<MSBPageTemplate> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the msb page templates where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of msb page templates
	* @param end the upper bound of the range of msb page templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching msb page templates
	*/
	public static List<MSBPageTemplate> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<MSBPageTemplate> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first msb page template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching msb page template
	* @throws NoSuchPageTemplateException if a matching msb page template could not be found
	*/
	public static MSBPageTemplate findByUuid_First(java.lang.String uuid,
		OrderByComparator<MSBPageTemplate> orderByComparator)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first msb page template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching msb page template, or <code>null</code> if a matching msb page template could not be found
	*/
	public static MSBPageTemplate fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<MSBPageTemplate> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last msb page template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching msb page template
	* @throws NoSuchPageTemplateException if a matching msb page template could not be found
	*/
	public static MSBPageTemplate findByUuid_Last(java.lang.String uuid,
		OrderByComparator<MSBPageTemplate> orderByComparator)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last msb page template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching msb page template, or <code>null</code> if a matching msb page template could not be found
	*/
	public static MSBPageTemplate fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<MSBPageTemplate> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the msb page templates before and after the current msb page template in the ordered set where uuid = &#63;.
	*
	* @param msbPageTemplateId the primary key of the current msb page template
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next msb page template
	* @throws NoSuchPageTemplateException if a msb page template with the primary key could not be found
	*/
	public static MSBPageTemplate[] findByUuid_PrevAndNext(
		long msbPageTemplateId, java.lang.String uuid,
		OrderByComparator<MSBPageTemplate> orderByComparator)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateException {
		return getPersistence()
				   .findByUuid_PrevAndNext(msbPageTemplateId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the msb page templates where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of msb page templates where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching msb page templates
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the msb page template where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPageTemplateException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching msb page template
	* @throws NoSuchPageTemplateException if a matching msb page template could not be found
	*/
	public static MSBPageTemplate findByUUID_G(java.lang.String uuid,
		long groupId)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the msb page template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching msb page template, or <code>null</code> if a matching msb page template could not be found
	*/
	public static MSBPageTemplate fetchByUUID_G(java.lang.String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the msb page template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching msb page template, or <code>null</code> if a matching msb page template could not be found
	*/
	public static MSBPageTemplate fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the msb page template where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the msb page template that was removed
	*/
	public static MSBPageTemplate removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of msb page templates where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching msb page templates
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the msb page templates where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching msb page templates
	*/
	public static List<MSBPageTemplate> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the msb page templates where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of msb page templates
	* @param end the upper bound of the range of msb page templates (not inclusive)
	* @return the range of matching msb page templates
	*/
	public static List<MSBPageTemplate> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the msb page templates where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of msb page templates
	* @param end the upper bound of the range of msb page templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching msb page templates
	*/
	public static List<MSBPageTemplate> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<MSBPageTemplate> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the msb page templates where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of msb page templates
	* @param end the upper bound of the range of msb page templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching msb page templates
	*/
	public static List<MSBPageTemplate> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<MSBPageTemplate> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first msb page template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching msb page template
	* @throws NoSuchPageTemplateException if a matching msb page template could not be found
	*/
	public static MSBPageTemplate findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<MSBPageTemplate> orderByComparator)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first msb page template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching msb page template, or <code>null</code> if a matching msb page template could not be found
	*/
	public static MSBPageTemplate fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<MSBPageTemplate> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last msb page template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching msb page template
	* @throws NoSuchPageTemplateException if a matching msb page template could not be found
	*/
	public static MSBPageTemplate findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<MSBPageTemplate> orderByComparator)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last msb page template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching msb page template, or <code>null</code> if a matching msb page template could not be found
	*/
	public static MSBPageTemplate fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<MSBPageTemplate> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the msb page templates before and after the current msb page template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param msbPageTemplateId the primary key of the current msb page template
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next msb page template
	* @throws NoSuchPageTemplateException if a msb page template with the primary key could not be found
	*/
	public static MSBPageTemplate[] findByUuid_C_PrevAndNext(
		long msbPageTemplateId, java.lang.String uuid, long companyId,
		OrderByComparator<MSBPageTemplate> orderByComparator)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(msbPageTemplateId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the msb page templates where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of msb page templates where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching msb page templates
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the msb page templates where msbPageTemplateFolderId = &#63;.
	*
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @return the matching msb page templates
	*/
	public static List<MSBPageTemplate> findByMSBPageTemplateFolderId(
		long msbPageTemplateFolderId) {
		return getPersistence()
				   .findByMSBPageTemplateFolderId(msbPageTemplateFolderId);
	}

	/**
	* Returns a range of all the msb page templates where msbPageTemplateFolderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @param start the lower bound of the range of msb page templates
	* @param end the upper bound of the range of msb page templates (not inclusive)
	* @return the range of matching msb page templates
	*/
	public static List<MSBPageTemplate> findByMSBPageTemplateFolderId(
		long msbPageTemplateFolderId, int start, int end) {
		return getPersistence()
				   .findByMSBPageTemplateFolderId(msbPageTemplateFolderId,
			start, end);
	}

	/**
	* Returns an ordered range of all the msb page templates where msbPageTemplateFolderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @param start the lower bound of the range of msb page templates
	* @param end the upper bound of the range of msb page templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching msb page templates
	*/
	public static List<MSBPageTemplate> findByMSBPageTemplateFolderId(
		long msbPageTemplateFolderId, int start, int end,
		OrderByComparator<MSBPageTemplate> orderByComparator) {
		return getPersistence()
				   .findByMSBPageTemplateFolderId(msbPageTemplateFolderId,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the msb page templates where msbPageTemplateFolderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @param start the lower bound of the range of msb page templates
	* @param end the upper bound of the range of msb page templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching msb page templates
	*/
	public static List<MSBPageTemplate> findByMSBPageTemplateFolderId(
		long msbPageTemplateFolderId, int start, int end,
		OrderByComparator<MSBPageTemplate> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByMSBPageTemplateFolderId(msbPageTemplateFolderId,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first msb page template in the ordered set where msbPageTemplateFolderId = &#63;.
	*
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching msb page template
	* @throws NoSuchPageTemplateException if a matching msb page template could not be found
	*/
	public static MSBPageTemplate findByMSBPageTemplateFolderId_First(
		long msbPageTemplateFolderId,
		OrderByComparator<MSBPageTemplate> orderByComparator)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateException {
		return getPersistence()
				   .findByMSBPageTemplateFolderId_First(msbPageTemplateFolderId,
			orderByComparator);
	}

	/**
	* Returns the first msb page template in the ordered set where msbPageTemplateFolderId = &#63;.
	*
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching msb page template, or <code>null</code> if a matching msb page template could not be found
	*/
	public static MSBPageTemplate fetchByMSBPageTemplateFolderId_First(
		long msbPageTemplateFolderId,
		OrderByComparator<MSBPageTemplate> orderByComparator) {
		return getPersistence()
				   .fetchByMSBPageTemplateFolderId_First(msbPageTemplateFolderId,
			orderByComparator);
	}

	/**
	* Returns the last msb page template in the ordered set where msbPageTemplateFolderId = &#63;.
	*
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching msb page template
	* @throws NoSuchPageTemplateException if a matching msb page template could not be found
	*/
	public static MSBPageTemplate findByMSBPageTemplateFolderId_Last(
		long msbPageTemplateFolderId,
		OrderByComparator<MSBPageTemplate> orderByComparator)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateException {
		return getPersistence()
				   .findByMSBPageTemplateFolderId_Last(msbPageTemplateFolderId,
			orderByComparator);
	}

	/**
	* Returns the last msb page template in the ordered set where msbPageTemplateFolderId = &#63;.
	*
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching msb page template, or <code>null</code> if a matching msb page template could not be found
	*/
	public static MSBPageTemplate fetchByMSBPageTemplateFolderId_Last(
		long msbPageTemplateFolderId,
		OrderByComparator<MSBPageTemplate> orderByComparator) {
		return getPersistence()
				   .fetchByMSBPageTemplateFolderId_Last(msbPageTemplateFolderId,
			orderByComparator);
	}

	/**
	* Returns the msb page templates before and after the current msb page template in the ordered set where msbPageTemplateFolderId = &#63;.
	*
	* @param msbPageTemplateId the primary key of the current msb page template
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next msb page template
	* @throws NoSuchPageTemplateException if a msb page template with the primary key could not be found
	*/
	public static MSBPageTemplate[] findByMSBPageTemplateFolderId_PrevAndNext(
		long msbPageTemplateId, long msbPageTemplateFolderId,
		OrderByComparator<MSBPageTemplate> orderByComparator)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateException {
		return getPersistence()
				   .findByMSBPageTemplateFolderId_PrevAndNext(msbPageTemplateId,
			msbPageTemplateFolderId, orderByComparator);
	}

	/**
	* Removes all the msb page templates where msbPageTemplateFolderId = &#63; from the database.
	*
	* @param msbPageTemplateFolderId the msb page template folder ID
	*/
	public static void removeByMSBPageTemplateFolderId(
		long msbPageTemplateFolderId) {
		getPersistence().removeByMSBPageTemplateFolderId(msbPageTemplateFolderId);
	}

	/**
	* Returns the number of msb page templates where msbPageTemplateFolderId = &#63;.
	*
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @return the number of matching msb page templates
	*/
	public static int countByMSBPageTemplateFolderId(
		long msbPageTemplateFolderId) {
		return getPersistence()
				   .countByMSBPageTemplateFolderId(msbPageTemplateFolderId);
	}

	/**
	* Returns all the msb page templates where name LIKE &#63; and msbPageTemplateFolderId = &#63;.
	*
	* @param name the name
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @return the matching msb page templates
	*/
	public static List<MSBPageTemplate> findByLikeN_P(java.lang.String name,
		long msbPageTemplateFolderId) {
		return getPersistence().findByLikeN_P(name, msbPageTemplateFolderId);
	}

	/**
	* Returns a range of all the msb page templates where name LIKE &#63; and msbPageTemplateFolderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @param start the lower bound of the range of msb page templates
	* @param end the upper bound of the range of msb page templates (not inclusive)
	* @return the range of matching msb page templates
	*/
	public static List<MSBPageTemplate> findByLikeN_P(java.lang.String name,
		long msbPageTemplateFolderId, int start, int end) {
		return getPersistence()
				   .findByLikeN_P(name, msbPageTemplateFolderId, start, end);
	}

	/**
	* Returns an ordered range of all the msb page templates where name LIKE &#63; and msbPageTemplateFolderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @param start the lower bound of the range of msb page templates
	* @param end the upper bound of the range of msb page templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching msb page templates
	*/
	public static List<MSBPageTemplate> findByLikeN_P(java.lang.String name,
		long msbPageTemplateFolderId, int start, int end,
		OrderByComparator<MSBPageTemplate> orderByComparator) {
		return getPersistence()
				   .findByLikeN_P(name, msbPageTemplateFolderId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the msb page templates where name LIKE &#63; and msbPageTemplateFolderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @param start the lower bound of the range of msb page templates
	* @param end the upper bound of the range of msb page templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching msb page templates
	*/
	public static List<MSBPageTemplate> findByLikeN_P(java.lang.String name,
		long msbPageTemplateFolderId, int start, int end,
		OrderByComparator<MSBPageTemplate> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLikeN_P(name, msbPageTemplateFolderId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first msb page template in the ordered set where name LIKE &#63; and msbPageTemplateFolderId = &#63;.
	*
	* @param name the name
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching msb page template
	* @throws NoSuchPageTemplateException if a matching msb page template could not be found
	*/
	public static MSBPageTemplate findByLikeN_P_First(java.lang.String name,
		long msbPageTemplateFolderId,
		OrderByComparator<MSBPageTemplate> orderByComparator)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateException {
		return getPersistence()
				   .findByLikeN_P_First(name, msbPageTemplateFolderId,
			orderByComparator);
	}

	/**
	* Returns the first msb page template in the ordered set where name LIKE &#63; and msbPageTemplateFolderId = &#63;.
	*
	* @param name the name
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching msb page template, or <code>null</code> if a matching msb page template could not be found
	*/
	public static MSBPageTemplate fetchByLikeN_P_First(java.lang.String name,
		long msbPageTemplateFolderId,
		OrderByComparator<MSBPageTemplate> orderByComparator) {
		return getPersistence()
				   .fetchByLikeN_P_First(name, msbPageTemplateFolderId,
			orderByComparator);
	}

	/**
	* Returns the last msb page template in the ordered set where name LIKE &#63; and msbPageTemplateFolderId = &#63;.
	*
	* @param name the name
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching msb page template
	* @throws NoSuchPageTemplateException if a matching msb page template could not be found
	*/
	public static MSBPageTemplate findByLikeN_P_Last(java.lang.String name,
		long msbPageTemplateFolderId,
		OrderByComparator<MSBPageTemplate> orderByComparator)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateException {
		return getPersistence()
				   .findByLikeN_P_Last(name, msbPageTemplateFolderId,
			orderByComparator);
	}

	/**
	* Returns the last msb page template in the ordered set where name LIKE &#63; and msbPageTemplateFolderId = &#63;.
	*
	* @param name the name
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching msb page template, or <code>null</code> if a matching msb page template could not be found
	*/
	public static MSBPageTemplate fetchByLikeN_P_Last(java.lang.String name,
		long msbPageTemplateFolderId,
		OrderByComparator<MSBPageTemplate> orderByComparator) {
		return getPersistence()
				   .fetchByLikeN_P_Last(name, msbPageTemplateFolderId,
			orderByComparator);
	}

	/**
	* Returns the msb page templates before and after the current msb page template in the ordered set where name LIKE &#63; and msbPageTemplateFolderId = &#63;.
	*
	* @param msbPageTemplateId the primary key of the current msb page template
	* @param name the name
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next msb page template
	* @throws NoSuchPageTemplateException if a msb page template with the primary key could not be found
	*/
	public static MSBPageTemplate[] findByLikeN_P_PrevAndNext(
		long msbPageTemplateId, java.lang.String name,
		long msbPageTemplateFolderId,
		OrderByComparator<MSBPageTemplate> orderByComparator)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateException {
		return getPersistence()
				   .findByLikeN_P_PrevAndNext(msbPageTemplateId, name,
			msbPageTemplateFolderId, orderByComparator);
	}

	/**
	* Removes all the msb page templates where name LIKE &#63; and msbPageTemplateFolderId = &#63; from the database.
	*
	* @param name the name
	* @param msbPageTemplateFolderId the msb page template folder ID
	*/
	public static void removeByLikeN_P(java.lang.String name,
		long msbPageTemplateFolderId) {
		getPersistence().removeByLikeN_P(name, msbPageTemplateFolderId);
	}

	/**
	* Returns the number of msb page templates where name LIKE &#63; and msbPageTemplateFolderId = &#63;.
	*
	* @param name the name
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @return the number of matching msb page templates
	*/
	public static int countByLikeN_P(java.lang.String name,
		long msbPageTemplateFolderId) {
		return getPersistence().countByLikeN_P(name, msbPageTemplateFolderId);
	}

	/**
	* Caches the msb page template in the entity cache if it is enabled.
	*
	* @param msbPageTemplate the msb page template
	*/
	public static void cacheResult(MSBPageTemplate msbPageTemplate) {
		getPersistence().cacheResult(msbPageTemplate);
	}

	/**
	* Caches the msb page templates in the entity cache if it is enabled.
	*
	* @param msbPageTemplates the msb page templates
	*/
	public static void cacheResult(List<MSBPageTemplate> msbPageTemplates) {
		getPersistence().cacheResult(msbPageTemplates);
	}

	/**
	* Creates a new msb page template with the primary key. Does not add the msb page template to the database.
	*
	* @param msbPageTemplateId the primary key for the new msb page template
	* @return the new msb page template
	*/
	public static MSBPageTemplate create(long msbPageTemplateId) {
		return getPersistence().create(msbPageTemplateId);
	}

	/**
	* Removes the msb page template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param msbPageTemplateId the primary key of the msb page template
	* @return the msb page template that was removed
	* @throws NoSuchPageTemplateException if a msb page template with the primary key could not be found
	*/
	public static MSBPageTemplate remove(long msbPageTemplateId)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateException {
		return getPersistence().remove(msbPageTemplateId);
	}

	public static MSBPageTemplate updateImpl(MSBPageTemplate msbPageTemplate) {
		return getPersistence().updateImpl(msbPageTemplate);
	}

	/**
	* Returns the msb page template with the primary key or throws a {@link NoSuchPageTemplateException} if it could not be found.
	*
	* @param msbPageTemplateId the primary key of the msb page template
	* @return the msb page template
	* @throws NoSuchPageTemplateException if a msb page template with the primary key could not be found
	*/
	public static MSBPageTemplate findByPrimaryKey(long msbPageTemplateId)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateException {
		return getPersistence().findByPrimaryKey(msbPageTemplateId);
	}

	/**
	* Returns the msb page template with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param msbPageTemplateId the primary key of the msb page template
	* @return the msb page template, or <code>null</code> if a msb page template with the primary key could not be found
	*/
	public static MSBPageTemplate fetchByPrimaryKey(long msbPageTemplateId) {
		return getPersistence().fetchByPrimaryKey(msbPageTemplateId);
	}

	public static java.util.Map<java.io.Serializable, MSBPageTemplate> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the msb page templates.
	*
	* @return the msb page templates
	*/
	public static List<MSBPageTemplate> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the msb page templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of msb page templates
	* @param end the upper bound of the range of msb page templates (not inclusive)
	* @return the range of msb page templates
	*/
	public static List<MSBPageTemplate> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the msb page templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of msb page templates
	* @param end the upper bound of the range of msb page templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of msb page templates
	*/
	public static List<MSBPageTemplate> findAll(int start, int end,
		OrderByComparator<MSBPageTemplate> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the msb page templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of msb page templates
	* @param end the upper bound of the range of msb page templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of msb page templates
	*/
	public static List<MSBPageTemplate> findAll(int start, int end,
		OrderByComparator<MSBPageTemplate> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the msb page templates from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of msb page templates.
	*
	* @return the number of msb page templates
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static MSBPageTemplatePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MSBPageTemplatePersistence, MSBPageTemplatePersistence> _serviceTracker =
		ServiceTrackerFactory.open(MSBPageTemplatePersistence.class);
}