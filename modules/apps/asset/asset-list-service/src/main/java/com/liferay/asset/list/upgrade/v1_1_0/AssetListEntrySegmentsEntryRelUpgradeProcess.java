package com.liferay.asset.list.upgrade.v1_1_0;

import com.liferay.asset.list.model.AssetListEntrySegmentsEntryRel;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Yurena Cabrera
 */
public class AssetListEntrySegmentsEntryRelUpgradeProcess extends UpgradeProcess {
	@Override
	protected void doUpgrade() throws Exception {
		alter(
			AssetListEntrySegmentsEntryRel.class,
			new UpgradeProcess.AlterTableAddColumn("assetListEntrySegmentsEntryRel", "INTEGER"));
	}
}
