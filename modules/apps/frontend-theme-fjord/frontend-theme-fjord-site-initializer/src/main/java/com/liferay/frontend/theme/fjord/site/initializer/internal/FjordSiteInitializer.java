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

package com.liferay.frontend.theme.fjord.site.initializer.internal;

import com.liferay.fragment.model.FragmentCollection;
import com.liferay.fragment.model.FragmentEntry;
import com.liferay.layout.page.template.model.LayoutPageTemplateCollection;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.site.exception.InitializationException;
import com.liferay.site.initializer.SiteInitializer;
import com.liferay.site.initializer.util.BaseSiteInitializer;

import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Chema Balsas
 */
@Component(
	immediate = true,
	property = "site.initializer.key=" + FjordSiteInitializer.KEY,
	service = SiteInitializer.class
)
public class FjordSiteInitializer extends BaseSiteInitializer {

	public static final String KEY = "fjord-site-initializer";

	@Override
	public String getDescription(Locale locale) {
		return StringPool.BLANK;
	}

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public String getName(Locale locale) {
		return _THEME_NAME;
	}

	@Override
	public String getThumbnailSrc() {
		return _servletContext.getContextPath() + "/images/thumbnail.png";
	}

	@Override
	public void initialize(long groupId) throws InitializationException {
		try {
			ServiceContext serviceContext = createServiceContext(groupId);

			updateLogo(serviceContext);
			updateLookAndFeel(serviceContext);

			FragmentCollection fragmentCollection = addFragmentCollection(
				serviceContext);

			addFileEntries(
				fragmentCollection.getFragmentCollectionId(),
				fragmentCollection.getResourcesFolderId(), serviceContext);

			LayoutPageTemplateCollection layoutPageTemplateCollection =
				addLayoutPageTemplateCollection(serviceContext);

			List<FragmentEntry> homeFragmentEntries = addFragmentEntries(
				fragmentCollection.getFragmentCollectionId(),
				_PATH + "/fragments/home", serviceContext);

			List<FragmentEntry> downloadFragmentEntries = addFragmentEntries(
				fragmentCollection.getFragmentCollectionId(),
				_PATH + "/fragments/download", serviceContext);

			homeFragmentEntries.addAll(downloadFragmentEntries);

			List<FragmentEntry> featuresFragmentEntries = addFragmentEntries(
				fragmentCollection.getFragmentCollectionId(),
				_PATH + "/fragments/features", serviceContext);

			homeFragmentEntries.addAll(featuresFragmentEntries);

			addLayout(
				layoutPageTemplateCollection.
					getLayoutPageTemplateCollectionId(),
				"Home", homeFragmentEntries, _PATH + "/fragments/home",
				serviceContext);

			addLayout(
				layoutPageTemplateCollection.
					getLayoutPageTemplateCollectionId(),
				"Features", featuresFragmentEntries,
				_PATH + "/fragments/features", serviceContext);

			addLayout(
				layoutPageTemplateCollection.
					getLayoutPageTemplateCollectionId(),
				"Download", downloadFragmentEntries,
				_PATH + "/fragments/download", serviceContext);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new InitializationException(e);
		}
	}

	@Override
	public boolean isActive(long companyId) {
		return true;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		setBundle(bundleContext.getBundle());
	}

	@Override
	protected String getPath() {
		return _PATH;
	}

	@Override
	protected String getThemeId() {
		return _THEME_ID;
	}

	@Override
	protected String getThemeName() {
		return _THEME_NAME;
	}

	private static final String _PATH =
		"com/liferay/frontend/theme/fjord/site/initializer/internal" +
			"/dependencies";

	private static final String _THEME_ID = "fjord_WAR_fjordtheme";

	private static final String _THEME_NAME = "Fjord";

	private static final Log _log = LogFactoryUtil.getLog(
		FjordSiteInitializer.class);

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.frontend.theme.fjord.site.initializer)"
	)
	private ServletContext _servletContext;

}