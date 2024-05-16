/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.content.dashboard.document.library.internal.item.filter;

import com.liferay.content.dashboard.item.filter.ContentDashboardItemFilter;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemBuilder;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemListBuilder;
import com.liferay.item.selector.ItemSelector;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.search.filter.RangeTermFilter;
import com.liferay.portal.kernel.util.HttpComponentsUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.portlet.PortletResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Cristina González
 */
public class FileResolutionContentDashboardItemFilter
	implements ContentDashboardItemFilter {

	public FileResolutionContentDashboardItemFilter(
		HttpServletRequest httpServletRequest, ItemSelector itemSelector,
		Language language, Portal portal) {

		_httpServletRequest = httpServletRequest;
		_itemSelector = itemSelector;
		_language = language;
		_portal = portal;
	}

	@Override
	public DropdownItem getDropdownItem() {
		return DropdownItemBuilder.putData(
			"action", "exampleAction"
		).setDropdownItems(
			DropdownItemListBuilder.add(
				dropdownItem -> {
					dropdownItem.setActive(_isSelected("small"));
					dropdownItem.setHref(_getURL("small"));
					dropdownItem.setLabel(
						_language.get(_httpServletRequest, "small") +
							" (<=400 x 300)");
				}
			).add(
				dropdownItem -> {
					dropdownItem.setActive(_isSelected("medium"));
					dropdownItem.setHref(_getURL("medium"));
					dropdownItem.setLabel(
						_language.get(_httpServletRequest, "medium") +
							" (401 x 301 <= 1024 x 768)");
				}
			).add(
				dropdownItem -> {
					dropdownItem.setActive(_isSelected("large"));
					dropdownItem.setHref(_getURL("large"));
					dropdownItem.setLabel(
						_language.get(_httpServletRequest, "large") +
							" (> 1024 x 768)");
				}
			).build()
		).setLabel(
			_language.get(_httpServletRequest, "resolution")
		).setType(
			"contextual"
		).build();
	}

	@Override
	public Filter getFilter() {
		List<String> parameterValues = getParameterValues();

		if (ListUtil.isEmpty(parameterValues)) {
			return null;
		}

		String type = parameterValues.get(0);

		BooleanFilter booleanFilter = new BooleanFilter();

		if (Objects.equals(type, "small")) {
			booleanFilter.add(
				new RangeTermFilter(
					"imageHeight_sortable", true, true, String.valueOf(0),
					String.valueOf(300)),
				BooleanClauseOccur.MUST);
			booleanFilter.add(
				new RangeTermFilter(
					"imageWidth_sortable", true, true, String.valueOf(0),
					String.valueOf(400)),
				BooleanClauseOccur.MUST);
		}
		else if (Objects.equals(type, "medium")) {
			booleanFilter.add(
				new RangeTermFilter(
					"imageHeight_sortable", false, true, String.valueOf(301),
					String.valueOf(768)),
				BooleanClauseOccur.MUST);
			booleanFilter.add(
				new RangeTermFilter(
					"imageWidth_sortable", false, true, String.valueOf(401),
					String.valueOf(1024)),
				BooleanClauseOccur.MUST);
		}
		else if (Objects.equals(type, "large")) {
			booleanFilter.add(
				new RangeTermFilter(
					"imageHeight_sortable", false, false, String.valueOf(768),
					String.valueOf(Long.MAX_VALUE)),
				BooleanClauseOccur.MUST);
			booleanFilter.add(
				new RangeTermFilter(
					"imageWidth_sortable", false, false, String.valueOf(1024),
					String.valueOf(Long.MAX_VALUE)),
				BooleanClauseOccur.MUST);
		}

		return booleanFilter;
	}

	@Override
	public String getIcon() {
		return null;
	}

	@Override
	public String getLabel(Locale locale) {
		return _language.get(locale, "filter-by-resolution");
	}

	@Override
	public String getName() {
		return "file-resolution";
	}

	@Override
	public String getParameterLabel(Locale locale) {
		return _language.get(locale, "resolution");
	}

	@Override
	public String getParameterName() {
		return getName();
	}

	@Override
	public List<String> getParameterValues() {
		return Arrays.asList(
			ParamUtil.getStringValues(_httpServletRequest, getParameterName()));
	}

	@Override
	public Type getType() {
		return Type.ITEM_SELECTOR;
	}

	@Override
	public String getURL() {
		return null;
	}

	private String _getURL(String type) {
		PortletResponse portletResponse =
			(PortletResponse)_httpServletRequest.getAttribute(
				JavaConstants.JAVAX_PORTLET_RESPONSE);

		String url = HttpComponentsUtil.removeParameter(
			_portal.getCurrentCompleteURL(_httpServletRequest),
			portletResponse.getNamespace() + getParameterName());

		return HttpComponentsUtil.addParameter(
			url, portletResponse.getNamespace() + getParameterName(), type);
	}

	private boolean _isSelected(String type) {
		List<String> parameterValues = getParameterValues();

		if (ListUtil.isEmpty(parameterValues)) {
			return false;
		}

		return Objects.equals(parameterValues.get(0), type);
	}

	private final HttpServletRequest _httpServletRequest;
	private final ItemSelector _itemSelector;
	private final Language _language;
	private final Portal _portal;

}