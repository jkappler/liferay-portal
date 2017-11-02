import Component from 'metal-component';
import {Config} from 'metal-state';
import Soy from 'metal-soy';

import templates from './PageTemplateFragment.soy';

/**
 * PageTemplateFragment
 */
class PageTemplateFragment extends Component {
}

/**
 * State definition.
 * @type {!Object}
 * @static
 */
PageTemplateFragment.STATE = {
	/**
	 * Fragment name
	 * @default undefined
	 * @instance
	 * @memberOf PageTemplateFragment
	 * @type {!string}
	 */
	name: Config.string().required(),
};

Soy.register(PageTemplateFragment, templates);

export {PageTemplateFragment};
export default PageTemplateFragment;