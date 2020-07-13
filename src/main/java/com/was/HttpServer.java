package com.was;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.was.http.servlet.ServletRegistration;
import com.was.virtualhost.VirtualHost;

public class HttpServer {
    private static final Logger logger = LoggerFactory.getLogger(HttpServer.class);
    private static final int NUM_THREADS = 50;
    
    private ServletRegistration servletRegistration;
    private VirtualHost virtualHost;
    
    public void setServletRegistration(ServletRegistration servletRegistration) {
		this.servletRegistration = servletRegistration;
	}

    
	public void setVirtualHosts(VirtualHost virtualHost) {
		this.virtualHost = virtualHost;
	}
	
	public void start() throws IOException {
        ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
        try (ServerSocket server = new ServerSocket(virtualHost.getPort())) {
            logger.info("Accepting connections on port " + server.getLocalPort());
            while (true) {
                try {
                    Socket request = server.accept();
                    Runnable r = new RequestProcessor(request, servletRegistration, virtualHost);
                    pool.submit(r);
                } catch (IOException e) {
                    logger.error("Error accepting connection", e);
                } catch(Exception e) {
                	logger.error("Exception", e);
                }
            }
        }
    }
}