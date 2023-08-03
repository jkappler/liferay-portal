/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayIcon from '@clayui/icon';
import {createPortletURL} from 'frontend-js-web';
import PropTypes from 'prop-types';
import React from 'react';

import {LAYOUT_DATA_ITEM_TYPES} from '../../app/config/constants/layoutDataItemTypes';
import {config} from '../../app/config/index';
import {useSelector} from '../../app/contexts/StoreContext';
import itemSelectorValueToCollection from '../../app/utils/item_selector_value/itemSelectorValueToCollection';
import ItemSelector from './ItemSelector';

const DEFAULT_OPTION_MENU_ITEMS = [];

export default function CollectionSelector({
	collectionItem,
	itemSelectorURL,
	label,
	item,
	onBeforeCollectionSelect,
	onCollectionSelect,
	optionsMenuItems = DEFAULT_OPTION_MENU_ITEMS,
}) {
	const eventName = `${config.portletNamespace}selectInfoList`;

	const layoutData = useSelector((state) => state.layoutData);

	const customCollectionSelectorURL = getCustomCollectionURL({
		item,
		layoutData,
	});

	const filterConfig = collectionItem?.config ?? {};

	const isPrefiltered = !!Object.keys(filterConfig).length;

	return (
		<>
			<ItemSelector
				className="mb-0"
				eventName={eventName}
				itemSelectorURL={
					customCollectionSelectorURL ||
					itemSelectorURL ||
					config.infoListSelectorURL
				}
				label={label}
				onBeforeItemSelect={onBeforeCollectionSelect}
				onItemSelect={onCollectionSelect}
				optionsMenuItems={optionsMenuItems}
				quickMappedInfoItems={
					config.selectedMappingTypes?.linkedCollection
				}
				selectedItem={collectionItem}
				showMappedItems={
					!!config.selectedMappingTypes?.linkedCollection
				}
				transformValueCallback={itemSelectorValueToCollection}
			/>

			{isPrefiltered && (
				<p className="text-info">
					<ClayIcon className="mr-2 mt-0" symbol="info-panel-open" />

					<span className="text-2">
						{Liferay.Language.get('collection-filtered')}
					</span>
				</p>
			)}
		</>
	);
}

const getCustomCollectionURL = ({item, layoutData}) => {
	const getFormOrCollectionParentConfig = (item, layoutData) => {
		if (!item) {
			return null;
		}

		if (item.type === LAYOUT_DATA_ITEM_TYPES.collection) {
			return item.config?.collection;
		}

		if (item.type === LAYOUT_DATA_ITEM_TYPES.form) {
			return item.config;
		}

		return getFormOrCollectionParentConfig(
			layoutData.items[item.parentId],
			layoutData
		);
	};

	const itemConfig = getFormOrCollectionParentConfig(
		layoutData.items[item.parentId],
		layoutData
	);

	if (!itemConfig) {
		return null;
	}

	return createPortletURL(config.selectCollectionURL, {
		classNameId: itemConfig.classNameId || 0,
		itemType: itemConfig.itemType || '',
	});
};

CollectionSelector.propTypes = {
	collectionItem: PropTypes.shape({title: PropTypes.string}),
	label: PropTypes.string,
	onBeforeCollectionSelect: PropTypes.func,
	onCollectionSelect: PropTypes.func.isRequired,
};
