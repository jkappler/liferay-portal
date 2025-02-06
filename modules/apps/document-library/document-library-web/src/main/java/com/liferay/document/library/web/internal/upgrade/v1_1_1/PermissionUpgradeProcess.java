/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.web.internal.upgrade.v1_1_1;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;

/**
 * @author Gergely Szalay
 */
public class PermissionUpgradeProcess extends UpgradeProcess {
	@Override
	protected void doUpgrade() throws Exception {
		_addDocumentFolderAdminResourceActions();
	}

	private void _addDocumentFolderAdminResourceActions() {

		long resourceActionId = increment(ResourceAction.class.getName());

		try (PreparedStatement preparedStatement = connection.prepareStatement(
			StringBundler.concat(
				"insert into ResourceAction (mvccVersion, ",
				"resourceActionId, name, actionId, bitwiseValue) values ",
				"(?, ?, ?, ?, ?)"))) {

			preparedStatement.setLong(1, 0);
			preparedStatement.setLong(2, resourceActionId);
			preparedStatement.setString(
				3,
				"com.liferay.document.library.kernel.model.DLFolder");
			preparedStatement.setString(4, ActionKeys.ADVANCE_UPDATE);
			preparedStatement.setLong(5, 512);

			preparedStatement.executeUpdate();
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to add resource action", exception);
			}
		}
	}


	private static final Log _log = LogFactoryUtil.getLog(
		PermissionUpgradeProcess.class);
}
