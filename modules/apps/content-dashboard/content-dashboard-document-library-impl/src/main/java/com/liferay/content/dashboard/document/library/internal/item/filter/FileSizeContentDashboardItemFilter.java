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
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.util.HttpComponentsUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.portlet.PortletResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Cristina González
 */
public class FileSizeContentDashboardItemFilter
	implements ContentDashboardItemFilter {

	public FileSizeContentDashboardItemFilter(
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
							" (<=150 KB)");
				}
			).add(
				dropdownItem -> {
					dropdownItem.setActive(_isSelected("medium"));
					dropdownItem.setHref(_getURL("medium"));
					dropdownItem.setLabel(
						_language.get(_httpServletRequest, "medium") +
							" (151KB <= 1MB)");
				}
			).add(
				dropdownItem -> {
					dropdownItem.setActive(_isSelected("large"));
					dropdownItem.setHref(_getURL("large"));
					dropdownItem.setLabel(
						_language.get(_httpServletRequest, "large") +
							" (> 1MB)");
				}
			).build()
		).setLabel(
			_language.get(_httpServletRequest, "size")
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
			booleanFilter.addRangeTerm("size_sortable", 0, 150000);
		}
		else if (Objects.equals(type, "medium")) {
			booleanFilter.addRangeTerm("size_sortable", 150001, 1000000);
		}
		else if (Objects.equals(type, "large")) {
			booleanFilter.addRangeTerm(
				"size_sortable", 1000001, Long.MAX_VALUE);
		}

		return booleanFilter;
	}

	@Override
	public String getIcon() {
		return null;
	}

	@Override
	public String getLabel(Locale locale) {
		return _language.get(locale, "filter-by-size");
	}

	@Override
	public String getName() {
		return "file-size";
	}

	@Override
	public String getParameterLabel(Locale locale) {
		return _language.get(locale, "size");
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