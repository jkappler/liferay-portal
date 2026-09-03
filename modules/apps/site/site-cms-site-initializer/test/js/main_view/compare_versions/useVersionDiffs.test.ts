/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import '@testing-library/jest-dom';
import {renderHook, waitFor} from '@testing-library/react';
import {fetch} from 'frontend-js-web';

import {
	injectContentDiffs,
	useVersionDiffs,
} from '../../../../src/main/resources/META-INF/resources/js/main_view/compare_versions/useVersionDiffs';

const mockFetch = fetch as jest.Mock;

function createIframe(bodyHTML: string): HTMLIFrameElement {
	const iframe = document.createElement('iframe');

	document.body.appendChild(iframe);

	const iframeDocument = iframe.contentDocument!;

	iframeDocument.body.innerHTML = bodyHTML;

	defineInnerText(iframe.contentWindow as Window & typeof globalThis);

	return iframe;
}

// JSDOM does not implement innerText; approximate the piece the code relies
// on: hidden elements contribute no visible text.

function defineInnerText(iframeWindow: Window & typeof globalThis) {
	Object.defineProperty(iframeWindow.HTMLElement.prototype, 'innerText', {
		configurable: true,
		get(this: HTMLElement): string {
			if (this.style.display === 'none') {
				return '';
			}

			return Array.from(this.childNodes)
				.map((node) => {
					if (node.nodeType === Node.TEXT_NODE) {
						return node.textContent;
					}

					if (node instanceof iframeWindow.HTMLElement) {
						return node.innerText;
					}

					return '';
				})
				.join('');
		},
	});
}

function createFieldHTML(
	fieldName: string,
	controlHTML = '<input class="form-control" />'
) {
	return `
		<div data-field-name="${fieldName}">
			<div class="form-group">${controlHTML}</div>
		</div>
	`;
}

function mockDiffsResponse(diffs: object) {
	mockFetch.mockResolvedValueOnce({
		json: async () => ({diffs}),
		ok: true,
		status: 200,
	} as Response);
}

describe('injectContentDiffs', () => {
	afterEach(() => {
		document.body.innerHTML = '';
	});

	it('overlays the diff inside the changed field, styled like the control it replaces', () => {
		const iframe = createIframe(
			createFieldHTML(
				'ObjectField_title',
				'<input class="form-control form-control-inline" />'
			)
		);

		injectContentDiffs(iframe, {title: 'Old Title'}, 'removals');

		const box = iframe.contentDocument!.querySelector(
			'[data-field-name="ObjectField_title"] .form-group .cms-compare-versions-diff'
		);

		expect(box).toHaveTextContent('Old Title');
		expect(box).toHaveClass('form-control', 'form-control-inline');
	});

	it('falls back to the ObjectEntry wrapper and to a plain form-control box', () => {
		const iframe = createIframe(
			`<div data-field-name="ObjectEntry_objectEntryFriendlyURL">
				<div class="form-group"></div>
			</div>`
		);

		injectContentDiffs(
			iframe,
			{objectEntryFriendlyURL: 'old-url'},
			'removals'
		);

		const box = iframe.contentDocument!.querySelector(
			'.cms-compare-versions-diff'
		);

		expect(box).toHaveTextContent('old-url');
		expect(box).toHaveClass('form-control');
	});

	it('ignores diffs whose field is not on the page', () => {
		const iframe = createIframe(createFieldHTML('ObjectField_title'));

		injectContentDiffs(iframe, {unknownField: 'value'}, 'removals');

		expect(
			iframe.contentDocument!.querySelector('.cms-compare-versions-diff')
		).toBeNull();
	});

	it('marks the pane view on the body', () => {
		const iframe = createIframe(createFieldHTML('ObjectField_title'));

		injectContentDiffs(iframe, {title: 'Old Title'}, 'additions');

		expect(iframe.contentDocument!.body).toHaveClass(
			'cms-compare-versions-additions'
		);
	});

	it('does not duplicate overlays when injecting again', () => {
		const iframe = createIframe(createFieldHTML('ObjectField_title'));

		injectContentDiffs(iframe, {title: 'First'}, 'removals');
		injectContentDiffs(iframe, {title: 'Second'}, 'removals');

		const boxes = iframe.contentDocument!.querySelectorAll(
			'.cms-compare-versions-diff'
		);

		expect(boxes).toHaveLength(1);
		expect(boxes[0]).toHaveTextContent('Second');
	});

	it('removes every trace of the previous comparison when there is no diff', () => {
		const iframe = createIframe(createFieldHTML('ObjectField_upload'));

		injectContentDiffs(
			iframe,
			{
				upload: '<img class="cms-compare-versions-attachment" src="a.png" /> a.png',
			},
			'removals'
		);
		injectContentDiffs(iframe, null, 'removals');

		const iframeDocument = iframe.contentDocument!;

		expect(
			iframeDocument.querySelector('.cms-compare-versions-diff')
		).toBeNull();
		expect(
			iframeDocument.querySelector('.cms-compare-versions-attachment')
		).toBeNull();
		expect(iframeDocument.body).not.toHaveClass(
			'cms-compare-versions-removals'
		);
	});

	it('moves the attachment image above the diff box, framed with the pane color', () => {
		const iframe = createIframe(createFieldHTML('ObjectField_upload'));

		injectContentDiffs(
			iframe,
			{
				upload: '<span class="diff-html-added"><img class="cms-compare-versions-attachment" src="a.png" /></span><span class="diff-html-added">a.png</span>',
			},
			'additions'
		);

		const formGroup = iframe.contentDocument!.querySelector(
			'[data-field-name="ObjectField_upload"] .form-group'
		)!;

		const image = formGroup.querySelector(
			'.cms-compare-versions-attachment'
		)!;

		expect(image).toHaveClass('border-success');
		expect(image.closest('.cms-compare-versions-diff')).toBeNull();

		const box = formGroup.querySelector('.cms-compare-versions-diff')!;

		expect(
			image.compareDocumentPosition(box) &
				Node.DOCUMENT_POSITION_FOLLOWING
		).toBeTruthy();
	});

	it('moves only its own image out when the image shares the mark with the file name', () => {
		const iframe = createIframe(createFieldHTML('ObjectField_file'));

		injectContentDiffs(
			iframe,
			{
				file: '<span class="diff-html-removed" style="display: none"><img class="cms-compare-versions-attachment" src="old.png" /> old.png</span><span class="diff-html-added"><img class="cms-compare-versions-attachment" src="new.png" /> new.png</span>',
			},
			'additions'
		);

		const formGroup = iframe.contentDocument!.querySelector(
			'[data-field-name="ObjectField_file"] .form-group'
		)!;

		const addedImage = formGroup.querySelector('img[src="new.png"]')!;

		expect(addedImage).toHaveClass('border-success');
		expect(addedImage.closest('.cms-compare-versions-diff')).toBeNull();

		const removedImage = formGroup.querySelector('img[src="old.png"]')!;

		expect(removedImage.closest('.diff-html-removed')).not.toBeNull();
		expect(removedImage).not.toHaveClass('border-success');

		const box = formGroup.querySelector('.cms-compare-versions-diff')!;

		expect(box.querySelector('.diff-html-added')).toHaveTextContent(
			'new.png'
		);
	});

	it('hides the structural elements the hidden content leaves empty, unless they hold media', () => {
		const iframe = createIframe(createFieldHTML('ObjectField_content'));

		injectContentDiffs(
			iframe,
			{
				content:
					'<ul id="ghost"><li><span class="diff-html-removed" style="display: none">Gone</span></li></ul>' +
					'<ul id="kept"><li>Visible text</li></ul>' +
					'<figure id="media"><img src="a.png" /></figure>',
			},
			'additions'
		);

		const iframeDocument = iframe.contentDocument!;

		const getDisplay = (id: string) =>
			(iframeDocument.getElementById(id) as HTMLElement).style.display;

		expect(getDisplay('ghost')).toBe('none');
		expect(getDisplay('kept')).not.toBe('none');
		expect(getDisplay('media')).not.toBe('none');
	});
});

describe('useVersionDiffs', () => {
	const DEFAULT_INPUT = {
		languageId: 'en_US',
		leftVersion: 2,
		objectEntryId: 42,
		rightVersion: 1,
	};

	beforeEach(() => {
		jest.clearAllMocks();
	});

	it('does not request anything until both versions are selected', () => {
		const {result} = renderHook(() =>
			useVersionDiffs({...DEFAULT_INPUT, rightVersion: null})
		);

		expect(result.current).toBeNull();
		expect(mockFetch).not.toHaveBeenCalled();
	});

	it('requests the diff of both versions once and exposes both directions', async () => {
		const diffs = {source: {title: 'old'}, target: {title: 'new'}};

		mockDiffsResponse(diffs);

		const {result} = renderHook(() => useVersionDiffs(DEFAULT_INPUT));

		await waitFor(() => expect(result.current).toEqual(diffs));

		expect(mockFetch).toHaveBeenCalledTimes(1);

		const [url, options] = mockFetch.mock.calls[0];

		expect(url).toBe('/o/cms/diff_html');
		expect(JSON.parse(options.body)).toEqual({
			languageId: 'en_US',
			objectEntryId: 42,
			sourceVersion: 2,
			targetVersion: 1,
		});
	});

	it('clears the diffs when the request fails', async () => {
		mockDiffsResponse({source: {}, target: {}});

		const {rerender, result} = renderHook(
			(input) => useVersionDiffs(input),
			{initialProps: DEFAULT_INPUT}
		);

		await waitFor(() => expect(result.current).not.toBeNull());

		mockFetch.mockResolvedValueOnce({
			json: async () => ({title: 'Server error'}),
			ok: false,
			status: 500,
		} as Response);

		rerender({...DEFAULT_INPUT, rightVersion: 3});

		await waitFor(() => expect(result.current).toBeNull());
	});

	it('refetches when the comparison language changes', async () => {
		mockDiffsResponse({source: {}, target: {}});

		const {rerender} = renderHook((input) => useVersionDiffs(input), {
			initialProps: DEFAULT_INPUT,
		});

		await waitFor(() => expect(mockFetch).toHaveBeenCalledTimes(1));

		mockDiffsResponse({source: {}, target: {}});

		rerender({...DEFAULT_INPUT, languageId: 'es_ES'});

		await waitFor(() => expect(mockFetch).toHaveBeenCalledTimes(2));

		expect(JSON.parse(mockFetch.mock.calls[1][1].body).languageId).toBe(
			'es_ES'
		);
	});

	it('discards a stale response that resolves after the versions changed', async () => {
		let resolveFirst: (response: Response) => void;

		mockFetch.mockImplementationOnce(
			() =>
				new Promise<Response>((resolve) => {
					resolveFirst = resolve;
				})
		);

		const {rerender, result} = renderHook(
			(input) => useVersionDiffs(input),
			{initialProps: DEFAULT_INPUT}
		);

		const freshDiffs = {source: {}, target: {title: 'fresh'}};

		mockDiffsResponse(freshDiffs);

		rerender({...DEFAULT_INPUT, rightVersion: 3});

		await waitFor(() => expect(result.current).toEqual(freshDiffs));

		resolveFirst!({
			json: async () => ({
				diffs: {source: {}, target: {title: 'stale'}},
			}),
			ok: true,
			status: 200,
		} as Response);

		await waitFor(() => expect(result.current).toEqual(freshDiffs));
	});
});
