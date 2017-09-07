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
String redirect = ParamUtil.getString(request, "redirect");

if (Validator.isNull(redirect)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	redirect = portletURL.toString();
}

long msbPageTemplateId = ParamUtil.getLong(request, "msbPageTemplateId");

MSBPageTemplate msbPageTemplate = null;

if (msbPageTemplateId > 0) {
	msbPageTemplate = MSBPageTemplateLocalServiceUtil.fetchMSBPageTemplate(msbPageTemplateId);
}

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(((msbPageTemplate == null) ? LanguageUtil.get(request, "add-page-template") : msbPageTemplate.getName()));
%>

<liferay-portlet:actionURL name="/page_template/edit_template" portletName="<%= PagesPortletKeys.PAGE_TEMPLATES %>" var="editMSBPageTemplateURL">
	<portlet:param name="mvcPath" value="/edit_page_template.jsp" />
</liferay-portlet:actionURL>

<aui:form action="<%= editMSBPageTemplateURL %>" cssClass="container-fluid-1280" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="msbPageTemplateId" type="hidden" value="<%= msbPageTemplateId %>" />
	<aui:input name="name" type="hidden" />

	<aui:model-context bean="<%= msbPageTemplate %>" model="<%= MSBPageTemplateFolder.class %>" />

	<aui:button-row cssClass="pull-right">
		<aui:button cssClass="btn-lg" type="submit" value="publish" />
	</aui:button-row>

	<%
	Map<String, Object> context = new HashMap<>();

	context.put("fragmentCollections", msbPageTemplateDisplayContext.getFragmentCollectionsJSONArray());
	%>

</aui:form>