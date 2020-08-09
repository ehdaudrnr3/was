package com.was.http.servlet;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.was.annotation.WebServlet;
import com.was.http.contants.HttpMethod;
import com.was.http.contants.HttpStatus;
import com.was.http.servlet.request.ServletRequest;
import com.was.http.servlet.response.ServletResponse;
import com.was.http.util.FileUtil;
import com.was.http.util.HttpUtil;

@WebServlet(value = "BaseServlet", uri = "/")
public class BaseServlet extends HttpServlet {

	private final Logger logger = LoggerFactory.getLogger(BaseServlet.class);
	
	@Override
	public void service(ServletRequest request, ServletResponse response) {
		try {
			if(request.getMethod().equals(HttpMethod.GET)) {
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
		String contentType = "text/html";
		
		if(request.getRequestUrl().equals("/")) {
			file = getFile(HttpStatus.OK);
		} else {
			file = getFile(request.getRequestUrl());
		}
		
		if(request.getRequestUrl().contains("/..") || request.getRequestUrl().endsWith(".exe")) {
			responseCode = "HTTP/1.1 403 Forbidden";
			status = HttpStatus.FOBBIDEN;
			file = this.getFile(status);
		} else if(file == null) {
			responseCode = "HTTP/1.1 404 Not found";
			status = HttpStatus.NOT_FOUND;
			file = getFile(status);
		} 
		
		byte[] bytes = FileUtil.readToBytes(file);
		response.addHeader(responseCode, contentType, status, bytes.length);
		response.addBody(bytes);
	}
	
	private void doError(ServletRequest request, ServletResponse response) throws Exception {
		File file = getFile(HttpStatus.INTERNAL_SERVER_ERROR);
		String responseCode = "HTTP/1.1 500 Internal Server Error";
		int status = HttpStatus.INTERNAL_SERVER_ERROR;
		String contentType = HttpUtil.getContentType(request.getRequestUrl());
		
		byte[] bytes = FileUtil.readToBytes(file);
		response.addHeader(responseCode, contentType, status, bytes.length);
		response.addBody(bytes);
	}
}
