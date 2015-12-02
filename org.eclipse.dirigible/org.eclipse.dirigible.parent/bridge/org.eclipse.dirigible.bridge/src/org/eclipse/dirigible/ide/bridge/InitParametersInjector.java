/*******************************************************************************
 * Copyright (c) 2015 SAP and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * SAP - initial API and implementation
 *******************************************************************************/

package org.eclipse.dirigible.ide.bridge;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InitParametersInjector implements IInjector {

	public static final String RUNTIME_URL = "runtimeUrl"; //$NON-NLS-1$
	public static final String SERVICES_URL = "servicesUrl"; //$NON-NLS-1$
	public static final String ENABLE_ROLES = "enableRoles"; //$NON-NLS-1$
	public static final String LOG_IN_SYSTEM_OUTPUT = "logInSystemOutput"; //$NON-NLS-1$
	public static final String JNDI_DEFAULT_DATASOURCE = "jndiDefaultDataSource"; //$NON-NLS-1$
	public static final String JNDI_CONNECTIVITY_CONFIGURATION = "jndiConnectivityService"; //$NON-NLS-1$
	public static final String JNDI_MAIL_SESSION = "jndiMailService"; //$NON-NLS-1$
	public static final String SET_AUTO_COMMIT = "jdbcAutoCommit"; //$NON-NLS-1$
	public static final String REPOSITORY_PROVIDER = "repositoryProvider"; //$NON-NLS-1$
	public static final String DEFAULT_DATASOURCE_TYPE = "defaultDataSourceType"; //$NON-NLS-1$
	public static final String DEFAULT_DATASOURCE_TYPE_JNDI = "jndi"; //$NON-NLS-1$
	public static final String DEFAULT_DATASOURCE_TYPE_LOCAL = "local"; //$NON-NLS-1$

	@Override
	public void injectOnRequest(ServletConfig servletConfig, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Enumeration<String> parameterNames = servletConfig.getInitParameterNames();
		while (parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			String parameterValue = servletConfig.getInitParameter(parameterName);
			req.getSession().setAttribute(parameterName, parameterValue);
		}

	}

	@Override
	public void injectOnStart(ServletConfig servletConfig) throws ServletException, IOException {

		Enumeration<String> parameterNames = servletConfig.getInitParameterNames();
		while (parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			String parameterValue = servletConfig.getInitParameter(parameterName);
			System.getProperties().put(parameterName, parameterValue);
		}

	}

	public static String get(String key) {
		return System.getProperty(key);
	}

}
