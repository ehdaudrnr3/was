package com.was.http.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;

public class HttpServletResponse implements ServletResponse {
	
	private String status;
	private OutputStream outputStream;
	private Writer writer;
	
	public HttpServletResponse(OutputStream outputStream) {
		this.outputStream = outputStream;
		this.writer = new OutputStreamWriter(outputStream);
	}

	@Override
	public OutputStream getOutputStream() throws IOException {
		return outputStream;
	}
	
	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public Writer getWriter() throws Exception {
		return writer;
	}
	
	@Override
	public void addHeader(String responseCode, String contentType, int status, int length)
            throws IOException {
        writer.write(responseCode + "\r\n");
        Date now = new Date();
        writer.write("Date: " + now + "\r\n");
        writer.write("Server: JHTTP 2.0\r\n");
        writer.write("Status: " + status + "\r\n");
        writer.write("Content-length: " + length + "\r\n");
        writer.write("Content-type: " + contentType + "\r\n\r\n");
        writer.flush();
    }

	@Override
	public void addBody(byte[] body) throws IOException {
		outputStream.write(body);
		outputStream.flush();
	}
}
