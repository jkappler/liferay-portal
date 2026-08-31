/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.cms.internal.resource.v1_0;

import com.liferay.depot.service.DepotEntryLocalService;
import com.liferay.depot.service.DepotEntryService;
import com.liferay.headless.cms.dto.v1_0.SimilarLink;
import com.liferay.headless.cms.internal.links.SimilarLinkSearcher;
import com.liferay.headless.cms.internal.util.CMSGroupUtil;
import com.liferay.headless.cms.resource.v1_0.SimilarLinkResource;
import com.liferay.object.constants.ObjectFolderConstants;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectDefinitionService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectEntryService;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.feature.flag.FeatureFlagManagerUtil;
import com.liferay.portal.kernel.license.util.LicenseManagerUtil;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.search.aggregation.Aggregations;
import com.liferay.portal.search.aggregation.bucket.Bucket;
import com.liferay.portal.search.searcher.SearchRequestBuilderFactory;
import com.liferay.portal.search.searcher.Searcher;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Jürgen Kappler
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/similar-link.properties",
	scope = ServiceScope.PROTOTYPE, service = SimilarLinkResource.class
)
public class SimilarLinkResourceImpl extends BaseSimilarLinkResourceImpl {

	@Override
	public Page<SimilarLink> getSimilarLinksPage(
			Long assetLibraryId, Pagination pagination)
		throws Exception {

		LicenseManagerUtil.checkFreeTier();

		if (!FeatureFlagManagerUtil.isEnabled(
				contextCompany.getCompanyId(), "LPD-82226")) {

			throw new UnsupportedOperationException();
		}

		Long[] spaceGroupIds = CMSGroupUtil.getSpaceGroupIds(
			contextCompany.getCompanyId(), _depotEntryService,
			contextUser.getUserId());

		Long[] selectedSpaceGroupIds = CMSGroupUtil.getSelectedSpaceGroupIds(
			assetLibraryId, contextCompany.getCompanyId(),
			_depotEntryLocalService, groupLocalService, spaceGroupIds);

		if (ArrayUtil.isEmpty(selectedSpaceGroupIds)) {
			return Page.of(Collections.emptyList());
		}

		List<ObjectDefinition> objectDefinitions =
			_objectDefinitionService.getCMSObjectDefinitions(
				contextCompany.getCompanyId(),
				new String[] {
					ObjectFolderConstants.
						EXTERNAL_REFERENCE_CODE_CONTENT_STRUCTURES,
					ObjectFolderConstants.EXTERNAL_REFERENCE_CODE_FILE_TYPES
				});

		Long[] objectDefinitionIds = transformToArray(
			objectDefinitions, ObjectDefinition::getObjectDefinitionId,
			Long.class);

		if (ArrayUtil.isEmpty(objectDefinitionIds)) {
			return Page.of(Collections.emptyList());
		}

		SimilarLinkSearcher similarLinkSearcher = new SimilarLinkSearcher(
			_aggregations, _objectEntryLocalService, _searcher,
			_searchRequestBuilderFactory);

		List<Bucket> buckets = similarLinkSearcher.getBuckets(
			contextCompany.getCompanyId(), selectedSpaceGroupIds);

		if (buckets.isEmpty()) {
			return Page.of(Collections.emptyList());
		}

		List<Bucket> pageBuckets = ListUtil.subList(
			buckets, pagination.getStartPosition(),
			pagination.getEndPosition());

		Map<String, Long> objectEntryIdsMap =
			similarLinkSearcher.getObjectEntryIdsMap(
				contextCompany.getCompanyId(), objectDefinitionIds,
				_getOutboundLinkTokens(pageBuckets), spaceGroupIds);

		return Page.of(
			transform(
				pageBuckets,
				bucket -> _toSimilarLink(bucket, objectEntryIdsMap)),
			pagination, buckets.size());
	}

	private Map<String, Map<String, String>> _getActions(
			ObjectEntry objectEntry)
		throws PortalException {

		if (objectEntry == null) {
			return Collections.emptyMap();
		}

		ModelResourcePermission<ObjectEntry> modelResourcePermission =
			_objectEntryService.getModelResourcePermission(
				objectEntry.getObjectDefinitionId());

		return HashMapBuilder.<String, Map<String, String>>put(
			"update",
			() -> addAction(
				ActionKeys.UPDATE, objectEntry.getObjectEntryId(),
				"getSimilarLinksPage", modelResourcePermission)
		).put(
			"viewUsages",
			() -> addAction(
				ActionKeys.VIEW, objectEntry.getObjectEntryId(),
				"getSimilarLinksPage", modelResourcePermission)
		).build();
	}

	private String _getHref(ObjectEntry objectEntry) {
		if (objectEntry == null) {
			return null;
		}

		return StringBundler.concat(
			_portal.getPortalURL(contextHttpServletRequest),
			_portal.getPathMain(), GroupConstants.CMS_FRIENDLY_URL,
			"/edit_content_item?p_l_mode=read&p_p_state=",
			LiferayWindowState.POP_UP, "&objectEntryId=",
			objectEntry.getObjectEntryId());
	}

	private long _getObjectEntryId(ObjectEntry objectEntry) {
		if (objectEntry == null) {
			return 0;
		}

		return objectEntry.getObjectEntryId();
	}

	private Set<String> _getOutboundLinkTokens(List<Bucket> buckets) {
		Set<String> outboundLinkTokens = new LinkedHashSet<>();

		for (Bucket bucket : buckets) {
			outboundLinkTokens.add(bucket.getKey());
		}

		return outboundLinkTokens;
	}

	private String _getTitle(ObjectEntry objectEntry) throws PortalException {
		if (objectEntry == null) {
			return null;
		}

		return objectEntry.getTitleValue(
			contextAcceptLanguage.getPreferredLanguageId(), true);
	}

	private SimilarLink _toSimilarLink(
		Bucket bucket, Map<String, Long> objectEntryIdsMap) {

		ObjectEntry objectEntry = _objectEntryLocalService.fetchObjectEntry(
			GetterUtil.getLong(objectEntryIdsMap.get(bucket.getKey())));

		return new SimilarLink() {
			{
				setActions(() -> _getActions(objectEntry));
				setHref(() -> _getHref(objectEntry));
				setId(() -> _getObjectEntryId(objectEntry));
				setReferringAssetsCount(bucket::getDocCount);
				setTitle(() -> _getTitle(objectEntry));
			}
		};
	}

	@Reference
	private Aggregations _aggregations;

	@Reference
	private DepotEntryLocalService _depotEntryLocalService;

	@Reference
	private DepotEntryService _depotEntryService;

	@Reference
	private ObjectDefinitionService _objectDefinitionService;

	@Reference
	private ObjectEntryLocalService _objectEntryLocalService;

	@Reference
	private ObjectEntryService _objectEntryService;

	@Reference
	private Portal _portal;

	@Reference
	private Searcher _searcher;

	@Reference
	private SearchRequestBuilderFactory _searchRequestBuilderFactory;

}