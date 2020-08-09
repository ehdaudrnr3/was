package com.was.http.servlet.request;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Optional;

import com.was.http.HttpParser;

public class HttpServletRequest implements ServletRequest {

	private String method;
	private String requestUrl;
	private String uri;
	private String version;
	private String host;
	private String query;
	private Map<String, String> parameters;
	private Map<String, String> header;
	private InputStream inputStream;
	private Socket socket;
	
	public HttpServletRequest(Socket connection) throws IOException, Exception {
		Map<String, String> header = HttpParser.parseHeader(connection.getInputStream());
    	Map<String, String> parameters = HttpParser.parseQuery(header.get("query"));
    	
		this.method = header.get("method");
		this.requestUrl = header.get("requestUrl");
		this.uri = header.get("uri");
		this.version = header.get("version");
		this.host = header.get("host");
		this.query = header.get("query");
		this.parameters = parameters;
		this.header = header;
		this.inputStream = connection.getInputStream();
		this.socket = connection;
	}

	@Override
	public String getMethod() {
		return method;
	}

	@Override
	public String getRequestUrl() {
		return requestUrl;
	}
	
	@Override
	public String getRequestUri() {
		return uri;
	}

	@Override
	public String getVersion() {
		return version;
	}

	@Override
	public String getHost() {
		return host;
	}

	@Override
	public String getQuery() {
		return this.query;
	}
	
	@Override
	public String getParamerter(String key) {
		return Optional.ofNullable(this.parameters.get(key)).orElse("");
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return inputStream;
	}

	@Override
	public String getAttribute(String attribute) {
		return header.get(attribute);
	}

	public Socket getSocket() {
		return socket;
	}
}
