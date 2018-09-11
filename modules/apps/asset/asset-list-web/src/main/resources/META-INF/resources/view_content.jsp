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

<div class="preview-container pt-3">
	<liferay-portlet:runtime
		defaultPreferences="<%= assetListDisplayContext.getViewContentPreferences() %>"
		instanceId="<%= assetListDisplayContext.getAssetPublisherInstanceId() %>"
		persistSettings="<%= false %>"
		portletName="<%= AssetPublisherPortletKeys.ASSET_PUBLISHER %>"
	/>

	<div class="sidebar sidebar-light sidebar-panel">
		<div class="display-template">
			<liferay-ddm:template-selector
				className="<%= AssetEntry.class.getName() %>"
				displayStyle="<%= assetListDisplayContext.getDisplayStyle() %>"
				displayStyleGroupId="<%= assetListDisplayContext.getDisplayStyleGroupId() %>"
				displayStyles='<%= Arrays.asList("table", "title-list", "abstracts", "full-content") %>'
				label="display-template"
				refreshURL="<%= currentURL %>"
			/>
		</div>
	</div>
</div>

<aui:script require="metal-uri/src/Uri">
	var Uri = metalUriSrcUri.default;

	var selectDisplayStyle = $('#<portlet:namespace />displayStyle');

	selectDisplayStyle.on(
		'change',
		function(event) {
			if (selectDisplayStyle.prop('selectedIndex') > -1) {
				var uri = new Uri('<%= currentURL %>');

				uri.setParameterValue('<portlet:namespace/>displayStyle', selectDisplayStyle.val());

				Liferay.Util.navigate(uri.toString());
			}
		}
	);
</aui:script>

<aui:button-row>
	<aui:button type="cancel" value="close" />
</aui:button-row>