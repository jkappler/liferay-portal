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

package com.liferay.frontend.taglib.internal.display.context;

import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortletKeys;

import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Sandro Chinea
 */
public class LogoSelectorDisplayContext {

	public LogoSelectorDisplayContext(HttpServletRequest httpServletRequest) {
		_httpServletRequest = httpServletRequest;
	}

	public Integer getAspectRatio() {
		return GetterUtil.getInteger(
			(String)_httpServletRequest.getAttribute(
				"liferay-frontend:logo-selector:aspectRatio"));
	}

	public String getCurrentLogoURL() {
		return (String)_httpServletRequest.getAttribute(
			"liferay-frontend:logo-selector:currentLogoURL");
	}

	public String getDefaultLogoURL() {
		return (String)_httpServletRequest.getAttribute(
			"liferay-frontend:logo-selector:defaultLogoURL");
	}

	public String getLogoURL() {
		boolean deleteLogo = ParamUtil.getBoolean(
			_httpServletRequest, "deleteLogo");
		long fileEntryId = ParamUtil.getLong(
			_httpServletRequest, "fileEntryId");

		String logoURL = null;

		if (deleteLogo) {
			logoURL = getDefaultLogoURL();
		}
		else if (fileEntryId > 0) {
			PortletRequest portletRequest =
				(PortletRequest)_httpServletRequest.getAttribute(
					JavaConstants.JAVAX_PORTLET_REQUEST);

			ResourceURL previewURL = PortletURLFactoryUtil.create(
				portletRequest, PortletKeys.IMAGE_UPLOADER,
				PortletRequest.RESOURCE_PHASE);

			previewURL.setParameter(
				"mvcRenderCommandName", "/image_uploader/upload_image");
			previewURL.setParameter(Constants.CMD, Constants.GET_TEMP);
			previewURL.setParameter(
				"tempImageFileName", getTempImageFileName());

			logoURL = previewURL.toString();
		}
		else {
			logoURL = getCurrentLogoURL();
		}

		return logoURL;
	}

	public Map<String, Object> getLogoSelectorProps() {
		return HashMapBuilder.<String, Object>put(
			"defaultLogoURL", getDefaultLogoURL()
		).put(
			"logoURL", getLogoURL()
		).build();
	}

	public Boolean getPreserveRatio() {
		return GetterUtil.getBoolean(
			(String)_httpServletRequest.getAttribute(
				"liferay-frontend:logo-selector:preserveRatio"));
	}

	public String getTempImageFileName() {
		return (String)_httpServletRequest.getAttribute(
			"liferay-frontend:logo-selector:tempImageFileName");
	}

	private final HttpServletRequest _httpServletRequest;

}