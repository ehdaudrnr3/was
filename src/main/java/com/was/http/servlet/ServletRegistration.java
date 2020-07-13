package com.was.http.servlet;

import java.util.HashMap;
import java.util.Map;

public class ServletRegistration {
	
	private Map<String, SimpleServlet> registration = new HashMap<>();
	
	public void put(String name, SimpleServlet servlet) {
		registration.put(name, servlet);
	}
	
	public boolean has(String servletName) {
		return registration.containsKey(servletName);
	}
	
	public SimpleServlet get(String servletName) {
		return registration.get(servletName);
	}
}
