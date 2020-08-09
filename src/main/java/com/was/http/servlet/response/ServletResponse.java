package com.was.http.servlet.response;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public interface ServletResponse {
	
	String getStatus();
	OutputStream getOutputStream() throws IOException;
	Writer getWriter() throws Exception;
	void addHeader(String responseCode, String contentType, int status, int length) throws IOException;
	void addBody(byte[] body) throws IOException;
}
