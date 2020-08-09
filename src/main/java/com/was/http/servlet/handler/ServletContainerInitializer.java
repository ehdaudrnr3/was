package com.was.http.servlet.handler;

public class ServletContainerInitializer {
	
	public void init() {
		ServletContext servletContext = ServletContext.getInstance();
		servletContext.setServletRegistration(new ServletRegistration());
	}
}
