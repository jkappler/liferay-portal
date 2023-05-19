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

package com.liferay.fragment.web.internal.display.context;

import com.liferay.fragment.importer.FragmentsImporterResultEntry;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.kernel.feature.flag.FeatureFlagManagerUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.MapUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.RenderRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jürgen Kappler
 */
public class ImportDisplayContext {

	public ImportDisplayContext(
		HttpServletRequest httpServletRequest, RenderRequest renderRequest) {

		_httpServletRequest = httpServletRequest;
		_renderRequest = renderRequest;
	}

	public String getDraftMessage() {
		List<String> draftFragmentsImporterResultEntries =
			getFragmentsImporterResultEntries(
				FragmentsImporterResultEntry.Status.IMPORTED_DRAFT);

		if (ListUtil.isEmpty(draftFragmentsImporterResultEntries)) {
			return null;
		}

		StringBuilder sb = new StringBuilder("");

		String draftMessage = LanguageUtil.format(
			_httpServletRequest,
			"x-imported-fragments-contains-errors-that-might-cause-malfunction",
			draftFragmentsImporterResultEntries.size());

		sb.append(draftMessage);

		if (draftFragmentsImporterResultEntries.size() < 3) {
			sb.append("<ul>");

			for (String fragment : draftFragmentsImporterResultEntries) {
				sb.append("<li>" + fragment + "</li>");
			}

			sb.append("</ul>");
		}

		return sb.toString();
	}

	public List<String> getFragmentsImporterResultEntries(
		FragmentsImporterResultEntry.Status status) {

		Map
			<FragmentsImporterResultEntry.Status,
			 Map
				 <FragmentsImporterResultEntry.Type,
				  List<FragmentsImporterResultEntry>>>
					fragmentsImporterResultEntriesMap =
						_geFragmentsImporterResultEntriesMap();

		Map
			<FragmentsImporterResultEntry.Type,
			 List<FragmentsImporterResultEntry>>
				fragmentsImporterResultEntryTypeMap =
					fragmentsImporterResultEntriesMap.get(status);

		if (MapUtil.isEmpty(fragmentsImporterResultEntryTypeMap)) {
			return null;
		}

		List<FragmentsImporterResultEntry> fragmentsImporterResultEntries =
			new ArrayList<>();

		for (Map.Entry
				<FragmentsImporterResultEntry.Type,
				 List<FragmentsImporterResultEntry>> entry :
					fragmentsImporterResultEntryTypeMap.entrySet()) {

			fragmentsImporterResultEntries.addAll(entry.getValue());
		}

		if (ListUtil.isEmpty(fragmentsImporterResultEntries)) {
			return null;
		}

		return TransformUtil.transform(
			fragmentsImporterResultEntries,
			fragmentsImporterResultEntry ->
				fragmentsImporterResultEntry.getName());
	}

	public String getSuccessMessage() {
		if (!FeatureFlagManagerUtil.isEnabled("LPS-174939") ||
			!SessionMessages.contains(_renderRequest, "success")) {

			return null;
		}

		Map
			<FragmentsImporterResultEntry.Status,
			 Map
				 <FragmentsImporterResultEntry.Type,
				  List<FragmentsImporterResultEntry>>>
					fragmentsImporterResultEntriesMap =
						_geFragmentsImporterResultEntriesMap();

		Map
			<FragmentsImporterResultEntry.Type,
			 List<FragmentsImporterResultEntry>>
				fragmentsImporterResultEntryTypeMap =
					fragmentsImporterResultEntriesMap.get(
						FragmentsImporterResultEntry.Status.IMPORTED);

		if (MapUtil.isEmpty(fragmentsImporterResultEntryTypeMap)) {
			return null;
		}

		String successMessage = null;

		List<FragmentsImporterResultEntry> validCompositions =
			fragmentsImporterResultEntryTypeMap.get(
				FragmentsImporterResultEntry.Type.COMPOSITION);

		int validCompositionsCount = 0;

		if (ListUtil.isNotEmpty(validCompositions)) {
			validCompositionsCount = validCompositions.size();
		}

		List<FragmentsImporterResultEntry> validFragments =
			fragmentsImporterResultEntryTypeMap.get(
				FragmentsImporterResultEntry.Type.FRAGMENT);

		int validFragmentsCount = 0;

		if (ListUtil.isNotEmpty(validFragments)) {
			validFragmentsCount = validFragments.size();
		}

		if ((validFragmentsCount > 0) && (validCompositionsCount > 0)) {
			successMessage = LanguageUtil.format(
				_httpServletRequest, "x-x-s-and-x-x-s-imported-correctly",
				new String[] {
					String.valueOf(validCompositionsCount), "composition",
					String.valueOf(validFragmentsCount), "fragment"
				},
				true);
		}
		else if (validCompositionsCount > 0) {
			successMessage = LanguageUtil.format(
				_httpServletRequest, "x-x-s-imported-correctly",
				new String[] {
					String.valueOf(validCompositionsCount), "composition"
				},
				true);
		}
		else if (validFragmentsCount > 0) {
			successMessage = LanguageUtil.format(
				_httpServletRequest, "x-x-s-imported-correctly",
				new String[] {String.valueOf(validFragmentsCount), "fragment"},
				true);
		}

		return successMessage;
	}

	private Map
		<FragmentsImporterResultEntry.Status,
		 Map
			 <FragmentsImporterResultEntry.Type,
			  List<FragmentsImporterResultEntry>>>
				_geFragmentsImporterResultEntriesMap() {

		if (_fragmentsImporterResultEntriesMap != null) {
			return _fragmentsImporterResultEntriesMap;
		}

		Map
			<FragmentsImporterResultEntry.Status,
			 Map
				 <FragmentsImporterResultEntry.Type,
				  List<FragmentsImporterResultEntry>>>
					fragmentsImporterResultEntriesMap = new HashMap<>();

		List<FragmentsImporterResultEntry> fragmentsImporterResultEntries =
			(List<FragmentsImporterResultEntry>)SessionMessages.get(
				_renderRequest, "fragmentsImporterResultEntries");

		if (ListUtil.isEmpty(fragmentsImporterResultEntries)) {
			_fragmentsImporterResultEntriesMap =
				fragmentsImporterResultEntriesMap;

			return _fragmentsImporterResultEntriesMap;
		}

		for (FragmentsImporterResultEntry fragmentsImporterResultEntry :
				fragmentsImporterResultEntries) {

			Map
				<FragmentsImporterResultEntry.Type,
				 List<FragmentsImporterResultEntry>>
					fragmentsImporterResultEntryTypeMap =
						fragmentsImporterResultEntriesMap.computeIfAbsent(
							fragmentsImporterResultEntry.getStatus(),
							k -> new HashMap<>());

			List<FragmentsImporterResultEntry>
				currentFragmentsImporterResultEntries =
					fragmentsImporterResultEntryTypeMap.computeIfAbsent(
						fragmentsImporterResultEntry.getType(),
						k -> new ArrayList<>());

			currentFragmentsImporterResultEntries.add(
				fragmentsImporterResultEntry);
		}

		_fragmentsImporterResultEntriesMap = fragmentsImporterResultEntriesMap;

		return _fragmentsImporterResultEntriesMap;
	}

	private Map
		<FragmentsImporterResultEntry.Status,
		 Map
			 <FragmentsImporterResultEntry.Type,
			  List<FragmentsImporterResultEntry>>>
				_fragmentsImporterResultEntriesMap;
	private final HttpServletRequest _httpServletRequest;
	private final RenderRequest _renderRequest;

}