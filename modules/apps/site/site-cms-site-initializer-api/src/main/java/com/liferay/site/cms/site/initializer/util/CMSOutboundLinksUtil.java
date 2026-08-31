/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.cms.site.initializer.util;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Jürgen Kappler
 */
public class CMSOutboundLinksUtil {

	public static String getObjectEntryExternalReferenceCode(String token) {
		return _getValue(_PREFIX_OBJECT_ENTRY_ERC, token);
	}

	public static String getObjectEntryExternalReferenceCodeToken(
		String externalReferenceCode) {

		return _getToken(_PREFIX_OBJECT_ENTRY_ERC, externalReferenceCode);
	}

	public static long getObjectEntryId(String token) {
		return GetterUtil.getLong(_getValue(_PREFIX_OBJECT_ENTRY_ID, token));
	}

	public static String getObjectEntryIdToken(long objectEntryId) {
		return _getToken(
			_PREFIX_OBJECT_ENTRY_ID, String.valueOf(objectEntryId));
	}

	private static String _getToken(String prefix, String value) {
		return StringBundler.concat(prefix, StringPool.UNDERLINE, value);
	}

	private static String _getValue(String prefix, String token) {
		String tokenPrefix = prefix + StringPool.UNDERLINE;

		if (!token.startsWith(tokenPrefix)) {
			return null;
		}

		return token.substring(tokenPrefix.length());
	}

	private static final String _PREFIX_OBJECT_ENTRY_ERC = "objectEntryERC";

	private static final String _PREFIX_OBJECT_ENTRY_ID = "objectEntryId";

}