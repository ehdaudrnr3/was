package com.was.http.servlet.mapping;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.was.http.servlet.SimpleServlet;

public class ServletHandlerMapping implements InvocationHandler{
	
	private final Logger logger = LoggerFactory.getLogger(ServletHandlerMapping.class);
	
	private SimpleServlet servlet;
	
	public ServletHandlerMapping(SimpleServlet servlet) {
		this.servlet = servlet;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		logger.info("Invoke Servlet : {}", servlet.getClass().getName());
        Object result = method.invoke(servlet, args);
        return result;
	}
	
}	
