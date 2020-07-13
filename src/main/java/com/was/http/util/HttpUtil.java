package com.was.http.util;

import java.net.URLConnection;

public class HttpUtil {

	public static String getContentType(String filename) {
		return URLConnection.getFileNameMap().getContentTypeFor(filename);
	}
}
