/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.item.selector.view;

import com.liferay.fragment.item.selector.criterion.ObjectsAttachmentItemSelectorCriterion;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.ItemSelectorView;
import com.liferay.item.selector.ItemSelectorViewDescriptorRenderer;
import com.liferay.item.selector.criteria.FileEntryItemSelectorReturnType;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.util.JavaConstants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * @author Pablo Molina
 */
@Component(service = ItemSelectorView.class)
public class ObjectsAttachmentItemSelectorView implements ItemSelectorView<ObjectsAttachmentItemSelectorCriterion> {
	@Override
	public Class<? extends ObjectsAttachmentItemSelectorCriterion> getItemSelectorCriterionClass() {
		return ObjectsAttachmentItemSelectorCriterion.class;
	}

	@Override
	public List<ItemSelectorReturnType> getSupportedItemSelectorReturnTypes() {
		return _supportedItemSelectorReturnTypes;
	}

	@Override
	public String getTitle(Locale locale) {
		return _language.get(locale, "attachments");
	}

	@Override
	public void renderHTML(
		ServletRequest servletRequest, ServletResponse servletResponse,
		ObjectsAttachmentItemSelectorCriterion objectsAttachmentItemSelectorCriterion,
		PortletURL portletURL, String itemSelectedEventName, boolean search)
		throws IOException, ServletException {

		HttpServletRequest httpServletRequest =
			(HttpServletRequest)servletRequest;

		RenderRequest renderRequest =
			(RenderRequest)httpServletRequest.getAttribute(
				JavaConstants.JAVAX_PORTLET_REQUEST);
		RenderResponse renderResponse =
			(RenderResponse)httpServletRequest.getAttribute(
				JavaConstants.JAVAX_PORTLET_RESPONSE);

		_itemSelectorViewDescriptorRenderer.renderHTML(
			httpServletRequest, servletResponse, objectsAttachmentItemSelectorCriterion,
			portletURL, itemSelectedEventName, search,
			new AssetTagsItemSelectorViewDescriptor(
				assetTagsItemSelectorCriterion, assetTagsDisplayContext));

	}

	private static final List<ItemSelectorReturnType>
		_supportedItemSelectorReturnTypes = Collections.singletonList(
		new FileEntryItemSelectorReturnType());

	@Reference
	private ItemSelectorViewDescriptorRenderer<ObjectsAttachmentItemSelectorView>
		_itemSelectorViewDescriptorRenderer;

	@Reference
	private Language _language;
}
