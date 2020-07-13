package com.was;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.GsonBuilder;
import com.was.virtualhost.VirtualHost;
import com.was.virtualhost.VirtualHostResource;

public class ResourceLoader {

	private static final Logger logger = LoggerFactory.getLogger(ResourceLoader.class);
	private static final String configFile = "config.json";
	
	public static VirtualHost load(String host) throws IOException {
		BufferedReader br = null;
		InputStream inputStream = null;
		try {
			StringBuilder builder = new StringBuilder();
			ClassLoader classLoader = HttpServer.class.getClassLoader();
			inputStream = classLoader.getResourceAsStream(configFile);
			
			if(inputStream == null) {
				throw new RuntimeException("Configuration load fail");
			}
			br = new BufferedReader(new InputStreamReader(inputStream));
			
			String line;
			while((line = br.readLine()) != null) {
				builder.append(line);
			}
			
			VirtualHostResource resource = new GsonBuilder().create().fromJson(builder.toString(), VirtualHostResource.class);
			
			return resource.getVirtualHosts().stream()
				.filter(virtual -> virtual.getHost().equals(host))
				.findAny()
				.orElse(new VirtualHost().defaultHost());
		} catch(IOException e) {
			logger.error("file not found", e);
		} catch(Exception e) {
			logger.error("VirtualHostResourceLoader erro to", e);
		} finally {
			inputStream.close();
			br.close();
		}
		return new VirtualHost().defaultHost();
	}
	
}
