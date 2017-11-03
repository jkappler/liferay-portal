import 'metal-modal';
import Component from 'metal-component';
import Soy from 'metal-soy';
import Uri from 'metal-uri';
import {Config} from 'metal-state';

import templates from './PageTemplateNameEditor.soy';

/**
 * Component that generates a form for setting the PageTemplate name.
 */
class PageTemplateNameEditor extends Component {
	/**
	 * Callback executed when the modal is hidden.
	 * @protected
	 */
	_handleModalHidden() {
		this.emit('hide');
	}

	/**
	 * Callback executed when the generated form is submited.
	 * If the user has written a PageTemplate name, it closes the dialog,
	 * otherwise it does nothing.
	 * @param {Event} event Submit event that is prevented.
	 * @protected
	 */
	_handleSubmitForm(event) {
		event.preventDefault();

		const form = document.getElementById(`${this.namespace}pageTemplateNameEditorForm`);
		const formData = new FormData(form);

		fetch(this.actionURL, {
			body: formData,
			credentials: 'include',
			method: 'POST',
		})
			.then(response => response.json())
			.then((jsonResponse) => {
				if (jsonResponse.redirectURL) {
					this._redirect(jsonResponse.redirectURL);
				}
				else if(jsonResponse.error) {
					this.error = jsonResponse.error;
				}
			});
	}

	/**
	 * Method to redirect via JS or SPA depending if SPA exists or not.
	 * @param {!string} uri URI to be redirected to.
	 * @protected
	 */
	_redirect(uri) {
		if (Liferay.SPA) {
			this.dispose();

			Liferay.SPA.app.navigate(uri);
		}
		else {
			location.href = uri;
		}
	}
}

/**
 * State definition.
 * @type {!Object}
 * @static
 */
PageTemplateNameEditor.STATE = {
	/**
	 * Error message returned by the server.
	 * It is actually the exception description, but safely captured
	 * and processed.
	 * @default undefined
	 * @instance
	 * @memberof PageTemplateNameEditor
	 * @type {?string}
	 */
	_error: Config.string().internal(),

	/**
	 * URL used for creating/updating the pageTemplate. The generated form
	 * will be submited to this url.
	 * @default undefined
	 * @instance
	 * @memberof PageTemplateNameEditor
	 * @type {!string}
	 */
	actionURL: Config.string().required(),

	/**
	 * String to be shown as the editor modal title.
	 * @default undefined
	 * @instance
	 * @memberof PageTemplateNameEditor
	 * @type {!string}
	 */
	editorTitle: Config.string().required(),

	/**
	 * ID of an existing pageTemplate. When specified, this ID will be submitted
	 * inside the form to modify an existing pageTemplate.
	 * @default undefined
	 * @instance
	 * @memberof PageTemplateNameEditor
	 * @type {?string}
	 */
	pageTemplateEntryId: Config.string(),

	/**
	 * Initial name of the pageTemplate. When specified, this value will initially
	 * fill the rendered form.
	 * @default undefined
	 * @instance
	 * @memberof PageTemplateNameEditor
	 * @type {?string}
	 */
	pageTemplateEntryName: Config.string(),

	/**
	 * Portlet namespace needed for prefixing form inputs
	 * @default undefined
	 * @instance
	 * @memberof PageTemplateNameEditor
	 * @type {!string}
	 */
	namespace: Config.string().required(),

	/**
	 * Path of the available icons.
	 * @default undefined
	 * @instance
	 * @memberof PageTemplateEditor
	 * @type {!string}
	 */
	spritemap: Config.string().required(),
};

Soy.register(PageTemplateNameEditor, templates);

export { PageTemplateNameEditor };
export default PageTemplateNameEditor;