/******************************************************************************* 
 * Copyright (c) 2015 SAP and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0 
 * which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   SAP - initial API and implementation
 *******************************************************************************/

package org.eclipse.dirigible.runtime.content;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.eclipse.dirigible.repository.api.IRepositoryPaths;
import org.eclipse.dirigible.repository.ext.security.IRoles;
import org.eclipse.dirigible.repository.logging.Logger;
import org.eclipse.dirigible.runtime.PermissionsUtils;

/**
 * Imports the provided content into the Registry
 *
 */
public class ContentImporterServlet extends ContentBaseServlet {

	private static final long serialVersionUID = 5844468087553458293L;

	private static final Logger logger = Logger.getLogger(ContentImporterServlet.class);
	
	private static final String DEFAULT_PATH_FOR_IMPORT = IRepositoryPaths.REGISTRY_IMPORT_PATH;
	private static final String PARAMETER_OVERRIDE = "override";
	private static final String HEADER_OVERRIDE = "override";

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		if (!PermissionsUtils.isUserInRole(request, IRoles.ROLE_OPERATOR)) {
			String err = String.format(PermissionsUtils.PERMISSION_ERR, "Import");
			logger.error(err);
			throw new ServletException(err);
		}

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		boolean override = Boolean.parseBoolean(request.getParameter(PARAMETER_OVERRIDE)) || Boolean.parseBoolean(request.getHeader(HEADER_OVERRIDE));

		try {
			if (isMultipart) {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);

				try {
					List<FileItem> items = upload.parseRequest(request);
					logger.debug("Importing multiple content...");
					for (FileItem fileItem : items) {
						logger.debug("Importing " + fileItem.getFieldName());
						InputStream in = fileItem.getInputStream();
						importZipAndUpdate(in, request, override);
						logger.debug("Content imported.");
					}
				} catch (FileUploadException e) {
					logger.error(e.getMessage(), e);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
				}
			} else {
				logger.debug("Importing single content...");
				InputStream in = request.getInputStream();
				importZipAndUpdate(in, request, override);
				logger.debug("Content imported.");

			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	protected String getDefaultPathForImport() {
		return DEFAULT_PATH_FOR_IMPORT;
	}

	/**
	 * Import ZIP and execute DB updates. Don't override previous content.
	 * 
	 * @param content
	 * @param request
	 * @param override
	 * @throws Exception 
	 * @throws IOException 
	 */
	public void importZipAndUpdate(InputStream content, String pathForImport, HttpServletRequest request) throws IOException, Exception {
		importZipAndUpdate(content, pathForImport, request, false);
	}

	/**
	 * Import ZIP and execute DB updates. Override previous content depending on the override parameter.
	 * 
	 * @param content
	 * @param request
	 * @param override
	 * @throws Exception 
	 * @throws IOException 
	 */
	public void importZipAndUpdate(InputStream content, HttpServletRequest request, boolean override) throws IOException, Exception {
		importZipAndUpdate(content, getDefaultPathForImport(), request, override);
	}

	/**
	 * Import ZIP and execute DB updates. Override previous content depending on the override parameter. 
	 * 
	 * @param content
	 * @param pathForImport
	 * @param request
	 * @param override
	 * @throws Exception 
	 * @throws IOException 
	 */
	public void importZipAndUpdate(InputStream content, String pathForImport, HttpServletRequest request, boolean override) throws IOException, Exception {
		// 1. Import content.zip into repository
		getRepository(request).importZip(new ZipInputStream(content), pathForImport, override);
		postImport(request);
	}

	public void postImport(HttpServletRequest request) throws IOException,
			Exception {
		// 2. Post import actions
		ContentPostImportUpdater contentPostImportUpdater = new ContentPostImportUpdater(
				getRepository(request));
		contentPostImportUpdater.update(request);
	}


}
