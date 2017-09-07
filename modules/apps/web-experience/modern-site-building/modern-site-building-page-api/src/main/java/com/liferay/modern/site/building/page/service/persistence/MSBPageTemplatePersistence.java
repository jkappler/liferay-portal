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

import com.liferay.modern.site.building.page.exception.NoSuchPageTemplateException;
import com.liferay.modern.site.building.page.model.MSBPageTemplate;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the msb page template service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.modern.site.building.page.service.persistence.impl.MSBPageTemplatePersistenceImpl
 * @see MSBPageTemplateUtil
 * @generated
 */
@ProviderType
public interface MSBPageTemplatePersistence extends BasePersistence<MSBPageTemplate> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MSBPageTemplateUtil} to access the msb page template persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the msb page templates where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching msb page templates
	*/
	public java.util.List<MSBPageTemplate> findByUuid(java.lang.String uuid);

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
	public java.util.List<MSBPageTemplate> findByUuid(java.lang.String uuid,
		int start, int end);

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
	public java.util.List<MSBPageTemplate> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplate> orderByComparator);

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
	public java.util.List<MSBPageTemplate> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplate> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first msb page template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching msb page template
	* @throws NoSuchPageTemplateException if a matching msb page template could not be found
	*/
	public MSBPageTemplate findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplate> orderByComparator)
		throws NoSuchPageTemplateException;

	/**
	* Returns the first msb page template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching msb page template, or <code>null</code> if a matching msb page template could not be found
	*/
	public MSBPageTemplate fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplate> orderByComparator);

	/**
	* Returns the last msb page template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching msb page template
	* @throws NoSuchPageTemplateException if a matching msb page template could not be found
	*/
	public MSBPageTemplate findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplate> orderByComparator)
		throws NoSuchPageTemplateException;

	/**
	* Returns the last msb page template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching msb page template, or <code>null</code> if a matching msb page template could not be found
	*/
	public MSBPageTemplate fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplate> orderByComparator);

	/**
	* Returns the msb page templates before and after the current msb page template in the ordered set where uuid = &#63;.
	*
	* @param msbPageTemplateId the primary key of the current msb page template
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next msb page template
	* @throws NoSuchPageTemplateException if a msb page template with the primary key could not be found
	*/
	public MSBPageTemplate[] findByUuid_PrevAndNext(long msbPageTemplateId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplate> orderByComparator)
		throws NoSuchPageTemplateException;

	/**
	* Removes all the msb page templates where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of msb page templates where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching msb page templates
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the msb page template where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPageTemplateException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching msb page template
	* @throws NoSuchPageTemplateException if a matching msb page template could not be found
	*/
	public MSBPageTemplate findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchPageTemplateException;

	/**
	* Returns the msb page template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching msb page template, or <code>null</code> if a matching msb page template could not be found
	*/
	public MSBPageTemplate fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the msb page template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching msb page template, or <code>null</code> if a matching msb page template could not be found
	*/
	public MSBPageTemplate fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the msb page template where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the msb page template that was removed
	*/
	public MSBPageTemplate removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchPageTemplateException;

	/**
	* Returns the number of msb page templates where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching msb page templates
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the msb page templates where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching msb page templates
	*/
	public java.util.List<MSBPageTemplate> findByUuid_C(java.lang.String uuid,
		long companyId);

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
	public java.util.List<MSBPageTemplate> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

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
	public java.util.List<MSBPageTemplate> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplate> orderByComparator);

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
	public java.util.List<MSBPageTemplate> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplate> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first msb page template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching msb page template
	* @throws NoSuchPageTemplateException if a matching msb page template could not be found
	*/
	public MSBPageTemplate findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplate> orderByComparator)
		throws NoSuchPageTemplateException;

	/**
	* Returns the first msb page template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching msb page template, or <code>null</code> if a matching msb page template could not be found
	*/
	public MSBPageTemplate fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplate> orderByComparator);

	/**
	* Returns the last msb page template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching msb page template
	* @throws NoSuchPageTemplateException if a matching msb page template could not be found
	*/
	public MSBPageTemplate findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplate> orderByComparator)
		throws NoSuchPageTemplateException;

	/**
	* Returns the last msb page template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching msb page template, or <code>null</code> if a matching msb page template could not be found
	*/
	public MSBPageTemplate fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplate> orderByComparator);

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
	public MSBPageTemplate[] findByUuid_C_PrevAndNext(long msbPageTemplateId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplate> orderByComparator)
		throws NoSuchPageTemplateException;

	/**
	* Removes all the msb page templates where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of msb page templates where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching msb page templates
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the msb page templates where msbPageTemplateFolderId = &#63;.
	*
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @return the matching msb page templates
	*/
	public java.util.List<MSBPageTemplate> findByMSBPageTemplateFolderId(
		long msbPageTemplateFolderId);

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
	public java.util.List<MSBPageTemplate> findByMSBPageTemplateFolderId(
		long msbPageTemplateFolderId, int start, int end);

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
	public java.util.List<MSBPageTemplate> findByMSBPageTemplateFolderId(
		long msbPageTemplateFolderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplate> orderByComparator);

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
	public java.util.List<MSBPageTemplate> findByMSBPageTemplateFolderId(
		long msbPageTemplateFolderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplate> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first msb page template in the ordered set where msbPageTemplateFolderId = &#63;.
	*
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching msb page template
	* @throws NoSuchPageTemplateException if a matching msb page template could not be found
	*/
	public MSBPageTemplate findByMSBPageTemplateFolderId_First(
		long msbPageTemplateFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplate> orderByComparator)
		throws NoSuchPageTemplateException;

	/**
	* Returns the first msb page template in the ordered set where msbPageTemplateFolderId = &#63;.
	*
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching msb page template, or <code>null</code> if a matching msb page template could not be found
	*/
	public MSBPageTemplate fetchByMSBPageTemplateFolderId_First(
		long msbPageTemplateFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplate> orderByComparator);

	/**
	* Returns the last msb page template in the ordered set where msbPageTemplateFolderId = &#63;.
	*
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching msb page template
	* @throws NoSuchPageTemplateException if a matching msb page template could not be found
	*/
	public MSBPageTemplate findByMSBPageTemplateFolderId_Last(
		long msbPageTemplateFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplate> orderByComparator)
		throws NoSuchPageTemplateException;

	/**
	* Returns the last msb page template in the ordered set where msbPageTemplateFolderId = &#63;.
	*
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching msb page template, or <code>null</code> if a matching msb page template could not be found
	*/
	public MSBPageTemplate fetchByMSBPageTemplateFolderId_Last(
		long msbPageTemplateFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplate> orderByComparator);

	/**
	* Returns the msb page templates before and after the current msb page template in the ordered set where msbPageTemplateFolderId = &#63;.
	*
	* @param msbPageTemplateId the primary key of the current msb page template
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next msb page template
	* @throws NoSuchPageTemplateException if a msb page template with the primary key could not be found
	*/
	public MSBPageTemplate[] findByMSBPageTemplateFolderId_PrevAndNext(
		long msbPageTemplateId, long msbPageTemplateFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplate> orderByComparator)
		throws NoSuchPageTemplateException;

	/**
	* Removes all the msb page templates where msbPageTemplateFolderId = &#63; from the database.
	*
	* @param msbPageTemplateFolderId the msb page template folder ID
	*/
	public void removeByMSBPageTemplateFolderId(long msbPageTemplateFolderId);

	/**
	* Returns the number of msb page templates where msbPageTemplateFolderId = &#63;.
	*
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @return the number of matching msb page templates
	*/
	public int countByMSBPageTemplateFolderId(long msbPageTemplateFolderId);

	/**
	* Returns all the msb page templates where name LIKE &#63; and msbPageTemplateFolderId = &#63;.
	*
	* @param name the name
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @return the matching msb page templates
	*/
	public java.util.List<MSBPageTemplate> findByLikeN_P(
		java.lang.String name, long msbPageTemplateFolderId);

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
	public java.util.List<MSBPageTemplate> findByLikeN_P(
		java.lang.String name, long msbPageTemplateFolderId, int start, int end);

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
	public java.util.List<MSBPageTemplate> findByLikeN_P(
		java.lang.String name, long msbPageTemplateFolderId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplate> orderByComparator);

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
	public java.util.List<MSBPageTemplate> findByLikeN_P(
		java.lang.String name, long msbPageTemplateFolderId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplate> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first msb page template in the ordered set where name LIKE &#63; and msbPageTemplateFolderId = &#63;.
	*
	* @param name the name
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching msb page template
	* @throws NoSuchPageTemplateException if a matching msb page template could not be found
	*/
	public MSBPageTemplate findByLikeN_P_First(java.lang.String name,
		long msbPageTemplateFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplate> orderByComparator)
		throws NoSuchPageTemplateException;

	/**
	* Returns the first msb page template in the ordered set where name LIKE &#63; and msbPageTemplateFolderId = &#63;.
	*
	* @param name the name
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching msb page template, or <code>null</code> if a matching msb page template could not be found
	*/
	public MSBPageTemplate fetchByLikeN_P_First(java.lang.String name,
		long msbPageTemplateFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplate> orderByComparator);

	/**
	* Returns the last msb page template in the ordered set where name LIKE &#63; and msbPageTemplateFolderId = &#63;.
	*
	* @param name the name
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching msb page template
	* @throws NoSuchPageTemplateException if a matching msb page template could not be found
	*/
	public MSBPageTemplate findByLikeN_P_Last(java.lang.String name,
		long msbPageTemplateFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplate> orderByComparator)
		throws NoSuchPageTemplateException;

	/**
	* Returns the last msb page template in the ordered set where name LIKE &#63; and msbPageTemplateFolderId = &#63;.
	*
	* @param name the name
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching msb page template, or <code>null</code> if a matching msb page template could not be found
	*/
	public MSBPageTemplate fetchByLikeN_P_Last(java.lang.String name,
		long msbPageTemplateFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplate> orderByComparator);

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
	public MSBPageTemplate[] findByLikeN_P_PrevAndNext(long msbPageTemplateId,
		java.lang.String name, long msbPageTemplateFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplate> orderByComparator)
		throws NoSuchPageTemplateException;

	/**
	* Removes all the msb page templates where name LIKE &#63; and msbPageTemplateFolderId = &#63; from the database.
	*
	* @param name the name
	* @param msbPageTemplateFolderId the msb page template folder ID
	*/
	public void removeByLikeN_P(java.lang.String name,
		long msbPageTemplateFolderId);

	/**
	* Returns the number of msb page templates where name LIKE &#63; and msbPageTemplateFolderId = &#63;.
	*
	* @param name the name
	* @param msbPageTemplateFolderId the msb page template folder ID
	* @return the number of matching msb page templates
	*/
	public int countByLikeN_P(java.lang.String name,
		long msbPageTemplateFolderId);

	/**
	* Caches the msb page template in the entity cache if it is enabled.
	*
	* @param msbPageTemplate the msb page template
	*/
	public void cacheResult(MSBPageTemplate msbPageTemplate);

	/**
	* Caches the msb page templates in the entity cache if it is enabled.
	*
	* @param msbPageTemplates the msb page templates
	*/
	public void cacheResult(java.util.List<MSBPageTemplate> msbPageTemplates);

	/**
	* Creates a new msb page template with the primary key. Does not add the msb page template to the database.
	*
	* @param msbPageTemplateId the primary key for the new msb page template
	* @return the new msb page template
	*/
	public MSBPageTemplate create(long msbPageTemplateId);

	/**
	* Removes the msb page template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param msbPageTemplateId the primary key of the msb page template
	* @return the msb page template that was removed
	* @throws NoSuchPageTemplateException if a msb page template with the primary key could not be found
	*/
	public MSBPageTemplate remove(long msbPageTemplateId)
		throws NoSuchPageTemplateException;

	public MSBPageTemplate updateImpl(MSBPageTemplate msbPageTemplate);

	/**
	* Returns the msb page template with the primary key or throws a {@link NoSuchPageTemplateException} if it could not be found.
	*
	* @param msbPageTemplateId the primary key of the msb page template
	* @return the msb page template
	* @throws NoSuchPageTemplateException if a msb page template with the primary key could not be found
	*/
	public MSBPageTemplate findByPrimaryKey(long msbPageTemplateId)
		throws NoSuchPageTemplateException;

	/**
	* Returns the msb page template with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param msbPageTemplateId the primary key of the msb page template
	* @return the msb page template, or <code>null</code> if a msb page template with the primary key could not be found
	*/
	public MSBPageTemplate fetchByPrimaryKey(long msbPageTemplateId);

	@Override
	public java.util.Map<java.io.Serializable, MSBPageTemplate> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the msb page templates.
	*
	* @return the msb page templates
	*/
	public java.util.List<MSBPageTemplate> findAll();

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
	public java.util.List<MSBPageTemplate> findAll(int start, int end);

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
	public java.util.List<MSBPageTemplate> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplate> orderByComparator);

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
	public java.util.List<MSBPageTemplate> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplate> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the msb page templates from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of msb page templates.
	*
	* @return the number of msb page templates
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}