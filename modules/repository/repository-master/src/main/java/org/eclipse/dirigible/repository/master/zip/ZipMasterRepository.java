/*
 * Copyright (c) 2017 SAP and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * SAP - initial API and implementation
 */

package org.eclipse.dirigible.repository.master.zip;

import org.eclipse.dirigible.repository.local.LocalRepositoryException;
import org.eclipse.dirigible.repository.master.IMasterRepository;

/**
 * The Zip Master Repository.
 */
public class ZipMasterRepository extends ZipRepository implements IMasterRepository {

	/** The Constant TYPE. */
	public static final String TYPE = "zip";

	/** The Constant DIRIGIBLE_MASTER_REPOSITORY_ZIP_LOCATION. */
	public static final String DIRIGIBLE_MASTER_REPOSITORY_ZIP_LOCATION = "DIRIGIBLE_MASTER_REPOSITORY_ZIP_LOCATION";

	/**
	 * Instantiates a new zip master repository.
	 *
	 * @param zip
	 *            the zip
	 * @throws LocalRepositoryException
	 *             the local repository exception
	 */
	public ZipMasterRepository(String zip) throws LocalRepositoryException {
		super(zip);
	}

}