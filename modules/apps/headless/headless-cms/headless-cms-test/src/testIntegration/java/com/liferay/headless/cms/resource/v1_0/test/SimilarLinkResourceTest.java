/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.cms.resource.v1_0.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.depot.constants.DepotConstants;
import com.liferay.depot.model.DepotEntry;
import com.liferay.depot.service.DepotEntryLocalService;
import com.liferay.headless.cms.client.dto.v1_0.SimilarLink;
import com.liferay.headless.cms.client.pagination.Page;
import com.liferay.headless.cms.client.pagination.Pagination;
import com.liferay.headless.cms.resource.v1_0.test.util.CMSFreeTierTestUtil;
import com.liferay.headless.cms.resource.v1_0.test.util.CMSOutboundLinkTestUtil;
import com.liferay.object.constants.ObjectDefinitionConstants;
import com.liferay.object.constants.ObjectDefinitionSettingConstants;
import com.liferay.object.constants.ObjectFolderConstants;
import com.liferay.object.field.builder.RichTextObjectFieldBuilder;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.model.ObjectEntryFolder;
import com.liferay.object.model.ObjectField;
import com.liferay.object.model.ObjectFolder;
import com.liferay.object.model.ObjectRelationship;
import com.liferay.object.relationship.util.ObjectRelationshipUtil;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectDefinitionSettingLocalService;
import com.liferay.object.service.ObjectEntryFolderLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectFolderLocalService;
import com.liferay.object.service.ObjectRelationshipLocalService;
import com.liferay.object.test.util.ObjectDefinitionTestUtil;
import com.liferay.object.test.util.ObjectRelationshipTestUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.rule.FeatureFlag;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.portal.vulcan.util.LocalizedMapUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Jürgen Kappler
 */
@FeatureFlag("LPD-82226")
@RunWith(Arquillian.class)
public class SimilarLinkResourceTest extends BaseSimilarLinkResourceTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE,
			SynchronousDestinationTestRule.INSTANCE);

	@Override
	@Test
	public void testEscapeRegexInStringFields() throws Exception {
	}

	@Override
	@Test
	public void testGetSimilarLinksPage() throws Exception {
		_testGetSimilarLinksPage();
		_testGetSimilarLinksPageWithFreeTier();
		_testGetSimilarLinksPageWithRelationshipReferences();
		_testGetSimilarLinksPageWithUniqueReference();
	}

	@Override
	@Test
	public void testGetSimilarLinksPageWithPagination() throws Exception {
		DepotEntry depotEntry = _addSpaceDepotEntry(
			ServiceContextTestUtil.getServiceContext());

		ObjectDefinition objectDefinition =
			_getBasicWebContentObjectDefinition();

		_addUnrelatedObjectEntries(depotEntry, objectDefinition);

		_addSharedTargetObjectEntry(depotEntry, objectDefinition, 2);
		_addSharedTargetObjectEntry(depotEntry, objectDefinition, 2);

		_assertPage(depotEntry, Pagination.of(1, 1), 2, 1);
		_assertPage(depotEntry, Pagination.of(2, 1), 2, 1);
		_assertPage(depotEntry, Pagination.of(1, 20), 2, 2);
	}

	private ObjectDefinition _addCMSObjectDefinition() throws Exception {
		ObjectFolder objectFolder =
			_objectFolderLocalService.getObjectFolderByExternalReferenceCode(
				ObjectFolderConstants.
					EXTERNAL_REFERENCE_CODE_CONTENT_STRUCTURES,
				TestPropsValues.getCompanyId());

		ObjectDefinition objectDefinition =
			ObjectDefinitionTestUtil.publishObjectDefinition(
				true, false, false, ObjectDefinitionTestUtil.getRandomName(),
				Collections.singletonList(_buildRichTextObjectField()),
				objectFolder.getObjectFolderId(),
				ObjectDefinitionConstants.SCOPE_DEPOT,
				TestPropsValues.getUserId());

		_objectDefinitionSettingLocalService.addObjectDefinitionSetting(
			TestPropsValues.getUserId(),
			objectDefinition.getObjectDefinitionId(),
			ObjectDefinitionSettingConstants.NAME_ACCEPT_ALL_GROUPS, "true");

		_objectDefinitions.add(objectDefinition);

		return objectDefinition;
	}

	private ObjectEntry _addObjectEntry(
			DepotEntry depotEntry, ObjectDefinition objectDefinition,
			Map<String, Serializable> values)
		throws Exception {

		ObjectEntryFolder objectEntryFolder =
			_objectEntryFolderLocalService.
				getObjectEntryFolderByExternalReferenceCode(
					"L_CONTENTS", depotEntry.getGroupId(),
					depotEntry.getCompanyId());

		return _objectEntryLocalService.addObjectEntry(
			depotEntry.getGroupId(), depotEntry.getUserId(),
			objectDefinition.getObjectDefinitionId(),
			objectEntryFolder.getObjectEntryFolderId(), "en_US", values,
			ServiceContextTestUtil.getServiceContext());
	}

	private ObjectEntry _addObjectEntry(
			DepotEntry depotEntry, ObjectDefinition objectDefinition,
			String content)
		throws Exception {

		ObjectEntryFolder objectEntryFolder =
			_objectEntryFolderLocalService.
				getObjectEntryFolderByExternalReferenceCode(
					"L_CONTENTS", depotEntry.getGroupId(),
					depotEntry.getCompanyId());

		return _objectEntryLocalService.addObjectEntry(
			depotEntry.getGroupId(), depotEntry.getUserId(),
			objectDefinition.getObjectDefinitionId(),
			objectEntryFolder.getObjectEntryFolderId(), "en_US",
			HashMapBuilder.<String, Serializable>put(
				"content_i18n",
				HashMapBuilder.put(
					"en_US", content
				).build()
			).put(
				"title_i18n",
				HashMapBuilder.put(
					"en_US", RandomTestUtil.randomString()
				).build()
			).build(),
			ServiceContextTestUtil.getServiceContext());
	}

	private ObjectEntry _addSharedTargetObjectEntry(
			DepotEntry depotEntry, ObjectDefinition objectDefinition,
			int referringAssetsCount)
		throws Exception {

		ObjectEntry targetObjectEntry = _addObjectEntry(
			depotEntry, objectDefinition, RandomTestUtil.randomString());

		String imageHTML = CMSOutboundLinkTestUtil.getImageHTML(
			targetObjectEntry.getExternalReferenceCode());

		for (int i = 0; i < referringAssetsCount; i++) {
			_addObjectEntry(depotEntry, objectDefinition, imageHTML);
		}

		return targetObjectEntry;
	}

	private DepotEntry _addSpaceDepotEntry(ServiceContext serviceContext)
		throws Exception {

		DepotEntry depotEntry = _depotEntryLocalService.addDepotEntry(
			HashMapBuilder.put(
				LocaleUtil.getDefault(), StringUtil.randomString()
			).build(),
			HashMapBuilder.put(
				LocaleUtil.getDefault(), StringUtil.randomString()
			).build(),
			DepotConstants.TYPE_SPACE, serviceContext);

		_depotEntries.add(depotEntry);

		return depotEntry;
	}

	private void _addUnrelatedObjectEntries(
			DepotEntry depotEntry, ObjectDefinition objectDefinition)
		throws Exception {

		for (int i = 0; i < 6; i++) {
			_addObjectEntry(
				depotEntry, objectDefinition, RandomTestUtil.randomString());
		}
	}

	private void _assertPage(
			DepotEntry depotEntry, Pagination pagination,
			long expectedTotalCount, int expectedSize)
		throws Exception {

		Page<SimilarLink> similarLinksPage =
			similarLinkResource.getSimilarLinksPage(
				depotEntry.getDepotEntryId(), pagination);

		Assert.assertEquals(
			expectedTotalCount, similarLinksPage.getTotalCount());

		List<SimilarLink> similarLinks =
			(List<SimilarLink>)similarLinksPage.getItems();

		Assert.assertEquals(
			similarLinks.toString(), expectedSize, similarLinks.size());
	}

	private ObjectField _buildRichTextObjectField() throws Exception {
		return new RichTextObjectFieldBuilder(
		).indexed(
			true
		).labelMap(
			LocalizedMapUtil.getLocalizedMap(RandomTestUtil.randomString())
		).name(
			"content"
		).userId(
			TestPropsValues.getUserId()
		).build();
	}

	private ObjectDefinition _getBasicWebContentObjectDefinition()
		throws Exception {

		return _objectDefinitionLocalService.
			getObjectDefinitionByExternalReferenceCode(
				"L_CMS_BASIC_WEB_CONTENT", TestPropsValues.getCompanyId());
	}

	private SimilarLink _getSingleSimilarLink(DepotEntry depotEntry)
		throws Exception {

		Page<SimilarLink> similarLinksPage =
			similarLinkResource.getSimilarLinksPage(
				depotEntry.getDepotEntryId(), null);

		Assert.assertEquals(1, similarLinksPage.getTotalCount());

		List<SimilarLink> similarLinks =
			(List<SimilarLink>)similarLinksPage.getItems();

		return similarLinks.get(0);
	}

	private void _testGetSimilarLinksPage() throws Exception {
		DepotEntry depotEntry = _addSpaceDepotEntry(
			ServiceContextTestUtil.getServiceContext());

		ObjectDefinition objectDefinition =
			_getBasicWebContentObjectDefinition();

		_addUnrelatedObjectEntries(depotEntry, objectDefinition);

		ObjectEntry targetObjectEntry = _addSharedTargetObjectEntry(
			depotEntry, objectDefinition, 2);

		SimilarLink similarLink = _getSingleSimilarLink(depotEntry);

		Assert.assertEquals(
			targetObjectEntry.getObjectEntryId(),
			GetterUtil.getLong(similarLink.getId()));
		Assert.assertEquals(
			targetObjectEntry.getTitleValue("en_US", true),
			similarLink.getTitle());
		Assert.assertEquals(
			2, GetterUtil.getLong(similarLink.getReferringAssetsCount()));

		Assert.assertTrue(
			StringUtil.endsWith(
				similarLink.getHref(),
				"&objectEntryId=" + targetObjectEntry.getObjectEntryId()));

		Map<String, Map<String, String>> actions = similarLink.getActions();

		Assert.assertNotNull(actions.get("update"));
		Assert.assertNotNull(actions.get("viewUsages"));

		_addObjectEntry(
			depotEntry, objectDefinition,
			CMSOutboundLinkTestUtil.getImageHTML(
				targetObjectEntry.getExternalReferenceCode()));

		similarLink = _getSingleSimilarLink(depotEntry);

		Assert.assertEquals(
			3, GetterUtil.getLong(similarLink.getReferringAssetsCount()));
	}

	private void _testGetSimilarLinksPageWithFreeTier() throws Exception {
		try (AutoCloseable autoCloseable = CMSFreeTierTestUtil.withFreeTier()) {
			assertHttpResponseStatusCode(
				400,
				similarLinkResource.getSimilarLinksPageHttpResponse(
					null, Pagination.of(1, 20)));
		}

		assertHttpResponseStatusCode(
			200,
			similarLinkResource.getSimilarLinksPageHttpResponse(
				null, Pagination.of(1, 20)));
	}

	private void _testGetSimilarLinksPageWithRelationshipReferences()
		throws Exception {

		DepotEntry depotEntry = _addSpaceDepotEntry(
			ServiceContextTestUtil.getServiceContext());

		ObjectDefinition objectDefinition =
			_getBasicWebContentObjectDefinition();

		_addUnrelatedObjectEntries(depotEntry, objectDefinition);

		ObjectEntry targetObjectEntry = _addObjectEntry(
			depotEntry, objectDefinition, RandomTestUtil.randomString());

		ObjectDefinition referringObjectDefinition = _addCMSObjectDefinition();

		ObjectRelationship objectRelationship =
			ObjectRelationshipTestUtil.addObjectRelationship(
				_objectRelationshipLocalService, objectDefinition,
				referringObjectDefinition);

		String objectRelationshipFieldName =
			ObjectRelationshipUtil.getObjectRelationshipFieldName(
				objectDefinition, objectRelationship.getName());

		for (int i = 0; i < 2; i++) {
			_addObjectEntry(
				depotEntry, referringObjectDefinition,
				HashMapBuilder.<String, Serializable>put(
					objectRelationshipFieldName,
					targetObjectEntry.getObjectEntryId()
				).build());
		}

		SimilarLink similarLink = _getSingleSimilarLink(depotEntry);

		Assert.assertEquals(
			targetObjectEntry.getObjectEntryId(),
			GetterUtil.getLong(similarLink.getId()));
		Assert.assertEquals(
			2, GetterUtil.getLong(similarLink.getReferringAssetsCount()));

		_addObjectEntry(
			depotEntry, objectDefinition,
			CMSOutboundLinkTestUtil.getImageHTML(
				targetObjectEntry.getExternalReferenceCode()));

		similarLink = _getSingleSimilarLink(depotEntry);

		Assert.assertEquals(
			3, GetterUtil.getLong(similarLink.getReferringAssetsCount()));
	}

	private void _testGetSimilarLinksPageWithUniqueReference()
		throws Exception {

		DepotEntry depotEntry = _addSpaceDepotEntry(
			ServiceContextTestUtil.getServiceContext());

		ObjectDefinition objectDefinition =
			_getBasicWebContentObjectDefinition();

		_addUnrelatedObjectEntries(depotEntry, objectDefinition);

		_addSharedTargetObjectEntry(depotEntry, objectDefinition, 1);

		_assertPage(depotEntry, null, 0, 0);
	}

	@DeleteAfterTestRun
	private final List<DepotEntry> _depotEntries = new ArrayList<>();

	@Inject
	private DepotEntryLocalService _depotEntryLocalService;

	@Inject
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

	@DeleteAfterTestRun
	private final List<ObjectDefinition> _objectDefinitions = new ArrayList<>();

	@Inject
	private ObjectDefinitionSettingLocalService
		_objectDefinitionSettingLocalService;

	@Inject
	private ObjectEntryFolderLocalService _objectEntryFolderLocalService;

	@Inject
	private ObjectEntryLocalService _objectEntryLocalService;

	@Inject
	private ObjectFolderLocalService _objectFolderLocalService;

	@Inject
	private ObjectRelationshipLocalService _objectRelationshipLocalService;

}