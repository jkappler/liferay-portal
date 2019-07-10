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

package com.liferay.layout.page.template.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.fragment.model.FragmentCollection;
import com.liferay.fragment.model.FragmentEntry;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.service.FragmentCollectionServiceUtil;
import com.liferay.fragment.service.FragmentEntryLinkLocalServiceUtil;
import com.liferay.fragment.service.FragmentEntryServiceUtil;
import com.liferay.layout.page.template.constants.LayoutPageTemplateEntryTypeConstants;
import com.liferay.layout.page.template.exception.LayoutPageTemplateEntryNameException;
import com.liferay.layout.page.template.model.LayoutPageTemplateCollection;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryService;
import com.liferay.layout.page.template.service.persistence.LayoutPageTemplateEntryPersistence;
import com.liferay.layout.page.template.service.persistence.impl.constants.LayoutPersistenceConstants;
import com.liferay.layout.page.template.util.LayoutPageTemplateTestUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Jürgen Kappler
 */
@RunWith(Arquillian.class)
public class LayoutPageTemplateEntryServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE,
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED,
				LayoutPersistenceConstants.BUNDLE_SYMBOLIC_NAME));

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_layoutPageTemplateCollection =
			LayoutPageTemplateTestUtil.addLayoutPageTemplateCollection(
				_group.getGroupId());
	}

	@Test(
		expected = LayoutPageTemplateEntryNameException.MustNotBeDuplicate.class
	)
	public void testAddDuplicateLayoutPageTemplateEntries() throws Exception {
		String name = RandomTestUtil.randomString();

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithL_N(
			_layoutPageTemplateCollection.getLayoutPageTemplateCollectionId(),
			name);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithL_N(
			_layoutPageTemplateCollection.getLayoutPageTemplateCollectionId(),
			name);
	}

	@Test(expected = LayoutPageTemplateEntryNameException.class)
	public void testAddLayoutPageEntryWithNullName() throws Exception {
		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithL_N(
			_layoutPageTemplateCollection.getLayoutPageTemplateCollectionId(),
			null);
	}

	@Test
	public void testAddLayoutPageTemplateEntryByL_N() throws PortalException {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		final long layoutPageTemplateCollectionId =
			_layoutPageTemplateCollection.getLayoutPageTemplateCollectionId();

		final String name = RandomTestUtil.randomString();

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			_layoutPageTemplateEntryService.addLayoutPageTemplateEntry(
				_group.getGroupId(),
				_layoutPageTemplateCollection.
					getLayoutPageTemplateCollectionId(),
				name, serviceContext);

		LayoutPageTemplateEntry persistedLayoutPageTemplateEntry =
			_layoutPageTemplateEntryPersistence.fetchByPrimaryKey(
				layoutPageTemplateEntry.getLayoutPageTemplateEntryId());

		Assert.assertEquals(
			layoutPageTemplateCollectionId,
			persistedLayoutPageTemplateEntry.
				getLayoutPageTemplateCollectionId());
		Assert.assertEquals(name, persistedLayoutPageTemplateEntry.getName());
	}

	@Test
	public void testAddLayoutPageTemplateEntryByL_N_T() throws PortalException {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		final long layoutPageTemplateCollectionId =
			_layoutPageTemplateCollection.getLayoutPageTemplateCollectionId();

		final String name = RandomTestUtil.randomString();

		final int type = LayoutPageTemplateEntryTypeConstants.TYPE_DISPLAY_PAGE;

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			_layoutPageTemplateEntryService.addLayoutPageTemplateEntry(
				_group.getGroupId(), layoutPageTemplateCollectionId, name, type,
				serviceContext);

		LayoutPageTemplateEntry persistedLayoutPageTemplateEntry =
			_layoutPageTemplateEntryPersistence.fetchByPrimaryKey(
				layoutPageTemplateEntry.getLayoutPageTemplateEntryId());

		Assert.assertEquals(
			layoutPageTemplateCollectionId,
			persistedLayoutPageTemplateEntry.
				getLayoutPageTemplateCollectionId());
		Assert.assertEquals(name, persistedLayoutPageTemplateEntry.getName());
		Assert.assertEquals(type, persistedLayoutPageTemplateEntry.getType());
	}

	@Test
	public void testAddLayoutPageTemplateEntryByL_N_T_S()
		throws PortalException {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		final long layoutPageTemplateCollectionId =
			_layoutPageTemplateCollection.getLayoutPageTemplateCollectionId();

		final String name = RandomTestUtil.randomString();

		final int type = LayoutPageTemplateEntryTypeConstants.TYPE_DISPLAY_PAGE;

		final int status = WorkflowConstants.STATUS_APPROVED;

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			_layoutPageTemplateEntryService.addLayoutPageTemplateEntry(
				_group.getGroupId(), layoutPageTemplateCollectionId, name, type,
				status, serviceContext);

		LayoutPageTemplateEntry persistedLayoutPageTemplateEntry =
			_layoutPageTemplateEntryPersistence.fetchByPrimaryKey(
				layoutPageTemplateEntry.getLayoutPageTemplateEntryId());

		Assert.assertEquals(
			layoutPageTemplateCollectionId,
			persistedLayoutPageTemplateEntry.
				getLayoutPageTemplateCollectionId());
		Assert.assertEquals(name, persistedLayoutPageTemplateEntry.getName());
		Assert.assertEquals(type, persistedLayoutPageTemplateEntry.getType());
		Assert.assertEquals(
			status, persistedLayoutPageTemplateEntry.getStatus());
	}

	@Test(expected = LayoutPageTemplateEntryNameException.class)
	public void testAddLayoutPageTemplateEntryWithEmptyName() throws Exception {
		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithL_N(
			_layoutPageTemplateCollection.getLayoutPageTemplateCollectionId(),
			StringPool.BLANK);
	}

	@Test(expected = LayoutPageTemplateEntryNameException.class)
	public void testAddLayoutPageTemplateEntryWithSymbolInName()
		throws Exception {

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithL_N(
			_layoutPageTemplateCollection.getLayoutPageTemplateCollectionId(),
			"Test %&# Name");
	}

	@Test
	public void testAddLayoutPageTemplateEntryWithUTF8CharsInName()
		throws Exception {

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithL_N(
			_layoutPageTemplateCollection.getLayoutPageTemplateCollectionId(),
			"你好andこんにちは");
	}

	@Test
	public void testAddMultipleLayoutPageTemplateEntries()
		throws PortalException {

		List<LayoutPageTemplateEntry> originalLayoutPageTemplateEntries =
			_layoutPageTemplateEntryPersistence.findByG_L(
				_layoutPageTemplateCollection.getGroupId(),
				_layoutPageTemplateCollection.
					getLayoutPageTemplateCollectionId());

		LayoutPageTemplateTestUtil.
			addLayoutPageTemplateEntryWithLayoutPageTemplateCollectionId(
				_layoutPageTemplateCollection.
					getLayoutPageTemplateCollectionId());

		LayoutPageTemplateTestUtil.
			addLayoutPageTemplateEntryWithLayoutPageTemplateCollectionId(
				_layoutPageTemplateCollection.
					getLayoutPageTemplateCollectionId());

		List<LayoutPageTemplateEntry> actualLayoutPageTemplateEntries =
			_layoutPageTemplateEntryPersistence.findByG_L(
				_layoutPageTemplateCollection.getGroupId(),
				_layoutPageTemplateCollection.
					getLayoutPageTemplateCollectionId());

		Assert.assertEquals(
			actualLayoutPageTemplateEntries.toString(),
			originalLayoutPageTemplateEntries.size() + 2,
			actualLayoutPageTemplateEntries.size());
	}

	@Test
	public void testDeleteLayoutPageTemplateEntries() throws Exception {
		LayoutPageTemplateEntry layoutPageTemplateEntry1 =
			LayoutPageTemplateTestUtil.
				addLayoutPageTemplateEntryWithLayoutPageTemplateCollectionId(
					_layoutPageTemplateCollection.
						getLayoutPageTemplateCollectionId());

		LayoutPageTemplateEntry layoutPageTemplateEntry2 =
			LayoutPageTemplateTestUtil.
				addLayoutPageTemplateEntryWithLayoutPageTemplateCollectionId(
					_layoutPageTemplateCollection.
						getLayoutPageTemplateCollectionId());

		long[] layoutPageTemplateEntries = {
			layoutPageTemplateEntry1.getLayoutPageTemplateEntryId(),
			layoutPageTemplateEntry2.getLayoutPageTemplateEntryId()
		};

		_layoutPageTemplateEntryService.deleteLayoutPageTemplateEntries(
			layoutPageTemplateEntries);

		Assert.assertNull(
			_layoutPageTemplateEntryPersistence.fetchByPrimaryKey(
				layoutPageTemplateEntry1.getLayoutPageTemplateEntryId()));

		Assert.assertNull(
			_layoutPageTemplateEntryPersistence.fetchByPrimaryKey(
				layoutPageTemplateEntry2.getLayoutPageTemplateEntryId()));
	}

	@Test
	public void testDeleteLayoutPageTemplateEntry() throws Exception {
		LayoutPageTemplateEntry layoutPageTemplateEntry =
			LayoutPageTemplateTestUtil.
				addLayoutPageTemplateEntryWithLayoutPageTemplateCollectionId(
					_layoutPageTemplateCollection.
						getLayoutPageTemplateCollectionId());

		_layoutPageTemplateEntryService.deleteLayoutPageTemplateEntry(
			layoutPageTemplateEntry.getLayoutPageTemplateEntryId());

		Assert.assertNull(
			_layoutPageTemplateEntryPersistence.fetchByPrimaryKey(
				layoutPageTemplateEntry.getLayoutPageTemplateEntryId()));
	}

	@Test
	public void testFetchDefaultLayoutPageTemplateEntry() throws Exception {
		LayoutPageTemplateEntry layoutPageTemplateEntry =
			LayoutPageTemplateTestUtil.
				addLayoutPageTemplateEntryWithLayoutPageTemplateCollectionId(
					_layoutPageTemplateCollection.
						getLayoutPageTemplateCollectionId());

		_layoutPageTemplateEntryService.updateLayoutPageTemplateEntry(
			layoutPageTemplateEntry.getLayoutPageTemplateEntryId(), true);

		layoutPageTemplateEntry =
			_layoutPageTemplateEntryService.fetchDefaultLayoutPageTemplateEntry(
				_group.getGroupId(), layoutPageTemplateEntry.getClassNameId(),
				layoutPageTemplateEntry.getClassTypeId());

		Assert.assertNotNull(layoutPageTemplateEntry);
	}

	@Test
	public void testFetchLayoutPageTemplateEntryByG_N() throws PortalException {
		final long groupId = _group.getGroupId();

		final String name = RandomTestUtil.randomString();

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_N(
			groupId, name);

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			_layoutPageTemplateEntryService.fetchLayoutPageTemplateEntry(
				groupId, name);

		Assert.assertNotNull(layoutPageTemplateEntry);
	}

	@Test
	public void testFetchLayoutPageTemplateEntryByLayoutPageTemplateEntryId()
		throws Exception {

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			LayoutPageTemplateTestUtil.
				addLayoutPageTemplateEntryWithLayoutPageTemplateCollectionId(
					_layoutPageTemplateCollection.
						getLayoutPageTemplateCollectionId());

		layoutPageTemplateEntry =
			_layoutPageTemplateEntryService.fetchLayoutPageTemplateEntry(
				layoutPageTemplateEntry.getLayoutPageTemplateEntryId());

		Assert.assertNotNull(layoutPageTemplateEntry);
	}

	@Test
	public void testFetchLayoutPageTemplateEntryByUuidAndGroupId()
		throws Exception {

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			LayoutPageTemplateTestUtil.
				addLayoutPageTemplateEntryWithLayoutPageTemplateCollectionId(
					_layoutPageTemplateCollection.
						getLayoutPageTemplateCollectionId());

		layoutPageTemplateEntry =
			_layoutPageTemplateEntryService.
				fetchLayoutPageTemplateEntryByUuidAndGroupId(
					layoutPageTemplateEntry.getUuid(),
					layoutPageTemplateEntry.getGroupId());

		Assert.assertNotNull(layoutPageTemplateEntry);
	}

	@Test
	public void testGetLayoutPageTemplateEntriesByC_C_T() throws Exception {
		final long classNameId = RandomTestUtil.randomLong();

		final long classTypeId = RandomTestUtil.randomLong();

		final int type = LayoutPageTemplateEntryTypeConstants.TYPE_DISPLAY_PAGE;

		List<LayoutPageTemplateEntry> originalLayoutPageTemplateEntries =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntries(
				_group.getGroupId(), classNameId, classTypeId, type);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_C_T(
			_group.getGroupId(), 0, classTypeId, type);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_C_T(
			_group.getGroupId(), classNameId, 0, type);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_C_T(
			_group.getGroupId(), classNameId, classTypeId,
			LayoutPageTemplateEntryTypeConstants.TYPE_BASIC);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_C_T(
			_group.getGroupId(), classNameId, classTypeId, type);

		List<LayoutPageTemplateEntry> actualLayoutPageTemplateEntries =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntries(
				_group.getGroupId(), classNameId, classTypeId, type);

		Assert.assertEquals(
			actualLayoutPageTemplateEntries.toString(),
			originalLayoutPageTemplateEntries.size() + 1,
			actualLayoutPageTemplateEntries.size());
	}

	@Test
	public void testGetLayoutPageTemplateEntriesByC_C_T_S() throws Exception {
		final long classNameId = RandomTestUtil.randomLong();

		final long classTypeId = RandomTestUtil.randomLong();

		final int type = LayoutPageTemplateEntryTypeConstants.TYPE_DISPLAY_PAGE;

		final int status = WorkflowConstants.STATUS_DRAFT;

		List<LayoutPageTemplateEntry> originalLayoutPageTemplateEntries =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntries(
				_group.getGroupId(), classNameId, classTypeId, type, status);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_C_T_S(
			_group.getGroupId(), 0, classTypeId, type, status);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_C_T_S(
			_group.getGroupId(), classNameId, 0, type, status);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_C_T_S(
			_group.getGroupId(), classNameId, classTypeId,
			LayoutPageTemplateEntryTypeConstants.TYPE_BASIC, status);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_C_T_S(
			_group.getGroupId(), classNameId, classTypeId, type,
			WorkflowConstants.STATUS_APPROVED);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_C_T_S(
			_group.getGroupId(), classNameId, classTypeId, type, status);

		List<LayoutPageTemplateEntry> actualLayoutPageTemplateEntries =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntries(
				_group.getGroupId(), classNameId, classTypeId, type, status);

		Assert.assertEquals(
			actualLayoutPageTemplateEntries.toString(),
			originalLayoutPageTemplateEntries.size() + 1,
			actualLayoutPageTemplateEntries.size());
	}

	@Test
	public void testGetLayoutPageTemplateEntriesByC_T_D() throws Exception {
		final long classNameId = RandomTestUtil.randomLong();

		final int type = LayoutPageTemplateEntryTypeConstants.TYPE_DISPLAY_PAGE;

		final boolean defaultTemplate = false;

		List<LayoutPageTemplateEntry> originalLayoutPageTemplateEntries =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntries(
				_group.getGroupId(), classNameId, type, defaultTemplate);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_T_D(
			_group.getGroupId(), 0, type, defaultTemplate);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_T_D(
			_group.getGroupId(), classNameId,
			LayoutPageTemplateEntryTypeConstants.TYPE_BASIC, defaultTemplate);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_T_D(
			_group.getGroupId(), classNameId, type, true);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_T_D(
			_group.getGroupId(), classNameId, type, defaultTemplate);

		List<LayoutPageTemplateEntry> actualLayoutPageTemplateEntries =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntries(
				_group.getGroupId(), classNameId,
				LayoutPageTemplateEntryTypeConstants.TYPE_DISPLAY_PAGE,
				defaultTemplate);

		Assert.assertEquals(
			actualLayoutPageTemplateEntries.toString(),
			originalLayoutPageTemplateEntries.size() + 1,
			actualLayoutPageTemplateEntries.size());
	}

	@Test
	public void testGetLayoutPageTemplateEntriesByL_S() throws Exception {
		LayoutPageTemplateCollection layoutPageTemplateCollection =
			LayoutPageTemplateTestUtil.addLayoutPageTemplateCollection(
				_group.getGroupId());

		final long layoutPageTemplateCollectionId =
			layoutPageTemplateCollection.getLayoutPageTemplateCollectionId();

		final int status = WorkflowConstants.STATUS_APPROVED;

		List<LayoutPageTemplateEntry> originalLayoutPageTemplateEntries =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntries(
				_group.getGroupId(), layoutPageTemplateCollectionId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithL_S(
			_layoutPageTemplateCollection.getLayoutPageTemplateCollectionId(),
			status);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithL_S(
			layoutPageTemplateCollectionId, WorkflowConstants.STATUS_DRAFT);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithL_S(
			layoutPageTemplateCollectionId, status);

		List<LayoutPageTemplateEntry> actualLayoutPageTemplateEntries =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntries(
				_group.getGroupId(), layoutPageTemplateCollectionId,
				WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		Assert.assertEquals(
			actualLayoutPageTemplateEntries.toString(),
			originalLayoutPageTemplateEntries.size() + 1,
			actualLayoutPageTemplateEntries.size());
	}

	@Test
	public void testGetLayoutPageTemplateEntriesByLayoutPageTemplateCollectionId()
		throws Exception {

		LayoutPageTemplateCollection layoutPageTemplateCollection =
			LayoutPageTemplateTestUtil.addLayoutPageTemplateCollection(
				_group.getGroupId());

		final long layoutPageTemplateCollectionId =
			layoutPageTemplateCollection.getLayoutPageTemplateCollectionId();

		List<LayoutPageTemplateEntry> originalLayoutPageTemplateEntries =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntries(
				_group.getGroupId(), layoutPageTemplateCollectionId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		LayoutPageTemplateTestUtil.
			addLayoutPageTemplateEntryWithLayoutPageTemplateCollectionId(
				layoutPageTemplateCollectionId);

		LayoutPageTemplateTestUtil.
			addLayoutPageTemplateEntryWithLayoutPageTemplateCollectionId(
				layoutPageTemplateCollectionId);

		LayoutPageTemplateTestUtil.
			addLayoutPageTemplateEntryWithLayoutPageTemplateCollectionId(
				_layoutPageTemplateCollection.
					getLayoutPageTemplateCollectionId());

		List<LayoutPageTemplateEntry> actualLayoutPageTemplateEntries =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntries(
				_group.getGroupId(), layoutPageTemplateCollectionId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		Assert.assertEquals(
			actualLayoutPageTemplateEntries.toString(),
			originalLayoutPageTemplateEntries.size() + 2,
			actualLayoutPageTemplateEntries.size());
	}

	@Test
	public void testGetLayoutPageTemplateEntriesCountByC_C_N_T()
		throws Exception {

		final long classNameId = RandomTestUtil.randomLong();

		final long classTypeId = RandomTestUtil.randomLong();

		final String name = "Name";

		final int type = LayoutPageTemplateEntryTypeConstants.TYPE_DISPLAY_PAGE;

		int originalCount =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntriesCount(
				_group.getGroupId(), classNameId, classTypeId, name, type);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_C_N_T(
			_group.getGroupId(), 0, classTypeId, "Test Name 1", type);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_C_N_T(
			_group.getGroupId(), classNameId, 0, "Test Name 2", type);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_C_N_T(
			_group.getGroupId(), classNameId, classTypeId, "Test 3", type);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_C_N_T(
			_group.getGroupId(), classNameId, classTypeId, "Test Name 4",
			LayoutPageTemplateEntryTypeConstants.TYPE_BASIC);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_C_N_T(
			_group.getGroupId(), classNameId, classTypeId, "Test Name 5", type);

		int actualCount =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntriesCount(
				_group.getGroupId(), classNameId, classTypeId, name, type);

		Assert.assertEquals(originalCount + 1, actualCount);
	}

	@Test
	public void testGetLayoutPageTemplateEntriesCountByC_C_N_T_S()
		throws Exception {

		final long classNameId = RandomTestUtil.randomLong();

		final long classTypeId = RandomTestUtil.randomLong();

		final String name = "Name";

		final int type = LayoutPageTemplateEntryTypeConstants.TYPE_DISPLAY_PAGE;

		final int status = WorkflowConstants.STATUS_DRAFT;

		int originalCount =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntriesCount(
				_group.getGroupId(), classNameId, classTypeId, name, type,
				status);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_C_N_T_S(
			_group.getGroupId(), 0, classTypeId, "Test Name 1", type, status);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_C_N_T_S(
			_group.getGroupId(), classNameId, 0, "Test Name 2", type, status);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_C_N_T_S(
			_group.getGroupId(), classNameId, 0, "Test 3", type, status);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_C_N_T_S(
			_group.getGroupId(), classNameId, classTypeId, "Test Name 4",
			LayoutPageTemplateEntryTypeConstants.TYPE_BASIC, status);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_C_N_T_S(
			_group.getGroupId(), classNameId, classTypeId, "Test Name 5", type,
			WorkflowConstants.STATUS_APPROVED);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_C_N_T_S(
			_group.getGroupId(), classNameId, classTypeId, "Test Name 6", type,
			status);

		int actualCount =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntriesCount(
				_group.getGroupId(), classNameId, classTypeId, name, type,
				status);

		Assert.assertEquals(originalCount + 1, actualCount);
	}

	@Test
	public void testGetLayoutPageTemplateEntriesCountByC_C_T()
		throws Exception {

		final long classNameId = RandomTestUtil.randomLong();

		final long classTypeId = RandomTestUtil.randomLong();

		final int type = LayoutPageTemplateEntryTypeConstants.TYPE_DISPLAY_PAGE;

		int originalCount =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntriesCount(
				_group.getGroupId(), classNameId, classTypeId, type);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_C_T(
			_group.getGroupId(), 0, classTypeId, type);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_C_T(
			_group.getGroupId(), classNameId, 0, type);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_C_T(
			_group.getGroupId(), classNameId, classTypeId,
			LayoutPageTemplateEntryTypeConstants.TYPE_BASIC);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_C_T(
			_group.getGroupId(), classNameId, classTypeId, type);

		int actualCount =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntriesCount(
				_group.getGroupId(), classNameId, classTypeId, type);

		Assert.assertEquals(originalCount + 1, actualCount);
	}

	@Test
	public void testGetLayoutPageTemplateEntriesCountByC_C_T_S()
		throws Exception {

		final long classNameId = RandomTestUtil.randomLong();

		final long classTypeId = RandomTestUtil.randomLong();

		final int type = LayoutPageTemplateEntryTypeConstants.TYPE_DISPLAY_PAGE;

		final int status = WorkflowConstants.STATUS_DRAFT;

		int originalCount =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntriesCount(
				_group.getGroupId(), classNameId, classTypeId, type, status);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_C_T_S(
			_group.getGroupId(), 0, classTypeId, type, status);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_C_T_S(
			_group.getGroupId(), classNameId, 0, type, status);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_C_T_S(
			_group.getGroupId(), classNameId, classTypeId,
			LayoutPageTemplateEntryTypeConstants.TYPE_BASIC, status);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_C_T_S(
			_group.getGroupId(), classNameId, classTypeId, type,
			WorkflowConstants.STATUS_APPROVED);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_C_C_T_S(
			_group.getGroupId(), classNameId, classTypeId, type, status);

		int actualCount =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntriesCount(
				_group.getGroupId(), classNameId, classTypeId, type, status);

		Assert.assertEquals(originalCount + 1, actualCount);
	}

	@Test
	public void testGetLayoutPageTemplateEntriesCountByL_N() throws Exception {
		LayoutPageTemplateCollection layoutPageTemplateCollection =
			LayoutPageTemplateTestUtil.addLayoutPageTemplateCollection(
				_group.getGroupId());

		final long layoutPageTemplateCollectionId =
			layoutPageTemplateCollection.getLayoutPageTemplateCollectionId();

		final String name = "Name";

		int originalCount =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntriesCount(
				_group.getGroupId(),
				layoutPageTemplateCollection.
					getLayoutPageTemplateCollectionId(),
				name);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithL_N(
			_layoutPageTemplateCollection.getLayoutPageTemplateCollectionId(),
			"Test Name 1");

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithL_N(
			layoutPageTemplateCollectionId, "Test 2");

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithL_N(
			layoutPageTemplateCollectionId, "Test Name 3");

		int actualCount =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntriesCount(
				_group.getGroupId(),
				layoutPageTemplateCollection.
					getLayoutPageTemplateCollectionId(),
				name);

		Assert.assertEquals(originalCount + 1, actualCount);
	}

	@Test
	public void testGetLayoutPageTemplateEntriesCountByL_N_S()
		throws Exception {

		LayoutPageTemplateCollection layoutPageTemplateCollection =
			LayoutPageTemplateTestUtil.addLayoutPageTemplateCollection(
				_group.getGroupId());

		final long layoutPageTemplateCollectionId =
			layoutPageTemplateCollection.getLayoutPageTemplateCollectionId();

		final String name = "Name";

		final int status = WorkflowConstants.STATUS_DRAFT;

		int originalCount =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntriesCount(
				_group.getGroupId(),
				layoutPageTemplateCollection.
					getLayoutPageTemplateCollectionId(),
				name, status);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithL_N(
			_layoutPageTemplateCollection.getLayoutPageTemplateCollectionId(),
			"Test Name 1");

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithL_N(
			layoutPageTemplateCollectionId, "Test 2");

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithL_N(
			layoutPageTemplateCollectionId, "Test Name 3");

		int actualCount =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntriesCount(
				_group.getGroupId(),
				layoutPageTemplateCollection.
					getLayoutPageTemplateCollectionId(),
				name, status);

		Assert.assertEquals(originalCount + 1, actualCount);
	}

	@Test
	public void testGetLayoutPageTemplateEntriesCountByLayoutPageTemplateCollectionId()
		throws Exception {

		LayoutPageTemplateCollection layoutPageTemplateCollection =
			LayoutPageTemplateTestUtil.addLayoutPageTemplateCollection(
				_group.getGroupId());

		final long layoutPageTemplateCollectionId =
			layoutPageTemplateCollection.getLayoutPageTemplateCollectionId();

		int originalCount =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntriesCount(
				_group.getGroupId(),
				layoutPageTemplateCollection.
					getLayoutPageTemplateCollectionId());

		LayoutPageTemplateTestUtil.
			addLayoutPageTemplateEntryWithLayoutPageTemplateCollectionId(
				layoutPageTemplateCollectionId);

		LayoutPageTemplateTestUtil.
			addLayoutPageTemplateEntryWithLayoutPageTemplateCollectionId(
				layoutPageTemplateCollectionId);

		LayoutPageTemplateTestUtil.
			addLayoutPageTemplateEntryWithLayoutPageTemplateCollectionId(
				_layoutPageTemplateCollection.
					getLayoutPageTemplateCollectionId());

		int actualCount =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntriesCount(
				_group.getGroupId(), layoutPageTemplateCollectionId);

		Assert.assertEquals(originalCount + 2, actualCount);
	}

	@Test
	public void testGetLayoutPageTemplateEntriesCountByN_T() throws Exception {
		final String name = "Name";

		final int type = LayoutPageTemplateEntryTypeConstants.TYPE_DISPLAY_PAGE;

		int originalCount =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntriesCount(
				_group.getGroupId(), name, type);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_N_T(
			_group.getGroupId(), "Test 1", type);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_N_T(
			_group.getGroupId(), "Test Name 2",
			LayoutPageTemplateEntryTypeConstants.TYPE_BASIC);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_N_T(
			_group.getGroupId(), "Test Name 3", type);

		int actualCount =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntriesCount(
				_group.getGroupId(), name, type);

		Assert.assertEquals(originalCount + 1, actualCount);
	}

	@Test
	public void testGetLayoutPageTemplateEntriesCountByN_T_S()
		throws Exception {

		final String name = "Name";

		final int type = LayoutPageTemplateEntryTypeConstants.TYPE_DISPLAY_PAGE;

		final int status = WorkflowConstants.STATUS_DRAFT;

		int originalCount =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntriesCount(
				_group.getGroupId(), name, type, status);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_N_T_S(
			_group.getGroupId(), "Test 1", type, status);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_N_T_S(
			_group.getGroupId(), "Test Name 2",
			LayoutPageTemplateEntryTypeConstants.TYPE_BASIC, status);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_N_T_S(
			_group.getGroupId(), "Test Name 3", type,
			WorkflowConstants.STATUS_PENDING);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_N_T_S(
			_group.getGroupId(), "Test Name 4", type, status);

		int actualCount =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntriesCount(
				_group.getGroupId(), name, type, status);

		Assert.assertEquals(originalCount + 1, actualCount);
	}

	@Test
	public void testGetLayoutPageTemplateEntriesCountByT_S() throws Exception {
		final int type = LayoutPageTemplateEntryTypeConstants.TYPE_DISPLAY_PAGE;

		final int status = WorkflowConstants.STATUS_DRAFT;

		int originalCount =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntriesCount(
				_group.getGroupId(), type, status);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_T_S(
			_group.getGroupId(), type, status);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_T_S(
			_group.getGroupId(),
			LayoutPageTemplateEntryTypeConstants.TYPE_BASIC, status);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_T_S(
			_group.getGroupId(), type, WorkflowConstants.STATUS_PENDING);

		int actualCount =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntriesCount(
				_group.getGroupId(), type, status);

		Assert.assertEquals(originalCount + 1, actualCount);
	}

	@Test
	public void testGetLayoutPageTemplateEntriesCountByType() throws Exception {
		final int type = LayoutPageTemplateEntryTypeConstants.TYPE_DISPLAY_PAGE;

		int originalCount =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntriesCount(
				_group.getGroupId(), type);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_T(
			_group.getGroupId(), type);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_T(
			_group.getGroupId(), type);

		LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithG_T(
			_group.getGroupId(),
			LayoutPageTemplateEntryTypeConstants.TYPE_BASIC);

		int actualCount =
			_layoutPageTemplateEntryService.getLayoutPageTemplateEntriesCount(
				_group.getGroupId(), type);

		Assert.assertEquals(originalCount + 2, actualCount);
	}

	@Test
	public void testUpdateLayoutPageTemplateEntryByRemovingFragmentEntryIds()
		throws PortalException {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			LayoutPageTemplateTestUtil.
				addLayoutPageTemplateEntryWithLayoutPageTemplateCollectionId(
					_layoutPageTemplateCollection.
						getLayoutPageTemplateCollectionId());

		FragmentCollection fragmentCollection =
			FragmentCollectionServiceUtil.addFragmentCollection(
				_group.getGroupId(), RandomTestUtil.randomString(),
				StringPool.BLANK, serviceContext);

		FragmentEntry fragmentEntry1 =
			FragmentEntryServiceUtil.addFragmentEntry(
				_group.getGroupId(),
				fragmentCollection.getFragmentCollectionId(),
				RandomTestUtil.randomString(),
				WorkflowConstants.STATUS_APPROVED, serviceContext);

		FragmentEntry fragmentEntry2 =
			FragmentEntryServiceUtil.addFragmentEntry(
				_group.getGroupId(),
				fragmentCollection.getFragmentCollectionId(),
				RandomTestUtil.randomString(),
				WorkflowConstants.STATUS_APPROVED, serviceContext);

		long[] fragmentEntryIds = {
			fragmentEntry1.getFragmentEntryId(),
			fragmentEntry2.getFragmentEntryId()
		};

		_layoutPageTemplateEntryService.updateLayoutPageTemplateEntry(
			layoutPageTemplateEntry.getLayoutPageTemplateEntryId(),
			RandomTestUtil.randomString(), fragmentEntryIds, serviceContext);

		List<FragmentEntryLink> originalFragmentEntryLinks =
			FragmentEntryLinkLocalServiceUtil.getFragmentEntryLinks(
				_group.getGroupId(),
				PortalUtil.getClassNameId(Layout.class.getName()),
				layoutPageTemplateEntry.getPlid());

		_layoutPageTemplateEntryService.updateLayoutPageTemplateEntry(
			layoutPageTemplateEntry.getLayoutPageTemplateEntryId(),
			RandomTestUtil.randomString(), null, serviceContext);

		List<FragmentEntryLink> actualFragmentEntryLinks =
			FragmentEntryLinkLocalServiceUtil.getFragmentEntryLinks(
				_group.getGroupId(),
				PortalUtil.getClassNameId(Layout.class.getName()),
				layoutPageTemplateEntry.getPlid());

		Assert.assertEquals(
			originalFragmentEntryLinks.toString(),
			originalFragmentEntryLinks.size() - 2,
			actualFragmentEntryLinks.size());
	}

	@Test
	public void testUpdateLayoutPageTemplateEntryC_C() throws PortalException {
		final long classNameId = PortalUtil.getClassNameId(Layout.class);
		final long classTypeId = RandomTestUtil.randomLong();

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			LayoutPageTemplateTestUtil.
				addLayoutPageTemplateEntryWithLayoutPageTemplateCollectionId(
					_layoutPageTemplateCollection.
						getLayoutPageTemplateCollectionId());

		layoutPageTemplateEntry =
			_layoutPageTemplateEntryService.updateLayoutPageTemplateEntry(
				layoutPageTemplateEntry.getLayoutPageTemplateEntryId(),
				classNameId, classTypeId);

		LayoutPageTemplateEntry persistedLayoutPageTemplateEntry =
			_layoutPageTemplateEntryPersistence.fetchByPrimaryKey(
				layoutPageTemplateEntry.getLayoutPageTemplateEntryId());

		Assert.assertEquals(
			classNameId, persistedLayoutPageTemplateEntry.getClassNameId());

		Assert.assertEquals(
			classTypeId, persistedLayoutPageTemplateEntry.getClassTypeId());
	}

	@Test
	public void testUpdateLayoutPageTemplateEntryDefaultTemplate()
		throws PortalException {

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			LayoutPageTemplateTestUtil.
				addLayoutPageTemplateEntryWithLayoutPageTemplateCollectionId(
					_layoutPageTemplateCollection.
						getLayoutPageTemplateCollectionId());

		_layoutPageTemplateEntryService.updateLayoutPageTemplateEntry(
			layoutPageTemplateEntry.getLayoutPageTemplateEntryId(), true);

		LayoutPageTemplateEntry persistedLayoutPageTemplateEntry =
			_layoutPageTemplateEntryPersistence.fetchByPrimaryKey(
				layoutPageTemplateEntry.getLayoutPageTemplateEntryId());

		Assert.assertTrue(persistedLayoutPageTemplateEntry.isDefaultTemplate());
	}

	@Test
	public void testUpdateLayoutPageTemplateEntryName() throws PortalException {
		LayoutPageTemplateEntry layoutPageTemplateEntry =
			LayoutPageTemplateTestUtil.
				addLayoutPageTemplateEntryWithLayoutPageTemplateCollectionId(
					_layoutPageTemplateCollection.
						getLayoutPageTemplateCollectionId());

		final String updatedName = RandomTestUtil.randomString();

		_layoutPageTemplateEntryService.updateLayoutPageTemplateEntry(
			layoutPageTemplateEntry.getLayoutPageTemplateEntryId(),
			updatedName);

		LayoutPageTemplateEntry persistedLayoutPageTemplateEntry =
			_layoutPageTemplateEntryPersistence.fetchByPrimaryKey(
				layoutPageTemplateEntry.getLayoutPageTemplateEntryId());

		Assert.assertEquals(
			updatedName, persistedLayoutPageTemplateEntry.getName());
	}

	@Test
	public void testUpdateLayoutPageTemplateEntryNameAndFragmentEntryIds()
		throws PortalException {

		final String name = RandomTestUtil.randomString();

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithGroupId(
				_group.getGroupId());

		FragmentCollection fragmentCollection =
			FragmentCollectionServiceUtil.addFragmentCollection(
				_group.getGroupId(), RandomTestUtil.randomString(),
				StringPool.BLANK, serviceContext);

		FragmentEntry fragmentEntry1 =
			FragmentEntryServiceUtil.addFragmentEntry(
				_group.getGroupId(),
				fragmentCollection.getFragmentCollectionId(),
				RandomTestUtil.randomString(),
				WorkflowConstants.STATUS_APPROVED, serviceContext);

		FragmentEntry fragmentEntry2 =
			FragmentEntryServiceUtil.addFragmentEntry(
				_group.getGroupId(),
				fragmentCollection.getFragmentCollectionId(),
				RandomTestUtil.randomString(),
				WorkflowConstants.STATUS_APPROVED, serviceContext);

		long[] fragmentEntryIds = {
			fragmentEntry1.getFragmentEntryId(),
			fragmentEntry2.getFragmentEntryId()
		};

		List<FragmentEntryLink> originalFragmentEntryLinks =
			FragmentEntryLinkLocalServiceUtil.getFragmentEntryLinks(
				_group.getGroupId(),
				PortalUtil.getClassNameId(Layout.class.getName()),
				layoutPageTemplateEntry.getPlid());

		_layoutPageTemplateEntryService.updateLayoutPageTemplateEntry(
			layoutPageTemplateEntry.getLayoutPageTemplateEntryId(), name,
			fragmentEntryIds, serviceContext);

		List<FragmentEntryLink> actualFragmentEntryLinks =
			FragmentEntryLinkLocalServiceUtil.getFragmentEntryLinks(
				_group.getGroupId(),
				PortalUtil.getClassNameId(Layout.class.getName()),
				layoutPageTemplateEntry.getPlid());

		Assert.assertEquals(
			actualFragmentEntryLinks.toString(),
			originalFragmentEntryLinks.size() + 2,
			actualFragmentEntryLinks.size());

		LayoutPageTemplateEntry persistedLayoutPageTemplateEntry =
			_layoutPageTemplateEntryPersistence.findByPrimaryKey(
				layoutPageTemplateEntry.getLayoutPageTemplateEntryId());

		Assert.assertEquals(name, persistedLayoutPageTemplateEntry.getName());
	}

	@Test
	public void testUpdateLayoutPageTemplateEntryPreviewFileEntryId()
		throws PortalException {

		final long previewFileEntryId = RandomTestUtil.randomLong();

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			LayoutPageTemplateTestUtil.addLayoutPageTemplateEntryWithGroupId(
				_group.getGroupId());

		_layoutPageTemplateEntryService.updateLayoutPageTemplateEntry(
			layoutPageTemplateEntry.getLayoutPageTemplateEntryId(),
			previewFileEntryId);

		LayoutPageTemplateEntry persistedLayoutPageTemplateEntry =
			_layoutPageTemplateEntryPersistence.fetchByPrimaryKey(
				layoutPageTemplateEntry.getLayoutPageTemplateEntryId());

		Assert.assertEquals(
			previewFileEntryId,
			persistedLayoutPageTemplateEntry.getPreviewFileEntryId());
	}

	@Test
	public void testUpdateLayoutPageTemplateEntryStatus()
		throws PortalException {

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			LayoutPageTemplateTestUtil.
				addLayoutPageTemplateEntryWithLayoutPageTemplateCollectionId(
					_layoutPageTemplateCollection.
						getLayoutPageTemplateCollectionId());

		final int status = WorkflowConstants.STATUS_PENDING;

		layoutPageTemplateEntry = _layoutPageTemplateEntryService.updateStatus(
			layoutPageTemplateEntry.getLayoutPageTemplateEntryId(), status);

		LayoutPageTemplateEntry persistedLayoutPageTemplateEntry =
			_layoutPageTemplateEntryPersistence.fetchByPrimaryKey(
				layoutPageTemplateEntry.getLayoutPageTemplateEntryId());

		Assert.assertEquals(
			status, persistedLayoutPageTemplateEntry.getStatus());
	}

	@DeleteAfterTestRun
	private Group _group;

	private LayoutPageTemplateCollection _layoutPageTemplateCollection;

	@Inject
	private LayoutPageTemplateEntryPersistence
		_layoutPageTemplateEntryPersistence;

	@Inject
	private LayoutPageTemplateEntryService _layoutPageTemplateEntryService;

}