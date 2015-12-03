/*******************************************************************************
 * Copyright (c) 2015 SAP and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * SAP - initial API and implementation
 *******************************************************************************/

package org.eclipse.dirigible.repository.api;

/**
 * The standard Repository structure
 */
public interface IRepositoryPaths {

	/** The default separator char */
	public static final String SEPARATOR = ICommonConstants.SEPARATOR;

	/** The base folder of the Repository structure */
	public static final String DB_DIRIGIBLE_BASE = "/db/"; //$NON-NLS-1$

	/** The root folder of the Repository structure */
	public static final String DB_DIRIGIBLE_ROOT = DB_DIRIGIBLE_BASE + "dirigible/"; //$NON-NLS-1$

	/** The registry root folder of the Repository structure */
	public static final String DB_DIRIGIBLE_REGISTRY = DB_DIRIGIBLE_ROOT + "registry/"; //$NON-NLS-1$

	/** The registry's public root folder of the Repository structure */
	public static final String DB_DIRIGIBLE_REGISTRY_PUBLIC = DB_DIRIGIBLE_REGISTRY + "public/"; //$NON-NLS-1$

	/** The registry's configuration root folder of the Repository structure */
	public static final String DB_DIRIGIBLE_REGISTRY_CONF = DB_DIRIGIBLE_REGISTRY + "conf/"; //$NON-NLS-1$

	/** The sandbox root folder of the Repository structure */
	public static final String DB_DIRIGIBLE_SANDBOX = DB_DIRIGIBLE_ROOT + "sandbox/"; //$NON-NLS-1$

	/** The users root folder of the Repository structure */
	public static final String DB_DIRIGIBLE_USERS = DB_DIRIGIBLE_ROOT + "users/"; //$NON-NLS-1$

	/** The templates root folder of the Repository structure */
	public static final String DB_DIRIGIBLE_TEMPLATES = DB_DIRIGIBLE_ROOT + "templates/"; //$NON-NLS-1$

	/** The project templates root folder of the Repository structure */
	public static final String DB_DIRIGIBLE_TEMPLATES_PROJECTS = DB_DIRIGIBLE_TEMPLATES + "projects/"; //$NON-NLS-1$

	/** The configurations folder name */
	public static final String CONF_FOLDER_NAME = "conf"; //$NON-NLS-1$

	/** The workspace folder name */
	public static final String WORKSPACE_FOLDER_NAME = "workspace"; //$NON-NLS-1$

	/** The configurations folder path */
	public static final String CONF_REGISTRY = DB_DIRIGIBLE_REGISTRY + CONF_FOLDER_NAME;

	/** The sandbox folder name */
	public static final String SANDBOX = "sandbox";

	/** The registry folder name */
	public static final String REGISTRY = "registry";

	/** The public registry path folder name */
	public static final String REGISTRY_DEPLOY_PATH = DB_DIRIGIBLE_ROOT + "registry/public"; //$NON-NLS-1$

	/** The default import folder path */
	public static final String REGISTRY_IMPORT_PATH = REGISTRY_DEPLOY_PATH;

	/** The sandbox import folder path */
	public static final String SANDBOX_DEPLOY_PATH = DB_DIRIGIBLE_ROOT + SANDBOX;

}
