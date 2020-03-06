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

package com.liferay.headless.delivery.client.serdes.v1_0;

import com.liferay.headless.delivery.client.dto.v1_0.AllowedFragment;
import com.liferay.headless.delivery.client.dto.v1_0.FragmentSettingsWhitelist;
import com.liferay.headless.delivery.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Stream;

import javax.annotation.Generated;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public class FragmentSettingsWhitelistSerDes {

	public static FragmentSettingsWhitelist toDTO(String json) {
		FragmentSettingsWhitelistJSONParser
			fragmentSettingsWhitelistJSONParser =
				new FragmentSettingsWhitelistJSONParser();

		return fragmentSettingsWhitelistJSONParser.parseToDTO(json);
	}

	public static FragmentSettingsWhitelist[] toDTOs(String json) {
		FragmentSettingsWhitelistJSONParser
			fragmentSettingsWhitelistJSONParser =
				new FragmentSettingsWhitelistJSONParser();

		return fragmentSettingsWhitelistJSONParser.parseToDTOs(json);
	}

	public static String toJSON(
		FragmentSettingsWhitelist fragmentSettingsWhitelist) {

		if (fragmentSettingsWhitelist == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (fragmentSettingsWhitelist.getAllowedFragments() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"allowedFragments\": ");

			sb.append("[");

			for (int i = 0;
				 i < fragmentSettingsWhitelist.getAllowedFragments().length;
				 i++) {

				sb.append(
					String.valueOf(
						fragmentSettingsWhitelist.getAllowedFragments()[i]));

				if ((i + 1) <
						fragmentSettingsWhitelist.
							getAllowedFragments().length) {

					sb.append(", ");
				}
			}

			sb.append("]");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		FragmentSettingsWhitelistJSONParser
			fragmentSettingsWhitelistJSONParser =
				new FragmentSettingsWhitelistJSONParser();

		return fragmentSettingsWhitelistJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		FragmentSettingsWhitelist fragmentSettingsWhitelist) {

		if (fragmentSettingsWhitelist == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (fragmentSettingsWhitelist.getAllowedFragments() == null) {
			map.put("allowedFragments", null);
		}
		else {
			map.put(
				"allowedFragments",
				String.valueOf(
					fragmentSettingsWhitelist.getAllowedFragments()));
		}

		return map;
	}

	public static class FragmentSettingsWhitelistJSONParser
		extends BaseJSONParser<FragmentSettingsWhitelist> {

		@Override
		protected FragmentSettingsWhitelist createDTO() {
			return new FragmentSettingsWhitelist();
		}

		@Override
		protected FragmentSettingsWhitelist[] createDTOArray(int size) {
			return new FragmentSettingsWhitelist[size];
		}

		@Override
		protected void setField(
			FragmentSettingsWhitelist fragmentSettingsWhitelist,
			String jsonParserFieldName, Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "allowedFragments")) {
				if (jsonParserFieldValue != null) {
					fragmentSettingsWhitelist.setAllowedFragments(
						Stream.of(
							toStrings((Object[])jsonParserFieldValue)
						).map(
							object -> AllowedFragmentSerDes.toDTO(
								(String)object)
						).toArray(
							size -> new AllowedFragment[size]
						));
				}
			}
			else {
				throw new IllegalArgumentException(
					"Unsupported field name " + jsonParserFieldName);
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\":");

			Object value = entry.getValue();

			Class<?> valueClass = value.getClass();

			if (value instanceof Map) {
				sb.append(_toJSON((Map)value));
			}
			else if (valueClass.isArray()) {
				Object[] values = (Object[])value;

				sb.append("[");

				for (int i = 0; i < values.length; i++) {
					sb.append("\"");
					sb.append(_escape(values[i]));
					sb.append("\"");

					if ((i + 1) < values.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else {
				sb.append("\"");
				sb.append(_escape(entry.getValue()));
				sb.append("\"");
			}

			if (iterator.hasNext()) {
				sb.append(",");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}