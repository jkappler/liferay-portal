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

<%@ include file="/init.jsp" %>

<%
String toolbarItem = ParamUtil.getString(request, "toolbarItem", "view-pages");
%>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<portlet:renderURL var="viewPagesTreeURL">
			<portlet:param name="toolbarItem" value="view-pages" />
		</portlet:renderURL>

		<aui:nav-item
			href="<%= viewPagesTreeURL %>"
			label="pages"
			selected='<%= toolbarItem.equals("view-pages") %>'
		/>

		<portlet:renderURL var="viewMSBPageTemplatesTreeURL">
			<portlet:param name="toolbarItem" value="view-page-templates" />
		</portlet:renderURL>

		<aui:nav-item
			href="<%= viewMSBPageTemplatesTreeURL %>"
			label="page-templates"
			selected='<%= toolbarItem.equals("view-page-templates") %>'
		/>
	</aui:nav>

	<portlet:renderURL var="portletURL">
		<portlet:param name="mvcPath" value="/view.jsp" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="toolbarItem" value="<%= toolbarItem %>" />
	</portlet:renderURL>

	<aui:nav-bar-search>
		<aui:form action="<%= portletURL.toString() %>" method="post" name="fm1">
			<liferay-ui:input-search markupView="lexicon" />
		</aui:form>
	</aui:nav-bar-search>
</aui:nav-bar>

<c:choose>
	<c:when test='<%= toolbarItem.equals("view-pages") %>'>

		<%
		renderResponse.setTitle(LanguageUtil.get(request, "pages"));
		%>

		<%@ include file="/view_pages.jspf" %>
	</c:when>
	<c:otherwise>

		<%
		renderResponse.setTitle(LanguageUtil.get(request, "page-templates"));
		%>

		<%@ include file="/view_page_templates.jspf" %>
	</c:otherwise>
</c:choose>