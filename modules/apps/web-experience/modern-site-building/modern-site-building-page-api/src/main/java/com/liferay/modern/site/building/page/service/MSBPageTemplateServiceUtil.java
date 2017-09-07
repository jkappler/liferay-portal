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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for MSBPageTemplate. This utility wraps
 * {@link com.liferay.modern.site.building.page.service.impl.MSBPageTemplateServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see MSBPageTemplateService
 * @see com.liferay.modern.site.building.page.service.base.MSBPageTemplateServiceBaseImpl
 * @see com.liferay.modern.site.building.page.service.impl.MSBPageTemplateServiceImpl
 * @generated
 */
@ProviderType
public class MSBPageTemplateServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.modern.site.building.page.service.impl.MSBPageTemplateServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.modern.site.building.page.model.MSBPageTemplate deleteMSBPageTemplate(
		long msbPageTempalteId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteMSBPageTemplate(msbPageTempalteId);
	}

	public static java.util.List<com.liferay.modern.site.building.page.model.MSBPageTemplate> getMSBPageTemplates(
		long msbPageTemplateFolderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService()
				   .getMSBPageTemplates(msbPageTemplateFolderId, start, end, obc);
	}

	public static int getMSBPageTemplatesCount(long msbPageTemplateFolderId) {
		return getService().getMSBPageTemplatesCount(msbPageTemplateFolderId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.modern.site.building.page.model.MSBPageTemplate> searchMSBPageTemplates(
		long msbPageTemplateFolderId, java.lang.String keywords, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService()
				   .searchMSBPageTemplates(msbPageTemplateFolderId, keywords,
			start, end, obc);
	}

	public static int searchMSBPageTemplatesCount(
		long msbPageTemplateFolderId, java.lang.String keywords) {
		return getService()
				   .searchMSBPageTemplatesCount(msbPageTemplateFolderId,
			keywords);
	}

	public static MSBPageTemplateService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MSBPageTemplateService, MSBPageTemplateService> _serviceTracker =
		ServiceTrackerFactory.open(MSBPageTemplateService.class);
}