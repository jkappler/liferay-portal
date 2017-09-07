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

package com.liferay.modern.site.building.page.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.modern.site.building.page.service.MSBPageTemplateServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link MSBPageTemplateServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link HttpPrincipal} parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MSBPageTemplateServiceSoap
 * @see HttpPrincipal
 * @see MSBPageTemplateServiceUtil
 * @generated
 */
@ProviderType
public class MSBPageTemplateServiceHttp {
	public static com.liferay.modern.site.building.page.model.MSBPageTemplate deleteMSBPageTemplate(
		HttpPrincipal httpPrincipal, long msbPageTempalteId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(MSBPageTemplateServiceUtil.class,
					"deleteMSBPageTemplate",
					_deleteMSBPageTemplateParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					msbPageTempalteId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.modern.site.building.page.model.MSBPageTemplate)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.modern.site.building.page.model.MSBPageTemplate> getMSBPageTemplates(
		HttpPrincipal httpPrincipal, long msbPageTemplateFolderId, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		try {
			MethodKey methodKey = new MethodKey(MSBPageTemplateServiceUtil.class,
					"getMSBPageTemplates", _getMSBPageTemplatesParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					msbPageTemplateFolderId, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.modern.site.building.page.model.MSBPageTemplate>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getMSBPageTemplatesCount(HttpPrincipal httpPrincipal,
		long msbPageTemplateFolderId) {
		try {
			MethodKey methodKey = new MethodKey(MSBPageTemplateServiceUtil.class,
					"getMSBPageTemplatesCount",
					_getMSBPageTemplatesCountParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					msbPageTemplateFolderId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.modern.site.building.page.model.MSBPageTemplate> searchMSBPageTemplates(
		HttpPrincipal httpPrincipal, long msbPageTemplateFolderId,
		java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		try {
			MethodKey methodKey = new MethodKey(MSBPageTemplateServiceUtil.class,
					"searchMSBPageTemplates",
					_searchMSBPageTemplatesParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					msbPageTemplateFolderId, keywords, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.modern.site.building.page.model.MSBPageTemplate>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int searchMSBPageTemplatesCount(HttpPrincipal httpPrincipal,
		long msbPageTemplateFolderId, java.lang.String keywords) {
		try {
			MethodKey methodKey = new MethodKey(MSBPageTemplateServiceUtil.class,
					"searchMSBPageTemplatesCount",
					_searchMSBPageTemplatesCountParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					msbPageTemplateFolderId, keywords);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(MSBPageTemplateServiceHttp.class);
	private static final Class<?>[] _deleteMSBPageTemplateParameterTypes0 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getMSBPageTemplatesParameterTypes1 = new Class[] {
			long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getMSBPageTemplatesCountParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[] _searchMSBPageTemplatesParameterTypes3 = new Class[] {
			long.class, java.lang.String.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _searchMSBPageTemplatesCountParameterTypes4 = new Class[] {
			long.class, java.lang.String.class
		};
}