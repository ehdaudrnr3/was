package com.was.http;

import java.util.HashMap;
import java.util.Map;

public class HttpHeaderSpecification {
	
	private Map<String, String> spec = new HashMap<>();
	
	public String get(String key) {
		return spec.containsKey(key) ? spec.get(key) : "";
	}
	
	public void put(String key, String value) {
		spec.put(key, value.trim());
	}
}
