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
	
	public static Map<String, String> parseHeader(InputStream is) throws Exception {
        try {
        	Map<String, String> header = new HashMap<>();
        	
        	StringBuilder logBuilder = new StringBuilder();
        	String line;
        	int index = 0;
        	BufferedReader br = new BufferedReader(new InputStreamReader(new BufferedInputStream(is), "UTF-8"));
            while((line = br.readLine()) != null && line.length() > 0) {
            	logBuilder.append(line).append(System.getProperty("line.separator"));
            	
            	if(index == 0) {
            		StringTokenizer token = new StringTokenizer(line);
            		String method = token.nextToken();
            		String path = token.nextToken();
            		String version = token.nextToken();
            		
            		header.put("method", method);
            		header.put("requestUrl", extractUrl(path));
            		header.put("uri", extractUri(path));
            		header.put("version", version);
            		header.put("query", extractQuery(path));
            	} else if(line.contains("Host")) {
            		int indexOf = line.indexOf(":");
            		int lastIndexOf = line.lastIndexOf(":");
            		header.put("host", line.substring(indexOf+1, lastIndexOf));
            		header.put("port", line.substring(lastIndexOf+1));
            	} else {
            		StringTokenizer token = new StringTokenizer(line, "\\:");
            		header.put(token.nextToken(), token.nextToken());
            	}
            	index++;
            }
            logger.info(logBuilder.toString());
            return header;
        } catch (IOException e) {
        	logger.error("HttpParser erorr to", e);
        } catch(Exception e) {
        	logger.error("HttpParser error to", e);
        }
		return null;
	}
	
	public static Map<String, String> parseQuery(String query) {
		Map<String, String> parameters = new HashMap<>();
		String[] params = query.split("&");
		for (String param : params) {
			String[] keyAndValue = param.split("\\=");
			if(keyAndValue.length > 0) {
				parameters.put(keyAndValue[0], keyAndValue.length > 1 ? keyAndValue[1] : "");
			}
		}
		return parameters;
	}
	
	private static String extractQuery(String path) {
		int lastIndexOf = path.lastIndexOf("?");
		return lastIndexOf != -1 ? path.substring(lastIndexOf+1) : "";
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
