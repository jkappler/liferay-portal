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

package com.liferay.modern.site.building.page.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.modern.site.building.page.model.MSBPageTemplateFolder;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing MSBPageTemplateFolder in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see MSBPageTemplateFolder
 * @generated
 */
@ProviderType
public class MSBPageTemplateFolderCacheModel implements CacheModel<MSBPageTemplateFolder>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MSBPageTemplateFolderCacheModel)) {
			return false;
		}

		MSBPageTemplateFolderCacheModel msbPageTemplateFolderCacheModel = (MSBPageTemplateFolderCacheModel)obj;

		if (msbPageTemplateFolderId == msbPageTemplateFolderCacheModel.msbPageTemplateFolderId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, msbPageTemplateFolderId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", msbPageTemplateFolderId=");
		sb.append(msbPageTemplateFolderId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MSBPageTemplateFolder toEntityModel() {
		MSBPageTemplateFolderImpl msbPageTemplateFolderImpl = new MSBPageTemplateFolderImpl();

		if (uuid == null) {
			msbPageTemplateFolderImpl.setUuid(StringPool.BLANK);
		}
		else {
			msbPageTemplateFolderImpl.setUuid(uuid);
		}

		msbPageTemplateFolderImpl.setMsbPageTemplateFolderId(msbPageTemplateFolderId);
		msbPageTemplateFolderImpl.setGroupId(groupId);
		msbPageTemplateFolderImpl.setCompanyId(companyId);
		msbPageTemplateFolderImpl.setUserId(userId);

		if (userName == null) {
			msbPageTemplateFolderImpl.setUserName(StringPool.BLANK);
		}
		else {
			msbPageTemplateFolderImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			msbPageTemplateFolderImpl.setCreateDate(null);
		}
		else {
			msbPageTemplateFolderImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			msbPageTemplateFolderImpl.setModifiedDate(null);
		}
		else {
			msbPageTemplateFolderImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			msbPageTemplateFolderImpl.setName(StringPool.BLANK);
		}
		else {
			msbPageTemplateFolderImpl.setName(name);
		}

		if (description == null) {
			msbPageTemplateFolderImpl.setDescription(StringPool.BLANK);
		}
		else {
			msbPageTemplateFolderImpl.setDescription(description);
		}

		msbPageTemplateFolderImpl.resetOriginalValues();

		return msbPageTemplateFolderImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		msbPageTemplateFolderId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(msbPageTemplateFolderId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}
	}

	public String uuid;
	public long msbPageTemplateFolderId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
}