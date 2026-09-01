/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {
	VersionItem,
	versionHasLanguage,
} from '../../../../src/main/resources/META-INF/resources/js/main_view/compare_versions/CompareVersionsModalContent';

function createVersionItem(item: object): VersionItem {
	return item as VersionItem;
}

describe('versionHasLanguage', () => {
	it('always has the version default language', () => {
		const item = createVersionItem({defaultLanguageId: 'en_US'});

		expect(versionHasLanguage(item, 'en_US')).toBe(true);
	});

	it('has a language some localized field value was saved in', () => {
		const item = createVersionItem({
			defaultLanguageId: 'en_US',
			title_i18n: {en_US: 'Title', es_ES: 'Título'},
		});

		expect(versionHasLanguage(item, 'es_ES')).toBe(true);
	});

	it('does not have a language no field was translated to', () => {
		const item = createVersionItem({
			defaultLanguageId: 'en_US',
			text_i18n: {en_US: 'Text'},
			title_i18n: {en_US: 'Title'},
		});

		expect(versionHasLanguage(item, 'es_ES')).toBe(false);
	});

	it('ignores the friendly URL localizations the system fills in for every language', () => {
		const item = createVersionItem({
			defaultLanguageId: 'en_US',
			friendlyUrlPath_i18n: {en_US: 'url', es_ES: 'url'},
			title_i18n: {en_US: 'Title'},
		});

		expect(versionHasLanguage(item, 'es_ES')).toBe(false);
	});

	it('does not have a language when the version holds no localized values at all', () => {
		const item = createVersionItem({defaultLanguageId: 'en_US'});

		expect(versionHasLanguage(item, 'es_ES')).toBe(false);
	});
});
