/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.configuration.web.internal.portlet.configuration.icon;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.configuration.icon.BaseJSPPortletConfigurationIcon;
import com.liferay.portal.kernel.util.HashMapBuilder;

import java.util.Map;

import javax.portlet.PortletRequest;

/**
 * @author Jürgen Kappler
 */
public abstract class BaseConfigurationPortletConfigurationIcon
	extends BaseJSPPortletConfigurationIcon {

	@Override
	public Map<String, Object> getContext(PortletRequest portletRequest) {
		return HashMapBuilder.<String, Object>put(
			"action", getNamespace(portletRequest) + "configuration"
		).put(
			"globalAction", true
		).build();
	}

	@Override
	public String getCssClass() {
		return "portlet-configuration portlet-configuration-icon";
	}

	@Override
	public String getIconCssClass() {
		return "cog";
	}

	@Override
	public String getMessage(PortletRequest portletRequest) {
		return LanguageUtil.get(getLocale(portletRequest), "configuration");
	}

	@Override
	public double getWeight() {
		return 14.0;
	}

	@Override
	public boolean isShowInEditMode(PortletRequest portletRequest) {
		return true;
	}

}