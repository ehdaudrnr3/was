package com.was.http.servlet;

import java.io.IOException;
import java.io.InputStream;

public interface ServletRequest {
	String getMethod();
	String getRequestUrl();
	String getUri();
	String getVersion();
	String getHost();
	String getQuery();
	String getParamerter(String key);
	String getAttribute(String attribute);
	InputStream getInputStream() throws IOException;
}
