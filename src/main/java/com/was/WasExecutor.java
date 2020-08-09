package com.was;

import java.io.IOException;

import com.was.config.ConfigLoader;
import com.was.config.ServerConfig;
import com.was.http.servlet.handler.ServletContainerInitializer;

public class WasExecutor {
	public static void main(String[] args) throws IOException {
    	String host = args.length > 0 ? args[0] : "localhost";
    	ServerConfig serverConfig = ConfigLoader.getInstance().load(host);
    	
    	ServletContainerInitializer servletContainerInitializer = new ServletContainerInitializer();
		servletContainerInitializer.init();
		
        HttpServer server = new HttpServer();
        server.setServerConfig(serverConfig);
        server.start();
    }
}
