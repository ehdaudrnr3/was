package com.was.http.servlet;

import com.was.http.servlet.request.ServletRequest;
import com.was.http.servlet.response.ServletResponse;

public interface SimpleServlet {
	public void service(ServletRequest request, ServletResponse response); 
}
