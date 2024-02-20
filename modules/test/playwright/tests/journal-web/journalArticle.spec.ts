/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {expect, mergeTests} from '@playwright/test';

import {apiHelpersTest} from '../../fixtures/apiHelpersTest';
import {applicationsMenuPageTest} from '../../fixtures/applicationsMenuPageTest';
import {loginTest} from '../../fixtures/loginTest';
import {journalPagesTest} from './fixtures/journalPagesTest';
import {getRandomString} from "../../utils/util";
import {clickAndExpectToBeVisible} from "../../utils/clickAndExpectToBeVisible";

export const test = mergeTests(
	apiHelpersTest,
	applicationsMenuPageTest,
	journalPagesTest,
	loginTest
);

test('This is a test to create Web Content', async ({
	journalPage,
	page,
}) => {
	await journalPage.goto();

	await clickAndExpectToBeVisible({
		autoClick: true,
		target: page.getByRole('menuitem', {
			name: 'Basic Web Content',
		}),
		trigger: page.getByText('New', {exact: true})
	});

	await page.getByRole('tab', {name: 'Properties'}).waitFor();

	const article1Title = getRandomString();

	await page.getByPlaceholder('Untitled Basic Web Content').fill(article1Title);

	await page.getByRole('button', {name: 'Publish'}).click();

	const article1 = await page
		.locator(
			'#_com_liferay_journal_web_portlet_JournalPortlet_articlesSearchContainer .list-group-item'
		)
		.filter({hasText: article1Title});

	await expect(article1).toBeVisible();

	await clickAndExpectToBeVisible({
		autoClick: true,
		target: page.getByRole('menuitem', {
			name: 'Basic Web Content',
		}),
		trigger: page.getByText('New', {exact: true})
	});

	await page.getByRole('tab', {name: 'Properties'}).waitFor();

	const article2Title = getRandomString();

	await page.getByPlaceholder('Untitled Basic Web Content').fill(article2Title);

	await page.getByRole('button', {name: 'Publish'}).click();

	const article2 = await page
		.locator(
			'#_com_liferay_journal_web_portlet_JournalPortlet_articlesSearchContainer .list-group-item'
		)
		.filter({hasText: article2Title});

	await expect(article2).toBeVisible();

	await article1.getByTitle('Select').check();
	await article2.getByTitle('Select').check();

	await clickAndExpectToBeVisible({
		autoClick: true,
		target: page.getByRole('menuitem', {
			name: 'Permissions',
		}),
		trigger: page.getByTitle('Actions', {exact: true})
	});

	await page.frameLocator('iframe[title="Permissions"]').locator('#guest_ACTION_DELETE').check();
	await page.frameLocator('iframe[title="Permissions"]').locator('#guest_ACTION_PERMISSIONS').check();
	await page.frameLocator('iframe[title="Permissions"]').getByRole('button', { name: 'Save' }).click();
	await page.frameLocator('iframe[title="Permissions"]').getByRole('button', { name: 'Cancel' }).click();

	await clickAndExpectToBeVisible({
		autoClick: true,
		target: page.getByRole('menuitem', {
			name: 'Permissions',
		}),
		trigger: page.getByLabel(`Actions for ${article1Title}`, {exact: true})
	});

	await expect(page.frameLocator('iframe[title="Permissions"]').locator('#guest_ACTION_DELETE')).toBeChecked();
	await expect(page.frameLocator('iframe[title="Permissions"]').locator('#guest_ACTION_PERMISSIONS')).toBeChecked();

	await page.frameLocator('iframe[title="Permissions"]').getByRole('button', { name: 'Cancel' }).click();

	await clickAndExpectToBeVisible({
		autoClick: true,
		target: page.getByRole('menuitem', {
			name: 'Permissions',
		}),
		trigger: page.getByLabel(`Actions for ${article2Title}`, {exact: true})
	});

	await expect(page.frameLocator('iframe[title="Permissions"]').locator('#guest_ACTION_DELETE')).toBeChecked();
	await expect(page.frameLocator('iframe[title="Permissions"]').locator('#guest_ACTION_PERMISSIONS')).toBeChecked();

	await journalPage.goto();

	await clickAndExpectToBeVisible({
		autoClick: true,
		target: page.getByRole('menuitem', {
			name: 'Delete',
		}),
		trigger: page.getByLabel(`Actions for ${article1Title}`, {exact: true})
	});

	await clickAndExpectToBeVisible({
		autoClick: true,
		target: page.getByRole('menuitem', {
			name: 'Delete',
		}),
		trigger: page.getByLabel(`Actions for ${article2Title}`, {exact: true})
	});
});
