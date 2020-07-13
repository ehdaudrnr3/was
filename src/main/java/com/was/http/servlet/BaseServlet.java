package com.was.http.servlet;

import java.io.File;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.was.HttpServer;
import com.was.http.contants.HttpStatus;
import com.was.http.util.FileUtil;
import com.was.http.util.HttpUtil;
import com.was.virtualhost.VirtualHost;

public class BaseServlet implements SimpleServlet {

	private Logger logger = LoggerFactory.getLogger(BaseServlet.class);
	
	private static final String METHOD_GET = "GET";

	private VirtualHost virtualHost;
	
	@Override
	public void init(VirtualHost virtualHost) {
		this.virtualHost = virtualHost;
	}
	
	@Override
	public void service(ServletRequest request, ServletResponse response) {
		try {
			if(request.getMethod().equals(METHOD_GET)) {
				doGet(request, response);
			} else {
				doError(request, response);
			}
		} catch (Exception e) {
			logger.error("해당 요청을 처리하는 데 실패하였습니다.", e);
		}
	}

	private void doGet(ServletRequest request, ServletResponse response) throws Exception {
		File file = null;
		String responseCode = "HTTP/1.1 200 Ok";
		int status = HttpStatus.OK;
		String contentType = HttpUtil.getContentType(request.getRequestUrl());
		
		if(request.getRequestUrl().equals("/")) {
			file = getFile(virtualHost.getIndexHtml());
		} else {
			file = getFile(request.getRequestUrl());
		}
		
		if(request.getRequestUrl().contains("/..") || request.getRequestUrl().endsWith(".exe")) {
			responseCode = "HTTP/1.1 403 Forbidden";
			status = HttpStatus.FOBBIDEN;
			contentType = HttpUtil.getContentType(virtualHost.getForbidden());
			file = getFile(virtualHost.getForbidden());
		} else if(file == null) {
			responseCode = "HTTP/1.1 404 Not found";
			status = HttpStatus.NOT_FOUND;
			contentType = HttpUtil.getContentType(virtualHost.getNotFound());
			file = getFile(virtualHost.getNotFound());
		} 
		
		byte[] bytes = FileUtil.readToBytes(file);
		response.addHeader(responseCode, contentType, status, bytes.length);
		response.addBody(bytes);
	}
	
	private void doError(ServletRequest request, ServletResponse response) throws Exception {
		File file = getFile(virtualHost.getServerError());;
		String responseCode = "HTTP/1.1 500 Internal Server Error";
		int status = HttpStatus.INTERNAL_SERVER_ERROR;
		String contentType = HttpUtil.getContentType(request.getRequestUrl());
		
		byte[] bytes = FileUtil.readToBytes(file);
		response.addHeader(responseCode, contentType, status, bytes.length);
		response.addBody(bytes);
	}
	
	private File getFile(String html) throws Exception{
		ClassLoader classLoader = HttpServer.class.getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(virtualHost.getDocumentRoot()+"/"+html);
		if(inputStream != null) {
			File tempFile = File.createTempFile(String.valueOf(inputStream.hashCode()), ".tmp");
	        tempFile.deleteOnExit();
	        
	        FileUtil.copyInputStreamToFile(inputStream, tempFile);
			return tempFile;
		}
		return null;
	}
}
