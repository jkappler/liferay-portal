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

package com.liferay.portal.kernel.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.exception.NoSuchLayoutPrototypeException;
import com.liferay.portal.kernel.model.LayoutPrototype;

/**
 * The persistence interface for the layout prototype service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.persistence.impl.LayoutPrototypePersistenceImpl
 * @see LayoutPrototypeUtil
 * @generated
 */
@ProviderType
public interface LayoutPrototypePersistence extends BasePersistence<LayoutPrototype> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LayoutPrototypeUtil} to access the layout prototype persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the layout prototypes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching layout prototypes
	*/
	public java.util.List<LayoutPrototype> findByUuid(String uuid);

	/**
	* Returns a range of all the layout prototypes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of layout prototypes
	* @param end the upper bound of the range of layout prototypes (not inclusive)
	* @return the range of matching layout prototypes
	*/
	public java.util.List<LayoutPrototype> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the layout prototypes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of layout prototypes
	* @param end the upper bound of the range of layout prototypes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout prototypes
	*/
	public java.util.List<LayoutPrototype> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator);

	/**
	* Returns an ordered range of all the layout prototypes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of layout prototypes
	* @param end the upper bound of the range of layout prototypes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching layout prototypes
	*/
	public java.util.List<LayoutPrototype> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first layout prototype in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout prototype
	* @throws NoSuchLayoutPrototypeException if a matching layout prototype could not be found
	*/
	public LayoutPrototype findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator)
		throws NoSuchLayoutPrototypeException;

	/**
	* Returns the first layout prototype in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout prototype, or <code>null</code> if a matching layout prototype could not be found
	*/
	public LayoutPrototype fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator);

	/**
	* Returns the last layout prototype in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout prototype
	* @throws NoSuchLayoutPrototypeException if a matching layout prototype could not be found
	*/
	public LayoutPrototype findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator)
		throws NoSuchLayoutPrototypeException;

	/**
	* Returns the last layout prototype in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout prototype, or <code>null</code> if a matching layout prototype could not be found
	*/
	public LayoutPrototype fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator);

	/**
	* Returns the layout prototypes before and after the current layout prototype in the ordered set where uuid = &#63;.
	*
	* @param layoutPrototypeId the primary key of the current layout prototype
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout prototype
	* @throws NoSuchLayoutPrototypeException if a layout prototype with the primary key could not be found
	*/
	public LayoutPrototype[] findByUuid_PrevAndNext(long layoutPrototypeId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator)
		throws NoSuchLayoutPrototypeException;

	/**
	* Removes all the layout prototypes where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of layout prototypes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching layout prototypes
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the layout prototype where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchLayoutPrototypeException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching layout prototype
	* @throws NoSuchLayoutPrototypeException if a matching layout prototype could not be found
	*/
	public LayoutPrototype findByUUID_G(String uuid, long groupId)
		throws NoSuchLayoutPrototypeException;

	/**
	* Returns the layout prototype where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching layout prototype, or <code>null</code> if a matching layout prototype could not be found
	*/
	public LayoutPrototype fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the layout prototype where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching layout prototype, or <code>null</code> if a matching layout prototype could not be found
	*/
	public LayoutPrototype fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the layout prototype where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the layout prototype that was removed
	*/
	public LayoutPrototype removeByUUID_G(String uuid, long groupId)
		throws NoSuchLayoutPrototypeException;

	/**
	* Returns the number of layout prototypes where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching layout prototypes
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the layout prototypes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching layout prototypes
	*/
	public java.util.List<LayoutPrototype> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the layout prototypes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of layout prototypes
	* @param end the upper bound of the range of layout prototypes (not inclusive)
	* @return the range of matching layout prototypes
	*/
	public java.util.List<LayoutPrototype> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the layout prototypes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of layout prototypes
	* @param end the upper bound of the range of layout prototypes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout prototypes
	*/
	public java.util.List<LayoutPrototype> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator);

	/**
	* Returns an ordered range of all the layout prototypes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of layout prototypes
	* @param end the upper bound of the range of layout prototypes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching layout prototypes
	*/
	public java.util.List<LayoutPrototype> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first layout prototype in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout prototype
	* @throws NoSuchLayoutPrototypeException if a matching layout prototype could not be found
	*/
	public LayoutPrototype findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator)
		throws NoSuchLayoutPrototypeException;

	/**
	* Returns the first layout prototype in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout prototype, or <code>null</code> if a matching layout prototype could not be found
	*/
	public LayoutPrototype fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator);

	/**
	* Returns the last layout prototype in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout prototype
	* @throws NoSuchLayoutPrototypeException if a matching layout prototype could not be found
	*/
	public LayoutPrototype findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator)
		throws NoSuchLayoutPrototypeException;

	/**
	* Returns the last layout prototype in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout prototype, or <code>null</code> if a matching layout prototype could not be found
	*/
	public LayoutPrototype fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator);

	/**
	* Returns the layout prototypes before and after the current layout prototype in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param layoutPrototypeId the primary key of the current layout prototype
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout prototype
	* @throws NoSuchLayoutPrototypeException if a layout prototype with the primary key could not be found
	*/
	public LayoutPrototype[] findByUuid_C_PrevAndNext(long layoutPrototypeId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator)
		throws NoSuchLayoutPrototypeException;

	/**
	* Removes all the layout prototypes where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of layout prototypes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching layout prototypes
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the layout prototypes where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching layout prototypes
	*/
	public java.util.List<LayoutPrototype> findByGroupId(long groupId);

	/**
	* Returns a range of all the layout prototypes where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of layout prototypes
	* @param end the upper bound of the range of layout prototypes (not inclusive)
	* @return the range of matching layout prototypes
	*/
	public java.util.List<LayoutPrototype> findByGroupId(long groupId,
		int start, int end);

	/**
	* Returns an ordered range of all the layout prototypes where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of layout prototypes
	* @param end the upper bound of the range of layout prototypes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout prototypes
	*/
	public java.util.List<LayoutPrototype> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator);

	/**
	* Returns an ordered range of all the layout prototypes where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of layout prototypes
	* @param end the upper bound of the range of layout prototypes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching layout prototypes
	*/
	public java.util.List<LayoutPrototype> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first layout prototype in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout prototype
	* @throws NoSuchLayoutPrototypeException if a matching layout prototype could not be found
	*/
	public LayoutPrototype findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator)
		throws NoSuchLayoutPrototypeException;

	/**
	* Returns the first layout prototype in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout prototype, or <code>null</code> if a matching layout prototype could not be found
	*/
	public LayoutPrototype fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator);

	/**
	* Returns the last layout prototype in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout prototype
	* @throws NoSuchLayoutPrototypeException if a matching layout prototype could not be found
	*/
	public LayoutPrototype findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator)
		throws NoSuchLayoutPrototypeException;

	/**
	* Returns the last layout prototype in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout prototype, or <code>null</code> if a matching layout prototype could not be found
	*/
	public LayoutPrototype fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator);

	/**
	* Returns the layout prototypes before and after the current layout prototype in the ordered set where groupId = &#63;.
	*
	* @param layoutPrototypeId the primary key of the current layout prototype
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout prototype
	* @throws NoSuchLayoutPrototypeException if a layout prototype with the primary key could not be found
	*/
	public LayoutPrototype[] findByGroupId_PrevAndNext(long layoutPrototypeId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator)
		throws NoSuchLayoutPrototypeException;

	/**
	* Returns all the layout prototypes that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching layout prototypes that the user has permission to view
	*/
	public java.util.List<LayoutPrototype> filterFindByGroupId(long groupId);

	/**
	* Returns a range of all the layout prototypes that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of layout prototypes
	* @param end the upper bound of the range of layout prototypes (not inclusive)
	* @return the range of matching layout prototypes that the user has permission to view
	*/
	public java.util.List<LayoutPrototype> filterFindByGroupId(long groupId,
		int start, int end);

	/**
	* Returns an ordered range of all the layout prototypes that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of layout prototypes
	* @param end the upper bound of the range of layout prototypes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout prototypes that the user has permission to view
	*/
	public java.util.List<LayoutPrototype> filterFindByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator);

	/**
	* Returns the layout prototypes before and after the current layout prototype in the ordered set of layout prototypes that the user has permission to view where groupId = &#63;.
	*
	* @param layoutPrototypeId the primary key of the current layout prototype
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout prototype
	* @throws NoSuchLayoutPrototypeException if a layout prototype with the primary key could not be found
	*/
	public LayoutPrototype[] filterFindByGroupId_PrevAndNext(
		long layoutPrototypeId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator)
		throws NoSuchLayoutPrototypeException;

	/**
	* Removes all the layout prototypes where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of layout prototypes where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching layout prototypes
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns the number of layout prototypes that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching layout prototypes that the user has permission to view
	*/
	public int filterCountByGroupId(long groupId);

	/**
	* Returns all the layout prototypes where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching layout prototypes
	*/
	public java.util.List<LayoutPrototype> findByCompanyId(long companyId);

	/**
	* Returns a range of all the layout prototypes where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of layout prototypes
	* @param end the upper bound of the range of layout prototypes (not inclusive)
	* @return the range of matching layout prototypes
	*/
	public java.util.List<LayoutPrototype> findByCompanyId(long companyId,
		int start, int end);

	/**
	* Returns an ordered range of all the layout prototypes where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of layout prototypes
	* @param end the upper bound of the range of layout prototypes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout prototypes
	*/
	public java.util.List<LayoutPrototype> findByCompanyId(long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator);

	/**
	* Returns an ordered range of all the layout prototypes where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of layout prototypes
	* @param end the upper bound of the range of layout prototypes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching layout prototypes
	*/
	public java.util.List<LayoutPrototype> findByCompanyId(long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first layout prototype in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout prototype
	* @throws NoSuchLayoutPrototypeException if a matching layout prototype could not be found
	*/
	public LayoutPrototype findByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator)
		throws NoSuchLayoutPrototypeException;

	/**
	* Returns the first layout prototype in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout prototype, or <code>null</code> if a matching layout prototype could not be found
	*/
	public LayoutPrototype fetchByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator);

	/**
	* Returns the last layout prototype in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout prototype
	* @throws NoSuchLayoutPrototypeException if a matching layout prototype could not be found
	*/
	public LayoutPrototype findByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator)
		throws NoSuchLayoutPrototypeException;

	/**
	* Returns the last layout prototype in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout prototype, or <code>null</code> if a matching layout prototype could not be found
	*/
	public LayoutPrototype fetchByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator);

	/**
	* Returns the layout prototypes before and after the current layout prototype in the ordered set where companyId = &#63;.
	*
	* @param layoutPrototypeId the primary key of the current layout prototype
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout prototype
	* @throws NoSuchLayoutPrototypeException if a layout prototype with the primary key could not be found
	*/
	public LayoutPrototype[] findByCompanyId_PrevAndNext(
		long layoutPrototypeId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator)
		throws NoSuchLayoutPrototypeException;

	/**
	* Removes all the layout prototypes where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public void removeByCompanyId(long companyId);

	/**
	* Returns the number of layout prototypes where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching layout prototypes
	*/
	public int countByCompanyId(long companyId);

	/**
	* Returns all the layout prototypes where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @return the matching layout prototypes
	*/
	public java.util.List<LayoutPrototype> findByC_A(long companyId,
		boolean active);

	/**
	* Returns a range of all the layout prototypes where companyId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param active the active
	* @param start the lower bound of the range of layout prototypes
	* @param end the upper bound of the range of layout prototypes (not inclusive)
	* @return the range of matching layout prototypes
	*/
	public java.util.List<LayoutPrototype> findByC_A(long companyId,
		boolean active, int start, int end);

	/**
	* Returns an ordered range of all the layout prototypes where companyId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param active the active
	* @param start the lower bound of the range of layout prototypes
	* @param end the upper bound of the range of layout prototypes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching layout prototypes
	*/
	public java.util.List<LayoutPrototype> findByC_A(long companyId,
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator);

	/**
	* Returns an ordered range of all the layout prototypes where companyId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param active the active
	* @param start the lower bound of the range of layout prototypes
	* @param end the upper bound of the range of layout prototypes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching layout prototypes
	*/
	public java.util.List<LayoutPrototype> findByC_A(long companyId,
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first layout prototype in the ordered set where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout prototype
	* @throws NoSuchLayoutPrototypeException if a matching layout prototype could not be found
	*/
	public LayoutPrototype findByC_A_First(long companyId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator)
		throws NoSuchLayoutPrototypeException;

	/**
	* Returns the first layout prototype in the ordered set where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching layout prototype, or <code>null</code> if a matching layout prototype could not be found
	*/
	public LayoutPrototype fetchByC_A_First(long companyId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator);

	/**
	* Returns the last layout prototype in the ordered set where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout prototype
	* @throws NoSuchLayoutPrototypeException if a matching layout prototype could not be found
	*/
	public LayoutPrototype findByC_A_Last(long companyId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator)
		throws NoSuchLayoutPrototypeException;

	/**
	* Returns the last layout prototype in the ordered set where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching layout prototype, or <code>null</code> if a matching layout prototype could not be found
	*/
	public LayoutPrototype fetchByC_A_Last(long companyId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator);

	/**
	* Returns the layout prototypes before and after the current layout prototype in the ordered set where companyId = &#63; and active = &#63;.
	*
	* @param layoutPrototypeId the primary key of the current layout prototype
	* @param companyId the company ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next layout prototype
	* @throws NoSuchLayoutPrototypeException if a layout prototype with the primary key could not be found
	*/
	public LayoutPrototype[] findByC_A_PrevAndNext(long layoutPrototypeId,
		long companyId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator)
		throws NoSuchLayoutPrototypeException;

	/**
	* Removes all the layout prototypes where companyId = &#63; and active = &#63; from the database.
	*
	* @param companyId the company ID
	* @param active the active
	*/
	public void removeByC_A(long companyId, boolean active);

	/**
	* Returns the number of layout prototypes where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @return the number of matching layout prototypes
	*/
	public int countByC_A(long companyId, boolean active);

	/**
	* Caches the layout prototype in the entity cache if it is enabled.
	*
	* @param layoutPrototype the layout prototype
	*/
	public void cacheResult(LayoutPrototype layoutPrototype);

	/**
	* Caches the layout prototypes in the entity cache if it is enabled.
	*
	* @param layoutPrototypes the layout prototypes
	*/
	public void cacheResult(java.util.List<LayoutPrototype> layoutPrototypes);

	/**
	* Creates a new layout prototype with the primary key. Does not add the layout prototype to the database.
	*
	* @param layoutPrototypeId the primary key for the new layout prototype
	* @return the new layout prototype
	*/
	public LayoutPrototype create(long layoutPrototypeId);

	/**
	* Removes the layout prototype with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param layoutPrototypeId the primary key of the layout prototype
	* @return the layout prototype that was removed
	* @throws NoSuchLayoutPrototypeException if a layout prototype with the primary key could not be found
	*/
	public LayoutPrototype remove(long layoutPrototypeId)
		throws NoSuchLayoutPrototypeException;

	public LayoutPrototype updateImpl(LayoutPrototype layoutPrototype);

	/**
	* Returns the layout prototype with the primary key or throws a {@link NoSuchLayoutPrototypeException} if it could not be found.
	*
	* @param layoutPrototypeId the primary key of the layout prototype
	* @return the layout prototype
	* @throws NoSuchLayoutPrototypeException if a layout prototype with the primary key could not be found
	*/
	public LayoutPrototype findByPrimaryKey(long layoutPrototypeId)
		throws NoSuchLayoutPrototypeException;

	/**
	* Returns the layout prototype with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param layoutPrototypeId the primary key of the layout prototype
	* @return the layout prototype, or <code>null</code> if a layout prototype with the primary key could not be found
	*/
	public LayoutPrototype fetchByPrimaryKey(long layoutPrototypeId);

	@Override
	public java.util.Map<java.io.Serializable, LayoutPrototype> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the layout prototypes.
	*
	* @return the layout prototypes
	*/
	public java.util.List<LayoutPrototype> findAll();

	/**
	* Returns a range of all the layout prototypes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of layout prototypes
	* @param end the upper bound of the range of layout prototypes (not inclusive)
	* @return the range of layout prototypes
	*/
	public java.util.List<LayoutPrototype> findAll(int start, int end);

	/**
	* Returns an ordered range of all the layout prototypes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of layout prototypes
	* @param end the upper bound of the range of layout prototypes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of layout prototypes
	*/
	public java.util.List<LayoutPrototype> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator);

	/**
	* Returns an ordered range of all the layout prototypes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LayoutPrototypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of layout prototypes
	* @param end the upper bound of the range of layout prototypes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of layout prototypes
	*/
	public java.util.List<LayoutPrototype> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LayoutPrototype> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the layout prototypes from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of layout prototypes.
	*
	* @return the number of layout prototypes
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}