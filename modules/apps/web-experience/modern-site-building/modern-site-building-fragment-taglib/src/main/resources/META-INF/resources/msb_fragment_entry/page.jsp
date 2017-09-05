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

<%@ include file="/msb_fragment_entry/init.jsp" %>

<%
MSBFragmentEntry msbFragmentEntry = (MSBFragmentEntry)request.getAttribute("liferay-modern-site-building-fragment:msb-fragment-entry-renderer:msbFragmentEntry");

String _RANDOM_KEY_INPUT = "msb_fragment_entry_" + StringUtil.randomString();

String randomNamespace = PortalUtil.generateRandomKey(request, _RANDOM_KEY_INPUT) + StringPool.UNDERLINE + msbFragmentEntry.getName();
%>

<liferay-util:html-top outputKey="<%= randomNamespace %>">
	<style type="text/css">
		<%= msbFragmentEntry.getCss() %>
	</style>
</liferay-util:html-top>

<div id="<%= randomNamespace %>">
	<%= msbFragmentEntry.getHtml() %>
</div>

<aui:script>
	(function() {
		var fragment = document.getElementById("<%= randomNamespace %>");
		<%= msbFragmentEntry.getJs() %>
	}());
</aui:script>