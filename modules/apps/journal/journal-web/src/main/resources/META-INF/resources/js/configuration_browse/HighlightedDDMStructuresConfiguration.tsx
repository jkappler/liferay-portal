/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import ClayForm, {ClayInput} from '@clayui/form';
import {openSelectionModal, sub} from 'frontend-js-web';
import React, {useState} from 'react';

import {Item, LabelList} from './LabelList';

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
}: Props) {
	const [selectedDDMStructures, setSelectedDDMStructures] = useState<
		DDMStructure[]
	>(initialSelectedDDMStructures || []);

	const onSelectButtonClick = () =>
		openSelectionModal({
			multiple: true,
			onSelect: (selectedItems: Array<{value: string}>) =>
				setSelectedDDMStructures((previousSelectedItems) =>
					[
						...previousSelectedItems,
						...selectedItems.map((item) => JSON.parse(item.value)),
					].filter(
						(ddmStructure, index, ddmStructureList) =>
							index ===
							ddmStructureList.findIndex(
								({ddmstructureid}) =>
									ddmstructureid ===
									ddmStructure.ddmstructureid
							)
					)
				),
			title: sub(
				Liferay.Language.get('select-x'),
				Liferay.Language.get('structures')
			),
			url: selectDDMStructureURL,
		});

	return (
		<div className="c-px-4">
			<p className="c-pb-4">
				{Liferay.Language.get(
					'select-the-structures-you-want-to-highlight-in-web-content-administration-to-quickly-access-and-manage-all-its-contents'
				)}
			</p>

			<input
				name={`${portletNamespace}preferences--highlightedDDMStructures--`}
				type="hidden"
				value={selectedDDMStructures
					.map((ddmStructure) => ddmStructure.ddmstructureid)
					.join(',')}
			/>

			<ClayForm.Group>
				<h4 className="h5 text-weight-semi-bold">
					{Liferay.Language.get('highlighted-structures')}
				</h4>

				<ClayInput.Group>
					<ClayInput.GroupItem>
						<LabelList
							items={selectedDDMStructures.map(
								ddmStructureToItem
							)}
							onItemsChange={(nextItems) =>
								setSelectedDDMStructures(
									nextItems.map(itemToDDMStructure)
								)
							}
						/>
					</ClayInput.GroupItem>

					<ClayInput.GroupItem shrink>
						<ClayButton
							displayType="secondary"
							onClick={onSelectButtonClick}
							type="button"
						>
							{Liferay.Language.get('select')}
						</ClayButton>
					</ClayInput.GroupItem>
				</ClayInput.Group>
			</ClayForm.Group>
		</div>
	);
}

function ddmStructureToItem(ddmStructure: DDMStructure): Item {
	return {
		label: ddmStructure.name,
		value: ddmStructure.ddmstructureid,
	};
}

function itemToDDMStructure(item: Item): DDMStructure {
	return {
		ddmstructureid: item.value,
		name: item.label,
	};
}
