/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

/// <reference types="react" />

interface DDMStructure {
	ddmstructureid: string;
	name: string;
}
interface Props {
	portletNamespace: string;
	selectDDMStructureURL: string;
	selectedDDMStructures?: DDMStructure[];
}
export default function HighlightedDDMStructuresConfiguration({
	portletNamespace,
	selectDDMStructureURL,
	selectedDDMStructures: initialSelectedDDMStructures,
}: Props): JSX.Element;
export {};
