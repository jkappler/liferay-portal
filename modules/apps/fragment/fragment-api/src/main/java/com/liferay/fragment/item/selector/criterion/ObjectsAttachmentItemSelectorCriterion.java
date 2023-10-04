/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.item.selector.criterion;

import com.liferay.item.selector.criteria.file.criterion.FileItemSelectorCriterion;

import java.util.List;

/**
 * @author Pablo Molina
 */
public class ObjectsAttachmentItemSelectorCriterion
	extends FileItemSelectorCriterion {

	public ObjectsAttachmentItemSelectorCriterion(
		List<String> extensions, long maxFileSize, String mimeTypeRestriction) {

		_extensions = extensions;
		_maxFileSize = maxFileSize;
		_mimeTypeRestriction = mimeTypeRestriction;
	}

	public List<String> getExtensions() {
		return _extensions;
	}

	public long getMaxFileSize() {
		return _maxFileSize;
	}

	@Override
	public String getMimeTypeRestriction() {
		return _mimeTypeRestriction;
	}

	private final List<String> _extensions;
	private final long _maxFileSize;
	private final String _mimeTypeRestriction;

}