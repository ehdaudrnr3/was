package com.was;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.was.http.HttpHeaderSpecification;
import com.was.http.HttpParser;
import com.was.http.servlet.HttpServeltResponse;
import com.was.http.servlet.HttpServletRequest;
import com.was.http.servlet.ServletRegistration;
import com.was.http.servlet.ServletRequest;
import com.was.http.servlet.ServletResponse;
import com.was.http.servlet.SimpleServlet;
import com.was.http.servlet.mapping.ServletMapping;
import com.was.virtualhost.VirtualHost;

public class RequestProcessor implements Runnable {
    
	private final static Logger logger = LoggerFactory.getLogger(RequestProcessor.class);
    private Socket connection;
    private ServletRegistration servletRegistration;
    
    private VirtualHost virtualHost;

	public RequestProcessor(Socket connection, ServletRegistration servletRegistration, VirtualHost virtualHost) {
        this.connection = connection;
        this.servletRegistration = servletRegistration;
        this.virtualHost = virtualHost;
    }

    @Override
    public void run() {
        try {
        	//이게 이게 이게 이게
        	HttpHeaderSpecification header = HttpParser.parseHeader(connection.getInputStream());
        	Map<String, String> parameters = HttpParser.parseQuery(header.get("query"));
        	
    		ServletRequest request = new HttpServletRequest(header, parameters, connection.getInputStream());
    		ServletResponse response = new HttpServeltResponse(connection.getOutputStream());
    	
    		ServletMapping mapping = new ServletMapping(servletRegistration);
    		SimpleServlet servlet = mapping.getServlet(request.getUri());
    		servlet.init(virtualHost);
    		servlet.service(request, response);
    		
        } catch (IOException ex) {
            logger.error("Error talking to " + connection.getRemoteSocketAddress(), ex);
        } catch (Exception e) {
			logger.error("RequestProcessor error to", e);
		} finally {
			try {
                connection.close();
            } catch (IOException e) {
            	logger.error("RequestProcessor error to", e);
            }
        }
    }
}