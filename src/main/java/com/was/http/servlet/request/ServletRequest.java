package com.was.http.servlet.request;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public interface ServletRequest {
	String getMethod();
	String getRequestUrl();
	String getRequestUri();
	String getVersion();
	String getHost();
	String getQuery();
	String getParamerter(String key);
	String getAttribute(String attribute);
	InputStream getInputStream() throws IOException;
	Socket getSocket();
}
