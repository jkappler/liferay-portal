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
 * This class is a wrapper for {@link MSBPageTemplate}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MSBPageTemplate
 * @generated
 */
@ProviderType
public class MSBPageTemplateWrapper implements MSBPageTemplate,
	ModelWrapper<MSBPageTemplate> {
	public MSBPageTemplateWrapper(MSBPageTemplate msbPageTemplate) {
		_msbPageTemplate = msbPageTemplate;
	}

	@Override
	public Class<?> getModelClass() {
		return MSBPageTemplate.class;
	}

	@Override
	public String getModelClassName() {
		return MSBPageTemplate.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("msbPageTemplateId", getMsbPageTemplateId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("msbPageTemplateFolderId", getMsbPageTemplateFolderId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long msbPageTemplateId = (Long)attributes.get("msbPageTemplateId");

		if (msbPageTemplateId != null) {
			setMsbPageTemplateId(msbPageTemplateId);
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

		Long msbPageTemplateFolderId = (Long)attributes.get(
				"msbPageTemplateFolderId");

		if (msbPageTemplateFolderId != null) {
			setMsbPageTemplateFolderId(msbPageTemplateFolderId);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new MSBPageTemplateWrapper((MSBPageTemplate)_msbPageTemplate.clone());
	}

	@Override
	public int compareTo(MSBPageTemplate msbPageTemplate) {
		return _msbPageTemplate.compareTo(msbPageTemplate);
	}

	/**
	* Returns the company ID of this msb page template.
	*
	* @return the company ID of this msb page template
	*/
	@Override
	public long getCompanyId() {
		return _msbPageTemplate.getCompanyId();
	}

	/**
	* Returns the create date of this msb page template.
	*
	* @return the create date of this msb page template
	*/
	@Override
	public Date getCreateDate() {
		return _msbPageTemplate.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _msbPageTemplate.getExpandoBridge();
	}

	/**
	* Returns the group ID of this msb page template.
	*
	* @return the group ID of this msb page template
	*/
	@Override
	public long getGroupId() {
		return _msbPageTemplate.getGroupId();
	}

	/**
	* Returns the modified date of this msb page template.
	*
	* @return the modified date of this msb page template
	*/
	@Override
	public Date getModifiedDate() {
		return _msbPageTemplate.getModifiedDate();
	}

	/**
	* Returns the msb page template folder ID of this msb page template.
	*
	* @return the msb page template folder ID of this msb page template
	*/
	@Override
	public long getMsbPageTemplateFolderId() {
		return _msbPageTemplate.getMsbPageTemplateFolderId();
	}

	/**
	* Returns the msb page template ID of this msb page template.
	*
	* @return the msb page template ID of this msb page template
	*/
	@Override
	public long getMsbPageTemplateId() {
		return _msbPageTemplate.getMsbPageTemplateId();
	}

	/**
	* Returns the name of this msb page template.
	*
	* @return the name of this msb page template
	*/
	@Override
	public java.lang.String getName() {
		return _msbPageTemplate.getName();
	}

	/**
	* Returns the primary key of this msb page template.
	*
	* @return the primary key of this msb page template
	*/
	@Override
	public long getPrimaryKey() {
		return _msbPageTemplate.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _msbPageTemplate.getPrimaryKeyObj();
	}

	/**
	* Returns the user ID of this msb page template.
	*
	* @return the user ID of this msb page template
	*/
	@Override
	public long getUserId() {
		return _msbPageTemplate.getUserId();
	}

	/**
	* Returns the user name of this msb page template.
	*
	* @return the user name of this msb page template
	*/
	@Override
	public java.lang.String getUserName() {
		return _msbPageTemplate.getUserName();
	}

	/**
	* Returns the user uuid of this msb page template.
	*
	* @return the user uuid of this msb page template
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _msbPageTemplate.getUserUuid();
	}

	/**
	* Returns the uuid of this msb page template.
	*
	* @return the uuid of this msb page template
	*/
	@Override
	public java.lang.String getUuid() {
		return _msbPageTemplate.getUuid();
	}

	@Override
	public int hashCode() {
		return _msbPageTemplate.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _msbPageTemplate.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _msbPageTemplate.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _msbPageTemplate.isNew();
	}

	@Override
	public void persist() {
		_msbPageTemplate.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_msbPageTemplate.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this msb page template.
	*
	* @param companyId the company ID of this msb page template
	*/
	@Override
	public void setCompanyId(long companyId) {
		_msbPageTemplate.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this msb page template.
	*
	* @param createDate the create date of this msb page template
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_msbPageTemplate.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_msbPageTemplate.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_msbPageTemplate.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_msbPageTemplate.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this msb page template.
	*
	* @param groupId the group ID of this msb page template
	*/
	@Override
	public void setGroupId(long groupId) {
		_msbPageTemplate.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this msb page template.
	*
	* @param modifiedDate the modified date of this msb page template
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_msbPageTemplate.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the msb page template folder ID of this msb page template.
	*
	* @param msbPageTemplateFolderId the msb page template folder ID of this msb page template
	*/
	@Override
	public void setMsbPageTemplateFolderId(long msbPageTemplateFolderId) {
		_msbPageTemplate.setMsbPageTemplateFolderId(msbPageTemplateFolderId);
	}

	/**
	* Sets the msb page template ID of this msb page template.
	*
	* @param msbPageTemplateId the msb page template ID of this msb page template
	*/
	@Override
	public void setMsbPageTemplateId(long msbPageTemplateId) {
		_msbPageTemplate.setMsbPageTemplateId(msbPageTemplateId);
	}

	/**
	* Sets the name of this msb page template.
	*
	* @param name the name of this msb page template
	*/
	@Override
	public void setName(java.lang.String name) {
		_msbPageTemplate.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_msbPageTemplate.setNew(n);
	}

	/**
	* Sets the primary key of this msb page template.
	*
	* @param primaryKey the primary key of this msb page template
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_msbPageTemplate.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_msbPageTemplate.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this msb page template.
	*
	* @param userId the user ID of this msb page template
	*/
	@Override
	public void setUserId(long userId) {
		_msbPageTemplate.setUserId(userId);
	}

	/**
	* Sets the user name of this msb page template.
	*
	* @param userName the user name of this msb page template
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_msbPageTemplate.setUserName(userName);
	}

	/**
	* Sets the user uuid of this msb page template.
	*
	* @param userUuid the user uuid of this msb page template
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_msbPageTemplate.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this msb page template.
	*
	* @param uuid the uuid of this msb page template
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_msbPageTemplate.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<MSBPageTemplate> toCacheModel() {
		return _msbPageTemplate.toCacheModel();
	}

	@Override
	public MSBPageTemplate toEscapedModel() {
		return new MSBPageTemplateWrapper(_msbPageTemplate.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _msbPageTemplate.toString();
	}

	@Override
	public MSBPageTemplate toUnescapedModel() {
		return new MSBPageTemplateWrapper(_msbPageTemplate.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _msbPageTemplate.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MSBPageTemplateWrapper)) {
			return false;
		}

		MSBPageTemplateWrapper msbPageTemplateWrapper = (MSBPageTemplateWrapper)obj;

		if (Objects.equals(_msbPageTemplate,
					msbPageTemplateWrapper._msbPageTemplate)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _msbPageTemplate.getStagedModelType();
	}

	@Override
	public MSBPageTemplate getWrappedModel() {
		return _msbPageTemplate;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _msbPageTemplate.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _msbPageTemplate.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_msbPageTemplate.resetOriginalValues();
	}

	private final MSBPageTemplate _msbPageTemplate;
}