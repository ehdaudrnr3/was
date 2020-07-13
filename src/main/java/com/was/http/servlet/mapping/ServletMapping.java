package com.was.http.servlet.mapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.was.http.servlet.ServletRegistration;
import com.was.http.servlet.SimpleServlet;

public class ServletMapping {
	
	private final static Logger logger = LoggerFactory.getLogger(ServletMapping.class);
	
	private ServletRegistration servletRegistration;
	
	public ServletMapping(ServletRegistration servletRegistration) {
		this.servletRegistration = servletRegistration;
	}

	public SimpleServlet getServlet(String servletName) {
		try {
			if(servletRegistration.has(servletName)) {
				return servletRegistration.get(servletName);
			}
		} catch(Exception e) {
			logger.error("Servlet Mapping error to", e);
		}
		return servletRegistration.get("/");
	}
}	
