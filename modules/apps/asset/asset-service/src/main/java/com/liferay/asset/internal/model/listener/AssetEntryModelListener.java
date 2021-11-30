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

package com.liferay.asset.internal.model.listener;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.layout.model.LayoutClassedModelUsage;
import com.liferay.layout.service.LayoutClassedModelUsageLocalService;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.cache.MultiVMPool;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;
import java.util.Locale;

/**
 * @author Bryan Engler
 */
@Component(immediate = true, service = ModelListener.class)
public class AssetEntryModelListener extends BaseModelListener<AssetEntry> {

	@Override
	public void onAfterCreate(AssetEntry assetEntry) {
		assetEntry.setListable(true);
	}

	@Override
	public void onBeforeRemove(AssetEntry assetEntry)
		throws ModelListenerException {

		PortalCache<String, String> portalCache =
			(PortalCache<String, String>)_multiVMPool.getPortalCache(
				FragmentEntryLink.class.getName());

		List<LayoutClassedModelUsage> layoutClassedModelUsages =
			_layoutClassedModelUsageLocalService.getLayoutClassedModelUsages(
				assetEntry.getClassNameId(),
				assetEntry.getClassPK());

		for (LayoutClassedModelUsage layoutClassedModelUsage :
			layoutClassedModelUsages) {

			for (Locale locale :
				LanguageUtil.getAvailableLocales(
					assetEntry.getGroupId())) {

				StringBundler sb = new StringBundler(5);

				sb.append(layoutClassedModelUsage.getContainerKey());
				sb.append(StringPool.DASH);
				sb.append(locale);
				sb.append(StringPool.DASH);
				sb.append(0);

				portalCache.remove(sb.toString());
			}
		}

		_layoutClassedModelUsageLocalService.deleteLayoutClassedModelUsages(
			assetEntry.getClassNameId(), assetEntry.getClassPK());
	}

	@Reference
	private LayoutClassedModelUsageLocalService
		_layoutClassedModelUsageLocalService;

	@Reference
	private Language _language;


	@Reference
	private MultiVMPool _multiVMPool;
}