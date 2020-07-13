package com.was.virtualhost;

public class VirtualHost {
	
	private String host;
	
	private int port;
	
	private String documentRoot;
	
	private String indexHtml;
	
	private String notFound;
	
	private String forbidden;
	
	private String serverError;
	
	public VirtualHost() {}

	public VirtualHost(String host, int port, String documentRoot, String indexHtml, String notFound, String forbidden,
			String serverError) {
		this.host = host;
		this.port = port;
		this.documentRoot = documentRoot;
		this.indexHtml = indexHtml;
		this.notFound = notFound;
		this.forbidden = forbidden;
		this.serverError = serverError;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDocumentRoot() {
		return documentRoot;
	}

	public void setDocumentRoot(String documentRoot) {
		this.documentRoot = documentRoot;
	}
	
	public String getIndexHtml() {
		return indexHtml;
	}

	public void setIndexHtml(String indexHtml) {
		this.indexHtml = indexHtml;
	}

	public String getNotFound() {
		return notFound;
	}

	public void setNotFound(String notFound) {
		this.notFound = notFound;
	}

	public String getForbidden() {
		return forbidden;
	}

	public void setForbidden(String forbidden) {
		this.forbidden = forbidden;
	}

	public String getServerError() {
		return serverError;
	}

	public void setServerError(String serverError) {
		this.serverError = serverError;
	}
	
	public VirtualHost defaultHost() {
		this.setHost("localhost");
		this.setPort(8080);
		this.setDocumentRoot("webapp");
		this.setIndexHtml("index.html");
		this.setForbidden("403.html");
		this.setNotFound("404.html");
		this.setServerError("500.html");
		
		return this;
	}
	
}
