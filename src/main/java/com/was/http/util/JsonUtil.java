package com.was.http.util;

import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class JsonUtil {
	
	private static final Gson gson = new GsonBuilder().create();
	
	public static <T> T readToObject(InputStreamReader isr, Class<T> type) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return gson.fromJson(new JsonReader(isr), type);
	}
	
	public static String toJson(Object object) {
		return gson.toJson(object);
	}
}
