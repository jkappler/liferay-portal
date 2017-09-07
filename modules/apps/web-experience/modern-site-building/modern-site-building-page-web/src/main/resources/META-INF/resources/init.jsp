<%--
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
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %><%@
taglib uri="http://liferay.com/tld/soy" prefix="soy" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.frontend.taglib.servlet.taglib.util.AddMenuKeys" %><%@
page import="com.liferay.modern.site.building.page.model.MSBPageTemplate" %><%@
page import="com.liferay.modern.site.building.page.model.MSBPageTemplateFolder" %><%@
page import="com.liferay.modern.site.building.page.service.MSBPageTemplateFolderLocalServiceUtil" %><%@
page import="com.liferay.modern.site.building.page.service.MSBPageTemplateLocalServiceUtil" %><%@
page import="com.liferay.modern.site.building.page.web.constants.PagesPortletKeys" %><%@
page import="com.liferay.modern.site.building.page.web.internal.display.context.MSBPageTemplateDisplayContext" %><%@
page import="com.liferay.modern.site.building.page.web.internal.display.context.MSBPagesDisplayContext" %><%@
page import="com.liferay.modern.site.building.page.web.internal.util.MSBPageTemplatePortletUtil" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.model.Layout" %><%@
page import="com.liferay.portal.kernel.portlet.PortletProvider" %><%@
page import="com.liferay.portal.kernel.portlet.PortletProviderUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.dao.search.ResultRow" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.security.permission.ActionKeys" %><%@
page import="com.liferay.modern.site.building.page.service.permission.MSBPageTemplateFolderPermission" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.modern.site.building.page.constants.MSBPageTemplateActionKeys" %><%@
page import="com.liferay.modern.site.building.page.service.permission.MSBPageTemplatePermission" %>

<%@ page import="java.util.HashMap" %><%@
page import="java.util.Map" %>

<%@ page import="javax.portlet.PortletURL" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%@ include file="/init-ext.jsp" %>

<%
MSBPagesDisplayContext msbPagesDisplayContext = new MSBPagesDisplayContext(renderRequest, renderResponse, request);
MSBPageTemplateDisplayContext msbPageTemplateDisplayContext = new MSBPageTemplateDisplayContext(renderRequest, renderResponse, request);
%>