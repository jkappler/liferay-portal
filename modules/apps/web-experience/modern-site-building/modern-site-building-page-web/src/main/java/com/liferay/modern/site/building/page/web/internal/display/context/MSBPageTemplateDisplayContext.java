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

package com.liferay.modern.site.building.page.web.internal.display.context;

import com.liferay.modern.site.building.fragment.constants.MSBFragmentPortletKeys;
import com.liferay.modern.site.building.fragment.model.MSBFragmentCollection;
import com.liferay.modern.site.building.fragment.model.MSBFragmentEntry;
import com.liferay.modern.site.building.fragment.service.MSBFragmentCollectionServiceUtil;
import com.liferay.modern.site.building.fragment.service.MSBFragmentEntryServiceUtil;
import com.liferay.modern.site.building.page.model.MSBPageTemplate;
import com.liferay.modern.site.building.page.model.MSBPageTemplateFolder;
import com.liferay.modern.site.building.page.service.MSBPageTemplateFolderServiceUtil;
import com.liferay.modern.site.building.page.service.MSBPageTemplateServiceUtil;
import com.liferay.modern.site.building.page.web.internal.util.MSBPageTemplatePortletUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Pavel Savinov
 */
public class MSBPageTemplateDisplayContext {

	public MSBPageTemplateDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest request) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_request = request;
	}

	public MSBPageTemplateFolder fetchMSBPageTemplateFolder() throws PortalException {
		return MSBPageTemplateFolderServiceUtil.fetchMSBPageTemplateFolder(
			getMSBPageTemplateFolderId());
	}

	public String getDisplayStyle() {
		if (Validator.isNotNull(_displayStyle)) {
			return _displayStyle;
		}

		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(_request);

		_displayStyle = portalPreferences.getValue(
			MSBFragmentPortletKeys.MODERN_SITE_BUILDING_FRAGMENT,
			"display-style", "icon");

		return _displayStyle;
	}

	public String getKeywords() {
		if (_keywords != null) {
			return _keywords;
		}

		_keywords = StringUtil.quote(
			ParamUtil.getString(_request, "keywords"), CharPool.PERCENT);

		return _keywords;
	}

	public JSONArray getMSBFragmentCollectionsJSONArray()
		throws PortalException {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		List<MSBFragmentCollection> msbFragmentCollections =
			MSBFragmentCollectionServiceUtil.getMSBFragmentCollections(
				themeDisplay.getScopeGroupId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		for (MSBFragmentCollection msbFragmentCollection :
				msbFragmentCollections) {

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put(
				"description", msbFragmentCollection.getDescription());
			jsonObject.put(
				"msbFragments",
				getMSBFragmentsJSONArray(
					msbFragmentCollection.getMsbFragmentCollectionId()));
			jsonObject.put("name", msbFragmentCollection.getName());

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	public JSONArray getMSBFragmentsJSONArray(long msbFragmentCollectionId)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<MSBFragmentEntry> msbFragmentEntries =
			MSBFragmentEntryServiceUtil.getMSBFragmentEntries(
				themeDisplay.getScopeGroupId(), msbFragmentCollectionId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (MSBFragmentEntry msbFragmentEntry : msbFragmentEntries) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("css", msbFragmentEntry.getCss());
			jsonObject.put("html", msbFragmentEntry.getHtml());
			jsonObject.put("js", msbFragmentEntry.getJs());
			jsonObject.put(
				"msbFragmentEntryId", msbFragmentEntry.getMsbFragmentEntryId());
			jsonObject.put("name", msbFragmentEntry.getName());

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	public String getOrderByCol() {
		if (Validator.isNotNull(_orderByCol)) {
			return _orderByCol;
		}

		_orderByCol = ParamUtil.getString(
			_request, "orderByCol", "create-date");

		return _orderByCol;
	}

	public String getOrderByType() {
		if (Validator.isNotNull(_orderByType)) {
			return _orderByType;
		}

		_orderByType = ParamUtil.getString(_request, "orderByType", "asc");

		return _orderByType;
	}

	public String[] getOrderColumns() {
		return new String[] {"create-date", "name"};
	}

	public long getMSBPageTemplateFolderId() {
		if (Validator.isNotNull(_msbPageTemplateFolderId)) {
			return _msbPageTemplateFolderId;
		}

		_msbPageTemplateFolderId = ParamUtil.getLong(
			_request, "msbPageTemplateFolderId");

		return _msbPageTemplateFolderId;
	}

	public PortletURL getPortletURL() throws PortalException {
		PortletURL portletURL = _renderResponse.createRenderURL();

		return portletURL;
	}

	public SearchContainer getSearchContainer() {
		long msbPageTemplateFolderId = getMSBPageTemplateFolderId();

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (msbPageTemplateFolderId > 0) {
			if (_msbPageTemplatesSearchContainer != null) {
				return _msbPageTemplatesSearchContainer;
			}

			_msbPageTemplatesSearchContainer = new SearchContainer(
				_renderRequest, _renderResponse.createRenderURL(), null,
				"there-are-no-page-templates");

			_msbPageTemplatesSearchContainer.setRowChecker(
				new EmptyOnClickRowChecker(_renderResponse));

			_msbPageTemplatesSearchContainer.setOrderByCol(getOrderByCol());
			_msbPageTemplatesSearchContainer.setOrderByType(getOrderByType());

			_msbPageTemplatesSearchContainer.setOrderByComparator(
				MSBPageTemplatePortletUtil.getMSBPageTemplateOrderByComparator(
					getOrderByCol(), getOrderByType()));

			int total = 0;

			if (!isSearch()) {
				total = MSBPageTemplateServiceUtil.getMSBPageTemplatesCount(
					msbPageTemplateFolderId);
			}
			else {
				total = MSBPageTemplateServiceUtil.searchMSBPageTemplatesCount(
					msbPageTemplateFolderId, getKeywords());
			}

			List<MSBPageTemplate> msbPageTemplates = null;

			if (!isSearch()) {
				msbPageTemplates = MSBPageTemplateServiceUtil.getMSBPageTemplates(
					msbPageTemplateFolderId,
					_msbPageTemplatesSearchContainer.getStart(),
					_msbPageTemplatesSearchContainer.getEnd(),
					_msbPageTemplatesSearchContainer.getOrderByComparator());
			}
			else {
				msbPageTemplates = MSBPageTemplateServiceUtil.searchMSBPageTemplates(
					msbPageTemplateFolderId, getKeywords(),
					_msbPageTemplatesSearchContainer.getStart(),
					_msbPageTemplatesSearchContainer.getEnd(),
					_msbPageTemplatesSearchContainer.getOrderByComparator());
			}

			_msbPageTemplatesSearchContainer.setTotal(total);
			_msbPageTemplatesSearchContainer.setResults(msbPageTemplates);

			return _msbPageTemplatesSearchContainer;
		}
		else {
			if (_msbPageTemplatesFolderSearchContainer != null) {
				return _msbPageTemplatesFolderSearchContainer;
			}

			_msbPageTemplatesFolderSearchContainer = new SearchContainer(
				_renderRequest, _renderResponse.createRenderURL(), null,
				"there-are-no-page-template-folders");

			_msbPageTemplatesFolderSearchContainer.setRowChecker(
				new EmptyOnClickRowChecker(_renderResponse));

			_msbPageTemplatesFolderSearchContainer.setOrderByCol(getOrderByCol());
			_msbPageTemplatesFolderSearchContainer.setOrderByType(
				getOrderByType());

			_msbPageTemplatesFolderSearchContainer.setOrderByComparator(
				MSBPageTemplatePortletUtil.getMSBPageTemplateFolderOrderByComparator(
					getOrderByCol(), getOrderByType()));

			int total = 0;

			if (!isSearch()) {
				total =
					MSBPageTemplateFolderServiceUtil.getMSBPageTemplateFoldersCount(
						themeDisplay.getScopeGroupId());
			}
			else {
				total =
					MSBPageTemplateFolderServiceUtil.
						searchMSBPageTemplateFoldersCount(
							themeDisplay.getScopeGroupId(), getKeywords());
			}

			List<MSBPageTemplateFolder> msbPageTemplateFolders = null;

			if (!isSearch()) {
				msbPageTemplateFolders =
					MSBPageTemplateFolderServiceUtil.getMSBPageTemplateFolders(
						themeDisplay.getScopeGroupId(),
						_msbPageTemplatesFolderSearchContainer.getStart(),
						_msbPageTemplatesFolderSearchContainer.getEnd(),
						_msbPageTemplatesFolderSearchContainer.
							getOrderByComparator());
			}
			else {
				msbPageTemplateFolders =
					MSBPageTemplateFolderServiceUtil.searchMSBPageTemplateFolders(
						themeDisplay.getScopeGroupId(), getKeywords(),
						_msbPageTemplatesFolderSearchContainer.getStart(),
						_msbPageTemplatesFolderSearchContainer.getEnd(),
						_msbPageTemplatesFolderSearchContainer.
							getOrderByComparator());
			}

			_msbPageTemplatesFolderSearchContainer.setTotal(total);
			_msbPageTemplatesFolderSearchContainer.setResults(msbPageTemplateFolders);

			return _msbPageTemplatesFolderSearchContainer;
		}
	}

	public boolean isSearch() {
		if (Validator.isNotNull(getKeywords())) {
			return true;
		}

		return false;
	}

	private String _displayStyle;
	private String _keywords;
	private String _orderByCol;
	private String _orderByType;
	private MSBPageTemplateFolder _msbPageTemplateFolder;
	private Long _msbPageTemplateFolderId;
	private SearchContainer _msbPageTemplatesFolderSearchContainer;
	private SearchContainer _msbPageTemplatesSearchContainer;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private final HttpServletRequest _request;

}