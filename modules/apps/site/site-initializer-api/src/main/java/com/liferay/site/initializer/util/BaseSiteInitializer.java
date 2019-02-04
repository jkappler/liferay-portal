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

package com.liferay.site.initializer.util;

import com.liferay.fragment.constants.FragmentPortletKeys;
import com.liferay.fragment.model.FragmentCollection;
import com.liferay.fragment.model.FragmentEntry;
import com.liferay.fragment.model.FragmentEntryModel;
import com.liferay.fragment.service.FragmentCollectionLocalService;
import com.liferay.fragment.service.FragmentEntryLocalService;
import com.liferay.layout.admin.constants.LayoutAdminPortletKeys;
import com.liferay.layout.page.template.constants.LayoutPageTemplateEntryTypeConstants;
import com.liferay.layout.page.template.model.LayoutPageTemplateCollection;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.service.LayoutPageTemplateCollectionLocalService;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalService;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.Repository;
import com.liferay.portal.kernel.model.Theme;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutSetLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ThemeLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.io.InputStream;

import java.net.URL;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.site.initializer.SiteInitializer;
import org.osgi.framework.Bundle;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
public abstract class BaseSiteInitializer implements SiteInitializer {

	protected FragmentCollection addFragmentCollection(
			ServiceContext serviceContext)
		throws PortalException {

		return _fragmentCollectionLocalService.addFragmentCollection(
			serviceContext.getUserId(), serviceContext.getScopeGroupId(),
			getThemeName(), null, serviceContext);
	}

	protected ServiceContext createServiceContext(long groupId)
		throws PortalException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		User user = _userLocalService.getUser(PrincipalThreadLocal.getUserId());

		Locale locale = LocaleUtil.getSiteDefault();

		serviceContext.setLanguageId(LanguageUtil.getLanguageId(locale));

		serviceContext.setScopeGroupId(groupId);
		serviceContext.setUserId(user.getUserId());
		serviceContext.setTimeZone(user.getTimeZone());

		return serviceContext;
	}

	protected abstract String getPath();

	protected abstract String getThemeId();

	protected abstract String getThemeName();

	protected void setBundle(Bundle bundle) {
		this.bundle = bundle;
	}

	protected void updateLogo(ServiceContext serviceContext) throws Exception {
		String path = getPath();

		URL url = bundle.getEntry(path + "/images/logo.png");

		byte[] bytes = null;

		try (InputStream is = url.openStream()) {
			bytes = FileUtil.getBytes(is);
		}

		_layoutSetLocalService.updateLogo(
			serviceContext.getScopeGroupId(), false, true, bytes);
	}

	protected void updateLookAndFeel(ServiceContext serviceContext)
		throws PortalException {

		String themeId = getThemeId();

		Theme theme = _themeLocalService.fetchTheme(
			serviceContext.getCompanyId(), getThemeId());

		if (theme == null) {
			if (_log.isInfoEnabled()) {
				_log.info("No theme found for " + getThemeId());
			}

			return;
		}

		_layoutSetLocalService.updateLookAndFeel(
			serviceContext.getScopeGroupId(), false, themeId, StringPool.BLANK,
			StringPool.BLANK);
	}

	protected void addFileEntries(
			long fragmentCollectionId, long folderId,
			ServiceContext serviceContext)
		throws Exception {

		Enumeration<URL> urls = bundle.findEntries(
			getPath() + "/images", StringPool.STAR, false);

		while (urls.hasMoreElements()) {
			URL url = urls.nextElement();

			byte[] bytes = null;

			try (InputStream is = url.openStream()) {
				bytes = FileUtil.getBytes(is);
			}

			String fileName = FileUtil.getShortFileName(url.getPath());

			PortletFileRepositoryUtil.addPortletFileEntry(
				serviceContext.getScopeGroupId(), serviceContext.getUserId(),
				FragmentCollection.class.getName(), fragmentCollectionId,
				FragmentPortletKeys.FRAGMENT, folderId, bytes, fileName,
				MimeTypesUtil.getContentType(fileName), false);
		}
	}

	protected LayoutPageTemplateCollection addLayoutPageTemplateCollection(ServiceContext serviceContext)
		throws PortalException {

		return _layoutPageTemplateCollectionLocalService.
			addLayoutPageTemplateCollection(
				serviceContext.getUserId(), serviceContext.getScopeGroupId(),
				getThemeName(), getThemeName(), serviceContext);
	}

	protected void addLayout(
			long layoutPageTemplateCollectionId, String name,
			List<FragmentEntry> fragmentEntries, String path,
			ServiceContext serviceContext)
		throws Exception {

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			addLayoutPageTemplateEntry(
				layoutPageTemplateCollectionId, name, path, serviceContext);

		long[] fragmentEntryIds = ListUtil.toLongArray(
			fragmentEntries, FragmentEntryModel::getFragmentEntryId);

		_layoutPageTemplateEntryLocalService.updateLayoutPageTemplateEntry(
			layoutPageTemplateEntry.getLayoutPageTemplateEntryId(), name,
			fragmentEntryIds, StringPool.BLANK, serviceContext);

		Map<Locale, String> nameMap = new HashMap<>();

		nameMap.put(LocaleUtil.getSiteDefault(), name);

		UnicodeProperties typeSettingsProperties = new UnicodeProperties();

		typeSettingsProperties.put(
			"layoutPageTemplateEntryId",
			String.valueOf(
				layoutPageTemplateEntry.getLayoutPageTemplateEntryId()));

		_layoutLocalService.addLayout(
			serviceContext.getUserId(), serviceContext.getScopeGroupId(), false,
			LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, nameMap, new HashMap<>(),
			new HashMap<>(), new HashMap<>(), new HashMap<>(), "content",
			typeSettingsProperties.toString(), false, new HashMap<>(),
			serviceContext);
	}

	protected LayoutPageTemplateEntry addLayoutPageTemplateEntry(
			long layoutPageTemplateCollectionId, String name, String path,
			ServiceContext serviceContext)
		throws Exception {

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			_layoutPageTemplateEntryLocalService.addLayoutPageTemplateEntry(
				serviceContext.getUserId(), serviceContext.getScopeGroupId(),
				layoutPageTemplateCollectionId, name,
				LayoutPageTemplateEntryTypeConstants.TYPE_BASIC, 0,
				WorkflowConstants.STATUS_APPROVED, serviceContext);

		long previewFileEntryId = _getPreviewFileEntryId(
			LayoutAdminPortletKeys.GROUP_PAGES,
			LayoutPageTemplateEntry.class.getName(),
			layoutPageTemplateEntry.getLayoutPageTemplateEntryId(), path,
			StringUtil.toLowerCase(name) + "_thumbnail.jpg", serviceContext);

		return _layoutPageTemplateEntryLocalService.
			updateLayoutPageTemplateEntry(
				layoutPageTemplateEntry.getLayoutPageTemplateEntryId(),
				previewFileEntryId);
	}

	protected List<FragmentEntry> addFragmentEntries(
		long fragmentCollectionId, String path,
		ServiceContext serviceContext)
		throws Exception {

		List<FragmentEntry> fragmentEntries = new ArrayList<>();

		Enumeration<URL> enumeration = bundle.findEntries(
			path, "*.html", false);

		while (enumeration.hasMoreElements()) {
			URL url = enumeration.nextElement();

			String shortFileName = FileUtil.getShortFileName(url.getPath());

			StringBundler sb = new StringBundler(4);

			sb.append(path);
			sb.append(StringPool.SLASH);
			sb.append(FileUtil.stripExtension(shortFileName));
			sb.append(".css");

			URL cssURL = bundle.getEntry(sb.toString());

			FragmentEntry fragmentEntry =
				_fragmentEntryLocalService.addFragmentEntry(
					serviceContext.getUserId(),
					serviceContext.getScopeGroupId(), fragmentCollectionId,
					StringUtil.upperCaseFirstLetter(
						FileUtil.stripExtension(shortFileName)),
					StringUtil.read(cssURL.openStream()),
					StringUtil.read(url.openStream()), StringPool.BLANK,
					WorkflowConstants.STATUS_APPROVED, serviceContext);

			long fragmentEntryPreviewFileEntryId = _getPreviewFileEntryId(
				FragmentPortletKeys.FRAGMENT, FragmentEntry.class.getName(),
				fragmentEntry.getFragmentEntryId(), path, shortFileName,
				serviceContext);

			fragmentEntry = _fragmentEntryLocalService.updateFragmentEntry(
				fragmentEntry.getFragmentEntryId(),
				fragmentEntryPreviewFileEntryId);

			fragmentEntries.add(fragmentEntry);
		}

		return fragmentEntries;
	}

	private long _getPreviewFileEntryId(
		String portletId, String className, long classPK, String path,
		String fileName, ServiceContext serviceContext)
		throws Exception {

		StringBundler sb = new StringBundler(4);

		sb.append(path);
		sb.append(StringPool.SLASH);
		sb.append(StringUtil.split(fileName, StringPool.PERIOD)[0]);
		sb.append(".jpg");

		URL url = bundle.getEntry(sb.toString());

		if (url == null) {
			return 0;
		}

		Repository repository =
			PortletFileRepositoryUtil.fetchPortletRepository(
				serviceContext.getScopeGroupId(), portletId);

		if (repository == null) {
			repository = PortletFileRepositoryUtil.addPortletRepository(
				serviceContext.getScopeGroupId(), portletId, serviceContext);
		}

		String imageFileName =
			classPK + "_preview." + FileUtil.getExtension(url.getPath());

		byte[] bytes = null;

		try (InputStream is = url.openStream()) {
			bytes = FileUtil.getBytes(is);
		}

		FileEntry fileEntry = PortletFileRepositoryUtil.addPortletFileEntry(
			serviceContext.getScopeGroupId(), serviceContext.getUserId(),
			className, classPK, portletId, repository.getDlFolderId(), bytes,
			imageFileName, MimeTypesUtil.getContentType(imageFileName), false);

		return fileEntry.getFileEntryId();
	}

	protected Bundle bundle;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseSiteInitializer.class);

	@Reference
	private FragmentCollectionLocalService _fragmentCollectionLocalService;

	@Reference
	private FragmentEntryLocalService _fragmentEntryLocalService;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private LayoutSetLocalService _layoutSetLocalService;

	@Reference
	private ThemeLocalService _themeLocalService;

	@Reference
	private LayoutPageTemplateCollectionLocalService
		_layoutPageTemplateCollectionLocalService;

	@Reference
	private LayoutPageTemplateEntryLocalService
		_layoutPageTemplateEntryLocalService;

	@Reference
	private UserLocalService _userLocalService;

}