package com.was.http.servlet.handler;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.was.annotation.WebServlet;
import com.was.http.servlet.SimpleServlet;
import com.was.http.servlet.mapping.ServletHandlerMapping;
import com.was.http.util.ClassUtil;

public class ServletRegistration {
	
	private final Logger logger = LoggerFactory.getLogger(ServletRegistration.class);
			
	private Map<String, SimpleServlet> container = new HashMap<>();
	
	public ServletRegistration() {
		loadAnnotationServlet();
	}
	
	public Map<String, SimpleServlet> getContainer() {
		return this.container;
	}
	
	private void loadAnnotationServlet() {
		try {
			ClassLoader definedPackages = Thread.currentThread().getContextClassLoader();
			for (Package pk : definedPackages.getDefinedPackages()) {
				String[] classNames = ClassUtil.getClassNames(pk.getName());
				for (String name : classNames) {
					Class<?> classType = Class.forName(name);
					WebServlet web = classType.getDeclaredAnnotation(WebServlet.class);
					if(web != null) {
						SimpleServlet servlet = (SimpleServlet) classType.getDeclaredConstructor().newInstance();
						ServletHandlerMapping servletHandlerMapping = new ServletHandlerMapping(servlet);
						ClassLoader classLoader = ServletHandlerMapping.class.getClassLoader();
						Class<?>[] args = new Class[] { SimpleServlet.class };
						
						SimpleServlet proxy = (SimpleServlet) Proxy.newProxyInstance(classLoader, args, servletHandlerMapping);
						container.put(web.uri(), proxy);
						logger.info("{} is {}, url: {}",web.annotationType().getSimpleName(), classType.getSimpleName(), web.uri());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void loadXmlServlet() {
		try {
			ClassLoader definedPackages = Thread.currentThread().getContextClassLoader();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
