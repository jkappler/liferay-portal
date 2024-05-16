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
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.search.filter.TermsFilter;
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
public class FileOrientationContentDashboardItemFilter
	implements ContentDashboardItemFilter {

	public FileOrientationContentDashboardItemFilter(
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
					dropdownItem.setActive(_isSelected("wide"));
					dropdownItem.setHref(_getURL("wide"));
					dropdownItem.setLabel(
						_language.get(_httpServletRequest, "wide"));
				}
			).add(
				dropdownItem -> {
					dropdownItem.setActive(_isSelected("tall"));
					dropdownItem.setHref(_getURL("tall"));
					dropdownItem.setLabel(
						_language.get(_httpServletRequest, "tall"));
				}
			).add(
				dropdownItem -> {
					dropdownItem.setActive(_isSelected("square"));
					dropdownItem.setHref(_getURL("square"));
					dropdownItem.setLabel(
						_language.get(_httpServletRequest, "square"));
				}
			).build()
		).setLabel(
			_language.get(_httpServletRequest, "aspect-ratios")
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

		TermsFilter termsFilter = new TermsFilter(getParameterName());

		termsFilter.addValue(parameterValues.get(0));

		return termsFilter;
	}

	@Override
	public String getIcon() {
		return null;
	}

	@Override
	public String getLabel(Locale locale) {
		return _language.get(locale, "filter-by-aspect-ratio");
	}

	@Override
	public String getName() {
		return "aspect-ratio";
	}

	@Override
	public String getParameterLabel(Locale locale) {
		return _language.get(locale, "aspect-ratios");
	}

	@Override
	public String getParameterName() {
		return "aspect";
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