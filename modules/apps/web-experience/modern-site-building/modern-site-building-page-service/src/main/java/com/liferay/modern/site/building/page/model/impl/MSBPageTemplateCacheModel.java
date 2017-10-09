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

import com.liferay.modern.site.building.page.model.MSBPageTemplate;

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
 * The cache model class for representing MSBPageTemplate in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see MSBPageTemplate
 * @generated
 */
@ProviderType
public class MSBPageTemplateCacheModel implements CacheModel<MSBPageTemplate>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MSBPageTemplateCacheModel)) {
			return false;
		}

		MSBPageTemplateCacheModel msbPageTemplateCacheModel = (MSBPageTemplateCacheModel)obj;

		if (msbPageTemplateId == msbPageTemplateCacheModel.msbPageTemplateId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, msbPageTemplateId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", msbPageTemplateId=");
		sb.append(msbPageTemplateId);
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
		sb.append(", msbPageTemplateFolderId=");
		sb.append(msbPageTemplateFolderId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MSBPageTemplate toEntityModel() {
		MSBPageTemplateImpl msbPageTemplateImpl = new MSBPageTemplateImpl();

		if (uuid == null) {
			msbPageTemplateImpl.setUuid(StringPool.BLANK);
		}
		else {
			msbPageTemplateImpl.setUuid(uuid);
		}

		msbPageTemplateImpl.setMsbPageTemplateId(msbPageTemplateId);
		msbPageTemplateImpl.setGroupId(groupId);
		msbPageTemplateImpl.setCompanyId(companyId);
		msbPageTemplateImpl.setUserId(userId);

		if (userName == null) {
			msbPageTemplateImpl.setUserName(StringPool.BLANK);
		}
		else {
			msbPageTemplateImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			msbPageTemplateImpl.setCreateDate(null);
		}
		else {
			msbPageTemplateImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			msbPageTemplateImpl.setModifiedDate(null);
		}
		else {
			msbPageTemplateImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			msbPageTemplateImpl.setName(StringPool.BLANK);
		}
		else {
			msbPageTemplateImpl.setName(name);
		}

		msbPageTemplateImpl.setMsbPageTemplateFolderId(msbPageTemplateFolderId);

		msbPageTemplateImpl.resetOriginalValues();

		return msbPageTemplateImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		msbPageTemplateId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();

		msbPageTemplateFolderId = objectInput.readLong();
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

		objectOutput.writeLong(msbPageTemplateId);

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

		objectOutput.writeLong(msbPageTemplateFolderId);
	}

	public String uuid;
	public long msbPageTemplateId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public long msbPageTemplateFolderId;
}