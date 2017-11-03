import Component from 'metal-component';
import {Config} from 'metal-state';
import Soy from 'metal-soy';

import templates from './PageTemplateFragment.soy';

/**
 * PageTemplateFragment
 */
class PageTemplateFragment extends Component {
	_handleFragmentRemoveButtonClick () {
		this.emit('fragmentRemoveButtonClick', {
			fragmentIndex: this.index
		});
	}
}

/**
 * State definition.
 * @type {!Object}
 * @static
 */
PageTemplateFragment.STATE = {
	/**
	 * Fragment index
	 * @default undefined
	 * @instance
	 * @memberOf PageTemplateFragment
	 * @type {!number}
	 */
	index: Config.number().required(),

	/**
	 * Fragment name
	 * @default undefined
	 * @instance
	 * @memberOf PageTemplateFragment
	 * @type {!string}
	 */
	name: Config.string().required(),

	/**
	 * Fragment spritemap
	 * @default undefined
	 * @instance
	 * @memberOf PageTemplateFragment
	 * @type {!string}
	 */
	spritemap: Config.string().required(),
};

Soy.register(PageTemplateFragment, templates);

export {PageTemplateFragment};
export default PageTemplateFragment;