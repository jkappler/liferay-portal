/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.display.page.internal.upgrade.v3_1_0;

import com.liferay.asset.display.page.constants.AssetDisplayPageConstants;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalService;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Jürgen Kappler
 */
public class AssetDisplayLayoutUpgradeProcess extends UpgradeProcess {

	public AssetDisplayLayoutUpgradeProcess(
		LayoutPageTemplateEntryLocalService
			layoutPageTemplateEntryLocalService) {

		_layoutPageTemplateEntryLocalService =
			layoutPageTemplateEntryLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		_upgradeAssetDisplayLayouts();
	}

	private void _upgradeAssetDisplayLayouts() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			Statement s = connection.createStatement();
			ResultSet resultSet = s.executeQuery(
				StringBundler.concat(
					"select assetDisplayPageEntryId, ",
					"layoutPageTemplateEntryId from AssetDisplayPageEntry ",
					"where type_ = ", AssetDisplayPageConstants.TYPE_SPECIFIC,
					" and (plid is null or plid = 0)"));
			PreparedStatement preparedStatement =
				AutoBatchPreparedStatementUtil.autoBatch(
					connection,
					"update AssetDisplayPageEntry set plid = ? where " +
						"assetDisplayPageEntryId = ?")) {

			while (resultSet.next()) {
				long layoutPageTemplateEntryId = resultSet.getLong(
					"layoutPageTemplateEntryId");

				LayoutPageTemplateEntry layoutPageTemplateEntry =
					_layoutPageTemplateEntryLocalService.
						fetchLayoutPageTemplateEntry(layoutPageTemplateEntryId);

				if (layoutPageTemplateEntry == null) {
					continue;
				}

				long plid = layoutPageTemplateEntry.getPlid();

				if (plid == LayoutConstants.DEFAULT_PLID) {
					continue;
				}

				preparedStatement.setLong(1, plid);

				long assetDisplayPageEntryId = resultSet.getLong(
					"assetDisplayPageEntryId");

				preparedStatement.setLong(2, assetDisplayPageEntryId);

				preparedStatement.addBatch();
			}

			preparedStatement.executeBatch();
		}
	}

	private final LayoutPageTemplateEntryLocalService
		_layoutPageTemplateEntryLocalService;

}