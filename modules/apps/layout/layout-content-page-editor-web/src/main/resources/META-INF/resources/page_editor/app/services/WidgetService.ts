/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {updateNetwork} from '../actions';
import {config} from '../config/index';
import draftServiceFetch from './draftServiceFetch';
import serviceFetch from './serviceFetch';

function addPortlet({
	onNetworkStatus,
	parentItemId,
	portletId,
	portletItemId,
	position,
	segmentsExperienceId,
}: {
	onNetworkStatus: (action: ReturnType<typeof updateNetwork>) => void;
	parentItemId: string;
	portletId: string;
	portletItemId: string;
	position: string;
	segmentsExperienceId: string;
}) {
	return draftServiceFetch(
		config.addPortletURL,
		{
			body: {
				parentItemId,
				portletId,
				portletItemId,
				position,
				segmentsExperienceId,
			},
		},
		onNetworkStatus
	);
}

function getMasterLayoutWidgets({
	masterLayoutPlid,
}: {
	masterLayoutPlid: string;
}) {
	return serviceFetch(config.getMasterLayoutNoninstanciablePortletsURL, {
		body: {
			masterLayoutPlid,
		},
	});
}

function getWidgets() {
	return serviceFetch(config.getPortletsURL);
}

function toggleWidgetHighlighted({
	highlighted,
	onNetworkStatus,
	portletId,
}: {
	highlighted: boolean;
	onNetworkStatus: (action: ReturnType<typeof updateNetwork>) => void;
	portletId: string;
}) {
	return draftServiceFetch(
		config.updatePortletsHighlightedConfigurationURL,
		{
			body: {
				highlighted,
				portletId,
			},
		},
		onNetworkStatus
	);
}

export default {
	addPortlet,
	getMasterLayoutWidgets,
	getWidgets,
	toggleWidgetHighlighted,
};
