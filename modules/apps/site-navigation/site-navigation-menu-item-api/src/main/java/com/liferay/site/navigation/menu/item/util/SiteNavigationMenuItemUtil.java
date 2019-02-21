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

package com.liferay.site.navigation.menu.item.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.PropertiesParamUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.site.navigation.model.SiteNavigationMenuItem;

import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.portlet.PortletRequest;

/**
 * @author Pavel Savinov
 */
public class SiteNavigationMenuItemUtil {

	public static UnicodeProperties getSiteNavigationMenuItemProperties(
		PortletRequest portletRequest, String prefix,
		String[] localizedParameters) {

		UnicodeProperties unicodeProperties = PropertiesParamUtil.getProperties(
			portletRequest, prefix);

		for (String localizedParameter : localizedParameters) {
			Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
				portletRequest, localizedParameter);

			if (MapUtil.isEmpty(map)) {
				continue;
			}

			for (Map.Entry<Locale, String> entry : map.entrySet()) {
				String value = entry.getValue();

				if (Validator.isNull(value)) {
					continue;
				}

				String key =
					localizedParameter + "_" +
						LocaleUtil.toLanguageId(entry.getKey());

				unicodeProperties.setProperty(key, value);
			}
		}

		return unicodeProperties;
	}

	public static String getSiteNavigationMenuItemXML(
		SiteNavigationMenuItem siteNavigationMenuItem, String name) {

		if (siteNavigationMenuItem == null) {
			return StringPool.BLANK;
		}

		UnicodeProperties typeSettingsProperties = new UnicodeProperties();

		typeSettingsProperties.fastLoad(
			siteNavigationMenuItem.getTypeSettings());

		Set<Locale> availableLocales = LanguageUtil.getAvailableLocales(
			siteNavigationMenuItem.getGroupId());

		Stream<Locale> stream = availableLocales.stream();

		Map<String, String> map = stream.map(
			locale -> LocaleUtil.toLanguageId(locale)
		).collect(
			Collectors.toMap(
				languageId -> languageId,
				languageId -> typeSettingsProperties.getProperty(
					name + "_" + languageId, StringPool.BLANK))
		);

		return LocalizationUtil.getXml(
			map, LocaleUtil.toLanguageId(LocaleUtil.getMostRelevantLocale()),
			name);
	}

}