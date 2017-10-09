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

package com.liferay.modern.site.building.page.web.internal.portlet.action;

import com.liferay.modern.site.building.page.service.MSBPageTemplateFolderService;
import com.liferay.modern.site.building.page.service.MSBPageTemplateService;
import com.liferay.modern.site.building.page.web.constants.PagesPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Pavel Savinov
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + PagesPortletKeys.PAGE_TEMPLATES,
		"mvc.command.name=/page_template/edit_template"
	},
	service = MVCActionCommand.class
)
public class EditTemplateMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long msbPageTemplateId = ParamUtil.getLong(
			actionRequest, "msbPageTemplateId");

		String name = ParamUtil.getString(actionRequest, "name");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		if (msbPageTemplateId <= 0) {
			//TODO: add new page template
		}
		else {
			//TODO: update existing page template
		}
	}

	@Reference
	private MSBPageTemplateService _msbPageTemplateService;

}