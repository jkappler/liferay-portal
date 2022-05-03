package com.liferay.portal.upgrade.v7_4_x;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

public class UpgradeCollectionLayout extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			StringBundler.concat(
				"update Layout set type_ = '", LayoutConstants.TYPE_CONTENT,
				"' where type_ = '", LayoutConstants.TYPE_COLLECTION, "'"));
	}

}