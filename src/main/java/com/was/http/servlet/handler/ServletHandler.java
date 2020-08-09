package com.was.http.servlet.handler;

import java.io.IOException;

import com.was.http.servlet.SimpleServlet;
import com.was.http.servlet.request.ServletRequest;
import com.was.http.servlet.response.HttpServletResponse;
import com.was.http.servlet.response.ServletResponse;

public class ServletHandler {
	
	private ServletContext servletContext;
	
	public ServletHandler() {
		this.servletContext = ServletContext.getInstance();
	}
	
	public void handle(ServletRequest request) throws IOException {
		ServletResponse response = new HttpServletResponse(request.getSocket());
		
		SimpleServlet servlet = servletContext.getServlet(request.getRequestUri());
		servlet.service(request, response);
	}	
}	
