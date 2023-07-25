/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.internal.renderer;

import com.liferay.client.extension.type.CET;
import com.liferay.client.extension.type.FragmentCET;
import com.liferay.client.extension.type.manager.CETManager;
import com.liferay.fragment.contributor.FragmentCollectionContributorRegistry;
import com.liferay.fragment.model.FragmentEntry;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.renderer.FragmentRenderer;
import com.liferay.fragment.renderer.FragmentRendererContext;
import com.liferay.fragment.renderer.constants.FragmentRendererConstants;
import com.liferay.fragment.util.configuration.FragmentEntryConfigurationParser;
import com.liferay.frontend.js.loader.modules.extender.npm.JSPackage;
import com.liferay.frontend.js.loader.modules.extender.npm.ModuleNameUtil;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.servlet.taglib.util.OutputData;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.template.react.renderer.ComponentDescriptor;
import com.liferay.portal.template.react.renderer.ReactRenderer;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author Jürgen Kappler
 */
@Component(service = FragmentRenderer.class)
public class FragmentEntryFragmentRendererClientExtensionEntry implements FragmentRenderer {

	@Override
	public String getCollectionKey() {
		return StringPool.BLANK;
	}

	@Override
	public String getConfiguration(
		FragmentRendererContext fragmentRendererContext) {

		FragmentEntryLink fragmentEntryLink =
			fragmentRendererContext.getFragmentEntryLink();

		return fragmentEntryLink.getConfiguration();
	}

	@Override
	public String getKey() {
		return FragmentRendererConstants.
			FRAGMENT_ENTRY_FRAGMENT_RENDERER_KEY_CLIENT_EXTENSION_ENTRY;
	}

	@Override
	public boolean isSelectable(HttpServletRequest httpServletRequest) {
		return false;
	}

	@Override
	public void render(
			FragmentRendererContext fragmentRendererContext,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		PrintWriter printWriter = httpServletResponse.getWriter();

		FragmentEntryLink fragmentEntryLink = fragmentRendererContext.getFragmentEntryLink();

		FragmentCET fragmentCET = (FragmentCET)_cetManager.getCET(fragmentEntryLink.getCompanyId(), fragmentEntryLink.getRendererKey());

		printWriter.write(
			_renderFragmentEntry(
				fragmentCET,
				fragmentEntryLink,
				fragmentRendererContext.getFragmentElementId(),
				fragmentRendererContext,
				httpServletRequest));
	}

	private String _renderFragmentEntry(
			FragmentCET fragmentCET,
			FragmentEntryLink fragmentEntryLink, String fragmentElementId,
			FragmentRendererContext fragmentRendererContext,
			HttpServletRequest httpServletRequest) {

		String cssURL = StringPool.BLANK;
		String jsURL = StringPool.BLANK;
		String htmlURL = StringPool.BLANK;

		String url = _portal.getPortalURL(httpServletRequest) +
					 _portal.getPathContext() + fragmentCET.getBaseURL() +
					 StringPool.SLASH;

		if(Validator.isNotNull(fragmentCET.getCSS())) {
			cssURL = url  + fragmentCET.getCSS();



		}

		if(Validator.isNotNull(fragmentCET.getJS())) {
			jsURL = url  + fragmentCET.getJS();
		}

		if(Validator.isNotNull(fragmentCET.getHTML())) {
			htmlURL = url  + fragmentCET.getHTML();
		}


		return StringPool.BLANK;

		//
	}


	@Reference
	private CETManager _cetManager;

	@Reference
	private Portal _portal;
}