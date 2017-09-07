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

package com.liferay.modern.site.building.page.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MSBPageTemplateService}.
 *
 * @author Brian Wing Shun Chan
 * @see MSBPageTemplateService
 * @generated
 */
@ProviderType
public class MSBPageTemplateServiceWrapper implements MSBPageTemplateService,
	ServiceWrapper<MSBPageTemplateService> {
	public MSBPageTemplateServiceWrapper(
		MSBPageTemplateService msbPageTemplateService) {
		_msbPageTemplateService = msbPageTemplateService;
	}

	@Override
	public com.liferay.modern.site.building.page.model.MSBPageTemplate deleteMSBPageTemplate(
		long msbPageTempalteId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _msbPageTemplateService.deleteMSBPageTemplate(msbPageTempalteId);
	}

	@Override
	public java.util.List<com.liferay.modern.site.building.page.model.MSBPageTemplate> getMSBPageTemplates(
		long msbPageTemplateFolderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return _msbPageTemplateService.getMSBPageTemplates(msbPageTemplateFolderId,
			start, end, obc);
	}

	@Override
	public int getMSBPageTemplatesCount(long msbPageTemplateFolderId) {
		return _msbPageTemplateService.getMSBPageTemplatesCount(msbPageTemplateFolderId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _msbPageTemplateService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.modern.site.building.page.model.MSBPageTemplate> searchMSBPageTemplates(
		long msbPageTemplateFolderId, java.lang.String keywords, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		return _msbPageTemplateService.searchMSBPageTemplates(msbPageTemplateFolderId,
			keywords, start, end, obc);
	}

	@Override
	public int searchMSBPageTemplatesCount(long msbPageTemplateFolderId,
		java.lang.String keywords) {
		return _msbPageTemplateService.searchMSBPageTemplatesCount(msbPageTemplateFolderId,
			keywords);
	}

	@Override
	public MSBPageTemplateService getWrappedService() {
		return _msbPageTemplateService;
	}

	@Override
	public void setWrappedService(MSBPageTemplateService msbPageTemplateService) {
		_msbPageTemplateService = msbPageTemplateService;
	}

	private MSBPageTemplateService _msbPageTemplateService;
}