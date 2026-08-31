/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.cms.internal.links;

import com.liferay.object.model.ObjectEntryTable;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.petra.sql.dsl.DSLQueryFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.search.aggregation.Aggregations;
import com.liferay.portal.search.aggregation.bucket.Bucket;
import com.liferay.portal.search.aggregation.bucket.TermsAggregation;
import com.liferay.portal.search.aggregation.bucket.TermsAggregationResult;
import com.liferay.portal.search.searcher.SearchRequestBuilderFactory;
import com.liferay.portal.search.searcher.SearchResponse;
import com.liferay.portal.search.searcher.Searcher;
import com.liferay.site.cms.site.initializer.util.CMSOutboundLinksUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Jürgen Kappler
 */
public class SimilarLinkSearcher {

	public SimilarLinkSearcher(
		Aggregations aggregations,
		ObjectEntryLocalService objectEntryLocalService, Searcher searcher,
		SearchRequestBuilderFactory searchRequestBuilderFactory) {

		_aggregations = aggregations;
		_objectEntryLocalService = objectEntryLocalService;
		_searcher = searcher;
		_searchRequestBuilderFactory = searchRequestBuilderFactory;
	}

	public List<Bucket> getBuckets(long companyId, Long[] groupIds) {
		TermsAggregation termsAggregation = _aggregations.terms(
			_OUTBOUND_LINKS_AGGREGATION_NAME, "outboundLinks");

		termsAggregation.setMinDocCount(_MINIMUM_REFERRING_ASSETS);
		termsAggregation.setSize(_MAXIMUM_BUCKETS);

		SearchResponse searchResponse = _searcher.search(
			OutboundLinksSearchUtil.getSearchRequestBuilder(
				OutboundLinksSearchUtil.getCMSAssetsBooleanQuery(), companyId,
				groupIds, _searchRequestBuilderFactory
			).addAggregation(
				termsAggregation
			).size(
				0
			).build());

		TermsAggregationResult termsAggregationResult =
			(TermsAggregationResult)searchResponse.getAggregationResult(
				_OUTBOUND_LINKS_AGGREGATION_NAME);

		if (termsAggregationResult == null) {
			return Collections.emptyList();
		}

		Collection<Bucket> buckets = termsAggregationResult.getBuckets();

		if ((buckets.size() >= _MAXIMUM_BUCKETS) && _log.isWarnEnabled()) {
			_log.warn(
				StringBundler.concat(
					"Similar links counts only the first ", _MAXIMUM_BUCKETS,
					" shared references"));
		}

		long maximumReferringAssets = Math.max(
			_MINIMUM_REFERRING_ASSETS,
			(searchResponse.getCount() * _MAXIMUM_REFERRING_ASSETS_PERCENTAGE) /
				100);

		return TransformUtil.transform(
			buckets,
			bucket -> {
				if (bucket.getDocCount() > maximumReferringAssets) {
					return null;
				}

				return bucket;
			});
	}

	public Map<String, Long> getObjectEntryIdsMap(
		long companyId, Long[] objectDefinitionIds,
		Set<String> outboundLinkTokens, Long[] spaceGroupIds) {

		Map<String, Long> objectEntryIdsMap = new LinkedHashMap<>();

		Set<String> externalReferenceCodes = new LinkedHashSet<>();

		for (String outboundLinkToken : outboundLinkTokens) {
			long objectEntryId = CMSOutboundLinksUtil.getObjectEntryId(
				outboundLinkToken);

			if (objectEntryId != 0) {
				objectEntryIdsMap.put(outboundLinkToken, objectEntryId);

				continue;
			}

			String externalReferenceCode =
				CMSOutboundLinksUtil.getObjectEntryExternalReferenceCode(
					outboundLinkToken);

			if (externalReferenceCode != null) {
				externalReferenceCodes.add(externalReferenceCode);
			}
		}

		if (externalReferenceCodes.isEmpty()) {
			return objectEntryIdsMap;
		}

		for (Object[] objects :
				_getObjectEntryObjectsList(
					companyId, externalReferenceCodes, objectDefinitionIds,
					spaceGroupIds)) {

			objectEntryIdsMap.put(
				CMSOutboundLinksUtil.getObjectEntryExternalReferenceCodeToken(
					GetterUtil.getString(objects[0])),
				GetterUtil.getLong(objects[1]));
		}

		return objectEntryIdsMap;
	}

	private List<Object[]> _getObjectEntryObjectsList(
		long companyId, Set<String> externalReferenceCodes,
		Long[] objectDefinitionIds, Long[] spaceGroupIds) {

		return _objectEntryLocalService.dslQuery(
			DSLQueryFactoryUtil.select(
				ObjectEntryTable.INSTANCE.externalReferenceCode,
				ObjectEntryTable.INSTANCE.objectEntryId
			).from(
				ObjectEntryTable.INSTANCE
			).where(
				ObjectEntryTable.INSTANCE.companyId.eq(
					companyId
				).and(
					ObjectEntryTable.INSTANCE.externalReferenceCode.in(
						externalReferenceCodes.toArray(new String[0]))
				).and(
					ObjectEntryTable.INSTANCE.groupId.in(spaceGroupIds)
				).and(
					ObjectEntryTable.INSTANCE.objectDefinitionId.in(
						objectDefinitionIds)
				)
			).orderBy(
				ObjectEntryTable.INSTANCE.objectEntryId.ascending()
			));
	}

	private static final int _MAXIMUM_BUCKETS = 10000;

	private static final int _MAXIMUM_REFERRING_ASSETS_PERCENTAGE = 50;

	private static final int _MINIMUM_REFERRING_ASSETS = 2;

	private static final String _OUTBOUND_LINKS_AGGREGATION_NAME =
		"outboundLinks";

	private static final Log _log = LogFactoryUtil.getLog(
		SimilarLinkSearcher.class);

	private final Aggregations _aggregations;
	private final ObjectEntryLocalService _objectEntryLocalService;
	private final Searcher _searcher;
	private final SearchRequestBuilderFactory _searchRequestBuilderFactory;

}