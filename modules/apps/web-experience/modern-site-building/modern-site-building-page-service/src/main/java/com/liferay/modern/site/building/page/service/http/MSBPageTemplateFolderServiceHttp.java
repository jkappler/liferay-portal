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

import com.liferay.modern.site.building.page.service.MSBPageTemplateFolderServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link MSBPageTemplateFolderServiceUtil} service utility. The
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
 * @see MSBPageTemplateFolderServiceSoap
 * @see HttpPrincipal
 * @see MSBPageTemplateFolderServiceUtil
 * @generated
 */
@ProviderType
public class MSBPageTemplateFolderServiceHttp {
	public static void deleteMSBPageTemplates(HttpPrincipal httpPrincipal,
		long msbPageTemplateFolderId) {
		try {
			MethodKey methodKey = new MethodKey(MSBPageTemplateFolderServiceUtil.class,
					"deleteMSBPageTemplates",
					_deleteMSBPageTemplatesParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					msbPageTemplateFolderId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.modern.site.building.page.model.MSBPageTemplateFolder deleteMSBPageTemplateFolder(
		HttpPrincipal httpPrincipal, long msbPageTemplateFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(MSBPageTemplateFolderServiceUtil.class,
					"deleteMSBPageTemplateFolder",
					_deleteMSBPageTemplateFolderParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					msbPageTemplateFolderId);

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

			return (com.liferay.modern.site.building.page.model.MSBPageTemplateFolder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.modern.site.building.page.model.MSBPageTemplateFolder addMSBPageTemplateFolder(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String name,
		java.lang.String description,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(MSBPageTemplateFolderServiceUtil.class,
					"addMSBPageTemplateFolder",
					_addMSBPageTemplateFolderParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					name, description, serviceContext);

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

			return (com.liferay.modern.site.building.page.model.MSBPageTemplateFolder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.modern.site.building.page.model.MSBPageTemplateFolder getMSBPageTemplateFolder(
		HttpPrincipal httpPrincipal, long msbPageTemplateFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(MSBPageTemplateFolderServiceUtil.class,
					"getMSBPageTemplateFolder",
					_getMSBPageTemplateFolderParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					msbPageTemplateFolderId);

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

			return (com.liferay.modern.site.building.page.model.MSBPageTemplateFolder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.modern.site.building.page.model.MSBPageTemplateFolder fetchMSBPageTemplateFolder(
		HttpPrincipal httpPrincipal, long msbPageTemplateFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(MSBPageTemplateFolderServiceUtil.class,
					"fetchMSBPageTemplateFolder",
					_fetchMSBPageTemplateFolderParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					msbPageTemplateFolderId);

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

			return (com.liferay.modern.site.building.page.model.MSBPageTemplateFolder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.modern.site.building.page.model.MSBPageTemplateFolder> getMSBPageTemplateFolders(
		HttpPrincipal httpPrincipal, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		try {
			MethodKey methodKey = new MethodKey(MSBPageTemplateFolderServiceUtil.class,
					"getMSBPageTemplateFolders",
					_getMSBPageTemplateFoldersParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.modern.site.building.page.model.MSBPageTemplateFolder>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getMSBPageTemplateFoldersCount(
		HttpPrincipal httpPrincipal, long groupId) {
		try {
			MethodKey methodKey = new MethodKey(MSBPageTemplateFolderServiceUtil.class,
					"getMSBPageTemplateFoldersCount",
					_getMSBPageTemplateFoldersCountParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId);

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

	public static java.util.List<com.liferay.modern.site.building.page.model.MSBPageTemplateFolder> searchMSBPageTemplateFolders(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String keywords,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		try {
			MethodKey methodKey = new MethodKey(MSBPageTemplateFolderServiceUtil.class,
					"searchMSBPageTemplateFolders",
					_searchMSBPageTemplateFoldersParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					keywords, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.modern.site.building.page.model.MSBPageTemplateFolder>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int searchMSBPageTemplateFoldersCount(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String keywords) {
		try {
			MethodKey methodKey = new MethodKey(MSBPageTemplateFolderServiceUtil.class,
					"searchMSBPageTemplateFoldersCount",
					_searchMSBPageTemplateFoldersCountParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					keywords);

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

	public static com.liferay.modern.site.building.page.model.MSBPageTemplateFolder updateMSBPageTemplateFolder(
		HttpPrincipal httpPrincipal, long msbPageTemplateFolderId,
		java.lang.String name, java.lang.String description,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(MSBPageTemplateFolderServiceUtil.class,
					"updateMSBPageTemplateFolder",
					_updateMSBPageTemplateFolderParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					msbPageTemplateFolderId, name, description, serviceContext);

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

			return (com.liferay.modern.site.building.page.model.MSBPageTemplateFolder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(MSBPageTemplateFolderServiceHttp.class);
	private static final Class<?>[] _deleteMSBPageTemplatesParameterTypes0 = new Class[] {
			long.class
		};
	private static final Class<?>[] _deleteMSBPageTemplateFolderParameterTypes1 = new Class[] {
			long.class
		};
	private static final Class<?>[] _addMSBPageTemplateFolderParameterTypes2 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _getMSBPageTemplateFolderParameterTypes3 = new Class[] {
			long.class
		};
	private static final Class<?>[] _fetchMSBPageTemplateFolderParameterTypes4 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getMSBPageTemplateFoldersParameterTypes5 = new Class[] {
			long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getMSBPageTemplateFoldersCountParameterTypes6 =
		new Class[] { long.class };
	private static final Class<?>[] _searchMSBPageTemplateFoldersParameterTypes7 =
		new Class[] {
			long.class, java.lang.String.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _searchMSBPageTemplateFoldersCountParameterTypes8 =
		new Class[] { long.class, java.lang.String.class };
	private static final Class<?>[] _updateMSBPageTemplateFolderParameterTypes9 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
}