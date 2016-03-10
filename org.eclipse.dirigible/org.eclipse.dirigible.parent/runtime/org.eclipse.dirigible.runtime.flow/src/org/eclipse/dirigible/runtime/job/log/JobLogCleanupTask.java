/*******************************************************************************
 * Copyright (c) 2016 SAP and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * SAP - initial API and implementation
 *******************************************************************************/

package org.eclipse.dirigible.runtime.job.log;

import org.eclipse.dirigible.repository.logging.Logger;
import org.eclipse.dirigible.runtime.task.IRunnableTask;

public class JobLogCleanupTask implements IRunnableTask {

	private static final Logger logger = Logger.getLogger(JobLogCleanupTask.class);

	@Override
	public String getName() {
		return "Job Log Cleanup Task";
	}

	@Override
	public void start() {
		logger.debug("entering: " + this.getClass().getCanonicalName() + " -> " //$NON-NLS-1$ //$NON-NLS-2$
				+ "start()"); //$NON-NLS-1$
		try {
			JobLogRecordDAO.cleanupOlderRecords();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.debug("exiting: " + this.getClass().getCanonicalName() + " -> " //$NON-NLS-1$ //$NON-NLS-2$
				+ "start()"); //$NON-NLS-1$
	}

}
