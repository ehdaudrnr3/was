package com.was.config;

import java.util.List;

public class ServerConfig {

	private String host;

	private int port;

	private int threadPoolSize;

	private String rootDir;

	private List<ResourceConfig> resources;

	public String getHost() {
		return host;
	}
	
	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public int getThreadPoolSize() {
		return threadPoolSize;
	}

	public String getRootDir() {
		return rootDir;
	}

	public List<ResourceConfig> getResources() {
		return resources;
	}

}
