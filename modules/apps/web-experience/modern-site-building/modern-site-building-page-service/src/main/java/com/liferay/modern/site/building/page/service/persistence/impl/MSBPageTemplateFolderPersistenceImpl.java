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

package com.liferay.modern.site.building.page.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.modern.site.building.page.exception.NoSuchPageTemplateFolderException;
import com.liferay.modern.site.building.page.model.MSBPageTemplateFolder;
import com.liferay.modern.site.building.page.model.impl.MSBPageTemplateFolderImpl;
import com.liferay.modern.site.building.page.model.impl.MSBPageTemplateFolderModelImpl;
import com.liferay.modern.site.building.page.service.persistence.MSBPageTemplateFolderPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the msb page template folder service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MSBPageTemplateFolderPersistence
 * @see com.liferay.modern.site.building.page.service.persistence.MSBPageTemplateFolderUtil
 * @generated
 */
@ProviderType
public class MSBPageTemplateFolderPersistenceImpl extends BasePersistenceImpl<MSBPageTemplateFolder>
	implements MSBPageTemplateFolderPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MSBPageTemplateFolderUtil} to access the msb page template folder persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MSBPageTemplateFolderImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MSBPageTemplateFolderModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateFolderModelImpl.FINDER_CACHE_ENABLED,
			MSBPageTemplateFolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MSBPageTemplateFolderModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateFolderModelImpl.FINDER_CACHE_ENABLED,
			MSBPageTemplateFolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MSBPageTemplateFolderModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateFolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(MSBPageTemplateFolderModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateFolderModelImpl.FINDER_CACHE_ENABLED,
			MSBPageTemplateFolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(MSBPageTemplateFolderModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateFolderModelImpl.FINDER_CACHE_ENABLED,
			MSBPageTemplateFolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			MSBPageTemplateFolderModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(MSBPageTemplateFolderModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateFolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the msb page template folders where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching msb page template folders
	 */
	@Override
	public List<MSBPageTemplateFolder> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<MSBPageTemplateFolder> findByUuid(String uuid, int start,
		int end) {
		return findByUuid(uuid, start, end, null);
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
	@Override
	public List<MSBPageTemplateFolder> findByUuid(String uuid, int start,
		int end, OrderByComparator<MSBPageTemplateFolder> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
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
	@Override
	public List<MSBPageTemplateFolder> findByUuid(String uuid, int start,
		int end, OrderByComparator<MSBPageTemplateFolder> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<MSBPageTemplateFolder> list = null;

		if (retrieveFromCache) {
			list = (List<MSBPageTemplateFolder>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MSBPageTemplateFolder msbPageTemplateFolder : list) {
					if (!Objects.equals(uuid, msbPageTemplateFolder.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_MSBPAGETEMPLATEFOLDER_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(MSBPageTemplateFolderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<MSBPageTemplateFolder>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MSBPageTemplateFolder>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first msb page template folder in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching msb page template folder
	 * @throws NoSuchPageTemplateFolderException if a matching msb page template folder could not be found
	 */
	@Override
	public MSBPageTemplateFolder findByUuid_First(String uuid,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws NoSuchPageTemplateFolderException {
		MSBPageTemplateFolder msbPageTemplateFolder = fetchByUuid_First(uuid,
				orderByComparator);

		if (msbPageTemplateFolder != null) {
			return msbPageTemplateFolder;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPageTemplateFolderException(msg.toString());
	}

	/**
	 * Returns the first msb page template folder in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching msb page template folder, or <code>null</code> if a matching msb page template folder could not be found
	 */
	@Override
	public MSBPageTemplateFolder fetchByUuid_First(String uuid,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator) {
		List<MSBPageTemplateFolder> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last msb page template folder in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching msb page template folder
	 * @throws NoSuchPageTemplateFolderException if a matching msb page template folder could not be found
	 */
	@Override
	public MSBPageTemplateFolder findByUuid_Last(String uuid,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws NoSuchPageTemplateFolderException {
		MSBPageTemplateFolder msbPageTemplateFolder = fetchByUuid_Last(uuid,
				orderByComparator);

		if (msbPageTemplateFolder != null) {
			return msbPageTemplateFolder;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPageTemplateFolderException(msg.toString());
	}

	/**
	 * Returns the last msb page template folder in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching msb page template folder, or <code>null</code> if a matching msb page template folder could not be found
	 */
	@Override
	public MSBPageTemplateFolder fetchByUuid_Last(String uuid,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<MSBPageTemplateFolder> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public MSBPageTemplateFolder[] findByUuid_PrevAndNext(
		long msbPageTemplateFolderId, String uuid,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws NoSuchPageTemplateFolderException {
		MSBPageTemplateFolder msbPageTemplateFolder = findByPrimaryKey(msbPageTemplateFolderId);

		Session session = null;

		try {
			session = openSession();

			MSBPageTemplateFolder[] array = new MSBPageTemplateFolderImpl[3];

			array[0] = getByUuid_PrevAndNext(session, msbPageTemplateFolder,
					uuid, orderByComparator, true);

			array[1] = msbPageTemplateFolder;

			array[2] = getByUuid_PrevAndNext(session, msbPageTemplateFolder,
					uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MSBPageTemplateFolder getByUuid_PrevAndNext(Session session,
		MSBPageTemplateFolder msbPageTemplateFolder, String uuid,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MSBPAGETEMPLATEFOLDER_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(MSBPageTemplateFolderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(msbPageTemplateFolder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MSBPageTemplateFolder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the msb page template folders where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (MSBPageTemplateFolder msbPageTemplateFolder : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(msbPageTemplateFolder);
		}
	}

	/**
	 * Returns the number of msb page template folders where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching msb page template folders
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MSBPAGETEMPLATEFOLDER_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "msbPageTemplateFolder.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "msbPageTemplateFolder.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(msbPageTemplateFolder.uuid IS NULL OR msbPageTemplateFolder.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(MSBPageTemplateFolderModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateFolderModelImpl.FINDER_CACHE_ENABLED,
			MSBPageTemplateFolderImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			MSBPageTemplateFolderModelImpl.UUID_COLUMN_BITMASK |
			MSBPageTemplateFolderModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(MSBPageTemplateFolderModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateFolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the msb page template folder where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPageTemplateFolderException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching msb page template folder
	 * @throws NoSuchPageTemplateFolderException if a matching msb page template folder could not be found
	 */
	@Override
	public MSBPageTemplateFolder findByUUID_G(String uuid, long groupId)
		throws NoSuchPageTemplateFolderException {
		MSBPageTemplateFolder msbPageTemplateFolder = fetchByUUID_G(uuid,
				groupId);

		if (msbPageTemplateFolder == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchPageTemplateFolderException(msg.toString());
		}

		return msbPageTemplateFolder;
	}

	/**
	 * Returns the msb page template folder where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching msb page template folder, or <code>null</code> if a matching msb page template folder could not be found
	 */
	@Override
	public MSBPageTemplateFolder fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the msb page template folder where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching msb page template folder, or <code>null</code> if a matching msb page template folder could not be found
	 */
	@Override
	public MSBPageTemplateFolder fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof MSBPageTemplateFolder) {
			MSBPageTemplateFolder msbPageTemplateFolder = (MSBPageTemplateFolder)result;

			if (!Objects.equals(uuid, msbPageTemplateFolder.getUuid()) ||
					(groupId != msbPageTemplateFolder.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_MSBPAGETEMPLATEFOLDER_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<MSBPageTemplateFolder> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					MSBPageTemplateFolder msbPageTemplateFolder = list.get(0);

					result = msbPageTemplateFolder;

					cacheResult(msbPageTemplateFolder);

					if ((msbPageTemplateFolder.getUuid() == null) ||
							!msbPageTemplateFolder.getUuid().equals(uuid) ||
							(msbPageTemplateFolder.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, msbPageTemplateFolder);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (MSBPageTemplateFolder)result;
		}
	}

	/**
	 * Removes the msb page template folder where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the msb page template folder that was removed
	 */
	@Override
	public MSBPageTemplateFolder removeByUUID_G(String uuid, long groupId)
		throws NoSuchPageTemplateFolderException {
		MSBPageTemplateFolder msbPageTemplateFolder = findByUUID_G(uuid, groupId);

		return remove(msbPageTemplateFolder);
	}

	/**
	 * Returns the number of msb page template folders where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching msb page template folders
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MSBPAGETEMPLATEFOLDER_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "msbPageTemplateFolder.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "msbPageTemplateFolder.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(msbPageTemplateFolder.uuid IS NULL OR msbPageTemplateFolder.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "msbPageTemplateFolder.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(MSBPageTemplateFolderModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateFolderModelImpl.FINDER_CACHE_ENABLED,
			MSBPageTemplateFolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(MSBPageTemplateFolderModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateFolderModelImpl.FINDER_CACHE_ENABLED,
			MSBPageTemplateFolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			MSBPageTemplateFolderModelImpl.UUID_COLUMN_BITMASK |
			MSBPageTemplateFolderModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(MSBPageTemplateFolderModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateFolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the msb page template folders where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching msb page template folders
	 */
	@Override
	public List<MSBPageTemplateFolder> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<MSBPageTemplateFolder> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
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
	@Override
	public List<MSBPageTemplateFolder> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
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
	@Override
	public List<MSBPageTemplateFolder> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<MSBPageTemplateFolder> list = null;

		if (retrieveFromCache) {
			list = (List<MSBPageTemplateFolder>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MSBPageTemplateFolder msbPageTemplateFolder : list) {
					if (!Objects.equals(uuid, msbPageTemplateFolder.getUuid()) ||
							(companyId != msbPageTemplateFolder.getCompanyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_MSBPAGETEMPLATEFOLDER_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(MSBPageTemplateFolderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				if (!pagination) {
					list = (List<MSBPageTemplateFolder>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MSBPageTemplateFolder>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public MSBPageTemplateFolder findByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws NoSuchPageTemplateFolderException {
		MSBPageTemplateFolder msbPageTemplateFolder = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (msbPageTemplateFolder != null) {
			return msbPageTemplateFolder;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPageTemplateFolderException(msg.toString());
	}

	/**
	 * Returns the first msb page template folder in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching msb page template folder, or <code>null</code> if a matching msb page template folder could not be found
	 */
	@Override
	public MSBPageTemplateFolder fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator) {
		List<MSBPageTemplateFolder> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public MSBPageTemplateFolder findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws NoSuchPageTemplateFolderException {
		MSBPageTemplateFolder msbPageTemplateFolder = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (msbPageTemplateFolder != null) {
			return msbPageTemplateFolder;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPageTemplateFolderException(msg.toString());
	}

	/**
	 * Returns the last msb page template folder in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching msb page template folder, or <code>null</code> if a matching msb page template folder could not be found
	 */
	@Override
	public MSBPageTemplateFolder fetchByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<MSBPageTemplateFolder> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public MSBPageTemplateFolder[] findByUuid_C_PrevAndNext(
		long msbPageTemplateFolderId, String uuid, long companyId,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws NoSuchPageTemplateFolderException {
		MSBPageTemplateFolder msbPageTemplateFolder = findByPrimaryKey(msbPageTemplateFolderId);

		Session session = null;

		try {
			session = openSession();

			MSBPageTemplateFolder[] array = new MSBPageTemplateFolderImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, msbPageTemplateFolder,
					uuid, companyId, orderByComparator, true);

			array[1] = msbPageTemplateFolder;

			array[2] = getByUuid_C_PrevAndNext(session, msbPageTemplateFolder,
					uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MSBPageTemplateFolder getByUuid_C_PrevAndNext(Session session,
		MSBPageTemplateFolder msbPageTemplateFolder, String uuid,
		long companyId,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_MSBPAGETEMPLATEFOLDER_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(MSBPageTemplateFolderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(msbPageTemplateFolder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MSBPageTemplateFolder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the msb page template folders where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (MSBPageTemplateFolder msbPageTemplateFolder : findByUuid_C(uuid,
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(msbPageTemplateFolder);
		}
	}

	/**
	 * Returns the number of msb page template folders where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching msb page template folders
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MSBPAGETEMPLATEFOLDER_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "msbPageTemplateFolder.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "msbPageTemplateFolder.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(msbPageTemplateFolder.uuid IS NULL OR msbPageTemplateFolder.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "msbPageTemplateFolder.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(MSBPageTemplateFolderModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateFolderModelImpl.FINDER_CACHE_ENABLED,
			MSBPageTemplateFolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(MSBPageTemplateFolderModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateFolderModelImpl.FINDER_CACHE_ENABLED,
			MSBPageTemplateFolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			MSBPageTemplateFolderModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(MSBPageTemplateFolderModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateFolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the msb page template folders where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching msb page template folders
	 */
	@Override
	public List<MSBPageTemplateFolder> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<MSBPageTemplateFolder> findByGroupId(long groupId, int start,
		int end) {
		return findByGroupId(groupId, start, end, null);
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
	@Override
	public List<MSBPageTemplateFolder> findByGroupId(long groupId, int start,
		int end, OrderByComparator<MSBPageTemplateFolder> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
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
	@Override
	public List<MSBPageTemplateFolder> findByGroupId(long groupId, int start,
		int end, OrderByComparator<MSBPageTemplateFolder> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<MSBPageTemplateFolder> list = null;

		if (retrieveFromCache) {
			list = (List<MSBPageTemplateFolder>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MSBPageTemplateFolder msbPageTemplateFolder : list) {
					if ((groupId != msbPageTemplateFolder.getGroupId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_MSBPAGETEMPLATEFOLDER_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(MSBPageTemplateFolderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<MSBPageTemplateFolder>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MSBPageTemplateFolder>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first msb page template folder in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching msb page template folder
	 * @throws NoSuchPageTemplateFolderException if a matching msb page template folder could not be found
	 */
	@Override
	public MSBPageTemplateFolder findByGroupId_First(long groupId,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws NoSuchPageTemplateFolderException {
		MSBPageTemplateFolder msbPageTemplateFolder = fetchByGroupId_First(groupId,
				orderByComparator);

		if (msbPageTemplateFolder != null) {
			return msbPageTemplateFolder;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPageTemplateFolderException(msg.toString());
	}

	/**
	 * Returns the first msb page template folder in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching msb page template folder, or <code>null</code> if a matching msb page template folder could not be found
	 */
	@Override
	public MSBPageTemplateFolder fetchByGroupId_First(long groupId,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator) {
		List<MSBPageTemplateFolder> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last msb page template folder in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching msb page template folder
	 * @throws NoSuchPageTemplateFolderException if a matching msb page template folder could not be found
	 */
	@Override
	public MSBPageTemplateFolder findByGroupId_Last(long groupId,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws NoSuchPageTemplateFolderException {
		MSBPageTemplateFolder msbPageTemplateFolder = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (msbPageTemplateFolder != null) {
			return msbPageTemplateFolder;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPageTemplateFolderException(msg.toString());
	}

	/**
	 * Returns the last msb page template folder in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching msb page template folder, or <code>null</code> if a matching msb page template folder could not be found
	 */
	@Override
	public MSBPageTemplateFolder fetchByGroupId_Last(long groupId,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<MSBPageTemplateFolder> list = findByGroupId(groupId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public MSBPageTemplateFolder[] findByGroupId_PrevAndNext(
		long msbPageTemplateFolderId, long groupId,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws NoSuchPageTemplateFolderException {
		MSBPageTemplateFolder msbPageTemplateFolder = findByPrimaryKey(msbPageTemplateFolderId);

		Session session = null;

		try {
			session = openSession();

			MSBPageTemplateFolder[] array = new MSBPageTemplateFolderImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, msbPageTemplateFolder,
					groupId, orderByComparator, true);

			array[1] = msbPageTemplateFolder;

			array[2] = getByGroupId_PrevAndNext(session, msbPageTemplateFolder,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MSBPageTemplateFolder getByGroupId_PrevAndNext(Session session,
		MSBPageTemplateFolder msbPageTemplateFolder, long groupId,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MSBPAGETEMPLATEFOLDER_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(MSBPageTemplateFolderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(msbPageTemplateFolder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MSBPageTemplateFolder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the msb page template folders that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching msb page template folders that the user has permission to view
	 */
	@Override
	public List<MSBPageTemplateFolder> filterFindByGroupId(long groupId) {
		return filterFindByGroupId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<MSBPageTemplateFolder> filterFindByGroupId(long groupId,
		int start, int end) {
		return filterFindByGroupId(groupId, start, end, null);
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
	@Override
	public List<MSBPageTemplateFolder> filterFindByGroupId(long groupId,
		int start, int end,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId(groupId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MSBPAGETEMPLATEFOLDER_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MSBPAGETEMPLATEFOLDER_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MSBPAGETEMPLATEFOLDER_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(MSBPageTemplateFolderModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(MSBPageTemplateFolderModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MSBPageTemplateFolder.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS,
					MSBPageTemplateFolderImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE,
					MSBPageTemplateFolderImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<MSBPageTemplateFolder>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
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
	@Override
	public MSBPageTemplateFolder[] filterFindByGroupId_PrevAndNext(
		long msbPageTemplateFolderId, long groupId,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws NoSuchPageTemplateFolderException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(msbPageTemplateFolderId, groupId,
				orderByComparator);
		}

		MSBPageTemplateFolder msbPageTemplateFolder = findByPrimaryKey(msbPageTemplateFolderId);

		Session session = null;

		try {
			session = openSession();

			MSBPageTemplateFolder[] array = new MSBPageTemplateFolderImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(session,
					msbPageTemplateFolder, groupId, orderByComparator, true);

			array[1] = msbPageTemplateFolder;

			array[2] = filterGetByGroupId_PrevAndNext(session,
					msbPageTemplateFolder, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MSBPageTemplateFolder filterGetByGroupId_PrevAndNext(
		Session session, MSBPageTemplateFolder msbPageTemplateFolder,
		long groupId,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MSBPAGETEMPLATEFOLDER_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MSBPAGETEMPLATEFOLDER_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MSBPAGETEMPLATEFOLDER_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(MSBPageTemplateFolderModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(MSBPageTemplateFolderModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MSBPageTemplateFolder.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, MSBPageTemplateFolderImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, MSBPageTemplateFolderImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(msbPageTemplateFolder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MSBPageTemplateFolder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the msb page template folders where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (MSBPageTemplateFolder msbPageTemplateFolder : findByGroupId(
				groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(msbPageTemplateFolder);
		}
	}

	/**
	 * Returns the number of msb page template folders where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching msb page template folders
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MSBPAGETEMPLATEFOLDER_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of msb page template folders that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching msb page template folders that the user has permission to view
	 */
	@Override
	public int filterCountByGroupId(long groupId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_MSBPAGETEMPLATEFOLDER_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MSBPageTemplateFolder.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "msbPageTemplateFolder.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_LIKEN = new FinderPath(MSBPageTemplateFolderModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateFolderModelImpl.FINDER_CACHE_ENABLED,
			MSBPageTemplateFolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_LikeN",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_LIKEN = new FinderPath(MSBPageTemplateFolderModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateFolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_LikeN",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the msb page template folders where groupId = &#63; and name LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the matching msb page template folders
	 */
	@Override
	public List<MSBPageTemplateFolder> findByG_LikeN(long groupId, String name) {
		return findByG_LikeN(groupId, name, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<MSBPageTemplateFolder> findByG_LikeN(long groupId, String name,
		int start, int end) {
		return findByG_LikeN(groupId, name, start, end, null);
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
	@Override
	public List<MSBPageTemplateFolder> findByG_LikeN(long groupId, String name,
		int start, int end,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator) {
		return findByG_LikeN(groupId, name, start, end, orderByComparator, true);
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
	@Override
	public List<MSBPageTemplateFolder> findByG_LikeN(long groupId, String name,
		int start, int end,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_LIKEN;
		finderArgs = new Object[] { groupId, name, start, end, orderByComparator };

		List<MSBPageTemplateFolder> list = null;

		if (retrieveFromCache) {
			list = (List<MSBPageTemplateFolder>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MSBPageTemplateFolder msbPageTemplateFolder : list) {
					if ((groupId != msbPageTemplateFolder.getGroupId()) ||
							!StringUtil.wildcardMatches(
								msbPageTemplateFolder.getName(), name,
								CharPool.UNDERLINE, CharPool.PERCENT,
								CharPool.BACK_SLASH, false)) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_MSBPAGETEMPLATEFOLDER_WHERE);

			query.append(_FINDER_COLUMN_G_LIKEN_GROUPID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_G_LIKEN_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_LIKEN_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_G_LIKEN_NAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(MSBPageTemplateFolderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindName) {
					qPos.add(StringUtil.toLowerCase(name));
				}

				if (!pagination) {
					list = (List<MSBPageTemplateFolder>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MSBPageTemplateFolder>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public MSBPageTemplateFolder findByG_LikeN_First(long groupId, String name,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws NoSuchPageTemplateFolderException {
		MSBPageTemplateFolder msbPageTemplateFolder = fetchByG_LikeN_First(groupId,
				name, orderByComparator);

		if (msbPageTemplateFolder != null) {
			return msbPageTemplateFolder;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPageTemplateFolderException(msg.toString());
	}

	/**
	 * Returns the first msb page template folder in the ordered set where groupId = &#63; and name LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching msb page template folder, or <code>null</code> if a matching msb page template folder could not be found
	 */
	@Override
	public MSBPageTemplateFolder fetchByG_LikeN_First(long groupId,
		String name, OrderByComparator<MSBPageTemplateFolder> orderByComparator) {
		List<MSBPageTemplateFolder> list = findByG_LikeN(groupId, name, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public MSBPageTemplateFolder findByG_LikeN_Last(long groupId, String name,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws NoSuchPageTemplateFolderException {
		MSBPageTemplateFolder msbPageTemplateFolder = fetchByG_LikeN_Last(groupId,
				name, orderByComparator);

		if (msbPageTemplateFolder != null) {
			return msbPageTemplateFolder;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPageTemplateFolderException(msg.toString());
	}

	/**
	 * Returns the last msb page template folder in the ordered set where groupId = &#63; and name LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching msb page template folder, or <code>null</code> if a matching msb page template folder could not be found
	 */
	@Override
	public MSBPageTemplateFolder fetchByG_LikeN_Last(long groupId, String name,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator) {
		int count = countByG_LikeN(groupId, name);

		if (count == 0) {
			return null;
		}

		List<MSBPageTemplateFolder> list = findByG_LikeN(groupId, name,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public MSBPageTemplateFolder[] findByG_LikeN_PrevAndNext(
		long msbPageTemplateFolderId, long groupId, String name,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws NoSuchPageTemplateFolderException {
		MSBPageTemplateFolder msbPageTemplateFolder = findByPrimaryKey(msbPageTemplateFolderId);

		Session session = null;

		try {
			session = openSession();

			MSBPageTemplateFolder[] array = new MSBPageTemplateFolderImpl[3];

			array[0] = getByG_LikeN_PrevAndNext(session, msbPageTemplateFolder,
					groupId, name, orderByComparator, true);

			array[1] = msbPageTemplateFolder;

			array[2] = getByG_LikeN_PrevAndNext(session, msbPageTemplateFolder,
					groupId, name, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MSBPageTemplateFolder getByG_LikeN_PrevAndNext(Session session,
		MSBPageTemplateFolder msbPageTemplateFolder, long groupId, String name,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_MSBPAGETEMPLATEFOLDER_WHERE);

		query.append(_FINDER_COLUMN_G_LIKEN_GROUPID_2);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_G_LIKEN_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_LIKEN_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_G_LIKEN_NAME_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(MSBPageTemplateFolderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindName) {
			qPos.add(StringUtil.toLowerCase(name));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(msbPageTemplateFolder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MSBPageTemplateFolder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the msb page template folders that the user has permission to view where groupId = &#63; and name LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the matching msb page template folders that the user has permission to view
	 */
	@Override
	public List<MSBPageTemplateFolder> filterFindByG_LikeN(long groupId,
		String name) {
		return filterFindByG_LikeN(groupId, name, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<MSBPageTemplateFolder> filterFindByG_LikeN(long groupId,
		String name, int start, int end) {
		return filterFindByG_LikeN(groupId, name, start, end, null);
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
	@Override
	public List<MSBPageTemplateFolder> filterFindByG_LikeN(long groupId,
		String name, int start, int end,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_LikeN(groupId, name, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MSBPAGETEMPLATEFOLDER_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MSBPAGETEMPLATEFOLDER_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_LIKEN_GROUPID_2);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_G_LIKEN_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_LIKEN_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_G_LIKEN_NAME_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MSBPAGETEMPLATEFOLDER_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(MSBPageTemplateFolderModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(MSBPageTemplateFolderModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MSBPageTemplateFolder.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS,
					MSBPageTemplateFolderImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE,
					MSBPageTemplateFolderImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (bindName) {
				qPos.add(StringUtil.toLowerCase(name));
			}

			return (List<MSBPageTemplateFolder>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
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
	@Override
	public MSBPageTemplateFolder[] filterFindByG_LikeN_PrevAndNext(
		long msbPageTemplateFolderId, long groupId, String name,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator)
		throws NoSuchPageTemplateFolderException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_LikeN_PrevAndNext(msbPageTemplateFolderId, groupId,
				name, orderByComparator);
		}

		MSBPageTemplateFolder msbPageTemplateFolder = findByPrimaryKey(msbPageTemplateFolderId);

		Session session = null;

		try {
			session = openSession();

			MSBPageTemplateFolder[] array = new MSBPageTemplateFolderImpl[3];

			array[0] = filterGetByG_LikeN_PrevAndNext(session,
					msbPageTemplateFolder, groupId, name, orderByComparator,
					true);

			array[1] = msbPageTemplateFolder;

			array[2] = filterGetByG_LikeN_PrevAndNext(session,
					msbPageTemplateFolder, groupId, name, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MSBPageTemplateFolder filterGetByG_LikeN_PrevAndNext(
		Session session, MSBPageTemplateFolder msbPageTemplateFolder,
		long groupId, String name,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MSBPAGETEMPLATEFOLDER_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MSBPAGETEMPLATEFOLDER_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_LIKEN_GROUPID_2);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_G_LIKEN_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_LIKEN_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_G_LIKEN_NAME_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MSBPAGETEMPLATEFOLDER_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(MSBPageTemplateFolderModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(MSBPageTemplateFolderModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MSBPageTemplateFolder.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, MSBPageTemplateFolderImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, MSBPageTemplateFolderImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindName) {
			qPos.add(StringUtil.toLowerCase(name));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(msbPageTemplateFolder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MSBPageTemplateFolder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the msb page template folders where groupId = &#63; and name LIKE &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 */
	@Override
	public void removeByG_LikeN(long groupId, String name) {
		for (MSBPageTemplateFolder msbPageTemplateFolder : findByG_LikeN(
				groupId, name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(msbPageTemplateFolder);
		}
	}

	/**
	 * Returns the number of msb page template folders where groupId = &#63; and name LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the number of matching msb page template folders
	 */
	@Override
	public int countByG_LikeN(long groupId, String name) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_LIKEN;

		Object[] finderArgs = new Object[] { groupId, name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MSBPAGETEMPLATEFOLDER_WHERE);

			query.append(_FINDER_COLUMN_G_LIKEN_GROUPID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_G_LIKEN_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_LIKEN_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_G_LIKEN_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindName) {
					qPos.add(StringUtil.toLowerCase(name));
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of msb page template folders that the user has permission to view where groupId = &#63; and name LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the number of matching msb page template folders that the user has permission to view
	 */
	@Override
	public int filterCountByG_LikeN(long groupId, String name) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_LikeN(groupId, name);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_MSBPAGETEMPLATEFOLDER_WHERE);

		query.append(_FINDER_COLUMN_G_LIKEN_GROUPID_2);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_G_LIKEN_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_LIKEN_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_G_LIKEN_NAME_2);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MSBPageTemplateFolder.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (bindName) {
				qPos.add(StringUtil.toLowerCase(name));
			}

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_G_LIKEN_GROUPID_2 = "msbPageTemplateFolder.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_LIKEN_NAME_1 = "msbPageTemplateFolder.name IS NULL";
	private static final String _FINDER_COLUMN_G_LIKEN_NAME_2 = "lower(msbPageTemplateFolder.name) LIKE ?";
	private static final String _FINDER_COLUMN_G_LIKEN_NAME_3 = "(msbPageTemplateFolder.name IS NULL OR msbPageTemplateFolder.name LIKE '')";

	public MSBPageTemplateFolderPersistenceImpl() {
		setModelClass(MSBPageTemplateFolder.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the msb page template folder in the entity cache if it is enabled.
	 *
	 * @param msbPageTemplateFolder the msb page template folder
	 */
	@Override
	public void cacheResult(MSBPageTemplateFolder msbPageTemplateFolder) {
		entityCache.putResult(MSBPageTemplateFolderModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateFolderImpl.class,
			msbPageTemplateFolder.getPrimaryKey(), msbPageTemplateFolder);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				msbPageTemplateFolder.getUuid(),
				msbPageTemplateFolder.getGroupId()
			}, msbPageTemplateFolder);

		msbPageTemplateFolder.resetOriginalValues();
	}

	/**
	 * Caches the msb page template folders in the entity cache if it is enabled.
	 *
	 * @param msbPageTemplateFolders the msb page template folders
	 */
	@Override
	public void cacheResult(List<MSBPageTemplateFolder> msbPageTemplateFolders) {
		for (MSBPageTemplateFolder msbPageTemplateFolder : msbPageTemplateFolders) {
			if (entityCache.getResult(
						MSBPageTemplateFolderModelImpl.ENTITY_CACHE_ENABLED,
						MSBPageTemplateFolderImpl.class,
						msbPageTemplateFolder.getPrimaryKey()) == null) {
				cacheResult(msbPageTemplateFolder);
			}
			else {
				msbPageTemplateFolder.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all msb page template folders.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MSBPageTemplateFolderImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the msb page template folder.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MSBPageTemplateFolder msbPageTemplateFolder) {
		entityCache.removeResult(MSBPageTemplateFolderModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateFolderImpl.class,
			msbPageTemplateFolder.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((MSBPageTemplateFolderModelImpl)msbPageTemplateFolder,
			true);
	}

	@Override
	public void clearCache(List<MSBPageTemplateFolder> msbPageTemplateFolders) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MSBPageTemplateFolder msbPageTemplateFolder : msbPageTemplateFolders) {
			entityCache.removeResult(MSBPageTemplateFolderModelImpl.ENTITY_CACHE_ENABLED,
				MSBPageTemplateFolderImpl.class,
				msbPageTemplateFolder.getPrimaryKey());

			clearUniqueFindersCache((MSBPageTemplateFolderModelImpl)msbPageTemplateFolder,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		MSBPageTemplateFolderModelImpl msbPageTemplateFolderModelImpl) {
		Object[] args = new Object[] {
				msbPageTemplateFolderModelImpl.getUuid(),
				msbPageTemplateFolderModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			msbPageTemplateFolderModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		MSBPageTemplateFolderModelImpl msbPageTemplateFolderModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					msbPageTemplateFolderModelImpl.getUuid(),
					msbPageTemplateFolderModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((msbPageTemplateFolderModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					msbPageTemplateFolderModelImpl.getOriginalUuid(),
					msbPageTemplateFolderModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new msb page template folder with the primary key. Does not add the msb page template folder to the database.
	 *
	 * @param msbPageTemplateFolderId the primary key for the new msb page template folder
	 * @return the new msb page template folder
	 */
	@Override
	public MSBPageTemplateFolder create(long msbPageTemplateFolderId) {
		MSBPageTemplateFolder msbPageTemplateFolder = new MSBPageTemplateFolderImpl();

		msbPageTemplateFolder.setNew(true);
		msbPageTemplateFolder.setPrimaryKey(msbPageTemplateFolderId);

		String uuid = PortalUUIDUtil.generate();

		msbPageTemplateFolder.setUuid(uuid);

		msbPageTemplateFolder.setCompanyId(companyProvider.getCompanyId());

		return msbPageTemplateFolder;
	}

	/**
	 * Removes the msb page template folder with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param msbPageTemplateFolderId the primary key of the msb page template folder
	 * @return the msb page template folder that was removed
	 * @throws NoSuchPageTemplateFolderException if a msb page template folder with the primary key could not be found
	 */
	@Override
	public MSBPageTemplateFolder remove(long msbPageTemplateFolderId)
		throws NoSuchPageTemplateFolderException {
		return remove((Serializable)msbPageTemplateFolderId);
	}

	/**
	 * Removes the msb page template folder with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the msb page template folder
	 * @return the msb page template folder that was removed
	 * @throws NoSuchPageTemplateFolderException if a msb page template folder with the primary key could not be found
	 */
	@Override
	public MSBPageTemplateFolder remove(Serializable primaryKey)
		throws NoSuchPageTemplateFolderException {
		Session session = null;

		try {
			session = openSession();

			MSBPageTemplateFolder msbPageTemplateFolder = (MSBPageTemplateFolder)session.get(MSBPageTemplateFolderImpl.class,
					primaryKey);

			if (msbPageTemplateFolder == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPageTemplateFolderException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(msbPageTemplateFolder);
		}
		catch (NoSuchPageTemplateFolderException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected MSBPageTemplateFolder removeImpl(
		MSBPageTemplateFolder msbPageTemplateFolder) {
		msbPageTemplateFolder = toUnwrappedModel(msbPageTemplateFolder);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(msbPageTemplateFolder)) {
				msbPageTemplateFolder = (MSBPageTemplateFolder)session.get(MSBPageTemplateFolderImpl.class,
						msbPageTemplateFolder.getPrimaryKeyObj());
			}

			if (msbPageTemplateFolder != null) {
				session.delete(msbPageTemplateFolder);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (msbPageTemplateFolder != null) {
			clearCache(msbPageTemplateFolder);
		}

		return msbPageTemplateFolder;
	}

	@Override
	public MSBPageTemplateFolder updateImpl(
		MSBPageTemplateFolder msbPageTemplateFolder) {
		msbPageTemplateFolder = toUnwrappedModel(msbPageTemplateFolder);

		boolean isNew = msbPageTemplateFolder.isNew();

		MSBPageTemplateFolderModelImpl msbPageTemplateFolderModelImpl = (MSBPageTemplateFolderModelImpl)msbPageTemplateFolder;

		if (Validator.isNull(msbPageTemplateFolder.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			msbPageTemplateFolder.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (msbPageTemplateFolder.getCreateDate() == null)) {
			if (serviceContext == null) {
				msbPageTemplateFolder.setCreateDate(now);
			}
			else {
				msbPageTemplateFolder.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!msbPageTemplateFolderModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				msbPageTemplateFolder.setModifiedDate(now);
			}
			else {
				msbPageTemplateFolder.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (msbPageTemplateFolder.isNew()) {
				session.save(msbPageTemplateFolder);

				msbPageTemplateFolder.setNew(false);
			}
			else {
				msbPageTemplateFolder = (MSBPageTemplateFolder)session.merge(msbPageTemplateFolder);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!MSBPageTemplateFolderModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					msbPageTemplateFolderModelImpl.getUuid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					msbPageTemplateFolderModelImpl.getUuid(),
					msbPageTemplateFolderModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { msbPageTemplateFolderModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((msbPageTemplateFolderModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						msbPageTemplateFolderModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { msbPageTemplateFolderModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((msbPageTemplateFolderModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						msbPageTemplateFolderModelImpl.getOriginalUuid(),
						msbPageTemplateFolderModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						msbPageTemplateFolderModelImpl.getUuid(),
						msbPageTemplateFolderModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((msbPageTemplateFolderModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						msbPageTemplateFolderModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { msbPageTemplateFolderModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}
		}

		entityCache.putResult(MSBPageTemplateFolderModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateFolderImpl.class,
			msbPageTemplateFolder.getPrimaryKey(), msbPageTemplateFolder, false);

		clearUniqueFindersCache(msbPageTemplateFolderModelImpl, false);
		cacheUniqueFindersCache(msbPageTemplateFolderModelImpl);

		msbPageTemplateFolder.resetOriginalValues();

		return msbPageTemplateFolder;
	}

	protected MSBPageTemplateFolder toUnwrappedModel(
		MSBPageTemplateFolder msbPageTemplateFolder) {
		if (msbPageTemplateFolder instanceof MSBPageTemplateFolderImpl) {
			return msbPageTemplateFolder;
		}

		MSBPageTemplateFolderImpl msbPageTemplateFolderImpl = new MSBPageTemplateFolderImpl();

		msbPageTemplateFolderImpl.setNew(msbPageTemplateFolder.isNew());
		msbPageTemplateFolderImpl.setPrimaryKey(msbPageTemplateFolder.getPrimaryKey());

		msbPageTemplateFolderImpl.setUuid(msbPageTemplateFolder.getUuid());
		msbPageTemplateFolderImpl.setMsbPageTemplateFolderId(msbPageTemplateFolder.getMsbPageTemplateFolderId());
		msbPageTemplateFolderImpl.setGroupId(msbPageTemplateFolder.getGroupId());
		msbPageTemplateFolderImpl.setCompanyId(msbPageTemplateFolder.getCompanyId());
		msbPageTemplateFolderImpl.setUserId(msbPageTemplateFolder.getUserId());
		msbPageTemplateFolderImpl.setUserName(msbPageTemplateFolder.getUserName());
		msbPageTemplateFolderImpl.setCreateDate(msbPageTemplateFolder.getCreateDate());
		msbPageTemplateFolderImpl.setModifiedDate(msbPageTemplateFolder.getModifiedDate());
		msbPageTemplateFolderImpl.setName(msbPageTemplateFolder.getName());
		msbPageTemplateFolderImpl.setDescription(msbPageTemplateFolder.getDescription());

		return msbPageTemplateFolderImpl;
	}

	/**
	 * Returns the msb page template folder with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the msb page template folder
	 * @return the msb page template folder
	 * @throws NoSuchPageTemplateFolderException if a msb page template folder with the primary key could not be found
	 */
	@Override
	public MSBPageTemplateFolder findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPageTemplateFolderException {
		MSBPageTemplateFolder msbPageTemplateFolder = fetchByPrimaryKey(primaryKey);

		if (msbPageTemplateFolder == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPageTemplateFolderException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return msbPageTemplateFolder;
	}

	/**
	 * Returns the msb page template folder with the primary key or throws a {@link NoSuchPageTemplateFolderException} if it could not be found.
	 *
	 * @param msbPageTemplateFolderId the primary key of the msb page template folder
	 * @return the msb page template folder
	 * @throws NoSuchPageTemplateFolderException if a msb page template folder with the primary key could not be found
	 */
	@Override
	public MSBPageTemplateFolder findByPrimaryKey(long msbPageTemplateFolderId)
		throws NoSuchPageTemplateFolderException {
		return findByPrimaryKey((Serializable)msbPageTemplateFolderId);
	}

	/**
	 * Returns the msb page template folder with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the msb page template folder
	 * @return the msb page template folder, or <code>null</code> if a msb page template folder with the primary key could not be found
	 */
	@Override
	public MSBPageTemplateFolder fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(MSBPageTemplateFolderModelImpl.ENTITY_CACHE_ENABLED,
				MSBPageTemplateFolderImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		MSBPageTemplateFolder msbPageTemplateFolder = (MSBPageTemplateFolder)serializable;

		if (msbPageTemplateFolder == null) {
			Session session = null;

			try {
				session = openSession();

				msbPageTemplateFolder = (MSBPageTemplateFolder)session.get(MSBPageTemplateFolderImpl.class,
						primaryKey);

				if (msbPageTemplateFolder != null) {
					cacheResult(msbPageTemplateFolder);
				}
				else {
					entityCache.putResult(MSBPageTemplateFolderModelImpl.ENTITY_CACHE_ENABLED,
						MSBPageTemplateFolderImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(MSBPageTemplateFolderModelImpl.ENTITY_CACHE_ENABLED,
					MSBPageTemplateFolderImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return msbPageTemplateFolder;
	}

	/**
	 * Returns the msb page template folder with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param msbPageTemplateFolderId the primary key of the msb page template folder
	 * @return the msb page template folder, or <code>null</code> if a msb page template folder with the primary key could not be found
	 */
	@Override
	public MSBPageTemplateFolder fetchByPrimaryKey(long msbPageTemplateFolderId) {
		return fetchByPrimaryKey((Serializable)msbPageTemplateFolderId);
	}

	@Override
	public Map<Serializable, MSBPageTemplateFolder> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, MSBPageTemplateFolder> map = new HashMap<Serializable, MSBPageTemplateFolder>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			MSBPageTemplateFolder msbPageTemplateFolder = fetchByPrimaryKey(primaryKey);

			if (msbPageTemplateFolder != null) {
				map.put(primaryKey, msbPageTemplateFolder);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(MSBPageTemplateFolderModelImpl.ENTITY_CACHE_ENABLED,
					MSBPageTemplateFolderImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (MSBPageTemplateFolder)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_MSBPAGETEMPLATEFOLDER_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (MSBPageTemplateFolder msbPageTemplateFolder : (List<MSBPageTemplateFolder>)q.list()) {
				map.put(msbPageTemplateFolder.getPrimaryKeyObj(),
					msbPageTemplateFolder);

				cacheResult(msbPageTemplateFolder);

				uncachedPrimaryKeys.remove(msbPageTemplateFolder.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(MSBPageTemplateFolderModelImpl.ENTITY_CACHE_ENABLED,
					MSBPageTemplateFolderImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the msb page template folders.
	 *
	 * @return the msb page template folders
	 */
	@Override
	public List<MSBPageTemplateFolder> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<MSBPageTemplateFolder> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<MSBPageTemplateFolder> findAll(int start, int end,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<MSBPageTemplateFolder> findAll(int start, int end,
		OrderByComparator<MSBPageTemplateFolder> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<MSBPageTemplateFolder> list = null;

		if (retrieveFromCache) {
			list = (List<MSBPageTemplateFolder>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MSBPAGETEMPLATEFOLDER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MSBPAGETEMPLATEFOLDER;

				if (pagination) {
					sql = sql.concat(MSBPageTemplateFolderModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MSBPageTemplateFolder>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MSBPageTemplateFolder>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the msb page template folders from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MSBPageTemplateFolder msbPageTemplateFolder : findAll()) {
			remove(msbPageTemplateFolder);
		}
	}

	/**
	 * Returns the number of msb page template folders.
	 *
	 * @return the number of msb page template folders
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MSBPAGETEMPLATEFOLDER);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return MSBPageTemplateFolderModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the msb page template folder persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(MSBPageTemplateFolderImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_MSBPAGETEMPLATEFOLDER = "SELECT msbPageTemplateFolder FROM MSBPageTemplateFolder msbPageTemplateFolder";
	private static final String _SQL_SELECT_MSBPAGETEMPLATEFOLDER_WHERE_PKS_IN = "SELECT msbPageTemplateFolder FROM MSBPageTemplateFolder msbPageTemplateFolder WHERE msbPageTemplateFolderId IN (";
	private static final String _SQL_SELECT_MSBPAGETEMPLATEFOLDER_WHERE = "SELECT msbPageTemplateFolder FROM MSBPageTemplateFolder msbPageTemplateFolder WHERE ";
	private static final String _SQL_COUNT_MSBPAGETEMPLATEFOLDER = "SELECT COUNT(msbPageTemplateFolder) FROM MSBPageTemplateFolder msbPageTemplateFolder";
	private static final String _SQL_COUNT_MSBPAGETEMPLATEFOLDER_WHERE = "SELECT COUNT(msbPageTemplateFolder) FROM MSBPageTemplateFolder msbPageTemplateFolder WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "msbPageTemplateFolder.msbPageTemplateFolderId";
	private static final String _FILTER_SQL_SELECT_MSBPAGETEMPLATEFOLDER_WHERE = "SELECT DISTINCT {msbPageTemplateFolder.*} FROM MSBPageTemplateFolder msbPageTemplateFolder WHERE ";
	private static final String _FILTER_SQL_SELECT_MSBPAGETEMPLATEFOLDER_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {MSBPageTemplateFolder.*} FROM (SELECT DISTINCT msbPageTemplateFolder.msbPageTemplateFolderId FROM MSBPageTemplateFolder msbPageTemplateFolder WHERE ";
	private static final String _FILTER_SQL_SELECT_MSBPAGETEMPLATEFOLDER_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN MSBPageTemplateFolder ON TEMP_TABLE.msbPageTemplateFolderId = MSBPageTemplateFolder.msbPageTemplateFolderId";
	private static final String _FILTER_SQL_COUNT_MSBPAGETEMPLATEFOLDER_WHERE = "SELECT COUNT(DISTINCT msbPageTemplateFolder.msbPageTemplateFolderId) AS COUNT_VALUE FROM MSBPageTemplateFolder msbPageTemplateFolder WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "msbPageTemplateFolder";
	private static final String _FILTER_ENTITY_TABLE = "MSBPageTemplateFolder";
	private static final String _ORDER_BY_ENTITY_ALIAS = "msbPageTemplateFolder.";
	private static final String _ORDER_BY_ENTITY_TABLE = "MSBPageTemplateFolder.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MSBPageTemplateFolder exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MSBPageTemplateFolder exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(MSBPageTemplateFolderPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}