/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.cms.client.dto.v1_0;

import com.liferay.headless.cms.client.function.UnsafeSupplier;
import com.liferay.headless.cms.client.serdes.v1_0.SimilarLinkSerDes;

import jakarta.annotation.Generated;

import java.io.Serializable;

import java.util.Map;
import java.util.Objects;

/**
 * @author Crescenzo Rega
 * @generated
 */
@Generated("")
public class SimilarLink implements Cloneable, Serializable {

	public static SimilarLink toDTO(String json) {
		return SimilarLinkSerDes.toDTO(json);
	}

	public Map<String, Map<String, String>> getActions() {
		return actions;
	}

	public void setActions(Map<String, Map<String, String>> actions) {
		this.actions = actions;
	}

	public void setActions(
		UnsafeSupplier<Map<String, Map<String, String>>, Exception>
			actionsUnsafeSupplier) {

		try {
			actions = actionsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Map<String, Map<String, String>> actions;

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public void setHref(UnsafeSupplier<String, Exception> hrefUnsafeSupplier) {
		try {
			href = hrefUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String href;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setId(UnsafeSupplier<Long, Exception> idUnsafeSupplier) {
		try {
			id = idUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long id;

	public Long getReferringAssetsCount() {
		return referringAssetsCount;
	}

	public void setReferringAssetsCount(Long referringAssetsCount) {
		this.referringAssetsCount = referringAssetsCount;
	}

	public void setReferringAssetsCount(
		UnsafeSupplier<Long, Exception> referringAssetsCountUnsafeSupplier) {

		try {
			referringAssetsCount = referringAssetsCountUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long referringAssetsCount;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setTitle(
		UnsafeSupplier<String, Exception> titleUnsafeSupplier) {

		try {
			title = titleUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String title;

	@Override
	public SimilarLink clone() throws CloneNotSupportedException {
		return (SimilarLink)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SimilarLink)) {
			return false;
		}

		SimilarLink similarLink = (SimilarLink)object;

		return Objects.equals(toString(), similarLink.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return SimilarLinkSerDes.toJSON(this);
	}

}
// LIFERAY-REST-BUILDER-HASH:260702825