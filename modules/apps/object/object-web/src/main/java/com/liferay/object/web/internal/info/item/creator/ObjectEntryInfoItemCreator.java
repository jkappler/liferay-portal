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

package com.liferay.object.web.internal.info.item.creator;

import com.liferay.depot.service.DepotEntryLocalService;
import com.liferay.info.field.InfoField;
import com.liferay.info.item.InfoItemReference;
import com.liferay.object.constants.ObjectConstants;
import com.liferay.object.constants.ObjectFieldConstants;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.model.ObjectField;
import com.liferay.object.rest.manager.v1_0.ObjectEntryManagerServicesTracker;
import com.liferay.object.scope.ObjectScopeProvider;
import com.liferay.object.scope.ObjectScopeProviderRegistry;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectEntryService;
import com.liferay.object.service.ObjectFieldLocalService;
import com.liferay.object.web.internal.info.item.ObjectEntryInfoItemFields;
import com.liferay.info.exception.InfoFormException;
import com.liferay.info.field.InfoFieldValue;
import com.liferay.info.item.InfoItemFieldValues;
import com.liferay.info.item.creator.InfoItemCreator;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.text.ParseException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.util.GroupUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

import javax.ws.rs.BadRequestException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/**
 * @author Rubén Pulido
 */
public class ObjectEntryInfoItemCreator implements InfoItemCreator<ObjectEntry> {

	public ObjectEntryInfoItemCreator(
		GroupLocalService groupLocalService,
		ObjectDefinition objectDefinition,
		ObjectDefinitionLocalService objectDefinitionLocalService,
		ObjectEntryLocalService objectEntryLocalService,
		ObjectEntryService objectEntryService,
		ObjectFieldLocalService objectFieldLocalService,
		ObjectScopeProviderRegistry objectScopeProviderRegistry,
		PortletResourcePermission portletResourcePermission
	) {
		_groupLocalService = groupLocalService;
		_objectDefinition = objectDefinition;
		_objectDefinitionLocalService = objectDefinitionLocalService;
		_objectEntryLocalService = objectEntryLocalService;
		_objectEntryService = objectEntryService;
		_objectFieldLocalService = objectFieldLocalService;
		_objectScopeProviderRegistry = objectScopeProviderRegistry;
		_portletResourcePermission = portletResourcePermission;
	}

	private long _getGroupId(
		ObjectDefinition objectDefinition, String scopeKey) {

		ObjectScopeProvider objectScopeProvider =
			_objectScopeProviderRegistry.getObjectScopeProvider(
				objectDefinition.getScope());

		if (objectScopeProvider.isGroupAware()) {
			if (Objects.equals("site", objectDefinition.getScope())) {
				return GroupUtil.getGroupId(
					objectDefinition.getCompanyId(), scopeKey,
					_groupLocalService);
			}

			return GroupUtil.getDepotGroupId(
				scopeKey, objectDefinition.getCompanyId(),
				_depotEntryLocalService, _groupLocalService);
		}

		return 0;
	}


	private Date _toDate(Locale locale, String valueString) {
		if (Validator.isNull(valueString)) {
			return null;
		}

		try {
			return DateUtil.parseDate(
				"yyyy-MM-dd'T'HH:mm:ss'Z'", valueString, locale);
		}
		catch (ParseException parseException1) {
			try {
				return DateUtil.parseDate("yyyy-MM-dd", valueString, locale);
			}
			catch (ParseException parseException2) {
				throw new BadRequestException(
					"Unable to parse date that does not conform to ISO-8601",
					parseException2);
			}
		}
	}

	private Map<String, Serializable> _toObjectValues(
		long objectDefinitionId, Map<String, Object> properties,
		Locale locale) {

		List<ObjectField> objectFields =
			_objectFieldLocalService.getObjectFields(objectDefinitionId);

		Map<String, Serializable> values = new HashMap<>();

		for (ObjectField objectField : objectFields) {
			String name = objectField.getName();

			Object object = properties.get(name);

			if (object == null) {
				continue;
			}

			if (Objects.equals(
					objectField.getDBType(),
					ObjectFieldConstants.DB_TYPE_DATE)) {

				values.put(name, _toDate(locale, String.valueOf(object)));
			}

			if (objectField.getListTypeDefinitionId() != 0) {
				Map<String, String> map = (HashMap<String, String>)object;

				values.put(name, map.get("key"));
			}
			else {
				values.put(name, (Serializable)object);
			}
		}

		return values;
	}

	public ObjectEntry _addObjectEntry(
		InfoItemFieldValues infoItemFieldValues) throws PortalException {

//		ObjectDefinition objectDefinition, ObjectEntry objectEntry,
//		String scopeKey)

//		return _objectEntryService.addObjectEntry(
//				_getGroupId(objectDefinition, scopeKey),
//				objectDefinition.getObjectDefinitionId(),
//				_toObjectValues(
//					objectDefinition.getObjectDefinitionId(),
//					objectEntry.getProperties(),
//					dtoConverterContext.getLocale()),
//				_createServiceContext(
//					objectEntry.getProperties(),
//					dtoConverterContext.getUserId()));

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		ThemeDisplay themeDisplay = serviceContext.getThemeDisplay();

//		InfoItemReference infoItemReference =
//			infoItemFieldValues.getInfoItemReference();
//
//		String className = infoItemReference.getClassName();
//
//		String[] parts = className.split(StringPool.POUND);
//
//		ObjectDefinition objectDefinition =
//			_objectDefinitionLocalService.fetchObjectDefinition(Long.parseLong(
//				parts[1]));

		Collection<InfoFieldValue<Object>> infoFieldValues =
			infoItemFieldValues.getInfoFieldValues();

		HashMapBuilder.HashMapWrapper<String, Serializable>
			hashMapWrapper = HashMapBuilder.create(infoFieldValues.size());

		for (InfoFieldValue<Object> infoFieldValue : infoFieldValues) {
			InfoField infoField = infoFieldValue.getInfoField();

			hashMapWrapper.put(
				infoField.getName(),
				(Serializable) infoFieldValue.getValue(themeDisplay.getLocale())
			);
		}

		return _objectEntryLocalService.addObjectEntry(
			themeDisplay.getUserId(), _getGroupId(
				_objectDefinition,
				String.valueOf(themeDisplay.getScopeGroupId())),
			_objectDefinition.getObjectDefinitionId(),
			hashMapWrapper.build(), serviceContext);
//			HashMapBuilder.<String, Serializable>put(
//				"able", 10 + i
//			).put(
//				"baker", (i % 2) == 0
//			).put(
//				"charlie", new Date()
//			).put(
//				"dog",
//				"The quick brown fox jumps over the lazy dog. " + i + "!"
//			).put(
//				"easy", "test" + i
//			).put(
//				"fox",
//				"The english brown fox trusted the lazy dog. " + i + "!"
//			).put(
//				"george",
//				"The unsearchable brown fox jumps over the lazy dog. " + i
//			).put(
//				"how", 180.5D + i
//			).put(
//				"item", 5 + i
//			).put(
//				"jig", BigDecimal.valueOf(45L + i)
//			).build(),);

	}

	@Override
	public ObjectEntry createFromInfoItemFieldValues(
			InfoItemFieldValues infoItemFieldValues)
		throws InfoFormException {

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		ThemeDisplay themeDisplay = serviceContext.getThemeDisplay();

//		try {
//			_portletResourcePermission.check(
//				themeDisplay.getPermissionChecker(),
//				serviceContext.getScopeGroupId(), ActionKeys.ADD_ENTRY);
//		}
//		catch (PrincipalException principalException) {
//			throw new InfoFormException();
//		}

		try {
			return _addObjectEntry(infoItemFieldValues);
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException);
			}

			throw new InfoFormException();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ObjectEntryInfoItemCreator.class);

	private DepotEntryLocalService _depotEntryLocalService;

//		policy = ReferencePolicy.DYNAMIC,
//		policyOption = ReferencePolicyOption.GREEDY,
//		target = "(resource.name=" + ObjectConstants.RESOURCE_NAME + ")"
//	)
//	private volatile PortletResourcePermission _portletResourcePermission;
	private PortletResourcePermission _portletResourcePermission;

	private ObjectDefinition _objectDefinition;

	private GroupLocalService _groupLocalService;

	private ObjectDefinitionLocalService _objectDefinitionLocalService;

	private ObjectEntryLocalService _objectEntryLocalService;

	private ObjectEntryService _objectEntryService;

	private ObjectFieldLocalService _objectFieldLocalService;

	private ObjectScopeProviderRegistry _objectScopeProviderRegistry;

}