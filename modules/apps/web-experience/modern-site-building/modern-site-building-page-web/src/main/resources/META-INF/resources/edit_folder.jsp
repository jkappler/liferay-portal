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

long msbPageTemplateFolderId = ParamUtil.getLong(request, "msbPageTemplateFolderId");

MSBPageTemplateFolder msbPageTemplateFolder = null;

if (msbPageTemplateFolderId > 0) {
	msbPageTemplateFolder = MSBPageTemplateFolderLocalServiceUtil.fetchMSBPageTemplateFolder(msbPageTemplateFolderId);
}

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(((msbPageTemplateFolder == null) ? LanguageUtil.get(request, "add-page-template-folder") : msbPageTemplateFolder.getName()));
%>

<liferay-portlet:actionURL name="/page_template/edit_folder" portletName="<%= PagesPortletKeys.PAGE_TEMPLATES %>" var="editMSBPageTemplateFolderURL">
	<portlet:param name="mvcPath" value="/edit_folder.jsp" />
</liferay-portlet:actionURL>

<aui:form action="<%= editMSBPageTemplateFolderURL %>" cssClass="container-fluid-1280" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="msbPageTemplateFolderId" type="hidden" value="<%= msbPageTemplateFolderId %>" />

	<aui:model-context bean="<%= msbPageTemplateFolder %>" model="<%= MSBPageTemplateFolder.class %>" />

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:input autoFocus="<%= true %>" label="name" name="name" placeholder="name">
				<aui:validator errorMessage="please-enter-a-valid-name" name="required" />
			</aui:input>

			<aui:input name="description" placeholder="description" />
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />

		<aui:button cssClass="btn-lg" href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>