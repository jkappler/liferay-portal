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

import com.liferay.modern.site.building.page.exception.NoSuchPageTemplateException;
import com.liferay.modern.site.building.page.model.MSBPageTemplate;
import com.liferay.modern.site.building.page.model.impl.MSBPageTemplateImpl;
import com.liferay.modern.site.building.page.model.impl.MSBPageTemplateModelImpl;
import com.liferay.modern.site.building.page.service.persistence.MSBPageTemplatePersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
 * The persistence implementation for the msb page template service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MSBPageTemplatePersistence
 * @see com.liferay.modern.site.building.page.service.persistence.MSBPageTemplateUtil
 * @generated
 */
@ProviderType
public class MSBPageTemplatePersistenceImpl extends BasePersistenceImpl<MSBPageTemplate>
	implements MSBPageTemplatePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MSBPageTemplateUtil} to access the msb page template persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MSBPageTemplateImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MSBPageTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateModelImpl.FINDER_CACHE_ENABLED,
			MSBPageTemplateImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MSBPageTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateModelImpl.FINDER_CACHE_ENABLED,
			MSBPageTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MSBPageTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(MSBPageTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateModelImpl.FINDER_CACHE_ENABLED,
			MSBPageTemplateImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(MSBPageTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateModelImpl.FINDER_CACHE_ENABLED,
			MSBPageTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			MSBPageTemplateModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(MSBPageTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the msb page templates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching msb page templates
	 */
	@Override
	public List<MSBPageTemplate> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<MSBPageTemplate> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
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
	@Override
	public List<MSBPageTemplate> findByUuid(String uuid, int start, int end,
		OrderByComparator<MSBPageTemplate> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
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
	@Override
	public List<MSBPageTemplate> findByUuid(String uuid, int start, int end,
		OrderByComparator<MSBPageTemplate> orderByComparator,
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

		List<MSBPageTemplate> list = null;

		if (retrieveFromCache) {
			list = (List<MSBPageTemplate>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MSBPageTemplate msbPageTemplate : list) {
					if (!Objects.equals(uuid, msbPageTemplate.getUuid())) {
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

			query.append(_SQL_SELECT_MSBPAGETEMPLATE_WHERE);

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
				query.append(MSBPageTemplateModelImpl.ORDER_BY_JPQL);
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
					list = (List<MSBPageTemplate>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MSBPageTemplate>)QueryUtil.list(q,
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
	 * Returns the first msb page template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching msb page template
	 * @throws NoSuchPageTemplateException if a matching msb page template could not be found
	 */
	@Override
	public MSBPageTemplate findByUuid_First(String uuid,
		OrderByComparator<MSBPageTemplate> orderByComparator)
		throws NoSuchPageTemplateException {
		MSBPageTemplate msbPageTemplate = fetchByUuid_First(uuid,
				orderByComparator);

		if (msbPageTemplate != null) {
			return msbPageTemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPageTemplateException(msg.toString());
	}

	/**
	 * Returns the first msb page template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching msb page template, or <code>null</code> if a matching msb page template could not be found
	 */
	@Override
	public MSBPageTemplate fetchByUuid_First(String uuid,
		OrderByComparator<MSBPageTemplate> orderByComparator) {
		List<MSBPageTemplate> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last msb page template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching msb page template
	 * @throws NoSuchPageTemplateException if a matching msb page template could not be found
	 */
	@Override
	public MSBPageTemplate findByUuid_Last(String uuid,
		OrderByComparator<MSBPageTemplate> orderByComparator)
		throws NoSuchPageTemplateException {
		MSBPageTemplate msbPageTemplate = fetchByUuid_Last(uuid,
				orderByComparator);

		if (msbPageTemplate != null) {
			return msbPageTemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPageTemplateException(msg.toString());
	}

	/**
	 * Returns the last msb page template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching msb page template, or <code>null</code> if a matching msb page template could not be found
	 */
	@Override
	public MSBPageTemplate fetchByUuid_Last(String uuid,
		OrderByComparator<MSBPageTemplate> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<MSBPageTemplate> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public MSBPageTemplate[] findByUuid_PrevAndNext(long msbPageTemplateId,
		String uuid, OrderByComparator<MSBPageTemplate> orderByComparator)
		throws NoSuchPageTemplateException {
		MSBPageTemplate msbPageTemplate = findByPrimaryKey(msbPageTemplateId);

		Session session = null;

		try {
			session = openSession();

			MSBPageTemplate[] array = new MSBPageTemplateImpl[3];

			array[0] = getByUuid_PrevAndNext(session, msbPageTemplate, uuid,
					orderByComparator, true);

			array[1] = msbPageTemplate;

			array[2] = getByUuid_PrevAndNext(session, msbPageTemplate, uuid,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MSBPageTemplate getByUuid_PrevAndNext(Session session,
		MSBPageTemplate msbPageTemplate, String uuid,
		OrderByComparator<MSBPageTemplate> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MSBPAGETEMPLATE_WHERE);

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
			query.append(MSBPageTemplateModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(msbPageTemplate);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MSBPageTemplate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the msb page templates where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (MSBPageTemplate msbPageTemplate : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(msbPageTemplate);
		}
	}

	/**
	 * Returns the number of msb page templates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching msb page templates
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MSBPAGETEMPLATE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "msbPageTemplate.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "msbPageTemplate.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(msbPageTemplate.uuid IS NULL OR msbPageTemplate.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(MSBPageTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateModelImpl.FINDER_CACHE_ENABLED,
			MSBPageTemplateImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			MSBPageTemplateModelImpl.UUID_COLUMN_BITMASK |
			MSBPageTemplateModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(MSBPageTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the msb page template where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPageTemplateException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching msb page template
	 * @throws NoSuchPageTemplateException if a matching msb page template could not be found
	 */
	@Override
	public MSBPageTemplate findByUUID_G(String uuid, long groupId)
		throws NoSuchPageTemplateException {
		MSBPageTemplate msbPageTemplate = fetchByUUID_G(uuid, groupId);

		if (msbPageTemplate == null) {
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

			throw new NoSuchPageTemplateException(msg.toString());
		}

		return msbPageTemplate;
	}

	/**
	 * Returns the msb page template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching msb page template, or <code>null</code> if a matching msb page template could not be found
	 */
	@Override
	public MSBPageTemplate fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the msb page template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching msb page template, or <code>null</code> if a matching msb page template could not be found
	 */
	@Override
	public MSBPageTemplate fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof MSBPageTemplate) {
			MSBPageTemplate msbPageTemplate = (MSBPageTemplate)result;

			if (!Objects.equals(uuid, msbPageTemplate.getUuid()) ||
					(groupId != msbPageTemplate.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_MSBPAGETEMPLATE_WHERE);

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

				List<MSBPageTemplate> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					MSBPageTemplate msbPageTemplate = list.get(0);

					result = msbPageTemplate;

					cacheResult(msbPageTemplate);

					if ((msbPageTemplate.getUuid() == null) ||
							!msbPageTemplate.getUuid().equals(uuid) ||
							(msbPageTemplate.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, msbPageTemplate);
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
			return (MSBPageTemplate)result;
		}
	}

	/**
	 * Removes the msb page template where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the msb page template that was removed
	 */
	@Override
	public MSBPageTemplate removeByUUID_G(String uuid, long groupId)
		throws NoSuchPageTemplateException {
		MSBPageTemplate msbPageTemplate = findByUUID_G(uuid, groupId);

		return remove(msbPageTemplate);
	}

	/**
	 * Returns the number of msb page templates where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching msb page templates
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MSBPAGETEMPLATE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "msbPageTemplate.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "msbPageTemplate.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(msbPageTemplate.uuid IS NULL OR msbPageTemplate.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "msbPageTemplate.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(MSBPageTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateModelImpl.FINDER_CACHE_ENABLED,
			MSBPageTemplateImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(MSBPageTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateModelImpl.FINDER_CACHE_ENABLED,
			MSBPageTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			MSBPageTemplateModelImpl.UUID_COLUMN_BITMASK |
			MSBPageTemplateModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(MSBPageTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the msb page templates where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching msb page templates
	 */
	@Override
	public List<MSBPageTemplate> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<MSBPageTemplate> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
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
	@Override
	public List<MSBPageTemplate> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<MSBPageTemplate> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
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
	@Override
	public List<MSBPageTemplate> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<MSBPageTemplate> orderByComparator,
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

		List<MSBPageTemplate> list = null;

		if (retrieveFromCache) {
			list = (List<MSBPageTemplate>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MSBPageTemplate msbPageTemplate : list) {
					if (!Objects.equals(uuid, msbPageTemplate.getUuid()) ||
							(companyId != msbPageTemplate.getCompanyId())) {
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

			query.append(_SQL_SELECT_MSBPAGETEMPLATE_WHERE);

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
				query.append(MSBPageTemplateModelImpl.ORDER_BY_JPQL);
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
					list = (List<MSBPageTemplate>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MSBPageTemplate>)QueryUtil.list(q,
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
	 * Returns the first msb page template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching msb page template
	 * @throws NoSuchPageTemplateException if a matching msb page template could not be found
	 */
	@Override
	public MSBPageTemplate findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<MSBPageTemplate> orderByComparator)
		throws NoSuchPageTemplateException {
		MSBPageTemplate msbPageTemplate = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (msbPageTemplate != null) {
			return msbPageTemplate;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPageTemplateException(msg.toString());
	}

	/**
	 * Returns the first msb page template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching msb page template, or <code>null</code> if a matching msb page template could not be found
	 */
	@Override
	public MSBPageTemplate fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<MSBPageTemplate> orderByComparator) {
		List<MSBPageTemplate> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public MSBPageTemplate findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<MSBPageTemplate> orderByComparator)
		throws NoSuchPageTemplateException {
		MSBPageTemplate msbPageTemplate = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (msbPageTemplate != null) {
			return msbPageTemplate;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPageTemplateException(msg.toString());
	}

	/**
	 * Returns the last msb page template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching msb page template, or <code>null</code> if a matching msb page template could not be found
	 */
	@Override
	public MSBPageTemplate fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<MSBPageTemplate> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<MSBPageTemplate> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public MSBPageTemplate[] findByUuid_C_PrevAndNext(long msbPageTemplateId,
		String uuid, long companyId,
		OrderByComparator<MSBPageTemplate> orderByComparator)
		throws NoSuchPageTemplateException {
		MSBPageTemplate msbPageTemplate = findByPrimaryKey(msbPageTemplateId);

		Session session = null;

		try {
			session = openSession();

			MSBPageTemplate[] array = new MSBPageTemplateImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, msbPageTemplate, uuid,
					companyId, orderByComparator, true);

			array[1] = msbPageTemplate;

			array[2] = getByUuid_C_PrevAndNext(session, msbPageTemplate, uuid,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MSBPageTemplate getByUuid_C_PrevAndNext(Session session,
		MSBPageTemplate msbPageTemplate, String uuid, long companyId,
		OrderByComparator<MSBPageTemplate> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_MSBPAGETEMPLATE_WHERE);

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
			query.append(MSBPageTemplateModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(msbPageTemplate);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MSBPageTemplate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the msb page templates where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (MSBPageTemplate msbPageTemplate : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(msbPageTemplate);
		}
	}

	/**
	 * Returns the number of msb page templates where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching msb page templates
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MSBPAGETEMPLATE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "msbPageTemplate.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "msbPageTemplate.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(msbPageTemplate.uuid IS NULL OR msbPageTemplate.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "msbPageTemplate.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MSBPAGETEMPLATEFOLDERID =
		new FinderPath(MSBPageTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateModelImpl.FINDER_CACHE_ENABLED,
			MSBPageTemplateImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByMSBPageTemplateFolderId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MSBPAGETEMPLATEFOLDERID =
		new FinderPath(MSBPageTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateModelImpl.FINDER_CACHE_ENABLED,
			MSBPageTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByMSBPageTemplateFolderId",
			new String[] { Long.class.getName() },
			MSBPageTemplateModelImpl.MSBPAGETEMPLATEFOLDERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MSBPAGETEMPLATEFOLDERID = new FinderPath(MSBPageTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByMSBPageTemplateFolderId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the msb page templates where msbPageTemplateFolderId = &#63;.
	 *
	 * @param msbPageTemplateFolderId the msb page template folder ID
	 * @return the matching msb page templates
	 */
	@Override
	public List<MSBPageTemplate> findByMSBPageTemplateFolderId(
		long msbPageTemplateFolderId) {
		return findByMSBPageTemplateFolderId(msbPageTemplateFolderId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<MSBPageTemplate> findByMSBPageTemplateFolderId(
		long msbPageTemplateFolderId, int start, int end) {
		return findByMSBPageTemplateFolderId(msbPageTemplateFolderId, start,
			end, null);
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
	@Override
	public List<MSBPageTemplate> findByMSBPageTemplateFolderId(
		long msbPageTemplateFolderId, int start, int end,
		OrderByComparator<MSBPageTemplate> orderByComparator) {
		return findByMSBPageTemplateFolderId(msbPageTemplateFolderId, start,
			end, orderByComparator, true);
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
	@Override
	public List<MSBPageTemplate> findByMSBPageTemplateFolderId(
		long msbPageTemplateFolderId, int start, int end,
		OrderByComparator<MSBPageTemplate> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MSBPAGETEMPLATEFOLDERID;
			finderArgs = new Object[] { msbPageTemplateFolderId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MSBPAGETEMPLATEFOLDERID;
			finderArgs = new Object[] {
					msbPageTemplateFolderId,
					
					start, end, orderByComparator
				};
		}

		List<MSBPageTemplate> list = null;

		if (retrieveFromCache) {
			list = (List<MSBPageTemplate>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MSBPageTemplate msbPageTemplate : list) {
					if ((msbPageTemplateFolderId != msbPageTemplate.getMsbPageTemplateFolderId())) {
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

			query.append(_SQL_SELECT_MSBPAGETEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_MSBPAGETEMPLATEFOLDERID_MSBPAGETEMPLATEFOLDERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(MSBPageTemplateModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(msbPageTemplateFolderId);

				if (!pagination) {
					list = (List<MSBPageTemplate>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MSBPageTemplate>)QueryUtil.list(q,
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
	 * Returns the first msb page template in the ordered set where msbPageTemplateFolderId = &#63;.
	 *
	 * @param msbPageTemplateFolderId the msb page template folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching msb page template
	 * @throws NoSuchPageTemplateException if a matching msb page template could not be found
	 */
	@Override
	public MSBPageTemplate findByMSBPageTemplateFolderId_First(
		long msbPageTemplateFolderId,
		OrderByComparator<MSBPageTemplate> orderByComparator)
		throws NoSuchPageTemplateException {
		MSBPageTemplate msbPageTemplate = fetchByMSBPageTemplateFolderId_First(msbPageTemplateFolderId,
				orderByComparator);

		if (msbPageTemplate != null) {
			return msbPageTemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("msbPageTemplateFolderId=");
		msg.append(msbPageTemplateFolderId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPageTemplateException(msg.toString());
	}

	/**
	 * Returns the first msb page template in the ordered set where msbPageTemplateFolderId = &#63;.
	 *
	 * @param msbPageTemplateFolderId the msb page template folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching msb page template, or <code>null</code> if a matching msb page template could not be found
	 */
	@Override
	public MSBPageTemplate fetchByMSBPageTemplateFolderId_First(
		long msbPageTemplateFolderId,
		OrderByComparator<MSBPageTemplate> orderByComparator) {
		List<MSBPageTemplate> list = findByMSBPageTemplateFolderId(msbPageTemplateFolderId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last msb page template in the ordered set where msbPageTemplateFolderId = &#63;.
	 *
	 * @param msbPageTemplateFolderId the msb page template folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching msb page template
	 * @throws NoSuchPageTemplateException if a matching msb page template could not be found
	 */
	@Override
	public MSBPageTemplate findByMSBPageTemplateFolderId_Last(
		long msbPageTemplateFolderId,
		OrderByComparator<MSBPageTemplate> orderByComparator)
		throws NoSuchPageTemplateException {
		MSBPageTemplate msbPageTemplate = fetchByMSBPageTemplateFolderId_Last(msbPageTemplateFolderId,
				orderByComparator);

		if (msbPageTemplate != null) {
			return msbPageTemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("msbPageTemplateFolderId=");
		msg.append(msbPageTemplateFolderId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPageTemplateException(msg.toString());
	}

	/**
	 * Returns the last msb page template in the ordered set where msbPageTemplateFolderId = &#63;.
	 *
	 * @param msbPageTemplateFolderId the msb page template folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching msb page template, or <code>null</code> if a matching msb page template could not be found
	 */
	@Override
	public MSBPageTemplate fetchByMSBPageTemplateFolderId_Last(
		long msbPageTemplateFolderId,
		OrderByComparator<MSBPageTemplate> orderByComparator) {
		int count = countByMSBPageTemplateFolderId(msbPageTemplateFolderId);

		if (count == 0) {
			return null;
		}

		List<MSBPageTemplate> list = findByMSBPageTemplateFolderId(msbPageTemplateFolderId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public MSBPageTemplate[] findByMSBPageTemplateFolderId_PrevAndNext(
		long msbPageTemplateId, long msbPageTemplateFolderId,
		OrderByComparator<MSBPageTemplate> orderByComparator)
		throws NoSuchPageTemplateException {
		MSBPageTemplate msbPageTemplate = findByPrimaryKey(msbPageTemplateId);

		Session session = null;

		try {
			session = openSession();

			MSBPageTemplate[] array = new MSBPageTemplateImpl[3];

			array[0] = getByMSBPageTemplateFolderId_PrevAndNext(session,
					msbPageTemplate, msbPageTemplateFolderId,
					orderByComparator, true);

			array[1] = msbPageTemplate;

			array[2] = getByMSBPageTemplateFolderId_PrevAndNext(session,
					msbPageTemplate, msbPageTemplateFolderId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MSBPageTemplate getByMSBPageTemplateFolderId_PrevAndNext(
		Session session, MSBPageTemplate msbPageTemplate,
		long msbPageTemplateFolderId,
		OrderByComparator<MSBPageTemplate> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MSBPAGETEMPLATE_WHERE);

		query.append(_FINDER_COLUMN_MSBPAGETEMPLATEFOLDERID_MSBPAGETEMPLATEFOLDERID_2);

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
			query.append(MSBPageTemplateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(msbPageTemplateFolderId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(msbPageTemplate);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MSBPageTemplate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the msb page templates where msbPageTemplateFolderId = &#63; from the database.
	 *
	 * @param msbPageTemplateFolderId the msb page template folder ID
	 */
	@Override
	public void removeByMSBPageTemplateFolderId(long msbPageTemplateFolderId) {
		for (MSBPageTemplate msbPageTemplate : findByMSBPageTemplateFolderId(
				msbPageTemplateFolderId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(msbPageTemplate);
		}
	}

	/**
	 * Returns the number of msb page templates where msbPageTemplateFolderId = &#63;.
	 *
	 * @param msbPageTemplateFolderId the msb page template folder ID
	 * @return the number of matching msb page templates
	 */
	@Override
	public int countByMSBPageTemplateFolderId(long msbPageTemplateFolderId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_MSBPAGETEMPLATEFOLDERID;

		Object[] finderArgs = new Object[] { msbPageTemplateFolderId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MSBPAGETEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_MSBPAGETEMPLATEFOLDERID_MSBPAGETEMPLATEFOLDERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(msbPageTemplateFolderId);

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

	private static final String _FINDER_COLUMN_MSBPAGETEMPLATEFOLDERID_MSBPAGETEMPLATEFOLDERID_2 =
		"msbPageTemplate.msbPageTemplateFolderId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LIKEN_P = new FinderPath(MSBPageTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateModelImpl.FINDER_CACHE_ENABLED,
			MSBPageTemplateImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByLikeN_P",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_LIKEN_P = new FinderPath(MSBPageTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByLikeN_P",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the msb page templates where name LIKE &#63; and msbPageTemplateFolderId = &#63;.
	 *
	 * @param name the name
	 * @param msbPageTemplateFolderId the msb page template folder ID
	 * @return the matching msb page templates
	 */
	@Override
	public List<MSBPageTemplate> findByLikeN_P(String name,
		long msbPageTemplateFolderId) {
		return findByLikeN_P(name, msbPageTemplateFolderId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<MSBPageTemplate> findByLikeN_P(String name,
		long msbPageTemplateFolderId, int start, int end) {
		return findByLikeN_P(name, msbPageTemplateFolderId, start, end, null);
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
	@Override
	public List<MSBPageTemplate> findByLikeN_P(String name,
		long msbPageTemplateFolderId, int start, int end,
		OrderByComparator<MSBPageTemplate> orderByComparator) {
		return findByLikeN_P(name, msbPageTemplateFolderId, start, end,
			orderByComparator, true);
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
	@Override
	public List<MSBPageTemplate> findByLikeN_P(String name,
		long msbPageTemplateFolderId, int start, int end,
		OrderByComparator<MSBPageTemplate> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LIKEN_P;
		finderArgs = new Object[] {
				name, msbPageTemplateFolderId,
				
				start, end, orderByComparator
			};

		List<MSBPageTemplate> list = null;

		if (retrieveFromCache) {
			list = (List<MSBPageTemplate>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MSBPageTemplate msbPageTemplate : list) {
					if (!StringUtil.wildcardMatches(msbPageTemplate.getName(),
								name, CharPool.UNDERLINE, CharPool.PERCENT,
								CharPool.BACK_SLASH, false) ||
							(msbPageTemplateFolderId != msbPageTemplate.getMsbPageTemplateFolderId())) {
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

			query.append(_SQL_SELECT_MSBPAGETEMPLATE_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_LIKEN_P_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LIKEN_P_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_LIKEN_P_NAME_2);
			}

			query.append(_FINDER_COLUMN_LIKEN_P_MSBPAGETEMPLATEFOLDERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(MSBPageTemplateModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(StringUtil.toLowerCase(name));
				}

				qPos.add(msbPageTemplateFolderId);

				if (!pagination) {
					list = (List<MSBPageTemplate>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MSBPageTemplate>)QueryUtil.list(q,
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
	 * Returns the first msb page template in the ordered set where name LIKE &#63; and msbPageTemplateFolderId = &#63;.
	 *
	 * @param name the name
	 * @param msbPageTemplateFolderId the msb page template folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching msb page template
	 * @throws NoSuchPageTemplateException if a matching msb page template could not be found
	 */
	@Override
	public MSBPageTemplate findByLikeN_P_First(String name,
		long msbPageTemplateFolderId,
		OrderByComparator<MSBPageTemplate> orderByComparator)
		throws NoSuchPageTemplateException {
		MSBPageTemplate msbPageTemplate = fetchByLikeN_P_First(name,
				msbPageTemplateFolderId, orderByComparator);

		if (msbPageTemplate != null) {
			return msbPageTemplate;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(", msbPageTemplateFolderId=");
		msg.append(msbPageTemplateFolderId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPageTemplateException(msg.toString());
	}

	/**
	 * Returns the first msb page template in the ordered set where name LIKE &#63; and msbPageTemplateFolderId = &#63;.
	 *
	 * @param name the name
	 * @param msbPageTemplateFolderId the msb page template folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching msb page template, or <code>null</code> if a matching msb page template could not be found
	 */
	@Override
	public MSBPageTemplate fetchByLikeN_P_First(String name,
		long msbPageTemplateFolderId,
		OrderByComparator<MSBPageTemplate> orderByComparator) {
		List<MSBPageTemplate> list = findByLikeN_P(name,
				msbPageTemplateFolderId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public MSBPageTemplate findByLikeN_P_Last(String name,
		long msbPageTemplateFolderId,
		OrderByComparator<MSBPageTemplate> orderByComparator)
		throws NoSuchPageTemplateException {
		MSBPageTemplate msbPageTemplate = fetchByLikeN_P_Last(name,
				msbPageTemplateFolderId, orderByComparator);

		if (msbPageTemplate != null) {
			return msbPageTemplate;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(", msbPageTemplateFolderId=");
		msg.append(msbPageTemplateFolderId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPageTemplateException(msg.toString());
	}

	/**
	 * Returns the last msb page template in the ordered set where name LIKE &#63; and msbPageTemplateFolderId = &#63;.
	 *
	 * @param name the name
	 * @param msbPageTemplateFolderId the msb page template folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching msb page template, or <code>null</code> if a matching msb page template could not be found
	 */
	@Override
	public MSBPageTemplate fetchByLikeN_P_Last(String name,
		long msbPageTemplateFolderId,
		OrderByComparator<MSBPageTemplate> orderByComparator) {
		int count = countByLikeN_P(name, msbPageTemplateFolderId);

		if (count == 0) {
			return null;
		}

		List<MSBPageTemplate> list = findByLikeN_P(name,
				msbPageTemplateFolderId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public MSBPageTemplate[] findByLikeN_P_PrevAndNext(long msbPageTemplateId,
		String name, long msbPageTemplateFolderId,
		OrderByComparator<MSBPageTemplate> orderByComparator)
		throws NoSuchPageTemplateException {
		MSBPageTemplate msbPageTemplate = findByPrimaryKey(msbPageTemplateId);

		Session session = null;

		try {
			session = openSession();

			MSBPageTemplate[] array = new MSBPageTemplateImpl[3];

			array[0] = getByLikeN_P_PrevAndNext(session, msbPageTemplate, name,
					msbPageTemplateFolderId, orderByComparator, true);

			array[1] = msbPageTemplate;

			array[2] = getByLikeN_P_PrevAndNext(session, msbPageTemplate, name,
					msbPageTemplateFolderId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MSBPageTemplate getByLikeN_P_PrevAndNext(Session session,
		MSBPageTemplate msbPageTemplate, String name,
		long msbPageTemplateFolderId,
		OrderByComparator<MSBPageTemplate> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_MSBPAGETEMPLATE_WHERE);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_LIKEN_P_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_LIKEN_P_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_LIKEN_P_NAME_2);
		}

		query.append(_FINDER_COLUMN_LIKEN_P_MSBPAGETEMPLATEFOLDERID_2);

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
			query.append(MSBPageTemplateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindName) {
			qPos.add(StringUtil.toLowerCase(name));
		}

		qPos.add(msbPageTemplateFolderId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(msbPageTemplate);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MSBPageTemplate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the msb page templates where name LIKE &#63; and msbPageTemplateFolderId = &#63; from the database.
	 *
	 * @param name the name
	 * @param msbPageTemplateFolderId the msb page template folder ID
	 */
	@Override
	public void removeByLikeN_P(String name, long msbPageTemplateFolderId) {
		for (MSBPageTemplate msbPageTemplate : findByLikeN_P(name,
				msbPageTemplateFolderId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(msbPageTemplate);
		}
	}

	/**
	 * Returns the number of msb page templates where name LIKE &#63; and msbPageTemplateFolderId = &#63;.
	 *
	 * @param name the name
	 * @param msbPageTemplateFolderId the msb page template folder ID
	 * @return the number of matching msb page templates
	 */
	@Override
	public int countByLikeN_P(String name, long msbPageTemplateFolderId) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_LIKEN_P;

		Object[] finderArgs = new Object[] { name, msbPageTemplateFolderId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MSBPAGETEMPLATE_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_LIKEN_P_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LIKEN_P_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_LIKEN_P_NAME_2);
			}

			query.append(_FINDER_COLUMN_LIKEN_P_MSBPAGETEMPLATEFOLDERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(StringUtil.toLowerCase(name));
				}

				qPos.add(msbPageTemplateFolderId);

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

	private static final String _FINDER_COLUMN_LIKEN_P_NAME_1 = "msbPageTemplate.name IS NULL AND ";
	private static final String _FINDER_COLUMN_LIKEN_P_NAME_2 = "lower(msbPageTemplate.name) LIKE ? AND ";
	private static final String _FINDER_COLUMN_LIKEN_P_NAME_3 = "(msbPageTemplate.name IS NULL OR msbPageTemplate.name LIKE '') AND ";
	private static final String _FINDER_COLUMN_LIKEN_P_MSBPAGETEMPLATEFOLDERID_2 =
		"msbPageTemplate.msbPageTemplateFolderId = ?";

	public MSBPageTemplatePersistenceImpl() {
		setModelClass(MSBPageTemplate.class);

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
	 * Caches the msb page template in the entity cache if it is enabled.
	 *
	 * @param msbPageTemplate the msb page template
	 */
	@Override
	public void cacheResult(MSBPageTemplate msbPageTemplate) {
		entityCache.putResult(MSBPageTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateImpl.class, msbPageTemplate.getPrimaryKey(),
			msbPageTemplate);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { msbPageTemplate.getUuid(), msbPageTemplate.getGroupId() },
			msbPageTemplate);

		msbPageTemplate.resetOriginalValues();
	}

	/**
	 * Caches the msb page templates in the entity cache if it is enabled.
	 *
	 * @param msbPageTemplates the msb page templates
	 */
	@Override
	public void cacheResult(List<MSBPageTemplate> msbPageTemplates) {
		for (MSBPageTemplate msbPageTemplate : msbPageTemplates) {
			if (entityCache.getResult(
						MSBPageTemplateModelImpl.ENTITY_CACHE_ENABLED,
						MSBPageTemplateImpl.class,
						msbPageTemplate.getPrimaryKey()) == null) {
				cacheResult(msbPageTemplate);
			}
			else {
				msbPageTemplate.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all msb page templates.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MSBPageTemplateImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the msb page template.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MSBPageTemplate msbPageTemplate) {
		entityCache.removeResult(MSBPageTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateImpl.class, msbPageTemplate.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((MSBPageTemplateModelImpl)msbPageTemplate, true);
	}

	@Override
	public void clearCache(List<MSBPageTemplate> msbPageTemplates) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MSBPageTemplate msbPageTemplate : msbPageTemplates) {
			entityCache.removeResult(MSBPageTemplateModelImpl.ENTITY_CACHE_ENABLED,
				MSBPageTemplateImpl.class, msbPageTemplate.getPrimaryKey());

			clearUniqueFindersCache((MSBPageTemplateModelImpl)msbPageTemplate,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		MSBPageTemplateModelImpl msbPageTemplateModelImpl) {
		Object[] args = new Object[] {
				msbPageTemplateModelImpl.getUuid(),
				msbPageTemplateModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			msbPageTemplateModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		MSBPageTemplateModelImpl msbPageTemplateModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					msbPageTemplateModelImpl.getUuid(),
					msbPageTemplateModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((msbPageTemplateModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					msbPageTemplateModelImpl.getOriginalUuid(),
					msbPageTemplateModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new msb page template with the primary key. Does not add the msb page template to the database.
	 *
	 * @param msbPageTemplateId the primary key for the new msb page template
	 * @return the new msb page template
	 */
	@Override
	public MSBPageTemplate create(long msbPageTemplateId) {
		MSBPageTemplate msbPageTemplate = new MSBPageTemplateImpl();

		msbPageTemplate.setNew(true);
		msbPageTemplate.setPrimaryKey(msbPageTemplateId);

		String uuid = PortalUUIDUtil.generate();

		msbPageTemplate.setUuid(uuid);

		msbPageTemplate.setCompanyId(companyProvider.getCompanyId());

		return msbPageTemplate;
	}

	/**
	 * Removes the msb page template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param msbPageTemplateId the primary key of the msb page template
	 * @return the msb page template that was removed
	 * @throws NoSuchPageTemplateException if a msb page template with the primary key could not be found
	 */
	@Override
	public MSBPageTemplate remove(long msbPageTemplateId)
		throws NoSuchPageTemplateException {
		return remove((Serializable)msbPageTemplateId);
	}

	/**
	 * Removes the msb page template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the msb page template
	 * @return the msb page template that was removed
	 * @throws NoSuchPageTemplateException if a msb page template with the primary key could not be found
	 */
	@Override
	public MSBPageTemplate remove(Serializable primaryKey)
		throws NoSuchPageTemplateException {
		Session session = null;

		try {
			session = openSession();

			MSBPageTemplate msbPageTemplate = (MSBPageTemplate)session.get(MSBPageTemplateImpl.class,
					primaryKey);

			if (msbPageTemplate == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPageTemplateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(msbPageTemplate);
		}
		catch (NoSuchPageTemplateException nsee) {
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
	protected MSBPageTemplate removeImpl(MSBPageTemplate msbPageTemplate) {
		msbPageTemplate = toUnwrappedModel(msbPageTemplate);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(msbPageTemplate)) {
				msbPageTemplate = (MSBPageTemplate)session.get(MSBPageTemplateImpl.class,
						msbPageTemplate.getPrimaryKeyObj());
			}

			if (msbPageTemplate != null) {
				session.delete(msbPageTemplate);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (msbPageTemplate != null) {
			clearCache(msbPageTemplate);
		}

		return msbPageTemplate;
	}

	@Override
	public MSBPageTemplate updateImpl(MSBPageTemplate msbPageTemplate) {
		msbPageTemplate = toUnwrappedModel(msbPageTemplate);

		boolean isNew = msbPageTemplate.isNew();

		MSBPageTemplateModelImpl msbPageTemplateModelImpl = (MSBPageTemplateModelImpl)msbPageTemplate;

		if (Validator.isNull(msbPageTemplate.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			msbPageTemplate.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (msbPageTemplate.getCreateDate() == null)) {
			if (serviceContext == null) {
				msbPageTemplate.setCreateDate(now);
			}
			else {
				msbPageTemplate.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!msbPageTemplateModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				msbPageTemplate.setModifiedDate(now);
			}
			else {
				msbPageTemplate.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (msbPageTemplate.isNew()) {
				session.save(msbPageTemplate);

				msbPageTemplate.setNew(false);
			}
			else {
				msbPageTemplate = (MSBPageTemplate)session.merge(msbPageTemplate);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!MSBPageTemplateModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { msbPageTemplateModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					msbPageTemplateModelImpl.getUuid(),
					msbPageTemplateModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					msbPageTemplateModelImpl.getMsbPageTemplateFolderId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_MSBPAGETEMPLATEFOLDERID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MSBPAGETEMPLATEFOLDERID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((msbPageTemplateModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						msbPageTemplateModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { msbPageTemplateModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((msbPageTemplateModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						msbPageTemplateModelImpl.getOriginalUuid(),
						msbPageTemplateModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						msbPageTemplateModelImpl.getUuid(),
						msbPageTemplateModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((msbPageTemplateModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MSBPAGETEMPLATEFOLDERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						msbPageTemplateModelImpl.getOriginalMsbPageTemplateFolderId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MSBPAGETEMPLATEFOLDERID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MSBPAGETEMPLATEFOLDERID,
					args);

				args = new Object[] {
						msbPageTemplateModelImpl.getMsbPageTemplateFolderId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MSBPAGETEMPLATEFOLDERID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MSBPAGETEMPLATEFOLDERID,
					args);
			}
		}

		entityCache.putResult(MSBPageTemplateModelImpl.ENTITY_CACHE_ENABLED,
			MSBPageTemplateImpl.class, msbPageTemplate.getPrimaryKey(),
			msbPageTemplate, false);

		clearUniqueFindersCache(msbPageTemplateModelImpl, false);
		cacheUniqueFindersCache(msbPageTemplateModelImpl);

		msbPageTemplate.resetOriginalValues();

		return msbPageTemplate;
	}

	protected MSBPageTemplate toUnwrappedModel(MSBPageTemplate msbPageTemplate) {
		if (msbPageTemplate instanceof MSBPageTemplateImpl) {
			return msbPageTemplate;
		}

		MSBPageTemplateImpl msbPageTemplateImpl = new MSBPageTemplateImpl();

		msbPageTemplateImpl.setNew(msbPageTemplate.isNew());
		msbPageTemplateImpl.setPrimaryKey(msbPageTemplate.getPrimaryKey());

		msbPageTemplateImpl.setUuid(msbPageTemplate.getUuid());
		msbPageTemplateImpl.setMsbPageTemplateId(msbPageTemplate.getMsbPageTemplateId());
		msbPageTemplateImpl.setGroupId(msbPageTemplate.getGroupId());
		msbPageTemplateImpl.setCompanyId(msbPageTemplate.getCompanyId());
		msbPageTemplateImpl.setUserId(msbPageTemplate.getUserId());
		msbPageTemplateImpl.setUserName(msbPageTemplate.getUserName());
		msbPageTemplateImpl.setCreateDate(msbPageTemplate.getCreateDate());
		msbPageTemplateImpl.setModifiedDate(msbPageTemplate.getModifiedDate());
		msbPageTemplateImpl.setName(msbPageTemplate.getName());
		msbPageTemplateImpl.setMsbPageTemplateFolderId(msbPageTemplate.getMsbPageTemplateFolderId());

		return msbPageTemplateImpl;
	}

	/**
	 * Returns the msb page template with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the msb page template
	 * @return the msb page template
	 * @throws NoSuchPageTemplateException if a msb page template with the primary key could not be found
	 */
	@Override
	public MSBPageTemplate findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPageTemplateException {
		MSBPageTemplate msbPageTemplate = fetchByPrimaryKey(primaryKey);

		if (msbPageTemplate == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPageTemplateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return msbPageTemplate;
	}

	/**
	 * Returns the msb page template with the primary key or throws a {@link NoSuchPageTemplateException} if it could not be found.
	 *
	 * @param msbPageTemplateId the primary key of the msb page template
	 * @return the msb page template
	 * @throws NoSuchPageTemplateException if a msb page template with the primary key could not be found
	 */
	@Override
	public MSBPageTemplate findByPrimaryKey(long msbPageTemplateId)
		throws NoSuchPageTemplateException {
		return findByPrimaryKey((Serializable)msbPageTemplateId);
	}

	/**
	 * Returns the msb page template with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the msb page template
	 * @return the msb page template, or <code>null</code> if a msb page template with the primary key could not be found
	 */
	@Override
	public MSBPageTemplate fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(MSBPageTemplateModelImpl.ENTITY_CACHE_ENABLED,
				MSBPageTemplateImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		MSBPageTemplate msbPageTemplate = (MSBPageTemplate)serializable;

		if (msbPageTemplate == null) {
			Session session = null;

			try {
				session = openSession();

				msbPageTemplate = (MSBPageTemplate)session.get(MSBPageTemplateImpl.class,
						primaryKey);

				if (msbPageTemplate != null) {
					cacheResult(msbPageTemplate);
				}
				else {
					entityCache.putResult(MSBPageTemplateModelImpl.ENTITY_CACHE_ENABLED,
						MSBPageTemplateImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(MSBPageTemplateModelImpl.ENTITY_CACHE_ENABLED,
					MSBPageTemplateImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return msbPageTemplate;
	}

	/**
	 * Returns the msb page template with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param msbPageTemplateId the primary key of the msb page template
	 * @return the msb page template, or <code>null</code> if a msb page template with the primary key could not be found
	 */
	@Override
	public MSBPageTemplate fetchByPrimaryKey(long msbPageTemplateId) {
		return fetchByPrimaryKey((Serializable)msbPageTemplateId);
	}

	@Override
	public Map<Serializable, MSBPageTemplate> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, MSBPageTemplate> map = new HashMap<Serializable, MSBPageTemplate>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			MSBPageTemplate msbPageTemplate = fetchByPrimaryKey(primaryKey);

			if (msbPageTemplate != null) {
				map.put(primaryKey, msbPageTemplate);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(MSBPageTemplateModelImpl.ENTITY_CACHE_ENABLED,
					MSBPageTemplateImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (MSBPageTemplate)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_MSBPAGETEMPLATE_WHERE_PKS_IN);

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

			for (MSBPageTemplate msbPageTemplate : (List<MSBPageTemplate>)q.list()) {
				map.put(msbPageTemplate.getPrimaryKeyObj(), msbPageTemplate);

				cacheResult(msbPageTemplate);

				uncachedPrimaryKeys.remove(msbPageTemplate.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(MSBPageTemplateModelImpl.ENTITY_CACHE_ENABLED,
					MSBPageTemplateImpl.class, primaryKey, nullModel);
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
	 * Returns all the msb page templates.
	 *
	 * @return the msb page templates
	 */
	@Override
	public List<MSBPageTemplate> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<MSBPageTemplate> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<MSBPageTemplate> findAll(int start, int end,
		OrderByComparator<MSBPageTemplate> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<MSBPageTemplate> findAll(int start, int end,
		OrderByComparator<MSBPageTemplate> orderByComparator,
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

		List<MSBPageTemplate> list = null;

		if (retrieveFromCache) {
			list = (List<MSBPageTemplate>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MSBPAGETEMPLATE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MSBPAGETEMPLATE;

				if (pagination) {
					sql = sql.concat(MSBPageTemplateModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MSBPageTemplate>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MSBPageTemplate>)QueryUtil.list(q,
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
	 * Removes all the msb page templates from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MSBPageTemplate msbPageTemplate : findAll()) {
			remove(msbPageTemplate);
		}
	}

	/**
	 * Returns the number of msb page templates.
	 *
	 * @return the number of msb page templates
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MSBPAGETEMPLATE);

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
		return MSBPageTemplateModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the msb page template persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(MSBPageTemplateImpl.class.getName());
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
	private static final String _SQL_SELECT_MSBPAGETEMPLATE = "SELECT msbPageTemplate FROM MSBPageTemplate msbPageTemplate";
	private static final String _SQL_SELECT_MSBPAGETEMPLATE_WHERE_PKS_IN = "SELECT msbPageTemplate FROM MSBPageTemplate msbPageTemplate WHERE msbPageTemplateId IN (";
	private static final String _SQL_SELECT_MSBPAGETEMPLATE_WHERE = "SELECT msbPageTemplate FROM MSBPageTemplate msbPageTemplate WHERE ";
	private static final String _SQL_COUNT_MSBPAGETEMPLATE = "SELECT COUNT(msbPageTemplate) FROM MSBPageTemplate msbPageTemplate";
	private static final String _SQL_COUNT_MSBPAGETEMPLATE_WHERE = "SELECT COUNT(msbPageTemplate) FROM MSBPageTemplate msbPageTemplate WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "msbPageTemplate.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MSBPageTemplate exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MSBPageTemplate exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(MSBPageTemplatePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}