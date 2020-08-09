package com.was.handler;

import java.io.IOException;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.was.http.servlet.handler.ServletHandler;
import com.was.http.servlet.request.HttpServletRequest;
import com.was.http.servlet.request.ServletRequest;

public class RequestHandler implements Runnable {
    
	private final Logger logger = LoggerFactory.getLogger(RequestHandler.class);
    
	private Socket connection;
    
	public RequestHandler(Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try {
    		ServletRequest request = new HttpServletRequest(connection);
    		ServletHandler servletHandler = new ServletHandler();
    		servletHandler.handle(request);

        } catch (IOException ex) {
            logger.error("Error talking to " + connection.getRemoteSocketAddress(), ex);
        } catch (Exception e) {
			logger.error("RequestProcessor error to", e);
		} finally {
//			try {
//                connection.close();
//            } catch (IOException e) {
//            	logger.error("RequestProcessor error to", e);
//            }
        }
    }
}