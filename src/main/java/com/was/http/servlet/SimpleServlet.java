package com.was.http.servlet;

import com.was.virtualhost.VirtualHost;

public interface SimpleServlet {
	public void init(VirtualHost virtualHost);
	public void service(ServletRequest request, ServletResponse response); 
}
