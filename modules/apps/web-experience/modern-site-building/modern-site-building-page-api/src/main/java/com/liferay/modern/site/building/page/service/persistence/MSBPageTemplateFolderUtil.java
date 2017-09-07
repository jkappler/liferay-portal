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

import com.liferay.modern.site.building.page.model.MSBPageTemplateFolder;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the msb page template folder service. This utility wraps {@link com.liferay.modern.site.building.page.service.persistence.impl.MSBPageTemplateFolderPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MSBPageTemplateFolderPersistence
 * @see com.liferay.modern.site.building.page.service.persistence.impl.MSBPageTemplateFolderPersistenceImpl
 * @generated
 */
@ProviderType
public class MSBPageTemplateFolderUtil {
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
	public static void clearCache(MSBPageTemplateFolder msbPageTemplateFolder) {
		getPersistence().clearCache(msbPageTemplateFolder);
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
	public static List<MSBPageTemplateFolder> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MSBPageTemplateFolder> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MSBPageTemplateFolder> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MSBPageTemplateFolder update(
		MSBPageTemplateFolder msbPageTemplateFolder) {
		return getPersistence().update(msbPageTemplateFolder);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MSBPageTemplateFolder update(
		MSBPageTemplateFolder msbPageTemplateFolder,
		ServiceContext serviceContext) {
		return getPersistence().update(msbPageTemplateFolder, serviceContext);
	}

	/**
	* Returns all the msb page template folders where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching msb page template folders
	*/
	public static List<MSBPageTemplateFolder> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the msb page template folders where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of msb page template folders
	* @param end the upper bound of the range of msb page template folders (not inclusive)
	* @return the range of matching msb page template folders
	*/
	public static List<MSBPageTemplateFolder> findByUuid(
		java.lang.String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the msb page template folders where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of msb page template folders
	* @param end the upper bound of the range of msb page template folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching msb page template folders
	*/
	public static List<MSBPageTemplateFolder> findByUuid(
		java.lang.String uuid, int start, int end,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the msb page template folders where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of msb page template folders
	* @param end the upper bound of the range of msb page template folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching msb page template folders
	*/
	public static List<MSBPageTemplateFolder> findByUuid(
		java.lang.String uuid, int start, int end,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first msb page template folder in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching msb page template folder
	* @throws NoSuchPageTemplateFolderException if a matching msb page template folder could not be found
	*/
	public static MSBPageTemplateFolder findByUuid_First(
		java.lang.String uuid,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateFolderException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first msb page template folder in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching msb page template folder, or <code>null</code> if a matching msb page template folder could not be found
	*/
	public static MSBPageTemplateFolder fetchByUuid_First(
		java.lang.String uuid,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last msb page template folder in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching msb page template folder
	* @throws NoSuchPageTemplateFolderException if a matching msb page template folder could not be found
	*/
	public static MSBPageTemplateFolder findByUuid_Last(java.lang.String uuid,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateFolderException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last msb page template folder in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching msb page template folder, or <code>null</code> if a matching msb page template folder could not be found
	*/
	public static MSBPageTemplateFolder fetchByUuid_Last(
		java.lang.String uuid,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the msb page template folders before and after the current msb page template folder in the ordered set where uuid = &#63;.
	*
	* @param msbPageTemplateFolderId the primary key of the current msb page template folder
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next msb page template folder
	* @throws NoSuchPageTemplateFolderException if a msb page template folder with the primary key could not be found
	*/
	public static MSBPageTemplateFolder[] findByUuid_PrevAndNext(
		long msbPageTemplateFolderId, java.lang.String uuid,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateFolderException {
		return getPersistence()
				   .findByUuid_PrevAndNext(msbPageTemplateFolderId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the msb page template folders where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of msb page template folders where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching msb page template folders
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the msb page template folder where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPageTemplateFolderException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching msb page template folder
	* @throws NoSuchPageTemplateFolderException if a matching msb page template folder could not be found
	*/
	public static MSBPageTemplateFolder findByUUID_G(java.lang.String uuid,
		long groupId)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateFolderException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the msb page template folder where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching msb page template folder, or <code>null</code> if a matching msb page template folder could not be found
	*/
	public static MSBPageTemplateFolder fetchByUUID_G(java.lang.String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the msb page template folder where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching msb page template folder, or <code>null</code> if a matching msb page template folder could not be found
	*/
	public static MSBPageTemplateFolder fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the msb page template folder where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the msb page template folder that was removed
	*/
	public static MSBPageTemplateFolder removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateFolderException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of msb page template folders where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching msb page template folders
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the msb page template folders where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching msb page template folders
	*/
	public static List<MSBPageTemplateFolder> findByUuid_C(
		java.lang.String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the msb page template folders where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of msb page template folders
	* @param end the upper bound of the range of msb page template folders (not inclusive)
	* @return the range of matching msb page template folders
	*/
	public static List<MSBPageTemplateFolder> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the msb page template folders where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of msb page template folders
	* @param end the upper bound of the range of msb page template folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching msb page template folders
	*/
	public static List<MSBPageTemplateFolder> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the msb page template folders where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of msb page template folders
	* @param end the upper bound of the range of msb page template folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching msb page template folders
	*/
	public static List<MSBPageTemplateFolder> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first msb page template folder in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching msb page template folder
	* @throws NoSuchPageTemplateFolderException if a matching msb page template folder could not be found
	*/
	public static MSBPageTemplateFolder findByUuid_C_First(
		java.lang.String uuid, long companyId,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateFolderException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first msb page template folder in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching msb page template folder, or <code>null</code> if a matching msb page template folder could not be found
	*/
	public static MSBPageTemplateFolder fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last msb page template folder in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching msb page template folder
	* @throws NoSuchPageTemplateFolderException if a matching msb page template folder could not be found
	*/
	public static MSBPageTemplateFolder findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateFolderException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last msb page template folder in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching msb page template folder, or <code>null</code> if a matching msb page template folder could not be found
	*/
	public static MSBPageTemplateFolder fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the msb page template folders before and after the current msb page template folder in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param msbPageTemplateFolderId the primary key of the current msb page template folder
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next msb page template folder
	* @throws NoSuchPageTemplateFolderException if a msb page template folder with the primary key could not be found
	*/
	public static MSBPageTemplateFolder[] findByUuid_C_PrevAndNext(
		long msbPageTemplateFolderId, java.lang.String uuid, long companyId,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateFolderException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(msbPageTemplateFolderId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the msb page template folders where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of msb page template folders where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching msb page template folders
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the msb page template folders where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching msb page template folders
	*/
	public static List<MSBPageTemplateFolder> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the msb page template folders where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of msb page template folders
	* @param end the upper bound of the range of msb page template folders (not inclusive)
	* @return the range of matching msb page template folders
	*/
	public static List<MSBPageTemplateFolder> findByGroupId(long groupId,
		int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the msb page template folders where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of msb page template folders
	* @param end the upper bound of the range of msb page template folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching msb page template folders
	*/
	public static List<MSBPageTemplateFolder> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the msb page template folders where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of msb page template folders
	* @param end the upper bound of the range of msb page template folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching msb page template folders
	*/
	public static List<MSBPageTemplateFolder> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first msb page template folder in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching msb page template folder
	* @throws NoSuchPageTemplateFolderException if a matching msb page template folder could not be found
	*/
	public static MSBPageTemplateFolder findByGroupId_First(long groupId,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateFolderException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first msb page template folder in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching msb page template folder, or <code>null</code> if a matching msb page template folder could not be found
	*/
	public static MSBPageTemplateFolder fetchByGroupId_First(long groupId,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last msb page template folder in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching msb page template folder
	* @throws NoSuchPageTemplateFolderException if a matching msb page template folder could not be found
	*/
	public static MSBPageTemplateFolder findByGroupId_Last(long groupId,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateFolderException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last msb page template folder in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching msb page template folder, or <code>null</code> if a matching msb page template folder could not be found
	*/
	public static MSBPageTemplateFolder fetchByGroupId_Last(long groupId,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the msb page template folders before and after the current msb page template folder in the ordered set where groupId = &#63;.
	*
	* @param msbPageTemplateFolderId the primary key of the current msb page template folder
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next msb page template folder
	* @throws NoSuchPageTemplateFolderException if a msb page template folder with the primary key could not be found
	*/
	public static MSBPageTemplateFolder[] findByGroupId_PrevAndNext(
		long msbPageTemplateFolderId, long groupId,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateFolderException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(msbPageTemplateFolderId, groupId,
			orderByComparator);
	}

	/**
	* Returns all the msb page template folders that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching msb page template folders that the user has permission to view
	*/
	public static List<MSBPageTemplateFolder> filterFindByGroupId(long groupId) {
		return getPersistence().filterFindByGroupId(groupId);
	}

	/**
	* Returns a range of all the msb page template folders that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of msb page template folders
	* @param end the upper bound of the range of msb page template folders (not inclusive)
	* @return the range of matching msb page template folders that the user has permission to view
	*/
	public static List<MSBPageTemplateFolder> filterFindByGroupId(
		long groupId, int start, int end) {
		return getPersistence().filterFindByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the msb page template folders that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of msb page template folders
	* @param end the upper bound of the range of msb page template folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching msb page template folders that the user has permission to view
	*/
	public static List<MSBPageTemplateFolder> filterFindByGroupId(
		long groupId, int start, int end,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator) {
		return getPersistence()
				   .filterFindByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the msb page template folders before and after the current msb page template folder in the ordered set of msb page template folders that the user has permission to view where groupId = &#63;.
	*
	* @param msbPageTemplateFolderId the primary key of the current msb page template folder
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next msb page template folder
	* @throws NoSuchPageTemplateFolderException if a msb page template folder with the primary key could not be found
	*/
	public static MSBPageTemplateFolder[] filterFindByGroupId_PrevAndNext(
		long msbPageTemplateFolderId, long groupId,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateFolderException {
		return getPersistence()
				   .filterFindByGroupId_PrevAndNext(msbPageTemplateFolderId,
			groupId, orderByComparator);
	}

	/**
	* Removes all the msb page template folders where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of msb page template folders where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching msb page template folders
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns the number of msb page template folders that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching msb page template folders that the user has permission to view
	*/
	public static int filterCountByGroupId(long groupId) {
		return getPersistence().filterCountByGroupId(groupId);
	}

	/**
	* Returns all the msb page template folders where groupId = &#63; and name LIKE &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the matching msb page template folders
	*/
	public static List<MSBPageTemplateFolder> findByG_LikeN(long groupId,
		java.lang.String name) {
		return getPersistence().findByG_LikeN(groupId, name);
	}

	/**
	* Returns a range of all the msb page template folders where groupId = &#63; and name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param name the name
	* @param start the lower bound of the range of msb page template folders
	* @param end the upper bound of the range of msb page template folders (not inclusive)
	* @return the range of matching msb page template folders
	*/
	public static List<MSBPageTemplateFolder> findByG_LikeN(long groupId,
		java.lang.String name, int start, int end) {
		return getPersistence().findByG_LikeN(groupId, name, start, end);
	}

	/**
	* Returns an ordered range of all the msb page template folders where groupId = &#63; and name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param name the name
	* @param start the lower bound of the range of msb page template folders
	* @param end the upper bound of the range of msb page template folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching msb page template folders
	*/
	public static List<MSBPageTemplateFolder> findByG_LikeN(long groupId,
		java.lang.String name, int start, int end,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator) {
		return getPersistence()
				   .findByG_LikeN(groupId, name, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the msb page template folders where groupId = &#63; and name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param name the name
	* @param start the lower bound of the range of msb page template folders
	* @param end the upper bound of the range of msb page template folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching msb page template folders
	*/
	public static List<MSBPageTemplateFolder> findByG_LikeN(long groupId,
		java.lang.String name, int start, int end,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_LikeN(groupId, name, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first msb page template folder in the ordered set where groupId = &#63; and name LIKE &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching msb page template folder
	* @throws NoSuchPageTemplateFolderException if a matching msb page template folder could not be found
	*/
	public static MSBPageTemplateFolder findByG_LikeN_First(long groupId,
		java.lang.String name,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateFolderException {
		return getPersistence()
				   .findByG_LikeN_First(groupId, name, orderByComparator);
	}

	/**
	* Returns the first msb page template folder in the ordered set where groupId = &#63; and name LIKE &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching msb page template folder, or <code>null</code> if a matching msb page template folder could not be found
	*/
	public static MSBPageTemplateFolder fetchByG_LikeN_First(long groupId,
		java.lang.String name,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator) {
		return getPersistence()
				   .fetchByG_LikeN_First(groupId, name, orderByComparator);
	}

	/**
	* Returns the last msb page template folder in the ordered set where groupId = &#63; and name LIKE &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching msb page template folder
	* @throws NoSuchPageTemplateFolderException if a matching msb page template folder could not be found
	*/
	public static MSBPageTemplateFolder findByG_LikeN_Last(long groupId,
		java.lang.String name,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateFolderException {
		return getPersistence()
				   .findByG_LikeN_Last(groupId, name, orderByComparator);
	}

	/**
	* Returns the last msb page template folder in the ordered set where groupId = &#63; and name LIKE &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching msb page template folder, or <code>null</code> if a matching msb page template folder could not be found
	*/
	public static MSBPageTemplateFolder fetchByG_LikeN_Last(long groupId,
		java.lang.String name,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator) {
		return getPersistence()
				   .fetchByG_LikeN_Last(groupId, name, orderByComparator);
	}

	/**
	* Returns the msb page template folders before and after the current msb page template folder in the ordered set where groupId = &#63; and name LIKE &#63;.
	*
	* @param msbPageTemplateFolderId the primary key of the current msb page template folder
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next msb page template folder
	* @throws NoSuchPageTemplateFolderException if a msb page template folder with the primary key could not be found
	*/
	public static MSBPageTemplateFolder[] findByG_LikeN_PrevAndNext(
		long msbPageTemplateFolderId, long groupId, java.lang.String name,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateFolderException {
		return getPersistence()
				   .findByG_LikeN_PrevAndNext(msbPageTemplateFolderId, groupId,
			name, orderByComparator);
	}

	/**
	* Returns all the msb page template folders that the user has permission to view where groupId = &#63; and name LIKE &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the matching msb page template folders that the user has permission to view
	*/
	public static List<MSBPageTemplateFolder> filterFindByG_LikeN(
		long groupId, java.lang.String name) {
		return getPersistence().filterFindByG_LikeN(groupId, name);
	}

	/**
	* Returns a range of all the msb page template folders that the user has permission to view where groupId = &#63; and name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param name the name
	* @param start the lower bound of the range of msb page template folders
	* @param end the upper bound of the range of msb page template folders (not inclusive)
	* @return the range of matching msb page template folders that the user has permission to view
	*/
	public static List<MSBPageTemplateFolder> filterFindByG_LikeN(
		long groupId, java.lang.String name, int start, int end) {
		return getPersistence().filterFindByG_LikeN(groupId, name, start, end);
	}

	/**
	* Returns an ordered range of all the msb page template folders that the user has permissions to view where groupId = &#63; and name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param name the name
	* @param start the lower bound of the range of msb page template folders
	* @param end the upper bound of the range of msb page template folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching msb page template folders that the user has permission to view
	*/
	public static List<MSBPageTemplateFolder> filterFindByG_LikeN(
		long groupId, java.lang.String name, int start, int end,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator) {
		return getPersistence()
				   .filterFindByG_LikeN(groupId, name, start, end,
			orderByComparator);
	}

	/**
	* Returns the msb page template folders before and after the current msb page template folder in the ordered set of msb page template folders that the user has permission to view where groupId = &#63; and name LIKE &#63;.
	*
	* @param msbPageTemplateFolderId the primary key of the current msb page template folder
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next msb page template folder
	* @throws NoSuchPageTemplateFolderException if a msb page template folder with the primary key could not be found
	*/
	public static MSBPageTemplateFolder[] filterFindByG_LikeN_PrevAndNext(
		long msbPageTemplateFolderId, long groupId, java.lang.String name,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateFolderException {
		return getPersistence()
				   .filterFindByG_LikeN_PrevAndNext(msbPageTemplateFolderId,
			groupId, name, orderByComparator);
	}

	/**
	* Removes all the msb page template folders where groupId = &#63; and name LIKE &#63; from the database.
	*
	* @param groupId the group ID
	* @param name the name
	*/
	public static void removeByG_LikeN(long groupId, java.lang.String name) {
		getPersistence().removeByG_LikeN(groupId, name);
	}

	/**
	* Returns the number of msb page template folders where groupId = &#63; and name LIKE &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the number of matching msb page template folders
	*/
	public static int countByG_LikeN(long groupId, java.lang.String name) {
		return getPersistence().countByG_LikeN(groupId, name);
	}

	/**
	* Returns the number of msb page template folders that the user has permission to view where groupId = &#63; and name LIKE &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the number of matching msb page template folders that the user has permission to view
	*/
	public static int filterCountByG_LikeN(long groupId, java.lang.String name) {
		return getPersistence().filterCountByG_LikeN(groupId, name);
	}

	/**
	* Caches the msb page template folder in the entity cache if it is enabled.
	*
	* @param msbPageTemplateFolder the msb page template folder
	*/
	public static void cacheResult(MSBPageTemplateFolder msbPageTemplateFolder) {
		getPersistence().cacheResult(msbPageTemplateFolder);
	}

	/**
	* Caches the msb page template folders in the entity cache if it is enabled.
	*
	* @param msbPageTemplateFolders the msb page template folders
	*/
	public static void cacheResult(
		List<MSBPageTemplateFolder> msbPageTemplateFolders) {
		getPersistence().cacheResult(msbPageTemplateFolders);
	}

	/**
	* Creates a new msb page template folder with the primary key. Does not add the msb page template folder to the database.
	*
	* @param msbPageTemplateFolderId the primary key for the new msb page template folder
	* @return the new msb page template folder
	*/
	public static MSBPageTemplateFolder create(long msbPageTemplateFolderId) {
		return getPersistence().create(msbPageTemplateFolderId);
	}

	/**
	* Removes the msb page template folder with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param msbPageTemplateFolderId the primary key of the msb page template folder
	* @return the msb page template folder that was removed
	* @throws NoSuchPageTemplateFolderException if a msb page template folder with the primary key could not be found
	*/
	public static MSBPageTemplateFolder remove(long msbPageTemplateFolderId)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateFolderException {
		return getPersistence().remove(msbPageTemplateFolderId);
	}

	public static MSBPageTemplateFolder updateImpl(
		MSBPageTemplateFolder msbPageTemplateFolder) {
		return getPersistence().updateImpl(msbPageTemplateFolder);
	}

	/**
	* Returns the msb page template folder with the primary key or throws a {@link NoSuchPageTemplateFolderException} if it could not be found.
	*
	* @param msbPageTemplateFolderId the primary key of the msb page template folder
	* @return the msb page template folder
	* @throws NoSuchPageTemplateFolderException if a msb page template folder with the primary key could not be found
	*/
	public static MSBPageTemplateFolder findByPrimaryKey(
		long msbPageTemplateFolderId)
		throws com.liferay.modern.site.building.page.exception.NoSuchPageTemplateFolderException {
		return getPersistence().findByPrimaryKey(msbPageTemplateFolderId);
	}

	/**
	* Returns the msb page template folder with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param msbPageTemplateFolderId the primary key of the msb page template folder
	* @return the msb page template folder, or <code>null</code> if a msb page template folder with the primary key could not be found
	*/
	public static MSBPageTemplateFolder fetchByPrimaryKey(
		long msbPageTemplateFolderId) {
		return getPersistence().fetchByPrimaryKey(msbPageTemplateFolderId);
	}

	public static java.util.Map<java.io.Serializable, MSBPageTemplateFolder> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the msb page template folders.
	*
	* @return the msb page template folders
	*/
	public static List<MSBPageTemplateFolder> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the msb page template folders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of msb page template folders
	* @param end the upper bound of the range of msb page template folders (not inclusive)
	* @return the range of msb page template folders
	*/
	public static List<MSBPageTemplateFolder> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the msb page template folders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of msb page template folders
	* @param end the upper bound of the range of msb page template folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of msb page template folders
	*/
	public static List<MSBPageTemplateFolder> findAll(int start, int end,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the msb page template folders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSBPageTemplateFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of msb page template folders
	* @param end the upper bound of the range of msb page template folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of msb page template folders
	*/
	public static List<MSBPageTemplateFolder> findAll(int start, int end,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the msb page template folders from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of msb page template folders.
	*
	* @return the number of msb page template folders
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static MSBPageTemplateFolderPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MSBPageTemplateFolderPersistence, MSBPageTemplateFolderPersistence> _serviceTracker =
		ServiceTrackerFactory.open(MSBPageTemplateFolderPersistence.class);
}