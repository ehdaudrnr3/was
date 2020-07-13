package com.was;

import java.io.IOException;

import com.was.http.servlet.BaseServlet;
import com.was.http.servlet.ServletRegistration;
import com.was.virtualhost.VirtualHost;

import service.Hello;

public class WasExecutor {
	public static void main(String[] args) throws IOException {
    	ServletRegistration servletRegistration = new ServletRegistration();
    	servletRegistration.put("/", new BaseServlet());
    	servletRegistration.put("Hello", new com.was.http.servlet.Hello());
    	servletRegistration.put("service.Hello", new Hello());
    	
    	String host = args.length > 0 ? args[0] : "localhost";
    	VirtualHost virtualHost = ResourceLoader.load(host);
        HttpServer server = new HttpServer();
        server.setServletRegistration(servletRegistration);
        server.setVirtualHosts(virtualHost);
        server.start();
    }
}
