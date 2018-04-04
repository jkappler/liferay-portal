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

package com.liferay.fragment.internal.exportimport.data.handler.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.exportimport.kernel.lar.DataLevel;
import com.liferay.fragment.constants.FragmentPortletKeys;
import com.liferay.fragment.model.FragmentCollection;
import com.liferay.fragment.model.FragmentEntry;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.service.FragmentCollectionLocalServiceUtil;
import com.liferay.fragment.util.FragmentTestUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.lar.test.BasePortletDataHandlerTestCase;
import com.liferay.portal.service.test.ServiceTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Pavel Savinov
 */
@RunWith(Arquillian.class)
public class FragmentPortletDataHandlerTest
	extends BasePortletDataHandlerTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	@Override
	public void setUp() throws Exception {
		ServiceTestUtil.setUser(TestPropsValues.getUser());

		super.setUp();
	}

	@Test
	public void testDeleteGroupFragmentCollections() throws Exception {
		Group group = GroupTestUtil.addGroup();

		FragmentTestUtil.addFragmentCollection(
			group.getGroupId(), RandomTestUtil.randomString(64));

		GroupLocalServiceUtil.deleteGroup(group);

		List<FragmentCollection> fragmentCollections =
			FragmentCollectionLocalServiceUtil.getFragmentCollections(
				group.getGroupId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		Assert.assertEquals(
			fragmentCollections.toString(), 0, fragmentCollections.size());
	}

	@Override
	protected void addStagedModels() throws Exception {
		Group group = GroupTestUtil.addGroup();

		FragmentCollection fragmentCollection =
			FragmentTestUtil.addFragmentCollection(
				stagingGroup.getGroupId(), RandomTestUtil.randomString(64));

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(stagingGroup.getGroupId());

		FragmentEntry fragmentEntry = FragmentTestUtil.addFragmentEntry(
			fragmentCollection.getFragmentCollectionId());

		FragmentEntryLink originalFragmentEntryLink =
			FragmentTestUtil.addFragmentEntryLink(
				fragmentEntry, 0, PortalUtil.getClassNameId(Layout.class),
				group.getDefaultPublicPlid());

		FragmentTestUtil.addFragmentEntryLink(
			fragmentEntry, originalFragmentEntryLink.getFragmentEntryLinkId(),
			PortalUtil.getClassNameId(Layout.class),
			group.getDefaultPublicPlid());
	}

	@Override
	protected DataLevel getDataLevel() {
		return DataLevel.SITE;
	}

	@Override
	protected String getPortletId() {
		return FragmentPortletKeys.FRAGMENT;
	}

	@Override
	protected boolean isDataPortalLevel() {
		return false;
	}

	@Override
	protected boolean isDataPortletInstanceLevel() {
		return false;
	}

	@Override
	protected boolean isDataSiteLevel() {
		return true;
	}

}