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

package com.liferay.layout.page.template.util;

import com.liferay.layout.page.template.constants.LayoutPageTemplateEntryTypeConstants;
import com.liferay.layout.page.template.model.LayoutPageTemplateCollection;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.service.LayoutPageTemplateCollectionLocalServiceUtil;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

/**
 * @author Kyle Miho
 */
public class LayoutPageTemplateTestUtil {

	public static LayoutPageTemplateCollection addLayoutPageTemplateCollection(
			long groupId)
		throws PortalException {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				groupId, TestPropsValues.getUserId());

		return LayoutPageTemplateCollectionLocalServiceUtil.
			addLayoutPageTemplateCollection(
				TestPropsValues.getUserId(), groupId,
				RandomTestUtil.randomString(), StringPool.BLANK,
				serviceContext);
	}

	public static LayoutPageTemplateEntry
			addLayoutPageTemplateEntryWithG_C_C_N_T(
				long groupId, long classNameId, long classTypeId, String name,
				int type)
		throws PortalException {

		return addLayoutPageTemplateEntryWithG_C_C_N_T_S(
			groupId, classNameId, classTypeId, name, type,
			WorkflowConstants.STATUS_DRAFT);
	}

	public static LayoutPageTemplateEntry
			addLayoutPageTemplateEntryWithG_C_C_N_T_S(
				long groupId, long classNameId, long classTypeId, String name,
				int type, int status)
		throws PortalException {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				groupId, TestPropsValues.getUserId());

		return LayoutPageTemplateEntryLocalServiceUtil.
			addLayoutPageTemplateEntry(
				TestPropsValues.getUserId(), groupId, 0, classNameId,
				classTypeId, name, type, false, 0, 0, status, serviceContext);
	}

	public static LayoutPageTemplateEntry addLayoutPageTemplateEntryWithG_C_C_T(
			long groupId, long classNameId, long classTypeId, int type)
		throws PortalException {

		return addLayoutPageTemplateEntryWithG_C_C_N_T_S(
			groupId, classNameId, classTypeId, RandomTestUtil.randomString(),
			type, WorkflowConstants.STATUS_DRAFT);
	}

	public static LayoutPageTemplateEntry
			addLayoutPageTemplateEntryWithG_C_C_T_S(
				long groupId, long classNameId, long classTypeId, int type,
				int status)
		throws PortalException {

		return addLayoutPageTemplateEntryWithG_C_C_N_T_S(
			groupId, classNameId, classTypeId, RandomTestUtil.randomString(),
			type, status);
	}

	public static LayoutPageTemplateEntry addLayoutPageTemplateEntryWithG_C_T_D(
			long groupId, long classNameId, int type, boolean defaultTemplate)
		throws PortalException {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				groupId, TestPropsValues.getUserId());

		return LayoutPageTemplateEntryLocalServiceUtil.
			addLayoutPageTemplateEntry(
				TestPropsValues.getUserId(), groupId, 0, classNameId, 0,
				RandomTestUtil.randomString(), type, defaultTemplate, 0, 0,
				WorkflowConstants.STATUS_DRAFT, serviceContext);
	}

	public static LayoutPageTemplateEntry addLayoutPageTemplateEntryWithG_N(
			long groupId, String name)
		throws PortalException {

		return addLayoutPageTemplateEntryWithG_N_T(
			groupId, name,
			LayoutPageTemplateEntryTypeConstants.TYPE_DISPLAY_PAGE);
	}

	public static LayoutPageTemplateEntry addLayoutPageTemplateEntryWithG_N_T(
			long groupId, String name, int type)
		throws PortalException {

		return addLayoutPageTemplateEntryWithG_C_C_N_T_S(
			groupId, 0, 0, name, type, WorkflowConstants.STATUS_DRAFT);
	}

	public static LayoutPageTemplateEntry addLayoutPageTemplateEntryWithG_N_T_S(
			long groupId, String name, int type, int status)
		throws PortalException {

		return addLayoutPageTemplateEntryWithG_C_C_N_T_S(
			groupId, 0, 0, name, type, status);
	}

	public static LayoutPageTemplateEntry addLayoutPageTemplateEntryWithG_T(
			long groupId, int type)
		throws PortalException {

		return addLayoutPageTemplateEntryWithG_T_S(
			groupId, type, WorkflowConstants.STATUS_DRAFT);
	}

	public static LayoutPageTemplateEntry addLayoutPageTemplateEntryWithG_T_S(
			long groupId, int type, int status)
		throws PortalException {

		return addLayoutPageTemplateEntryWithG_C_C_N_T_S(
			groupId, 0, 0, RandomTestUtil.randomString(), type, status);
	}

	public static LayoutPageTemplateEntry addLayoutPageTemplateEntryWithGroupId(
			long groupId)
		throws PortalException {

		return addLayoutPageTemplateEntryWithG_C_C_N_T_S(
			groupId, 0, 0, RandomTestUtil.randomString(),
			LayoutPageTemplateEntryTypeConstants.TYPE_DISPLAY_PAGE,
			WorkflowConstants.STATUS_DRAFT);
	}

	public static LayoutPageTemplateEntry addLayoutPageTemplateEntryWithL_N(
			long layoutPageTemplateCollectionId, String name)
		throws PortalException {

		LayoutPageTemplateCollection layoutPageTemplateCollection =
			LayoutPageTemplateCollectionLocalServiceUtil.
				getLayoutPageTemplateCollection(layoutPageTemplateCollectionId);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				layoutPageTemplateCollection.getGroupId(),
				TestPropsValues.getUserId());

		return LayoutPageTemplateEntryLocalServiceUtil.
			addLayoutPageTemplateEntry(
				TestPropsValues.getUserId(),
				layoutPageTemplateCollection.getGroupId(),
				layoutPageTemplateCollectionId, name, serviceContext);
	}

	public static LayoutPageTemplateEntry addLayoutPageTemplateEntryWithL_S(
			long layoutPageTemplateCollectionId, int status)
		throws PortalException {

		LayoutPageTemplateCollection layoutPageTemplateCollection =
			LayoutPageTemplateCollectionLocalServiceUtil.
				getLayoutPageTemplateCollection(layoutPageTemplateCollectionId);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				layoutPageTemplateCollection.getGroupId(),
				TestPropsValues.getUserId());

		return LayoutPageTemplateEntryLocalServiceUtil.
			addLayoutPageTemplateEntry(
				TestPropsValues.getUserId(),
				layoutPageTemplateCollection.getGroupId(),
				layoutPageTemplateCollectionId, RandomTestUtil.randomString(),
				LayoutPageTemplateEntryTypeConstants.TYPE_DISPLAY_PAGE, status,
				serviceContext);
	}

	public static LayoutPageTemplateEntry
			addLayoutPageTemplateEntryWithLayoutPageTemplateCollectionId(
				long layoutPageTemplateCollectionId)
		throws PortalException {

		return addLayoutPageTemplateEntryWithL_N(
			layoutPageTemplateCollectionId, RandomTestUtil.randomString());
	}

}