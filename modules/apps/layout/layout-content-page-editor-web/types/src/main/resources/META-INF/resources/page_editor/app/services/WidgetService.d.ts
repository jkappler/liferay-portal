/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {updateNetwork} from '../actions';
declare function addPortlet({
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
}): Promise<unknown>;
declare function getMasterLayoutWidgets({
	masterLayoutPlid,
}: {
	masterLayoutPlid: string;
}): Promise<unknown>;
declare function getWidgets(): Promise<unknown>;
declare function toggleWidgetHighlighted({
	highlighted,
	onNetworkStatus,
	portletId,
}: {
	highlighted: boolean;
	onNetworkStatus: (action: ReturnType<typeof updateNetwork>) => void;
	portletId: string;
}): Promise<unknown>;
declare const _default: {
	addPortlet: typeof addPortlet;
	getMasterLayoutWidgets: typeof getMasterLayoutWidgets;
	getWidgets: typeof getWidgets;
	toggleWidgetHighlighted: typeof toggleWidgetHighlighted;
};
export default _default;
