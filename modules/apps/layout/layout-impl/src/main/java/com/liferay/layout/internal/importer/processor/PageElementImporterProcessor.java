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

package com.liferay.layout.internal.importer.processor;

import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.service.FragmentEntryLinkLocalService;
import com.liferay.headless.delivery.dto.v1_0.PageElement;
import com.liferay.layout.internal.importer.LayoutStructureItemImporterContext;
import com.liferay.layout.internal.importer.structure.util.LayoutStructureItemImporter;
import com.liferay.layout.util.structure.FragmentStyledLayoutStructureItem;
import com.liferay.layout.util.structure.LayoutStructure;
import com.liferay.layout.util.structure.LayoutStructureItem;
import com.liferay.osgi.service.tracker.collections.map.ServiceReferenceMapperFactory;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.model.Layout;

import java.util.List;
import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(service = PageElementImporterProcessor.class)
public class PageElementImporterProcessor {

	public boolean processPageElement(
			List<FragmentEntryLink> fragmentEntryLinks, Layout layout,
			LayoutStructure layoutStructure, double pageDefinitionVersion,
			PageElement pageElement, String parentItemId, int position,
			long segmentsExperienceId, Set<String> warningMessages)
		throws Exception {

		LayoutStructureItemImporter layoutStructureItemImporter =
			_serviceTrackerMap.getService(pageElement.getType());

		LayoutStructureItem layoutStructureItem = null;

		if (layoutStructureItemImporter != null) {
			layoutStructureItem =
				layoutStructureItemImporter.addLayoutStructureItem(
					layoutStructure,
					new LayoutStructureItemImporterContext(
						layout, pageDefinitionVersion, parentItemId, position,
						segmentsExperienceId),
					pageElement, warningMessages);
		}
		else if (pageElement.getType() == PageElement.Type.ROOT) {
			layoutStructureItem = layoutStructure.getMainLayoutStructureItem();
		}
		else {
			return false;
		}

		if (layoutStructureItem == null) {
			return false;
		}

		if (layoutStructureItem instanceof FragmentStyledLayoutStructureItem) {
			FragmentStyledLayoutStructureItem
				fragmentStyledLayoutStructureItem =
					(FragmentStyledLayoutStructureItem)layoutStructureItem;

			fragmentEntryLinks.add(
				_fragmentEntryLinkLocalService.getFragmentEntryLink(
					fragmentStyledLayoutStructureItem.
						getFragmentEntryLinkId()));
		}

		if (pageElement.getPageElements() == null) {
			return true;
		}

		int childPosition = 0;

		for (PageElement childPageElement : pageElement.getPageElements()) {
			if (processPageElement(
					fragmentEntryLinks, layout, layoutStructure,
					pageDefinitionVersion, childPageElement,
					layoutStructureItem.getItemId(), childPosition,
					segmentsExperienceId, warningMessages)) {

				childPosition++;
			}
		}

		return true;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, LayoutStructureItemImporter.class, null,
			ServiceReferenceMapperFactory.createFromFunction(
				bundleContext,
				LayoutStructureItemImporter::getPageElementType));
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	@Reference
	private FragmentEntryLinkLocalService _fragmentEntryLinkLocalService;

	private ServiceTrackerMap<PageElement.Type, LayoutStructureItemImporter>
		_serviceTrackerMap;

}