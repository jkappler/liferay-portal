import Component from 'metal-component';
import {Config} from 'metal-state';
import Soy from 'metal-soy';

import templates from './PageTemplateFragmentCollection.soy';

/**
 * PageTemplateFragmentCollection
 */
class PageTemplateFragmentCollection extends Component {
	_handleEntryClick(event) {
		const fragmentId = event.delegateTarget.dataset.fragmentId;
		const fragmentName = this.collection.entries
			.find((entry) => entry.id === fragmentId)
			.name;

		this.emit('collectionEntryClick', {
			fragmentId,
			fragmentName
		});
	}
}

/**
 * State definition.
 * @type {!Object}
 * @static
 */
PageTemplateFragmentCollection.STATE = {
	/**
	 * Available entries that can be dragged inside the existing Page Template,
	 * organized by fragment categories.
	 * @default undefined
	 * @instance
	 * @memberOf PageTemplateEditor
	 * @type {!Array<object>}
	 */
	collection: Config.shapeOf({
		id: Config.string().required(),
		name: Config.string().required(),
		entries: Config.arrayOf(
			Config.shapeOf({
				id: Config.string().required(),
				name: Config.string().required()
			})
		).required()
	}),

	/**
	 * Portlet namespace needed for prefixing form inputs
	 * @default undefined
	 * @instance
	 * @memberOf PageTemplateFragmentCollection
	 * @type {!string}
	 */
	portletNamespace: Config.string().required(),

	/**
	 * Path of the available icons.
	 * @default undefined
	 * @instance
	 * @memberOf PageTemplateFragmentCollection
	 * @type {!string}
	 */
	spritemap: Config.string().required(),
};

Soy.register(PageTemplateFragmentCollection, templates);

export {PageTemplateFragmentCollection};
export default PageTemplateFragmentCollection;