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

package com.liferay.modern.site.building.page.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link MSBPageTemplateFolder}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MSBPageTemplateFolder
 * @generated
 */
@ProviderType
public class MSBPageTemplateFolderWrapper implements MSBPageTemplateFolder,
	ModelWrapper<MSBPageTemplateFolder> {
	public MSBPageTemplateFolderWrapper(
		MSBPageTemplateFolder msbPageTemplateFolder) {
		_msbPageTemplateFolder = msbPageTemplateFolder;
	}

	@Override
	public Class<?> getModelClass() {
		return MSBPageTemplateFolder.class;
	}

	@Override
	public String getModelClassName() {
		return MSBPageTemplateFolder.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("msbPageTemplateFolderId", getMsbPageTemplateFolderId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long msbPageTemplateFolderId = (Long)attributes.get(
				"msbPageTemplateFolderId");

		if (msbPageTemplateFolderId != null) {
			setMsbPageTemplateFolderId(msbPageTemplateFolderId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new MSBPageTemplateFolderWrapper((MSBPageTemplateFolder)_msbPageTemplateFolder.clone());
	}

	@Override
	public int compareTo(MSBPageTemplateFolder msbPageTemplateFolder) {
		return _msbPageTemplateFolder.compareTo(msbPageTemplateFolder);
	}

	/**
	* Returns the company ID of this msb page template folder.
	*
	* @return the company ID of this msb page template folder
	*/
	@Override
	public long getCompanyId() {
		return _msbPageTemplateFolder.getCompanyId();
	}

	/**
	* Returns the create date of this msb page template folder.
	*
	* @return the create date of this msb page template folder
	*/
	@Override
	public Date getCreateDate() {
		return _msbPageTemplateFolder.getCreateDate();
	}

	/**
	* Returns the description of this msb page template folder.
	*
	* @return the description of this msb page template folder
	*/
	@Override
	public java.lang.String getDescription() {
		return _msbPageTemplateFolder.getDescription();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _msbPageTemplateFolder.getExpandoBridge();
	}

	/**
	* Returns the group ID of this msb page template folder.
	*
	* @return the group ID of this msb page template folder
	*/
	@Override
	public long getGroupId() {
		return _msbPageTemplateFolder.getGroupId();
	}

	/**
	* Returns the modified date of this msb page template folder.
	*
	* @return the modified date of this msb page template folder
	*/
	@Override
	public Date getModifiedDate() {
		return _msbPageTemplateFolder.getModifiedDate();
	}

	/**
	* Returns the msb page template folder ID of this msb page template folder.
	*
	* @return the msb page template folder ID of this msb page template folder
	*/
	@Override
	public long getMsbPageTemplateFolderId() {
		return _msbPageTemplateFolder.getMsbPageTemplateFolderId();
	}

	/**
	* Returns the name of this msb page template folder.
	*
	* @return the name of this msb page template folder
	*/
	@Override
	public java.lang.String getName() {
		return _msbPageTemplateFolder.getName();
	}

	/**
	* Returns the primary key of this msb page template folder.
	*
	* @return the primary key of this msb page template folder
	*/
	@Override
	public long getPrimaryKey() {
		return _msbPageTemplateFolder.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _msbPageTemplateFolder.getPrimaryKeyObj();
	}

	/**
	* Returns the user ID of this msb page template folder.
	*
	* @return the user ID of this msb page template folder
	*/
	@Override
	public long getUserId() {
		return _msbPageTemplateFolder.getUserId();
	}

	/**
	* Returns the user name of this msb page template folder.
	*
	* @return the user name of this msb page template folder
	*/
	@Override
	public java.lang.String getUserName() {
		return _msbPageTemplateFolder.getUserName();
	}

	/**
	* Returns the user uuid of this msb page template folder.
	*
	* @return the user uuid of this msb page template folder
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _msbPageTemplateFolder.getUserUuid();
	}

	/**
	* Returns the uuid of this msb page template folder.
	*
	* @return the uuid of this msb page template folder
	*/
	@Override
	public java.lang.String getUuid() {
		return _msbPageTemplateFolder.getUuid();
	}

	@Override
	public int hashCode() {
		return _msbPageTemplateFolder.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _msbPageTemplateFolder.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _msbPageTemplateFolder.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _msbPageTemplateFolder.isNew();
	}

	@Override
	public void persist() {
		_msbPageTemplateFolder.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_msbPageTemplateFolder.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this msb page template folder.
	*
	* @param companyId the company ID of this msb page template folder
	*/
	@Override
	public void setCompanyId(long companyId) {
		_msbPageTemplateFolder.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this msb page template folder.
	*
	* @param createDate the create date of this msb page template folder
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_msbPageTemplateFolder.setCreateDate(createDate);
	}

	/**
	* Sets the description of this msb page template folder.
	*
	* @param description the description of this msb page template folder
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_msbPageTemplateFolder.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_msbPageTemplateFolder.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_msbPageTemplateFolder.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_msbPageTemplateFolder.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this msb page template folder.
	*
	* @param groupId the group ID of this msb page template folder
	*/
	@Override
	public void setGroupId(long groupId) {
		_msbPageTemplateFolder.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this msb page template folder.
	*
	* @param modifiedDate the modified date of this msb page template folder
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_msbPageTemplateFolder.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the msb page template folder ID of this msb page template folder.
	*
	* @param msbPageTemplateFolderId the msb page template folder ID of this msb page template folder
	*/
	@Override
	public void setMsbPageTemplateFolderId(long msbPageTemplateFolderId) {
		_msbPageTemplateFolder.setMsbPageTemplateFolderId(msbPageTemplateFolderId);
	}

	/**
	* Sets the name of this msb page template folder.
	*
	* @param name the name of this msb page template folder
	*/
	@Override
	public void setName(java.lang.String name) {
		_msbPageTemplateFolder.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_msbPageTemplateFolder.setNew(n);
	}

	/**
	* Sets the primary key of this msb page template folder.
	*
	* @param primaryKey the primary key of this msb page template folder
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_msbPageTemplateFolder.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_msbPageTemplateFolder.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this msb page template folder.
	*
	* @param userId the user ID of this msb page template folder
	*/
	@Override
	public void setUserId(long userId) {
		_msbPageTemplateFolder.setUserId(userId);
	}

	/**
	* Sets the user name of this msb page template folder.
	*
	* @param userName the user name of this msb page template folder
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_msbPageTemplateFolder.setUserName(userName);
	}

	/**
	* Sets the user uuid of this msb page template folder.
	*
	* @param userUuid the user uuid of this msb page template folder
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_msbPageTemplateFolder.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this msb page template folder.
	*
	* @param uuid the uuid of this msb page template folder
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_msbPageTemplateFolder.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<MSBPageTemplateFolder> toCacheModel() {
		return _msbPageTemplateFolder.toCacheModel();
	}

	@Override
	public MSBPageTemplateFolder toEscapedModel() {
		return new MSBPageTemplateFolderWrapper(_msbPageTemplateFolder.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _msbPageTemplateFolder.toString();
	}

	@Override
	public MSBPageTemplateFolder toUnescapedModel() {
		return new MSBPageTemplateFolderWrapper(_msbPageTemplateFolder.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _msbPageTemplateFolder.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MSBPageTemplateFolderWrapper)) {
			return false;
		}

		MSBPageTemplateFolderWrapper msbPageTemplateFolderWrapper = (MSBPageTemplateFolderWrapper)obj;

		if (Objects.equals(_msbPageTemplateFolder,
					msbPageTemplateFolderWrapper._msbPageTemplateFolder)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _msbPageTemplateFolder.getStagedModelType();
	}

	@Override
	public MSBPageTemplateFolder getWrappedModel() {
		return _msbPageTemplateFolder;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _msbPageTemplateFolder.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _msbPageTemplateFolder.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_msbPageTemplateFolder.resetOriginalValues();
	}

	private final MSBPageTemplateFolder _msbPageTemplateFolder;
}