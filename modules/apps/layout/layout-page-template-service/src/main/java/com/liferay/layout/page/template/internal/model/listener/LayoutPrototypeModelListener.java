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

package com.liferay.layout.page.template.internal.model.listener;

import com.liferay.layout.page.template.constants.LayoutPageTemplateEntryTypeConstants;
import com.liferay.layout.page.template.model.LayoutPageTemplateCollection;
import com.liferay.layout.page.template.service.LayoutPageTemplateCollectionLocalService;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.LayoutPrototype;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(immediate = true, service = ModelListener.class)
public class LayoutPrototypeModelListener
	extends BaseModelListener<LayoutPrototype> {

	@Override
	public void onAfterCreate(LayoutPrototype layoutPrototype)
		throws ModelListenerException {

		try {
			long layoutPrototypeId = layoutPrototype.getLayoutPrototypeId();
			long companyId = layoutPrototype.getCompanyId();
			long userId = layoutPrototype.getUserId();
			String nameXML = layoutPrototype.getName();

			Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(
				nameXML);

			Locale defaultLocale = LocaleUtil.fromLanguageId(
				LocalizationUtil.getDefaultLanguageId(nameXML));

			String name = nameMap.get(defaultLocale);

			Company company = _companyLocalService.getCompany(companyId);

			long layoutPageTemplateCollectionId =
				_getLayoutPageTemplateCollectionId(
					layoutPrototype, company, defaultLocale);

			_layoutPageTemplateEntryLocalService.addLayoutPageTemplateEntry(
				userId, company.getGroupId(), layoutPageTemplateCollectionId,
				name, LayoutPageTemplateEntryTypeConstants.TYPE_WIDGET_PAGE,
				new long[0], WorkflowConstants.STATUS_APPROVED,
				layoutPrototypeId, new ServiceContext());
		}
		catch (PortalException pe) {
			pe.printStackTrace();
		}
	}

	private long _getLayoutPageTemplateCollectionId(
			LayoutPrototype layoutPrototype, Company company, Locale locale)
		throws PortalException {

		LayoutPageTemplateCollection layoutPageTemplateCollection =
			_layoutPageTemplateCollectionLocalService.
				fetchSystemLayoutPageTemplateCollection(company.getGroupId());

		if (layoutPageTemplateCollection == null) {
			String name = LanguageUtil.format(
				locale, "x-pages", LanguageUtil.get(locale, "application"));

			layoutPageTemplateCollection =
				_layoutPageTemplateCollectionLocalService.
					addLayoutPageTemplateCollection(
						layoutPrototype.getUserId(), company.getGroupId(), name,
						StringPool.BLANK, true, new ServiceContext());
		}

		return layoutPageTemplateCollection.getLayoutPageTemplateCollectionId();
	}

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference(unbind = "-")
	private LayoutPageTemplateCollectionLocalService
		_layoutPageTemplateCollectionLocalService;

	@Reference(unbind = "-")
	private LayoutPageTemplateEntryLocalService
		_layoutPageTemplateEntryLocalService;

}