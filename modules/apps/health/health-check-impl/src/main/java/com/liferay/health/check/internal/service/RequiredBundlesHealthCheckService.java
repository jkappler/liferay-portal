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

package com.liferay.health.check.internal.service;

import com.liferay.health.check.configuration.HealthCheckLivenessConfiguration;
import com.liferay.health.check.configuration.HealthCheckReadinessConfiguration;
import com.liferay.health.check.model.HealthCheckResponse;
import com.liferay.health.check.service.HealthCheckService;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Louis-Guillaume Durand
 */
@Component(
	configurationPid = {
		HealthCheckLivenessConfiguration.PID,
		HealthCheckReadinessConfiguration.PID
	},
	immediate = true, service = HealthCheckService.class
)
public class RequiredBundlesHealthCheckService implements HealthCheckService {

	@Override
	public HealthCheckResponse isLive() {
		String[] bundleSymbolicNames =
			_healthCheckLivenessConfiguration.bundleSymbolicNames();

		if (ArrayUtil.isEmpty(bundleSymbolicNames)) {
			return HealthCheckResponse.builder(
			).name(
				RequiredBundlesHealthCheckService.class.getName()
			).up(
			).build();
		}

		return _verifyRequiredBundles(bundleSymbolicNames);
	}

	@Override
	public HealthCheckResponse isReady() {
		String[] bundleSymbolicNames =
			_healthCheckReadinessConfiguration.bundleSymbolicNames();

		if (ArrayUtil.isEmpty(bundleSymbolicNames)) {
			return HealthCheckResponse.builder(
			).name(
				RequiredBundlesHealthCheckService.class.getName()
			).up(
			).build();
		}

		return _verifyRequiredBundles(bundleSymbolicNames);
	}

	@Activate
	@Modified
	protected void activate(
		BundleContext bundleContext, Map<String, String> properties) {

		_bundleContext = bundleContext;
		_healthCheckLivenessConfiguration = ConfigurableUtil.createConfigurable(
			HealthCheckLivenessConfiguration.class, properties);
		_healthCheckReadinessConfiguration =
			ConfigurableUtil.createConfigurable(
				HealthCheckReadinessConfiguration.class, properties);
	}

	/**
	 * Verify if there are any required bundle missing.
	 * Required bundles are declared in the configuration.
	 *
	 * @param requiredBundleSymbolicNames list of required bundle symbolic names
	 * @return HTTP response corresponding to the result
	 * @see HealthCheckResponse
	 * @see HealthCheckReadinessConfiguration
	 * @see HealthCheckLivenessConfiguration
	 */
	private HealthCheckResponse _verifyRequiredBundles(
		String[] requiredBundleSymbolicNames) {

		Set<String> requiredBundleSymbolicNamesSet = new HashSet<>();

		for (String requiredBundleSymbolicName : requiredBundleSymbolicNames) {
			if (Validator.isNotNull(requiredBundleSymbolicName)) {
				requiredBundleSymbolicNamesSet.add(requiredBundleSymbolicName);
			}
		}

		Set<Bundle> bundlesFound = new HashSet<>();

		for (Bundle bundle : _bundleContext.getBundles()) {
			if (requiredBundleSymbolicNamesSet.contains(
					bundle.getSymbolicName())) {

				bundlesFound.add(bundle);
			}
		}

		if (bundlesFound.size() != requiredBundleSymbolicNamesSet.size()) {
			Set<String> bundleFoundSymbolicNames = new HashSet<>();

			for (Bundle bundleFound : bundlesFound) {
				bundleFoundSymbolicNames.add(bundleFound.getSymbolicName());
			}

			List<String> bundlesNotFound = new ArrayList<>();

			for (String requiredBundleSymbolicName :
					requiredBundleSymbolicNames) {

				if (!bundleFoundSymbolicNames.contains(
						requiredBundleSymbolicName)) {

					bundlesNotFound.add(requiredBundleSymbolicName);
				}
			}

			Map<String, String> data = new HashMap<>();

			bundlesNotFound.forEach(bundle -> data.put(bundle, "Not Found"));

			return HealthCheckResponse.builder(
			).name(
				RequiredBundlesHealthCheckService.class.getName()
			).down(
			).withData(
				data
			).build();
		}

		return HealthCheckResponse.builder(
		).name(
			RequiredBundlesHealthCheckService.class.getName()
		).up(
		).build();
	}

	private volatile BundleContext _bundleContext;
	private volatile HealthCheckLivenessConfiguration
		_healthCheckLivenessConfiguration;
	private volatile HealthCheckReadinessConfiguration
		_healthCheckReadinessConfiguration;

}