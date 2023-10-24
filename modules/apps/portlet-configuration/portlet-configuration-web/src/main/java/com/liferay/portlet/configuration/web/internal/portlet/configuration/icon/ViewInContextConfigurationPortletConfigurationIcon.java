/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.configuration.web.internal.portlet.configuration.icon;

import com.liferay.portal.kernel.feature.flag.FeatureFlagManagerUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.configuration.icon.PortletConfigurationIcon;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HttpComponentsUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.WindowState;

import org.osgi.service.component.annotations.Component;

/**
 * @author Eudaldo Alonso
 */
@Component(service = PortletConfigurationIcon.class)
public class ViewInContextConfigurationPortletConfigurationIcon
	extends BaseConfigurationPortletConfigurationIcon {

	@Override
	public String getURL(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		WindowState windowState =
			portletDisplay.getURLConfigurationViewInContextWindowState();

		return HttpComponentsUtil.addParameter(
			portletDisplay.getUrlConfigurationViewInContext(), "p_p_state",
			windowState.toString());
	}

	@Override
	public boolean isShow(PortletRequest portletRequest) {
		if (!FeatureFlagManagerUtil.isEnabled("LPS-197692")) {
			return false;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		if (layout.isEmbeddedPersonalApplication()) {
			return false;
		}

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		if (portletDisplay.getURLConfigurationViewInContextWindowState() ==
				LiferayWindowState.POP_UP) {

			return false;
		}

		return portletDisplay.isShowConfigurationIcon();
	}

}