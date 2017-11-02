import Component from 'metal-component';
import { Config } from 'metal-state';
import Soy from 'metal-soy';

import './PageTemplateFragment';
import './PageTemplateFragmentCollection';
import templates from './PageTemplateEditor.soy';

/**
 * Component that allows creating/editing Page Templates
 */
class PageTemplateEditor extends Component {
	_handleFragmentCollectionEntryClick (event) {
		this.fragments = [
			...this.fragments,
			{
				id: event.fragmentId,
				name: event.fragmentName,
				config: {}
			}
		];
	}

	_handleFragmentRemoveButtonClick (event) {
		const index = event.fragmentIndex;

		this.fragments = [
			...this.fragments.slice(0, index),
			...this.fragments.slice(index + 1)
		];
	}
}

/**
 * State definition.
 * @type {!Object}
 * @static
 */
PageTemplateEditor.STATE = {
	/**
	 * Available entries that can be dragged inside the existing Page Template,
	 * organized by fragment categories.
	 * @default undefined
	 * @instance
	 * @memberOf PageTemplateEditor
	 * @type {!Array<object>}
	 */
	fragmentCollections: Config.arrayOf(
		Config.shapeOf({
			id: Config.string().required(),
			name: Config.string().required(),
			entries: Config.arrayOf(
				Config.shapeOf({
					id: Config.string().required(),
					name: Config.string().required()
				})
			).required()
		})
	).required(),

	/**
	 * List of fragment instances part of the Page Template, the order of the
	 * elements in this array defines their position.
	 * @default []
	 * @instance
	 * @memberOf PageTemplateEditor
	 * @type {Array<string>}
	 */
	fragments: Config.arrayOf(
		Config.shapeOf({
			id: Config.string().required(),
			name: Config.string().required(),
			config: Config.object().value({})
		})
	).value([]),

	/**
	 * Portlet namespace needed for prefixing form inputs
	 * @default undefined
	 * @instance
	 * @memberOf PageTemplateEditor
	 * @type {!string}
	 */
	portletNamespace: Config.string().required(),

	/**
	 * Path of the available icons.
	 * @default undefined
	 * @instance
	 * @memberOf PageTemplateEditor
	 * @type {!string}
	 */
	spritemap: Config.string().required()
};

Soy.register(PageTemplateEditor, templates);

export { PageTemplateEditor }
export default PageTemplateEditor;