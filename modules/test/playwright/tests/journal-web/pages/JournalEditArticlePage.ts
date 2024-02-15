/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Locator, Page} from '@playwright/test';

import {JournalPage} from './JournalPage';

export class JournalEditArticlePage {
	readonly page: Page;

	readonly journalPage: JournalPage;
	readonly publishButton: Locator;
	readonly titlePlaceholder: Locator;

	constructor(page: Page) {
		this.page = page;

		this.journalPage = new JournalPage(page);
		this.publishButton = page.getByRole('button', {name: 'Publish'});
		this.titlePlaceholder = page.getByPlaceholder('Untitled Basic Web Content');
	}

	async goto() {
		await this.journalPage.goToCreateNewBasicWebContent();
	}

	async publishNewWebContent(title: string) {
		await this.goto();

		await this.titlePlaceholder.fill(title);
		await this.publishButton.click();
	}
}
