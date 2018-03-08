<#if entries?has_content>
	<div class="truncate-text">
		<@liferay_ui["icon-menu"]
			image="../language/" + themeDisplay.getLanguageId()
			message=themeDisplay.getLanguageId()
		>
			<#list entries as entry>
				<#if !entry.isSelected() && !entry.isDisabled()>
					<@liferay_ui["icon"]
						image="../language/" + entry.getLanguageId()
						label=true
						lang=entry.getW3cLanguageId()
						message=entry.getLanguageId()
						url=entry.getURL()
					/>
				</#if>
			</#list>
		</@>
	</div>
</#if>