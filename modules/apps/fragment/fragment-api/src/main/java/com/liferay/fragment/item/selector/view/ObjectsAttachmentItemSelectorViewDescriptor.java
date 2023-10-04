/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.tags.item.selector.web.internal;

import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.tags.item.selector.AssetTagsItemSelectorReturnType;
import com.liferay.asset.tags.item.selector.criterion.AssetTagsItemSelectorCriterion;
import com.liferay.asset.tags.item.selector.web.internal.display.context.AssetTagsDisplayContext;
import com.liferay.fragment.item.selector.criterion.ObjectsAttachmentItemSelectorCriterion;
import com.liferay.fragment.item.selector.view.ObjectsAttachmentItemDescriptor;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.ItemSelectorViewDescriptor;
import com.liferay.item.selector.TableItemView;
import com.liferay.mail.kernel.model.FileAttachment;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Stefan Tanasie
 */
public class ObjectsAttachmentItemSelectorViewDescriptor
	implements ItemSelectorViewDescriptor<FileAttachment> {

	public ObjectsAttachmentItemSelectorViewDescriptor(
		ObjectsAttachmentItemSelectorCriterion objectsAttachmentItemSelectorCriterion) {

		_objectsAttachmentItemSelectorCriterion = objectsAttachmentItemSelectorCriterion;
	}

	@Override
	public String getDefaultDisplayStyle() {
		return "list";
	}

	@Override
	public String[] getDisplayViews() {
		return new String[0];
	}

	@Override
	public ItemDescriptor getItemDescriptor(FileAttachment fileAttachment) {
		return new ObjectsAttachmentItemDescriptor(fileAttachment);
	}

	@Override
	public ItemSelectorReturnType getItemSelectorReturnType() {
		return new AssetTagsItemSelectorReturnType();
	}

	@Override
	public String getKeyProperty() {
		return "name";
	}

	@Override
	public String[] getOrderByKeys() {
		return new String[] {"name"};
	}

	@Override
	public SearchContainer<FileAttachment> getSearchContainer()
		throws PortalException {
		return null;
	}

	@Override
	public boolean isShowBreadcrumb() {
		return false;
	}

	@Override
	public boolean isShowSearch() {
		return true;
	}

	private final ObjectsAttachmentItemSelectorCriterion
		_objectsAttachmentItemSelectorCriterion;

}