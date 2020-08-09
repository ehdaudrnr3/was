package com.was.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.was.http.util.JsonUtil;

public class ConfigLoader {

	private final Logger logger = LoggerFactory.getLogger(ConfigLoader.class);
	
	private String configFile;
	private ServerConfig serverConfig;
	private ResourceConfig resourceConfig;
	
	public ConfigLoader() {
		this.configFile = "config.json";
	}
	
	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}
	
	private static class configLoaderHolder {
		private static final ConfigLoader INSTANCE = new ConfigLoader();
	}
	
	public static ConfigLoader getInstance() {
		return configLoaderHolder.INSTANCE;
	}
	
	public ServerConfig load(String host) throws IOException {
		loadConfigJson();
		resourceConfig = serverConfig.getResources().stream()
				.filter(resource->resource.getHost().equals(host))
				.findAny()
				.get();
		
		return serverConfig;
	}
	
	public ServerConfig load(int port) throws IOException {
		loadConfigJson();
		resourceConfig = serverConfig.getResources().stream()
				.filter(resource->resource.getPort() == port)
				.findAny()
				.get();
		
		return this.serverConfig;
	}
	
	public void loadConfigJson() throws IOException {
		InputStream inputStream = null;
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			inputStream = classLoader.getResourceAsStream(configFile);
			
			if(inputStream == null) {
				throw new RuntimeException("Configuration load fail");
			}
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			this.serverConfig = JsonUtil.readToObject(inputStreamReader, ServerConfig.class);
			
		} catch(Exception e) {
			logger.error("config load json error to", e);
		} finally {
			inputStream.close();
		}
	}
	
	public ServerConfig getServerConfig() {
		return serverConfig;
	}
	
	public ResourceConfig getResourceConfig() {
		return resourceConfig;
	}
}
