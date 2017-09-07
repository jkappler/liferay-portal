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

package com.liferay.modern.site.building.page.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the MSBPageTemplate service. Represents a row in the &quot;MSBPageTemplate&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see MSBPageTemplateModel
 * @see com.liferay.modern.site.building.page.model.impl.MSBPageTemplateImpl
 * @see com.liferay.modern.site.building.page.model.impl.MSBPageTemplateModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.modern.site.building.page.model.impl.MSBPageTemplateImpl")
@ProviderType
public interface MSBPageTemplate extends MSBPageTemplateModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.modern.site.building.page.model.impl.MSBPageTemplateImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<MSBPageTemplate, Long> MSB_PAGE_TEMPLATE_ID_ACCESSOR =
		new Accessor<MSBPageTemplate, Long>() {
			@Override
			public Long get(MSBPageTemplate msbPageTemplate) {
				return msbPageTemplate.getMsbPageTemplateId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<MSBPageTemplate> getTypeClass() {
				return MSBPageTemplate.class;
			}
		};
}