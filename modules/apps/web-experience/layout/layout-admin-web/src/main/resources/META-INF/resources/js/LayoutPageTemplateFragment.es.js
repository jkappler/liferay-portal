import Component from 'metal-component';
import {Config} from 'metal-state';
import Soy from 'metal-soy';

import templates from './LayoutPageTemplateFragment.soy';

/**
 * LayoutPageTemplateFragment
 * @review
 */
class LayoutPageTemplateFragment extends Component {
	/**
	 * @inheritDoc
	 * @review
	 */
	created() {
		this._handleEditorChange = this._handleEditorChange.bind(this);

		this._fetchFragmentContent(this.fragmentEntryId, this.index);
	}

	/**
	 * @inheritDoc
	 * @review
	 */
	detached() {
		for (let editor of this._editors) {
			editor.destroy();
		}

		this._editors = [];
	}

	/**
	 * After each render, script tags need to be reapended to the DOM
	 * in order to trigger an execution (content changes do not trigger it).
	 * @inheritDoc
	 * @review
	 */
	rendered() {
		if (this.refs.content) {
			this._executeFragmentScripts(this.refs.content);

			this._enableEditableFields(this.refs.content);
		}
	}

	/**
	 * @inheritDoc
	 * @param {object} changes
	 * @review
	 */
	willUpdate(changes) {
		if (changes.fragmentEntryId || changes.index) {
			const fragmentEntryId = changes.fragmentEntryId
				? changes.fragmentEntryId.newVal
				: this.fragmentEntryId;
			const fragmentEntryInstanceId = changes.index
				? changes.index.newVal
				: this.index;

			this._fetchFragmentContent(
				fragmentEntryId, fragmentEntryInstanceId);
		}
	}

	/**
	 * Allow inline edition using AlloyEditor
	 * @param {HTMLElement} content
	 * @private
	 * @review
	 */
	_enableEditableFields(content) {
		const editors = [];

		for (let editableElement of content.querySelectorAll('lfr-editable')) {
			const wrapper = document.createElement('div');
			wrapper.dataset.lfrEditableId = editableElement.id;
			wrapper.innerHTML = editableElement.innerHTML;
			editableElement.parentNode.replaceChild(wrapper, editableElement);

			const editor = AlloyEditor.editable(wrapper, {});
			editor.get('nativeEditor').on('change', this._handleEditorChange);
			editors.push(editor);
		}

		for (let editor of this._editors) {
			const newEditor = editors.find(newEditor =>
				newEditor.get('nativeEditor').element.$.dataset
					.lfrEditableId ===
				editor.get('nativeEditor').element.$.dataset.lfrEditableId
			);

			if (newEditor) {
				newEditor.get('nativeEditor').setData(
					editor.get('nativeEditor').getData()
				);
			}

			editor.destroy();
		}

		this._editors = editors;
	}

	/**
	 * After each render, script tags need to be reapended to the DOM
	 * in order to trigger an execution (content changes do not trigger it).
	 * @param {HTMLElement} content
	 * @private
	 * @review
	 */
	_executeFragmentScripts(content) {
		content.querySelectorAll('script').forEach(script => {
			const parentNode = script.parentNode;
			const newScript = document.createElement('script');

			newScript.innerHTML = script.innerHTML;
			parentNode.removeChild(script);
			parentNode.appendChild(newScript);
		});
	}

	/**
	 * Fetches a fragment entry from the given ID, and stores the HTML,
	 * CSS and JS result into component properties.
	 * @param {!string} fragmentEntryId
	 * @param {!string} fragmentEntryInstanceId
	 * @private
	 * @review
	 */
	_fetchFragmentContent(fragmentEntryId, fragmentEntryInstanceId) {
		const formData = new FormData();

		formData.append(
			`${this.portletNamespace}fragmentEntryId`,
			fragmentEntryId
		);
		formData.append(
			`${this.portletNamespace}fragmentEntryInstanceId`,
			fragmentEntryInstanceId
		);

		this._loading = true;

		fetch(this.renderFragmentEntryURL, {
			body: formData,
			credentials: 'include',
			method: 'POST',
		})
			.then(response => response.json())
			.then(response => {
				this._content = Soy.toIncDom(response.content);
				this._loading = false;
			});
	}

	/**
	 * Handle AlloyEditor changes and propagate them with an
	 * "editableChanged" event.
	 * @param {Object} event
	 * @private
	 * @review
	 */
	_handleEditorChange(event) {
		this.emit('editableChanged', {
			id: event.editor.element.$.dataset.lfrEditableId,
			value: event.editor.getData()
		});
	}

	/**
	 * Callback executed when the fragment remove button is clicked.
	 * It emits a 'fragmentRemoveButtonClick' event with the fragment index.
	 * @private
	 * @review
	 */
	_handleFragmentRemoveButtonClick() {
		this.emit('fragmentRemoveButtonClick', {
			fragmentIndex: this.index,
		});
	}
}

/**
 * State definition.
 * @type {!Object}
 * @review
 * @static
 */
LayoutPageTemplateFragment.STATE = {
	/**
	 * Fragment entry ID
	 * @default undefined
	 * @instance
	 * @memberOf LayoutPageTemplateEditor
	 * @review
	 * @type {!string}
	 */
	fragmentEntryId: Config.string().required(),

	/**
	 * Fragment index
	 * @default undefined
	 * @instance
	 * @memberOf LayoutPageTemplateFragment
	 * @review
	 * @type {!number}
	 */
	index: Config.number().required(),

	/**
	 * Fragment name
	 * @default undefined
	 * @instance
	 * @memberOf LayoutPageTemplateFragment
	 * @review
	 * @type {!string}
	 */
	name: Config.string().required(),

	/**
	 * Portlet namespace needed for prefixing form inputs
	 * @default undefined
	 * @instance
	 * @memberOf LayoutPageTemplateEditor
	 * @review
	 * @type {!string}
	 */
	portletNamespace: Config.string().required(),

	/**
	 * URL for getting a fragment render result.
	 * @default undefined
	 * @instance
	 * @memberOf LayoutPageTemplateEditor
	 * @review
	 * @type {!string}
	 */
	renderFragmentEntryURL: Config.string().required(),

	/**
	 * Fragment spritemap
	 * @default undefined
	 * @instance
	 * @memberOf LayoutPageTemplateFragment
	 * @review
	 * @type {!string}
	 */
	spritemap: Config.string().required(),

	/**
	 * Fragment content to be rendered
	 * @default function(){}
	 * @instance
	 * @memberOf LayoutPageTemplateFragment
	 * @private
	 * @review
	 * @type {function}
	 */
	_content: Config.func()
		.internal()
		.value(Soy.toIncDom('')),

	/**
	 * List of AlloyEditor instances used for inline edition
	 * @default []
	 * @instance
	 * @memberOf LayoutPageTemplateFragment
	 * @private
	 * @review
	 * @type {Array<AlloyEditor>}
	 */
	_editors: Config.arrayOf(Config.object())
		.internal()
		.value([]),

	/**
	 * Flag indicating that fragment information is being loaded
	 * @default false
	 * @instance
	 * @memberOf LayoutPageTemplateFragment
	 * @private
	 * @review
	 * @type {boolean}
	 */
	_loading: Config.bool().value(false),
};

Soy.register(LayoutPageTemplateFragment, templates);

export {LayoutPageTemplateFragment};
export default LayoutPageTemplateFragment;
