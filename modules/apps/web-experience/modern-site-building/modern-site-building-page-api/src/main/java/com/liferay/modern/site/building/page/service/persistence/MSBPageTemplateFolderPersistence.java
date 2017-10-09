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

import com.liferay.modern.site.building.page.exception.NoSuchPageTemplateFolderException;
import com.liferay.modern.site.building.page.model.MSBPageTemplateFolder;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the msb page template folder service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.modern.site.building.page.service.persistence.impl.MSBPageTemplateFolderPersistenceImpl
 * @see MSBPageTemplateFolderUtil
 * @generated
 */
@ProviderType
public interface MSBPageTemplateFolderPersistence extends BasePersistence<MSBPageTemplateFolder> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MSBPageTemplateFolderUtil} to access the msb page template folder persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the msb page template folders where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching msb page template folders
	*/
	public java.util.List<MSBPageTemplateFolder> findByUuid(
		java.lang.String uuid);

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
	public java.util.List<MSBPageTemplateFolder> findByUuid(
		java.lang.String uuid, int start, int end);

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
	public java.util.List<MSBPageTemplateFolder> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator);

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
	public java.util.List<MSBPageTemplateFolder> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first msb page template folder in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching msb page template folder
	* @throws NoSuchPageTemplateFolderException if a matching msb page template folder could not be found
	*/
	public MSBPageTemplateFolder findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws NoSuchPageTemplateFolderException;

	/**
	* Returns the first msb page template folder in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching msb page template folder, or <code>null</code> if a matching msb page template folder could not be found
	*/
	public MSBPageTemplateFolder fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator);

	/**
	* Returns the last msb page template folder in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching msb page template folder
	* @throws NoSuchPageTemplateFolderException if a matching msb page template folder could not be found
	*/
	public MSBPageTemplateFolder findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws NoSuchPageTemplateFolderException;

	/**
	* Returns the last msb page template folder in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching msb page template folder, or <code>null</code> if a matching msb page template folder could not be found
	*/
	public MSBPageTemplateFolder fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator);

	/**
	* Returns the msb page template folders before and after the current msb page template folder in the ordered set where uuid = &#63;.
	*
	* @param msbPageTemplateFolderId the primary key of the current msb page template folder
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next msb page template folder
	* @throws NoSuchPageTemplateFolderException if a msb page template folder with the primary key could not be found
	*/
	public MSBPageTemplateFolder[] findByUuid_PrevAndNext(
		long msbPageTemplateFolderId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws NoSuchPageTemplateFolderException;

	/**
	* Removes all the msb page template folders where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of msb page template folders where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching msb page template folders
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the msb page template folder where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPageTemplateFolderException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching msb page template folder
	* @throws NoSuchPageTemplateFolderException if a matching msb page template folder could not be found
	*/
	public MSBPageTemplateFolder findByUUID_G(java.lang.String uuid,
		long groupId) throws NoSuchPageTemplateFolderException;

	/**
	* Returns the msb page template folder where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching msb page template folder, or <code>null</code> if a matching msb page template folder could not be found
	*/
	public MSBPageTemplateFolder fetchByUUID_G(java.lang.String uuid,
		long groupId);

	/**
	* Returns the msb page template folder where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching msb page template folder, or <code>null</code> if a matching msb page template folder could not be found
	*/
	public MSBPageTemplateFolder fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache);

	/**
	* Removes the msb page template folder where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the msb page template folder that was removed
	*/
	public MSBPageTemplateFolder removeByUUID_G(java.lang.String uuid,
		long groupId) throws NoSuchPageTemplateFolderException;

	/**
	* Returns the number of msb page template folders where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching msb page template folders
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the msb page template folders where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching msb page template folders
	*/
	public java.util.List<MSBPageTemplateFolder> findByUuid_C(
		java.lang.String uuid, long companyId);

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
	public java.util.List<MSBPageTemplateFolder> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end);

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
	public java.util.List<MSBPageTemplateFolder> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator);

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
	public java.util.List<MSBPageTemplateFolder> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first msb page template folder in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching msb page template folder
	* @throws NoSuchPageTemplateFolderException if a matching msb page template folder could not be found
	*/
	public MSBPageTemplateFolder findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws NoSuchPageTemplateFolderException;

	/**
	* Returns the first msb page template folder in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching msb page template folder, or <code>null</code> if a matching msb page template folder could not be found
	*/
	public MSBPageTemplateFolder fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator);

	/**
	* Returns the last msb page template folder in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching msb page template folder
	* @throws NoSuchPageTemplateFolderException if a matching msb page template folder could not be found
	*/
	public MSBPageTemplateFolder findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws NoSuchPageTemplateFolderException;

	/**
	* Returns the last msb page template folder in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching msb page template folder, or <code>null</code> if a matching msb page template folder could not be found
	*/
	public MSBPageTemplateFolder fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator);

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
	public MSBPageTemplateFolder[] findByUuid_C_PrevAndNext(
		long msbPageTemplateFolderId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws NoSuchPageTemplateFolderException;

	/**
	* Removes all the msb page template folders where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of msb page template folders where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching msb page template folders
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the msb page template folders where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching msb page template folders
	*/
	public java.util.List<MSBPageTemplateFolder> findByGroupId(long groupId);

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
	public java.util.List<MSBPageTemplateFolder> findByGroupId(long groupId,
		int start, int end);

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
	public java.util.List<MSBPageTemplateFolder> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator);

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
	public java.util.List<MSBPageTemplateFolder> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first msb page template folder in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching msb page template folder
	* @throws NoSuchPageTemplateFolderException if a matching msb page template folder could not be found
	*/
	public MSBPageTemplateFolder findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws NoSuchPageTemplateFolderException;

	/**
	* Returns the first msb page template folder in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching msb page template folder, or <code>null</code> if a matching msb page template folder could not be found
	*/
	public MSBPageTemplateFolder fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator);

	/**
	* Returns the last msb page template folder in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching msb page template folder
	* @throws NoSuchPageTemplateFolderException if a matching msb page template folder could not be found
	*/
	public MSBPageTemplateFolder findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws NoSuchPageTemplateFolderException;

	/**
	* Returns the last msb page template folder in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching msb page template folder, or <code>null</code> if a matching msb page template folder could not be found
	*/
	public MSBPageTemplateFolder fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator);

	/**
	* Returns the msb page template folders before and after the current msb page template folder in the ordered set where groupId = &#63;.
	*
	* @param msbPageTemplateFolderId the primary key of the current msb page template folder
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next msb page template folder
	* @throws NoSuchPageTemplateFolderException if a msb page template folder with the primary key could not be found
	*/
	public MSBPageTemplateFolder[] findByGroupId_PrevAndNext(
		long msbPageTemplateFolderId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws NoSuchPageTemplateFolderException;

	/**
	* Returns all the msb page template folders that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching msb page template folders that the user has permission to view
	*/
	public java.util.List<MSBPageTemplateFolder> filterFindByGroupId(
		long groupId);

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
	public java.util.List<MSBPageTemplateFolder> filterFindByGroupId(
		long groupId, int start, int end);

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
	public java.util.List<MSBPageTemplateFolder> filterFindByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator);

	/**
	* Returns the msb page template folders before and after the current msb page template folder in the ordered set of msb page template folders that the user has permission to view where groupId = &#63;.
	*
	* @param msbPageTemplateFolderId the primary key of the current msb page template folder
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next msb page template folder
	* @throws NoSuchPageTemplateFolderException if a msb page template folder with the primary key could not be found
	*/
	public MSBPageTemplateFolder[] filterFindByGroupId_PrevAndNext(
		long msbPageTemplateFolderId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws NoSuchPageTemplateFolderException;

	/**
	* Removes all the msb page template folders where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of msb page template folders where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching msb page template folders
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns the number of msb page template folders that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching msb page template folders that the user has permission to view
	*/
	public int filterCountByGroupId(long groupId);

	/**
	* Returns all the msb page template folders where groupId = &#63; and name LIKE &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the matching msb page template folders
	*/
	public java.util.List<MSBPageTemplateFolder> findByG_LikeN(long groupId,
		java.lang.String name);

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
	public java.util.List<MSBPageTemplateFolder> findByG_LikeN(long groupId,
		java.lang.String name, int start, int end);

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
	public java.util.List<MSBPageTemplateFolder> findByG_LikeN(long groupId,
		java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator);

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
	public java.util.List<MSBPageTemplateFolder> findByG_LikeN(long groupId,
		java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first msb page template folder in the ordered set where groupId = &#63; and name LIKE &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching msb page template folder
	* @throws NoSuchPageTemplateFolderException if a matching msb page template folder could not be found
	*/
	public MSBPageTemplateFolder findByG_LikeN_First(long groupId,
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws NoSuchPageTemplateFolderException;

	/**
	* Returns the first msb page template folder in the ordered set where groupId = &#63; and name LIKE &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching msb page template folder, or <code>null</code> if a matching msb page template folder could not be found
	*/
	public MSBPageTemplateFolder fetchByG_LikeN_First(long groupId,
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator);

	/**
	* Returns the last msb page template folder in the ordered set where groupId = &#63; and name LIKE &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching msb page template folder
	* @throws NoSuchPageTemplateFolderException if a matching msb page template folder could not be found
	*/
	public MSBPageTemplateFolder findByG_LikeN_Last(long groupId,
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws NoSuchPageTemplateFolderException;

	/**
	* Returns the last msb page template folder in the ordered set where groupId = &#63; and name LIKE &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching msb page template folder, or <code>null</code> if a matching msb page template folder could not be found
	*/
	public MSBPageTemplateFolder fetchByG_LikeN_Last(long groupId,
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator);

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
	public MSBPageTemplateFolder[] findByG_LikeN_PrevAndNext(
		long msbPageTemplateFolderId, long groupId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws NoSuchPageTemplateFolderException;

	/**
	* Returns all the msb page template folders that the user has permission to view where groupId = &#63; and name LIKE &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the matching msb page template folders that the user has permission to view
	*/
	public java.util.List<MSBPageTemplateFolder> filterFindByG_LikeN(
		long groupId, java.lang.String name);

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
	public java.util.List<MSBPageTemplateFolder> filterFindByG_LikeN(
		long groupId, java.lang.String name, int start, int end);

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
	public java.util.List<MSBPageTemplateFolder> filterFindByG_LikeN(
		long groupId, java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator);

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
	public MSBPageTemplateFolder[] filterFindByG_LikeN_PrevAndNext(
		long msbPageTemplateFolderId, long groupId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws NoSuchPageTemplateFolderException;

	/**
	* Removes all the msb page template folders where groupId = &#63; and name LIKE &#63; from the database.
	*
	* @param groupId the group ID
	* @param name the name
	*/
	public void removeByG_LikeN(long groupId, java.lang.String name);

	/**
	* Returns the number of msb page template folders where groupId = &#63; and name LIKE &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the number of matching msb page template folders
	*/
	public int countByG_LikeN(long groupId, java.lang.String name);

	/**
	* Returns the number of msb page template folders that the user has permission to view where groupId = &#63; and name LIKE &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the number of matching msb page template folders that the user has permission to view
	*/
	public int filterCountByG_LikeN(long groupId, java.lang.String name);

	/**
	* Caches the msb page template folder in the entity cache if it is enabled.
	*
	* @param msbPageTemplateFolder the msb page template folder
	*/
	public void cacheResult(MSBPageTemplateFolder msbPageTemplateFolder);

	/**
	* Caches the msb page template folders in the entity cache if it is enabled.
	*
	* @param msbPageTemplateFolders the msb page template folders
	*/
	public void cacheResult(
		java.util.List<MSBPageTemplateFolder> msbPageTemplateFolders);

	/**
	* Creates a new msb page template folder with the primary key. Does not add the msb page template folder to the database.
	*
	* @param msbPageTemplateFolderId the primary key for the new msb page template folder
	* @return the new msb page template folder
	*/
	public MSBPageTemplateFolder create(long msbPageTemplateFolderId);

	/**
	* Removes the msb page template folder with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param msbPageTemplateFolderId the primary key of the msb page template folder
	* @return the msb page template folder that was removed
	* @throws NoSuchPageTemplateFolderException if a msb page template folder with the primary key could not be found
	*/
	public MSBPageTemplateFolder remove(long msbPageTemplateFolderId)
		throws NoSuchPageTemplateFolderException;

	public MSBPageTemplateFolder updateImpl(
		MSBPageTemplateFolder msbPageTemplateFolder);

	/**
	* Returns the msb page template folder with the primary key or throws a {@link NoSuchPageTemplateFolderException} if it could not be found.
	*
	* @param msbPageTemplateFolderId the primary key of the msb page template folder
	* @return the msb page template folder
	* @throws NoSuchPageTemplateFolderException if a msb page template folder with the primary key could not be found
	*/
	public MSBPageTemplateFolder findByPrimaryKey(long msbPageTemplateFolderId)
		throws NoSuchPageTemplateFolderException;

	/**
	* Returns the msb page template folder with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param msbPageTemplateFolderId the primary key of the msb page template folder
	* @return the msb page template folder, or <code>null</code> if a msb page template folder with the primary key could not be found
	*/
	public MSBPageTemplateFolder fetchByPrimaryKey(long msbPageTemplateFolderId);

	@Override
	public java.util.Map<java.io.Serializable, MSBPageTemplateFolder> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the msb page template folders.
	*
	* @return the msb page template folders
	*/
	public java.util.List<MSBPageTemplateFolder> findAll();

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
	public java.util.List<MSBPageTemplateFolder> findAll(int start, int end);

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
	public java.util.List<MSBPageTemplateFolder> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator);

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
	public java.util.List<MSBPageTemplateFolder> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MSBPageTemplateFolder> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the msb page template folders from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of msb page template folders.
	*
	* @return the number of msb page template folders
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}