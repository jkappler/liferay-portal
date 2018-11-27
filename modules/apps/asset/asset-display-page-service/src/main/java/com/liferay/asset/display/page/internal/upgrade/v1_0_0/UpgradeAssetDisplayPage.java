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

package com.liferay.asset.display.page.internal.upgrade.v1_0_0;

import com.liferay.asset.display.page.constants.AssetDisplayPageConstants;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 * @author Vendel Toreki
 */
public class UpgradeAssetDisplayPage extends UpgradeProcess {

	public UpgradeAssetDisplayPage(
		ClassNameLocalService classNameLocalService) {

		_classNameLocalService = classNameLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeSchema();

		upgradeAssetDisplayPageEntry();
	}

	protected void upgradeAssetDisplayPageEntry() throws Exception {
		StringBuilder sb = new StringBuilder(8);

		sb.append("select groupId, companyId, userId, userName, createDate, ");
		sb.append("resourcePrimKey from JournalArticle where ");
		sb.append("JournalArticle.layoutUuid is not null and ");
		sb.append("JournalArticle.layoutUuid != '' and not exists ( ");
		sb.append("select 1 from AssetDisplayPageEntry where ");
		sb.append("AssetDisplayPageEntry.classNameId = ? ");
		sb.append("and AssetDisplayPageEntry.classPK = ");
		sb.append("JournalArticle.resourcePrimKey )");

		long journalArticleClassNameId = _classNameLocalService.getClassNameId(
			JournalArticle.class);

		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement ps1 = connection.prepareStatement(sb.toString());
			PreparedStatement ps2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection, _createInsertSql());) {

			ps1.setLong(1, journalArticleClassNameId);

			try (ResultSet rs = ps1.executeQuery()) {
				while (rs.next()) {
					long groupId = rs.getLong("groupId");
					long companyId = rs.getLong("companyId");
					long userId = rs.getLong("userId");
					String userName = rs.getString("userName");
					Timestamp createDate = rs.getTimestamp("createDate");
					long resourcePrimKey = rs.getLong("resourcePrimKey");

					ps2.setString(1, PortalUUIDUtil.generate());
					ps2.setLong(2, increment());
					ps2.setLong(3, groupId);
					ps2.setLong(4, companyId);
					ps2.setLong(5, userId);
					ps2.setString(6, userName);
					ps2.setDate(7, new Date(createDate.getTime()));
					ps2.setDate(8, new Date(createDate.getTime()));
					ps2.setLong(9, journalArticleClassNameId);
					ps2.setLong(10, resourcePrimKey);
					ps2.setLong(11, 0);
					ps2.setInt(12, AssetDisplayPageConstants.TYPE_SPECIFIC);

					ps2.addBatch();
				}

				ps2.executeBatch();
			}
		}
	}

	protected void upgradeSchema() throws Exception {
		String template = StringUtil.read(
			UpgradeAssetDisplayPage.class.getResourceAsStream(
				"dependencies/update.sql"));

		runSQLTemplateString(template, false, false);
	}

	private String _createInsertSql() {
		StringBuilder sb = new StringBuilder();

		sb.append("insert into AssetDisplayPageEntry (uuid_, ");
		sb.append("assetDisplayPageEntryId, groupId, companyId, userId, ");
		sb.append("userName, createDate, modifiedDate, ");
		sb.append("classNameId, classPK, layoutPageTemplateEntryId, ");
		sb.append("type_) values (?, ?, ?, ?, ?, ?, ?, ");
		sb.append("?, ?, ?, ?, ?)");

		return sb.toString();
	}

	private final ClassNameLocalService _classNameLocalService;

}