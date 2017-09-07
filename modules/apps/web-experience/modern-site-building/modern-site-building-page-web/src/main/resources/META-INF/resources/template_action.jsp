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
String redirect = ParamUtil.getString(request, "redirect", currentURL);

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

MSBPageTemplate msbPageTemplate = (MSBPageTemplate)row.getObject();
%>

<liferay-ui:icon-menu direction="left-side" icon="<%= StringPool.BLANK %>" markupView="lexicon" message="<%= StringPool.BLANK %>" showWhenSingleIcon="<%= true %>">
	<c:if test="<%= MSBPageTemplatePermission.contains(permissionChecker, msbPageTemplate, ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="mvcPath" value="/edit_folder.jsp" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="groupId" value="<%= String.valueOf(msbPageTemplate.getGroupId()) %>" />
			<portlet:param name="msbPageTemplateFolderId" value="<%= String.valueOf(msbPageTemplate.getMsbPageTemplateId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="edit"
			url="<%= editURL %>"
		/>
	</c:if>

	<c:if test="<%= MSBPageTemplatePermission.contains(permissionChecker, msbPageTemplate, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= MSBPageTemplateFolder.class.getName() %>"
			modelResourceDescription="<%= HtmlUtil.escape(msbPageTemplate.getName()) %>"
			resourcePrimKey="<%= String.valueOf(msbPageTemplate.getPrimaryKey()) %>"
			var="permissionsURL"
			windowState="<%= LiferayWindowState.POP_UP.toString() %>"
		/>

		<liferay-ui:icon
			message="permissions"
			method="get"
			url="<%= permissionsURL %>"
			useDialog="<%= true %>"
		/>
	</c:if>

	<c:if test="<%= MSBPageTemplatePermission.contains(permissionChecker, msbPageTemplate, ActionKeys.DELETE) %>">
		<liferay-portlet:actionURL name="/page_template/delete_folder" portletName="<%= PagesPortletKeys.PAGE_TEMPLATES %>" var="deleteURL">
			<liferay-portlet:param name="redirect" value="<%= currentURL %>" />
			<liferay-portlet:param name="groupId" value="<%= String.valueOf(msbPageTemplate.getGroupId()) %>" />
			<liferay-portlet:param name="msbPageTemplateFolderId" value="<%= msbPageTemplate.getMsbPageTemplateId() %>" />
		</liferay-portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>