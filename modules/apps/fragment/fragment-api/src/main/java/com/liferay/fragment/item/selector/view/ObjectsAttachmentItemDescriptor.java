package com.liferay.fragment.item.selector.view;

import com.liferay.item.selector.ItemSelectorViewDescriptor;
import com.liferay.mail.kernel.model.FileAttachment;

import java.util.Locale;

public class ObjectsAttachmentItemDescriptor implements
	ItemSelectorViewDescriptor.ItemDescriptor {

	public ObjectsAttachmentItemDescriptor(FileAttachment fileAttachment) {
		_fileAttachment = fileAttachment;
	}

	@Override
	public String getIcon() {
		return null;
	}

	@Override
	public String getImageURL() {
		return null;
	}

	@Override
	public String getPayload() {
		return null;
	}

	@Override
	public String getSubtitle(Locale locale) {
		return null;
	}

	@Override
	public String getTitle(Locale locale) {
		return _fileAttachment.getFileName();
	}

	private final FileAttachment _fileAttachment;
}
