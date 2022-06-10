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

package com.liferay.info.internal.servlet;

import com.liferay.info.exception.InfoFormException;
import com.liferay.info.exception.InfoFormValidationException;
import com.liferay.info.form.InfoForm;
import com.liferay.info.internal.helper.InfoRequestFieldValuesProviderHelper;
import com.liferay.info.item.InfoItemFieldValues;
import com.liferay.info.item.InfoItemReference;
import com.liferay.info.item.InfoItemServiceTracker;
import com.liferay.info.item.creator.InfoItemCreator;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.Theme;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Rubén Pulido
 */
@Component(
	immediate = true,
	property = {
		"osgi.http.whiteboard.context.path=/info_item",
		"osgi.http.whiteboard.servlet.name=com.liferay.info.internal.servlet.InfoItemServlet",
		"osgi.http.whiteboard.servlet.pattern=/info_item/*"
	},
	service = {InfoItemServlet.class, Servlet.class}
)
public class InfoItemServlet extends HttpServlet {

	@Override
	protected void doGet(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException, ServletException {

		HttpServletRequest originalHttpServletRequest =
			_portal.getOriginalServletRequest(httpServletRequest);

		String className = _portal.getClassName(
			ParamUtil.getLong(originalHttpServletRequest, "classNameId"));

		try {
			InfoItemCreator<Object> infoItemCreator =
				_infoItemServiceTracker.getFirstInfoItemService(
					InfoItemCreator.class, className);

			infoItemCreator.createFromInfoItemFieldValues(
				InfoItemFieldValues.builder(
				).infoFieldValues(
					_infoRequestFieldValuesProviderHelper.getInfoFieldValues(
						httpServletRequest)
				).infoItemReference(
					new InfoItemReference(className, 0)
				).build());
		}
		catch (InfoFormValidationException infoFormValidationException) {
			if (_log.isDebugEnabled()) {
				_log.debug(infoFormValidationException);
			}

			SessionErrors.add(
				originalHttpServletRequest,
				infoFormValidationException.getInfoFieldUniqueId(),
				infoFormValidationException);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to add info item", exception);
			}

			InfoForm infoForm = _infoItemServiceTracker.getFirstInfoItemService(
				InfoForm.class, className);

			SessionErrors.add(
				originalHttpServletRequest, infoForm.getName(),
				new InfoFormException());
		}

		httpServletResponse.sendRedirect(
			httpServletRequest.getHeader(HttpHeaders.REFERER));
	}

	private static final long serialVersionUID = 1L;

	@Activate
	@Modified
	protected void activate() {
		_infoRequestFieldValuesProviderHelper =
			new InfoRequestFieldValuesProviderHelper(_infoItemServiceTracker);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		InfoItemServlet.class);

	@Reference
	private InfoItemServiceTracker _infoItemServiceTracker;

	private volatile InfoRequestFieldValuesProviderHelper
		_infoRequestFieldValuesProviderHelper;

	@Reference
	private Portal _portal;

}