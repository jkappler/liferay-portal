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
LayoutSet selLayoutSet = editLayoutSetDisplayContext.getSelLayoutSet();
%>

<div class="form-group">
	<img alt="<%= HtmlUtil.escape(editLayoutSetDisplayContext.getFaviconTitle()) %>" class="mb-2" height="16" id="<portlet:namespace />faviconImage" src="<%= editLayoutSetDisplayContext.getFaviconURL() %>" width="16" />

	<p>
		<b><liferay-ui:message key="favicon-name" />:</b> <span id="<portlet:namespace />faviconTitle"><%= editLayoutSetDisplayContext.getFaviconTitle() %></span>
	</p>

	<aui:input name="faviconFileEntryId" type="hidden" value="<%= selLayoutSet.getFaviconFileEntryId() %>" />
	<aui:input name="themeFaviconCETExternalReferenceCode" type="hidden" value="<%= editLayoutSetDisplayContext.getThemeFaviconCETExternalReferenceCode() %>" />

	<aui:button name="selectFaviconButton" value="change-favicon" />

	<aui:button disabled="<%= !editLayoutSetDisplayContext.isClearFaviconButtonEnabled() %>" name="clearFaviconButton" value="clear" />

	<aui:script sandbox="<%= true %>">
		const clearFaviconButton = document.getElementById(
			'<portlet:namespace />clearFaviconButton'
		);
		const faviconFileEntryId = document.getElementById(
			'<portlet:namespace />faviconFileEntryId'
		);
		const faviconImage = document.getElementById(
			'<portlet:namespace />faviconImage'
		);
		const faviconTitle = document.getElementById(
			'<portlet:namespace />faviconTitle'
		);
		const selectLayoutButton = document.getElementById(
			'<portlet:namespace />selectFaviconButton'
		);
		const themeFaviconCETExternalReferenceCode = document.getElementById(
			'<portlet:namespace />themeFaviconCETExternalReferenceCode'
		);

		selectLayoutButton.addEventListener('click', (event) => {
			event.preventDefault();

			Liferay.Util.openSelectionModal({
				onSelect: function (selectedItem) {
					if (
						faviconFileEntryId &&
						faviconImage &&
						faviconTitle &&
						selectedItem &&
						selectedItem.value &&
						themeFaviconCETExternalReferenceCode
					) {
						const itemValue = JSON.parse(selectedItem.value);

						if (
							selectedItem.returnType ===
							'<%= CETItemSelectorReturnType.class.getName() %>'
						) {
							faviconFileEntryId.value = 0;
							themeFaviconCETExternalReferenceCode.value =
								itemValue.cetExternalReferenceCode;
						}
						else {
							faviconFileEntryId.value = itemValue.fileEntryId;
							themeFaviconCETExternalReferenceCode.value = '';
						}

						if (itemValue.url) {
							faviconImage.src = itemValue.url;
						}
						else {
							faviconImage.classList.add('d-none');
						}

						faviconTitle.innerHTML = itemValue.title || itemValue.name;
					}
				},
				selectEventName:
					'<%= editLayoutSetDisplayContext.getSelectFaviconEventName() %>',
				title: '<liferay-ui:message key="change-favicon" />',
				url: '<%= editLayoutSetDisplayContext.getFileEntryItemSelectorURL() %>',
			});
		});

		if (
			clearFaviconButton &&
			faviconFileEntryId &&
			faviconImage &&
			faviconTitle &&
			themeFaviconCETExternalReferenceCode
		) {
			clearFaviconButton.addEventListener('click', (event) => {
				faviconFileEntryId.value = 0;
				faviconImage.classList.add('d-none');
				faviconTitle.innerHTML =
					'<liferay-ui:message key="favicon-from-theme" />';
				themeFaviconCETExternalReferenceCode.value = '';
			});
		}
	</aui:script>
</div>