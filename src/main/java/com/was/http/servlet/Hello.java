package com.was.http.servlet;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.was.annotation.WebServlet;
import com.was.http.servlet.request.ServletRequest;
import com.was.http.servlet.response.ServletResponse;

@WebServlet(value = "Hello",  uri = "Hello")
public class Hello implements SimpleServlet {

	private Logger logger = LoggerFactory.getLogger(Hello.class);
	
	@Override
	public void service(ServletRequest request, ServletResponse response) {
		try {
			String name = request.getParamerter("name");
			String body = new StringBuilder()
					.append("Hello, "+name).append("<br />")
					.append("now : "+LocalDateTime.now()).append("<br />")
					.toString();
			
			response.addHeader("HTTP/1.1", "text/html", 200, body.length());
			response.addBody(body.getBytes());
		} catch (Exception e) {
			logger.error("Hello Servlet Error to", e);
		}
	}

	
}
