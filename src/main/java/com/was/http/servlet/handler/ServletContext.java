package com.was.http.servlet.handler;

import com.was.http.servlet.SimpleServlet;

public class ServletContext {
	
	private ServletRegistration registration;
	
	private static class LazyHolder {
		private static final ServletContext INSTANCE = new ServletContext();
	}
	
	public static ServletContext getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	
	public void setServletRegistration(ServletRegistration registration) {
		this.registration = registration;
	}


	public SimpleServlet getServlet(String servletName) {
		for (String key : registration.getContainer().keySet()) {
			if(servletName.startsWith(key)) {
				return registration.getContainer().get(key);
			}
			
		}
		return registration.getContainer().get("/");
	}
}
