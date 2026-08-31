/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {useEffect, useState} from 'react';

import ApiHelper from '../../common/services/ApiHelper';

export type DiffType = 'additions' | 'removals';

export type Diffs = Record<string, string>;

type VersionDiffs = {source: Diffs; target: Diffs};

const DIFF_HTML_URL = '/o/cms/diff_html';

export function useVersionDiffs({
	languageId,
	leftVersion,
	objectEntryId,
	rightVersion,
}: {
	languageId: string;
	leftVersion: number | null;
	objectEntryId: number;
	rightVersion: number | null;
}) {
	const [diffs, setDiffs] = useState<VersionDiffs | null>(null);

	useEffect(() => {
		if (leftVersion === null || rightVersion === null) {
			setDiffs(null);

			return;
		}

		let stale = false;

		const getDiffs = async () => {
			const {data, error} = await ApiHelper.post<{
				diffs: VersionDiffs;
			}>(DIFF_HTML_URL, {
				languageId,
				objectEntryId,
				sourceVersion: leftVersion,
				targetVersion: rightVersion,
			});

			if (stale) {
				return;
			}

			setDiffs(error === null ? data.diffs : null);
		};

		getDiffs();

		return () => {
			stale = true;
		};
	}, [languageId, leftVersion, objectEntryId, rightVersion]);

	return diffs;
}

export function injectContentDiffs(
	iframe: HTMLIFrameElement,
	diffs: Diffs | null,
	diffType: DiffType
) {
	const document = iframe.contentDocument;

	if (!document) {
		return;
	}

	removePreviousContentDiffs(document);

	if (!diffs) {
		return;
	}

	document.body.classList.add(`cms-compare-versions-${diffType}`);

	applyFieldDiffs(diffs, diffType, document);

	hideEmptyElements(document);
}

function applyFieldDiffs(diffs: Diffs, diffType: DiffType, document: Document) {
	const borderColorCssClass =
		diffType === 'additions' ? 'border-success' : 'border-danger';

	Object.entries(diffs).forEach(([fieldName, diffHtml]) => {
		const field =
			document.querySelector(
				`[data-field-name="ObjectField_${fieldName}"]`
			) ??
			document.querySelector(
				`[data-field-name="ObjectEntry_${fieldName}"]`
			);

		if (!field) {
			return;
		}

		const container = document.createElement('div');

		container.className = 'form-control cms-compare-versions-diff';
		container.innerHTML = diffHtml;

		const formGroup = field.querySelector('.form-group') ?? field;

		formGroup.appendChild(container);

		container
			.querySelectorAll('.cms-compare-versions-attachment')
			.forEach((image) => {
				image.classList.add(borderColorCssClass);

				formGroup.insertBefore(
					image.closest('[class*="diff-html"]') ?? image,
					container
				);
			});
	});
}

function hideEmptyElements(document: Document) {
	document
		.querySelectorAll<HTMLElement>(
			'.cms-compare-versions-diff :is(blockquote, figure, li, ol, table, ul)'
		)
		.forEach((element) => {
			if (
				!element.innerText.trim() &&
				!element.querySelector(
					'audio, embed, iframe, img, oembed, picture, svg, video'
				)
			) {
				element.style.display = 'none';
			}
		});
}

function removePreviousContentDiffs(document: Document) {
	document
		.querySelectorAll('.cms-compare-versions-diff')
		.forEach((element) => element.remove());
	document
		.querySelectorAll('.cms-compare-versions-attachment')
		.forEach((element) =>
			(element.closest('[class*="diff-html"]') ?? element).remove()
		);

	document.body.classList.remove(
		'cms-compare-versions-additions',
		'cms-compare-versions-removals'
	);
}
