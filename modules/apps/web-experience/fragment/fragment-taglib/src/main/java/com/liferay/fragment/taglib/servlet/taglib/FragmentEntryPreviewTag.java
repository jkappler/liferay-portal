/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.fragment.taglib.servlet.taglib;

import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.fragment.model.FragmentEntry;
import com.liferay.fragment.service.FragmentEntryServiceUtil;
import com.liferay.fragment.taglib.servlet.ServletContextUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.TempFileEntryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.util.IncludeTag;

import java.awt.image.BufferedImage;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.xhtmlrenderer.swing.Java2DRenderer;
import org.xhtmlrenderer.util.FSImageWriter;

/**
 * @author Pavel Savinov
 */
public class FragmentEntryPreviewTag extends IncludeTag {

	@Override
	public int doStartTag() throws JspException {
		try {
			_fragmentEntry = FragmentEntryServiceUtil.fetchFragmentEntry(
				_fragmentEntryId);

			StringBundler sb = new StringBundler(8);

			sb.append("<html><head>");
			sb.append("<style>");
			sb.append(_fragmentEntry.getCss());
			sb.append("</style><script>");
			sb.append(_fragmentEntry.getJs());
			sb.append("</script></head><body>");
			sb.append(_fragmentEntry.getHtml());
			sb.append("</body></html>");

			File fragmentFile = FileUtil.createTempFile();

			FileUtil.write(fragmentFile, sb.toString());

			_thumbnailURL = _getThumbnailURL(fragmentFile);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to get fragment entry preview", e);
			}

			return SKIP_BODY;
		}

		return super.doStartTag();
	}

	public void setFragmentEntryId(long fragmentEntryId) {
		_fragmentEntryId = fragmentEntryId;
	}

	public void setHeight(int height) {
		_height = height;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setWidth(int width) {
		_width = width;
	}

	@Override
	protected void cleanUp() {
		_fragmentEntry = null;
		_fragmentEntryId = 0;
		_height = 0;
		_width = 0;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-fragment:fragment-entry-preview:fragmentEntryName",
			_fragmentEntry.getName());
		request.setAttribute(
			"liferay-fragment:fragment-entry-preview:height", _height);
		request.setAttribute(
			"liferay-fragment:fragment-entry-preview:thumbnailURL",
			_thumbnailURL);
		request.setAttribute(
			"liferay-fragment:fragment-entry-preview:width", _width);
	}

	private String _getThumbnailURL(File fragmentFile) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		Java2DRenderer renderer = new Java2DRenderer(fragmentFile, 1024);

		renderer.setBufferedImageType(BufferedImage.TYPE_INT_RGB);

		BufferedImage image = renderer.getImage();

		FSImageWriter imageWriter = new FSImageWriter();

		File outputFile = FileUtil.createTempFile("png");

		imageWriter.write(image, outputFile.getAbsolutePath());

		FileEntry fileEntry = TempFileEntryUtil.addTempFileEntry(
			themeDisplay.getScopeGroupId(), themeDisplay.getUserId(),
			_TEMP_FOLDER_NAME, outputFile.getName(), outputFile,
			ContentTypes.IMAGE_PNG);

		return DLUtil.getImagePreviewURL(fileEntry, themeDisplay);
	}

	private static final String _PAGE = "/fragment_entry_preview/page.jsp";

	private static final String _TEMP_FOLDER_NAME =
		FragmentEntryPreviewTag.class.getName();

	private static final Log _log = LogFactoryUtil.getLog(
		FragmentEntryPreviewTag.class);

	private FragmentEntry _fragmentEntry;
	private long _fragmentEntryId;
	private int _height;
	private String _thumbnailURL;
	private int _width;

}