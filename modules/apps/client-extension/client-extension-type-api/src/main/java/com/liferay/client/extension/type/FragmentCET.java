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

package com.liferay.client.extension.type;

import com.liferay.client.extension.type.annotation.CETProperty;
import com.liferay.client.extension.type.annotation.CETType;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Pablo Molina
 */
@CETType(description = "This is a description.", name = "fragment")
@ProviderType
public interface FragmentCET extends CET {

	@CETProperty(
		defaultValue = "", label = "css", name = "css",
		type = CETProperty.Type.String
	)
	public String getCSS();

	@CETProperty(
		defaultValue = "", label = "html", name = "html",
		type = CETProperty.Type.String
	)
	public String getHTML();

	@CETProperty(
		defaultValue = "", label = "js", name = "js",
		type = CETProperty.Type.String
	)
	public String getJS();

}