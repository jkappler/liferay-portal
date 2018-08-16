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

package com.liferay.site.navigation.internal.exportimport.data.handler.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.exportimport.kernel.lar.ExportImportThreadLocal;
import com.liferay.exportimport.test.util.lar.BaseStagedModelDataHandlerTestCase;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.util.test.LayoutTestUtil;
import com.liferay.site.navigation.model.SiteNavigationMenu;
import com.liferay.site.navigation.model.SiteNavigationMenuItem;
import com.liferay.site.navigation.service.SiteNavigationMenuItemLocalServiceUtil;
import com.liferay.site.navigation.util.SiteNavigationTestUtil;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Kyle Miho
 */
@RunWith(Arquillian.class)
public class SiteNavigationMenuItemStagedModelDataHandlerTest
	extends BaseStagedModelDataHandlerTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		super.setUp();

		LayoutTestUtil.addLayout(stagingGroup);
	}

	@Test
	public void testStageSiteNavigationMenuItem() throws Exception {
		Map<String, List<StagedModel>> dependentStagedModelsMap =
			addDependentStagedModelsMap(stagingGroup);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				stagingGroup.getGroupId(), TestPropsValues.getUserId());

		System.out.println("----------------------------");
		System.out.println("--------Test Start---------4");

		StagedModel stagedModel = addStagedModel(
			stagingGroup, dependentStagedModelsMap);

		System.out.println("Staged Model: " + stagedModel);
		System.out.flush();

		try {
			System.out.println("-------Export Start---------");
			System.out.flush();
			exportImportStagedModel(stagedModel);
		}
		finally {
			ExportImportThreadLocal.setPortletImportInProcess(false);
		}

		StagedModel importedStagedModel = getStagedModel(
			stagedModel.getUuid(), liveGroup);

		validateImportedStagedModel(stagedModel, importedStagedModel);
	}

	@Test
	public void testStageSiteNavigationMenuItem2() throws Exception {
		Map<String, List<StagedModel>> dependentStagedModelsMap =
			addDependentStagedModelsMap(stagingGroup);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				stagingGroup.getGroupId(), TestPropsValues.getUserId());

		System.out.println("----------------------------");
		System.out.println("11------Test Start---------4");

		StagedModel stagedModel = addStagedModel(
			stagingGroup, dependentStagedModelsMap);

		System.out.println("Staged Model: " + stagedModel);
		System.out.flush();

		try {
			System.out.println("-------Export Start---------");
			System.out.flush();
			exportImportStagedModel(stagedModel);
		}
		finally {
			ExportImportThreadLocal.setPortletImportInProcess(false);
		}

		StagedModel importedStagedModel = getStagedModel(
			stagedModel.getUuid(), liveGroup);

		validateImportedStagedModel(stagedModel, importedStagedModel);
	}

	@Override
	protected StagedModel addStagedModel(
			Group group,
			Map<String, List<StagedModel>> dependentStagedModelsMap)
		throws Exception {

		SiteNavigationMenu siteNavigationMenu =
			SiteNavigationTestUtil.addSiteNavigationMenu(group.getGroupId());

		Layout layout = LayoutTestUtil.addLayout(group);

		UnicodeProperties curTypeSettingsProperties = new UnicodeProperties(
			true);

		curTypeSettingsProperties.setProperty(
			"groupId", String.valueOf(group.getGroupId()));
		curTypeSettingsProperties.setProperty("layoutUuid", layout.getUuid());
		curTypeSettingsProperties.setProperty(
			"privateLayout", String.valueOf("false"));
		curTypeSettingsProperties.setProperty(
			"title", String.valueOf("Welcome"));

		System.out.println(
			"typeSettings: " + curTypeSettingsProperties.toString());
			System.out.println(
			"Added Staged Model: " +
			SiteNavigationTestUtil.addSiteNavigationMenuItem(
				siteNavigationMenu.getSiteNavigationMenuId(),
				curTypeSettingsProperties.toString()));

		System.out.flush();

		return SiteNavigationTestUtil.addSiteNavigationMenuItem(
			siteNavigationMenu.getSiteNavigationMenuId(),
			curTypeSettingsProperties.toString());
	}

	@Override
	protected StagedModel getStagedModel(String uuid, Group group) {
		return SiteNavigationMenuItemLocalServiceUtil.fetchSiteNavigationMenuItemByUuidAndGroupId(
			uuid, group.getGroupId());
	}

	@Override
	protected Class<? extends StagedModel> getStagedModelClass() {
		return SiteNavigationMenuItem.class;
	}

	@Override
	protected void validateImportedStagedModel(
			StagedModel stagedModel, StagedModel importedStagedModel)
		throws Exception {

		SiteNavigationMenuItem siteNavigationMenuItem =
			(SiteNavigationMenuItem)stagedModel;
		SiteNavigationMenuItem importedSiteNavigationMenuItem =
			(SiteNavigationMenuItem)importedStagedModel;
	}

}