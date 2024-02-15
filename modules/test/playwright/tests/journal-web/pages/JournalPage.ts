/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Locator, Page} from '@playwright/test';

import {clickAndExpectToBeVisible} from '../../../utils/clickAndExpectToBeVisible';
import {ProductMenuPage} from '../../../pages/product-navigation-product-menu/ProductMenu.page';

export class JournalPage {
	readonly page: Page;

	readonly newButton: Locator;
	readonly productMenuPage: ProductMenuPage;
	readonly templatesLink: Locator;

	constructor(page: Page) {
		this.page = page;

		this.newButton = page.getByText('New', {exact: true});
		this.productMenuPage = new ProductMenuPage(page);
		this.templatesLink = page.getByRole('link', {name: 'Templates'});
	}

	async goto() {
		await this.productMenuPage.goToJournalMenuItem();
	}

	async goToCreateNewTemplate() {
		await this.goToTemplates();
		await this.newButton.click();
	}

	async goToCreateNewBasicWebContent() {
		await this.goto();
		await clickAndExpectToBeVisible({
			autoClick: true,
			target: this.page.getByRole('menuitem', { name: 'Basic Web Content' }),
			trigger: this.newButton,
		});
	}

	async goToTemplates() {
		await this.goto();
		await this.templatesLink.click();
	}
}
