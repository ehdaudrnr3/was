package com.was;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.was.config.ServerConfig;
import com.was.handler.RequestHandler;

public class HttpServer {
	
    private final Logger logger = LoggerFactory.getLogger(HttpServer.class);
    
    private ServerConfig serverConfig;
    
	public void setServerConfig(ServerConfig serverConfig) {
		this.serverConfig = serverConfig;
	}

	public void start() throws IOException {
        ExecutorService pool = Executors.newFixedThreadPool(serverConfig.getThreadPoolSize());
        try (ServerSocket server = new ServerSocket(serverConfig.getPort())) {
            logger.info("Accepting connections on port " + server.getLocalPort());
            while (true) {
                try {
                    Socket connection = server.accept();
                    Runnable r = new RequestHandler(connection);
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