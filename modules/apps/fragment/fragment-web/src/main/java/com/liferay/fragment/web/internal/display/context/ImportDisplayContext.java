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