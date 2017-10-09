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

package com.liferay.modern.site.building.page.util.comparator;

import com.liferay.modern.site.building.page.model.MSBPageTemplateFolder;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Jürgen Kappler
 */
public class MSBPageTemplateFolderNameComparator
	extends OrderByComparator<MSBPageTemplateFolder> {

	public static final String ORDER_BY_ASC = "MSBPageTemplateFolder.name ASC";

	public static final String ORDER_BY_DESC =
		"MSBPageTemplateFolder.name DESC";

	public static final String[] ORDER_BY_FIELDS = {"name"};

	public MSBPageTemplateFolderNameComparator() {
		this(false);
	}

	public MSBPageTemplateFolderNameComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(
		MSBPageTemplateFolder msbPageTemplateFolder1,
		MSBPageTemplateFolder msbPageTemplateFolder2) {

		String name1 = StringUtil.toLowerCase(msbPageTemplateFolder1.getName());
		String name2 = StringUtil.toLowerCase(msbPageTemplateFolder2.getName());

		int value = name1.compareTo(name2);

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	@Override
	public String getOrderBy() {
		if (_ascending) {
			return ORDER_BY_ASC;
		}
		else {
			return ORDER_BY_DESC;
		}
	}

	@Override
	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	private final boolean _ascending;

}