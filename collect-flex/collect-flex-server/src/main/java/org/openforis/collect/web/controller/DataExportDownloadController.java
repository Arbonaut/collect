package org.openforis.collect.web.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openforis.collect.web.session.SessionState;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author S. Ricci
 * 
 */
@Controller
public class DataExportDownloadController {

	private static final String EXPORT_PATH = "export";

	private static Log LOG = LogFactory.getLog(DataExportDownloadController.class);
	
	@RequestMapping(value = "/downloadDataExport.htm", method = RequestMethod.GET)
	public @ResponseBody String downloadDataExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String path = buildDataExportFilePath(request);
			File file = new File(path);
			if ( file.exists() ) {
				write(response, file);
			} else {
				throw new IOException("Data export file not found");
			}
		} catch (IOException e) {
			LOG.error(e);
			throw e;
		}
		return "ok";
	}
	
	private String buildDataExportFilePath(HttpServletRequest request) {
		ServletContext context = request.getSession().getServletContext();
		String exportRealPath = context.getRealPath(EXPORT_PATH);
		SessionState sessionState = getSessionState(request);
		String fileName = "data.zip";
		String sessionId = sessionState.getSessionId();
		StringBuilder sb = new StringBuilder();
		sb.append(exportRealPath).append(File.separator).append(sessionId).append(File.separator).append(fileName);
		return sb.toString();
	}

	private SessionState getSessionState(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session != null) {
			SessionState sessionState = (SessionState) session.getAttribute(SessionState.SESSION_ATTRIBUTE_NAME);
			return sessionState;
		}
		return null;
	}
	
	private void write(HttpServletResponse response, File file) throws IOException {
		FileInputStream is = null;
		BufferedInputStream buf = null;
		try {
			String name = file.getName();
			String contentType = MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType(name);
			response.setContentType(contentType); 
			response.setContentLength(new Long(file.length()).intValue());
			response.setHeader("Content-Disposition", "attachment; filename=" + name);
			ServletOutputStream outputStream = response.getOutputStream();
			is = new FileInputStream(file);
			buf = new BufferedInputStream(is);
			int readBytes = 0;
			//read from the file; write to the ServletOutputStream
			while ((readBytes = buf.read()) != -1) {
				outputStream.write(readBytes);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if ( buf != null) {
				buf.close();
			}
			if ( is != null ) {
				is.close();
			}
		}
	}
	
}
