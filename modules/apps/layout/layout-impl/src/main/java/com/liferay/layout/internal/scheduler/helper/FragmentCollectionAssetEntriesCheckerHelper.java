/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.internal.scheduler.helper;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.list.asset.entry.provider.AssetListAssetEntryProvider;
import com.liferay.asset.list.model.AssetListEntry;
import com.liferay.asset.list.service.AssetListEntryLocalService;
import com.liferay.asset.list.service.AssetListEntrySegmentsEntryRelLocalService;
import com.liferay.info.pagination.InfoPage;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructure;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructureRel;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureLocalService;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureRelLocalService;
import com.liferay.layout.util.LayoutServiceContextHelperUtil;
import com.liferay.layout.util.structure.CollectionStyledLayoutStructureItem;
import com.liferay.layout.util.structure.LayoutStructure;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.EscapableLocalizableFunction;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.SubscriptionSender;
import com.liferay.portlet.asset.service.permission.AssetEntryPermission;
import com.liferay.segments.configuration.provider.SegmentsConfigurationProvider;
import com.liferay.segments.constants.SegmentsEntryConstants;
import com.liferay.subscription.model.Subscription;
import com.liferay.subscription.service.SubscriptionLocalService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(service = FragmentCollectionAssetEntriesCheckerHelper.class)
public class FragmentCollectionAssetEntriesCheckerHelper {

	public void checkFragmentCollectionAssetEntries() throws Exception {
		_companyLocalService.forEachCompanyId(
			companyId -> {
				ActionableDynamicQuery actionableDynamicQuery =
					_layoutPageTemplateStructureRelLocalService.
						getActionableDynamicQuery();

				actionableDynamicQuery.setCompanyId(companyId);

				actionableDynamicQuery.setPerformActionMethod(
					(LayoutPageTemplateStructureRel
						layoutPageTemplateStructureRel) -> _checkAssetEntries(
							layoutPageTemplateStructureRel));

				actionableDynamicQuery.performActions();
			});
	}

	private void _checkAssetEntries(
			LayoutPageTemplateStructureRel layoutPageTemplateStructureRel)
		throws PortalException {

		LayoutPageTemplateStructure layoutPageTemplateStructure =
			_layoutPageTemplateStructureLocalService.
				fetchLayoutPageTemplateStructure(
					layoutPageTemplateStructureRel.
						getLayoutPageTemplateStructureId());

		if (layoutPageTemplateStructure == null) {
			return;
		}

		Layout layout = _layoutLocalService.fetchLayout(
			layoutPageTemplateStructure.getPlid());

		if ((layout == null) || layout.isDraftLayout()) {
			return;
		}

		LayoutStructure layoutStructure = LayoutStructure.of(
			layoutPageTemplateStructureRel.getDataJSONObject());

		List<CollectionStyledLayoutStructureItem>
			collectionStyledLayoutStructureItems =
				layoutStructure.getCollectionStyledLayoutStructureItems();

		if (ListUtil.isEmpty(collectionStyledLayoutStructureItems)) {
			return;
		}

		for (CollectionStyledLayoutStructureItem
				collectionStyledLayoutStructureItem :
					collectionStyledLayoutStructureItems) {

			List<AssetEntry> assetEntries =
				_getAssetListEntrySelectedAssetEntries(
					layout,
					collectionStyledLayoutStructureItem.
						getCollectionJSONObject());

			if (assetEntries.isEmpty()) {
				continue;
			}

			ListUtil.distinct(assetEntries);

			// Getting the subscriptions depends on how a subscription is
			// handled for the collection provider. Maybe by structure item ID?

			_notifySubscribers(
				layout, _getLayoutFullURL(layout),
				_subscriptionLocalService.getSubscriptions(
					layout.getCompanyId(), "classNameSubscription", 0),
				assetEntries);
		}
	}

	private List<AssetEntry> _filterAssetEntries(
		long userId, List<AssetEntry> assetEntries) {

		User user = _userLocalService.fetchUser(userId);

		if (user == null) {
			return Collections.emptyList();
		}

		try {
			PermissionChecker permissionChecker =
				PermissionCheckerFactoryUtil.create(user);

			return TransformUtil.transform(
				assetEntries,
				assetEntry -> {
					try {
						if (AssetEntryPermission.contains(
								permissionChecker, assetEntry,
								ActionKeys.VIEW)) {

							return assetEntry;
						}
					}
					catch (Exception exception) {
						if (_log.isDebugEnabled()) {
							_log.debug(exception);
						}
					}

					return null;
				});
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}

			return Collections.emptyList();
		}
	}

	private List<AssetEntry> _getAssetListEntrySelectedAssetEntries(
		Layout layout, JSONObject collectionJSONObject) {

		List<AssetEntry> assetEntries = new ArrayList<>();

		try (AutoCloseable autoCloseable =
				LayoutServiceContextHelperUtil.getServiceContextAutoCloseable(
					layout, _getUser(layout.getCompanyId()))) {

			long classNameId = collectionJSONObject.getLong("classNameId");
			long classPK = collectionJSONObject.getLong("classPK");

			if ((classNameId != _portal.getClassNameId(AssetListEntry.class)) ||
				(classPK == 0)) {

				return Collections.emptyList();
			}

			AssetListEntry assetListEntry =
				_assetListEntryLocalService.fetchAssetListEntry(classPK);

			if (assetListEntry == null) {
				return Collections.emptyList();
			}

			long[] segmentsEntryIds = {SegmentsEntryConstants.ID_DEFAULT};

			try {
				if (_segmentsConfigurationProvider.isSegmentationEnabled(
						layout.getGroupId())) {

					segmentsEntryIds = ArrayUtil.toLongArray(
						TransformUtil.transform(
							_assetListEntrySegmentsEntryRelLocalService.
								getAssetListEntrySegmentsEntryRels(
									assetListEntry.getAssetListEntryId(),
									QueryUtil.ALL_POS, QueryUtil.ALL_POS),
							assetListEntrySegmentsEntryRel ->
								assetListEntrySegmentsEntryRel.
									getSegmentsEntryId()));
				}
			}
			catch (PortalException portalException) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to get segments entry IDs for asset list " +
							"entry " + assetListEntry.getAssetListEntryId());
				}

				if (_log.isDebugEnabled()) {
					_log.debug(portalException);
				}
			}

			InfoPage<AssetEntry> infoPage =
				_assetListAssetEntryProvider.getAssetEntriesInfoPage(
					assetListEntry, segmentsEntryIds, null, null,
					StringPool.BLANK, StringPool.BLANK, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			assetEntries.addAll(infoPage.getPageItems());
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}
		}

		return assetEntries;
	}

	private String _getFriendlyURLPath(Layout layout) {
		if (layout.isPrivateLayout()) {
			return _portal.getPathFriendlyURLPrivateGroup();
		}

		return _portal.getPathFriendlyURLPublic();
	}

	private String _getGroupDescriptiveName(Layout layout, Locale locale) {
		try {
			Group group = _groupLocalService.fetchGroup(layout.getGroupId());

			return group.getDescriptiveName(locale);
		}
		catch (PortalException portalException) {
			_log.error(portalException);
		}

		return StringPool.BLANK;
	}

	private String _getLayoutFullURL(Layout layout) throws PortalException {
		Company company = _companyLocalService.getCompany(
			layout.getCompanyId());
		Group group = _groupLocalService.getGroup(layout.getGroupId());

		return StringBundler.concat(
			company.getPortalURL(layout.getGroupId(), layout.isPrivateLayout()),
			_getFriendlyURLPath(layout), group.getFriendlyURL(),
			layout.getFriendlyURL());
	}

	private SubscriptionSender _getSubscriptionSender(
		Layout layout, String layoutURL, List<AssetEntry> assetEntries) {

		if (assetEntries.isEmpty()) {
			return null;
		}

		AssetEntry assetEntry = assetEntries.get(0);

		String fromAddress = "From email";
		String fromName = "From name";

		Map<Locale, String> localizedBodyMap = new HashMap<>();
		Map<Locale, String> localizedSubjectMap = new HashMap<>();

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		subscriptionSender.setContextAttributes(
			"[$ASSET_ENTRIES$]",
			StringUtil.merge(
				assetEntries,
				entry -> entry.getTitle(LocaleUtil.getSiteDefault()),
				StringPool.COMMA_AND_SPACE));
		subscriptionSender.setEntryURL(layoutURL);
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setGroupId(assetEntry.getGroupId());
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setLocalizedBodyMap(localizedBodyMap);
		subscriptionSender.setLocalizedContextAttribute(
			"[$ASSET_ENTRIES$]",
			new EscapableLocalizableFunction(
				locale -> StringUtil.merge(
					assetEntries, entry -> entry.getTitle(locale),
					StringPool.COMMA_AND_SPACE)));
		subscriptionSender.setLocalizedContextAttribute(
			"[$SITE_NAME$]",
			new EscapableLocalizableFunction(
				locale -> _getGroupDescriptiveName(layout, locale)));
		subscriptionSender.setLocalizedSubjectMap(localizedSubjectMap);
		subscriptionSender.setMailId("asset_entry", assetEntry.getEntryId());
		subscriptionSender.setNotificationType(
			UserNotificationDefinition.NOTIFICATION_TYPE_ADD_ENTRY);
		subscriptionSender.setReplyToAddress(fromAddress);

		return subscriptionSender;
	}

	private User _getUser(long companyId) throws PortalException {
		Role role = _roleLocalService.fetchRole(
			companyId, RoleConstants.ADMINISTRATOR);

		if (role == null) {
			return _userLocalService.getGuestUser(companyId);
		}

		List<User> adminUsers = _userLocalService.getRoleUsers(
			role.getRoleId(), 0, 1);

		if (adminUsers.isEmpty()) {
			return _userLocalService.getGuestUser(companyId);
		}

		return adminUsers.get(0);
	}

	private void _notifySubscribers(
		Layout layout, String layoutURL, List<Subscription> subscriptions,
		List<AssetEntry> assetEntries) {

		Map<List<AssetEntry>, List<User>> assetEntriesToUsersMap =
			new HashMap<>();

		for (Subscription subscription : subscriptions) {
			long userId = subscription.getUserId();

			User user = _userLocalService.fetchUser(userId);

			if ((user == null) || !user.isActive()) {
				continue;
			}

			List<AssetEntry> filteredAssetEntries = _filterAssetEntries(
				userId, assetEntries);

			if (filteredAssetEntries.isEmpty()) {
				continue;
			}

			List<User> users = assetEntriesToUsersMap.get(filteredAssetEntries);

			if (ListUtil.isEmpty(users)) {
				users = new LinkedList<>();

				assetEntriesToUsersMap.put(filteredAssetEntries, users);
			}

			users.add(user);
		}

		for (Map.Entry<List<AssetEntry>, List<User>> entry :
				assetEntriesToUsersMap.entrySet()) {

			SubscriptionSender subscriptionSender = _getSubscriptionSender(
				layout, layoutURL, entry.getKey());

			if (subscriptionSender == null) {
				continue;
			}

			for (User user : entry.getValue()) {
				subscriptionSender.addRuntimeSubscribers(
					user.getEmailAddress(), user.getFullName());
			}

			subscriptionSender.flushNotificationsAsync();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		FragmentCollectionAssetEntriesCheckerHelper.class);

	@Reference
	private AssetListAssetEntryProvider _assetListAssetEntryProvider;

	@Reference
	private AssetListEntryLocalService _assetListEntryLocalService;

	@Reference
	private AssetListEntrySegmentsEntryRelLocalService
		_assetListEntrySegmentsEntryRelLocalService;

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private LayoutPageTemplateStructureLocalService
		_layoutPageTemplateStructureLocalService;

	@Reference
	private LayoutPageTemplateStructureRelLocalService
		_layoutPageTemplateStructureRelLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private SegmentsConfigurationProvider _segmentsConfigurationProvider;

	@Reference
	private SubscriptionLocalService _subscriptionLocalService;

	@Reference
	private UserLocalService _userLocalService;

}