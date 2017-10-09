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

package com.liferay.modern.site.building.page.service.impl;

import com.liferay.modern.site.building.page.model.MSBPageTemplate;
import com.liferay.modern.site.building.page.service.base.MSBPageTemplateLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Pavel Savinov
 */
public class MSBPageTemplateLocalServiceImpl
	extends MSBPageTemplateLocalServiceBaseImpl {

	@Override
	public MSBPageTemplate deleteMSBPageTemplate(long msbPageTempalteId)
		throws PortalException {

		return msbPageTemplatePersistence.remove(msbPageTempalteId);
	}

	@Override
	public List<MSBPageTemplate> getMSBPageTemplates(
		long msbPageTemplateFolderId, int start, int end, OrderByComparator obc) {

		return msbPageTemplatePersistence.findByMSBPageTemplateFolderId(
			msbPageTemplateFolderId, start, end, obc);
	}

	@Override
	public int getMSBPageTemplatesCount(long msbPageTemplateFolderId) {
		return msbPageTemplatePersistence.countByMSBPageTemplateFolderId(
			msbPageTemplateFolderId);
	}

	@Override
	public List<MSBPageTemplate> searchMSBPageTemplates(
		long msbPageTemplateFolderId, String keywords, int start, int end,
		OrderByComparator obc) {

		return msbPageTemplatePersistence.findByLikeN_P(
			keywords, msbPageTemplateFolderId, start, end, obc);
	}

	@Override
	public int searchMSBPageTemplatesCount(
		long msbPageTemplateFolderId, String keywords) {

		return msbPageTemplatePersistence.countByLikeN_P(
			keywords, msbPageTemplateFolderId);
	}

}