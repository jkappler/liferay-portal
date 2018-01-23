import Component from 'metal-component';
import {Config} from 'metal-state';
import Soy from 'metal-soy';

import templates from './LayoutPageTemplateSidebarMapping.soy';

/**
 * LayoutPageTemplateSidebarMapping
 */
class LayoutPageTemplateSidebarMapping extends Component {
	/**
	 * @inheritDoc
	 * @param {object} changes
	 * @review
	 */
	willReceiveState(changes) {
		if (changes.mappingAssetTypes && changes.mappingAssetTypes.newVal) {
			const firstMappingAssetType = changes.mappingAssetTypes.newVal[0];

			if (firstMappingAssetType) {
				this._fetchMappingAssetFieldList(firstMappingAssetType.id);
			}
		}
	}

	/**
	 * Fetch a list of mapping assets for a given type id and stores
	 * the result inside _mappingAssets attribute
	 * @param mappingTypeId
	 * @private
	 * @review
	 */
	_fetchMappingAssetFieldList(mappingTypeId) {
		const formData = new FormData();

		formData.append(`${this.namespace}mappingTypeId`, mappingTypeId);

		this._loading = true;
		this._error = '';

		fetch(`${this.mappingAssetFieldListURL}`, {
			body: formData,
			credentials: 'include',
			method: 'POST'
		})
			.then(response => response.json())
			.then(response => {
				this._mappingAssets = response.mappingAssets || [];
				this._loading = false;
			})
			.catch(() => {
				this._mappingAssets = [];
				// this._error = Liferay.Language.get(
				// 	'error-loading-mapping-assets'
				// );

				this._loading = false;
			})
	}

	/**
	 * Callback called everytime a new mapping type is selected
	 * @param {Event} event
	 * @review
	 */
	_handleChangeAssetTypeSelect(event) {
		this._fetchMappingAssetFieldList(event.delegateTarget.value);
	}
}

/**
 * State definition.
 * @review
 * @static
 * @type {!Object}
 */
LayoutPageTemplateSidebarMapping.STATE = {
	/**
	 * URL used for fetching a list of AssetField from server
	 * @default undefined
	 * @instance
	 * @memberOf LayoutPageTemplateSidebarMapping
	 * @review
	 * @type {!string}
	 */
	mappingAssetFieldListURL: Config
		.string()
		.required(),

	/**
	 * List of asset types
	 * @default undefined
	 * @instance
	 * @memberOf LayoutPageTemplateSidebarMapping
	 * @review
	 * @type {!Array<{
	 *   id: string,
	 *   label: string
	 * }>}
	 */
	mappingAssetTypes: Config
		.arrayOf(Config.shapeOf({
			id: Config.string(),
			label: Config.string()
		}))
		.required(),

	/**
	 * Portlet namespace needed for prefixing form inputs
	 * @default undefined
	 * @instance
	 * @memberOf LayoutPageTemplateSidebarMapping
	 * @review
	 * @type {!string}
	 */
	namespace: Config.string().required(),

	/**
	 * If it has a truthy value and the component is not loading,
	 * it will be shown to the user as result of the loading process.
	 * @default ''
	 * @instance
	 * @memberOf LayoutPageTemplateSidebarMapping
	 * @private
	 * @review
	 * @type {string}
	 */
	_error: Config.string().value(''),

	/**
	 * Flag indicating that fragment information is being loaded
	 * @default false
	 * @instance
	 * @memberOf LayoutPageTemplateSidebarMapping
	 * @private
	 * @review
	 * @type {boolean}
	 */
	_loading: Config.bool().value(false),

	/**
	 * Mapping asset list associated to the selected type
	 * @default []
	 * @instance
	 * @memberOf LayoutPageTemplateSidebarMapping
	 * @private
	 * @review
	 * @type {Array<{
	 * 	fieldName: string,
	 * 	label: string
	 * }>}
	 */
	_mappingAssets: Config
		.arrayOf(Config.shapeOf({
			fieldName: Config.string(),
			label: Config.string()
		}))
		.value([])
};

Soy.register(LayoutPageTemplateSidebarMapping, templates);

export {LayoutPageTemplateSidebarMapping};
export default LayoutPageTemplateSidebarMapping;
