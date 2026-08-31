/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.cms.client.serdes.v1_0;

import com.liferay.headless.cms.client.dto.v1_0.SimilarLink;
import com.liferay.headless.cms.client.json.BaseJSONParser;

import jakarta.annotation.Generated;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author Crescenzo Rega
 * @generated
 */
@Generated("")
public class SimilarLinkSerDes {

	public static SimilarLink toDTO(String json) {
		SimilarLinkJSONParser similarLinkJSONParser =
			new SimilarLinkJSONParser();

		return similarLinkJSONParser.parseToDTO(json);
	}

	public static SimilarLink[] toDTOs(String json) {
		SimilarLinkJSONParser similarLinkJSONParser =
			new SimilarLinkJSONParser();

		return similarLinkJSONParser.parseToDTOs(json);
	}

	public static String toJSON(SimilarLink similarLink) {
		if (similarLink == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (similarLink.getActions() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"actions\": ");

			sb.append(_toJSON(similarLink.getActions()));
		}

		if (similarLink.getHref() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"href\": ");

			sb.append("\"");

			sb.append(_escape(similarLink.getHref()));

			sb.append("\"");
		}

		if (similarLink.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(similarLink.getId());
		}

		if (similarLink.getReferringAssetsCount() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"referringAssetsCount\": ");

			sb.append(similarLink.getReferringAssetsCount());
		}

		if (similarLink.getTitle() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"title\": ");

			sb.append("\"");

			sb.append(_escape(similarLink.getTitle()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		SimilarLinkJSONParser similarLinkJSONParser =
			new SimilarLinkJSONParser();

		return similarLinkJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(SimilarLink similarLink) {
		if (similarLink == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (similarLink.getActions() == null) {
			map.put("actions", null);
		}
		else {
			map.put("actions", String.valueOf(similarLink.getActions()));
		}

		if (similarLink.getHref() == null) {
			map.put("href", null);
		}
		else {
			map.put("href", String.valueOf(similarLink.getHref()));
		}

		if (similarLink.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(similarLink.getId()));
		}

		if (similarLink.getReferringAssetsCount() == null) {
			map.put("referringAssetsCount", null);
		}
		else {
			map.put(
				"referringAssetsCount",
				String.valueOf(similarLink.getReferringAssetsCount()));
		}

		if (similarLink.getTitle() == null) {
			map.put("title", null);
		}
		else {
			map.put("title", String.valueOf(similarLink.getTitle()));
		}

		return map;
	}

	public static class SimilarLinkJSONParser
		extends BaseJSONParser<SimilarLink> {

		@Override
		protected SimilarLink createDTO() {
			return new SimilarLink();
		}

		@Override
		protected SimilarLink[] createDTOArray(int size) {
			return new SimilarLink[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "actions")) {
				return true;
			}
			else if (Objects.equals(jsonParserFieldName, "href")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				return false;
			}
			else if (Objects.equals(
						jsonParserFieldName, "referringAssetsCount")) {

				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "title")) {
				return false;
			}

			return false;
		}

		@Override
		protected void setField(
			SimilarLink similarLink, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "actions")) {
				if (jsonParserFieldValue != null) {
					similarLink.setActions(
						(Map<String, Map<String, String>>)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "href")) {
				if (jsonParserFieldValue != null) {
					similarLink.setHref((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					similarLink.setId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "referringAssetsCount")) {

				if (jsonParserFieldValue != null) {
					similarLink.setReferringAssetsCount(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "title")) {
				if (jsonParserFieldValue != null) {
					similarLink.setTitle((String)jsonParserFieldValue);
				}
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
			sb.append("\": ");

			Object value = entry.getValue();

			sb.append(_toJSON(value));

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	private static String _toJSON(Object value) {
		if (value == null) {
			return "null";
		}

		if (value instanceof Map) {
			return _toJSON((Map)value);
		}

		Class<?> clazz = value.getClass();

		if (clazz.isArray()) {
			StringBuilder sb = new StringBuilder("[");

			Object[] values = (Object[])value;

			for (int i = 0; i < values.length; i++) {
				sb.append(_toJSON(values[i]));

				if ((i + 1) < values.length) {
					sb.append(", ");
				}
			}

			sb.append("]");

			return sb.toString();
		}

		if (value instanceof String) {
			return "\"" + _escape(value) + "\"";
		}

		return String.valueOf(value);
	}

}
// LIFERAY-REST-BUILDER-HASH:-1765344631