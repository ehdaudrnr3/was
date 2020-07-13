package com.was.http;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpParser {
	
	private static final Logger logger = LoggerFactory.getLogger(HttpParser.class);
	
	public static HttpHeaderSpecification parseHeader(InputStream is) throws Exception {
        BufferedReader br = null;
        try {
        	HttpHeaderSpecification headerSpecification = new HttpHeaderSpecification();
        	
        	StringBuilder logBuilder = new StringBuilder();
        	String line;
        	int index = 0;
        	br = new BufferedReader(new InputStreamReader(new BufferedInputStream(is), "UTF-8"));
            while((line = br.readLine()) != null && line.length() > 0) {
            	logBuilder.append(line).append(System.getProperty("line.separator"));
            	
            	if(index == 0) {
            		StringTokenizer token = new StringTokenizer(line);
            		String method = token.nextToken();
            		String path = token.nextToken();
            		String version = token.nextToken();
            		
            		headerSpecification.put("method", method);
            		headerSpecification.put("requestUrl", extractUrl(path));
            		headerSpecification.put("uri", extractUri(path));
            		headerSpecification.put("version", version);
            		headerSpecification.put("query", extractQuery(path));
            	} else if(line.contains("Host")) {
            		int indexOf = line.indexOf(":");
            		int lastIndexOf = line.lastIndexOf(":");
            		headerSpecification.put("host", line.substring(indexOf+1, lastIndexOf));
            		headerSpecification.put("port", line.substring(lastIndexOf+1));
            	} else {
            		StringTokenizer token = new StringTokenizer(line, "\\:");
            		headerSpecification.put(token.nextToken(), token.nextToken());
            	}
            	index++;
            }
            
            logger.info(logBuilder.toString());
            return headerSpecification;
        } catch (IOException e) {
        	logger.error("HttpParser erorr to", e);
        } catch(Exception e) {
        	logger.error("HttpParser error to", e);
        } finally {
		}
		return null;
	}
	
	public static Map<String, String> parseQuery(String path) {
		int lastIndexOf = path.lastIndexOf("?");
		
		Map<String, String> parameters = new HashMap<>();
		if(lastIndexOf != -1) {
			String[] items = path.substring(lastIndexOf+1).split("&");
			for (String item : items) {
				StringTokenizer token = new StringTokenizer(item, "=");
				if(token.hasMoreTokens()) {
					parameters.put(token.nextToken(), token.nextToken());
				}
			}
		}
		return parameters;
	}
	
	private static String extractQuery(String path) {
		int lastIndexOf = path.lastIndexOf("?");
		return lastIndexOf != -1 ? path.substring(lastIndexOf) : "";
	}
	
	private static String extractUrl(String path) {
		int lastIndexOf = path.lastIndexOf("?");
		if(lastIndexOf != -1) {
			return path.substring(0, lastIndexOf);
		}
		return path;
	}
	
	private static String extractUri(String path) {
		String url = extractUrl(path);
		int indexOf = url.indexOf("/");
		return url.substring(indexOf+1);
	}
}
