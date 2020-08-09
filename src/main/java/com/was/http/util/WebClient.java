package com.was.http.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WebClient {

	public static String sendGet(HttpURLConnection httpURLConnection) {
		StringBuffer response = new StringBuffer();
		try {
			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
			httpURLConnection.setConnectTimeout(10000);       
			httpURLConnection.setReadTimeout(5000);           
			
	        InputStream inputStream = httpURLConnection.getInputStream();
	        byte[] buffer = new byte[2048];
	        int count;
	        while(-1 != (count = inputStream.read(buffer))) {
	        	response.append(new String(buffer, 0, count));
	        }
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return response.toString();
	}
	
	public static String sendPost(HttpURLConnection httpURLConnection) {
		StringBuffer response = new StringBuffer();
		try {
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
			httpURLConnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			httpURLConnection.setConnectTimeout(10000);       
			httpURLConnection.setReadTimeout(5000);           
			
	        InputStream inputStream = httpURLConnection.getInputStream();
	        byte[] buffer = new byte[2048];
	        int count;
	        while(-1 != (count = inputStream.read(buffer))) {
	        	response.append(new String(buffer, 0, count));
	        }
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return response.toString();
	}
	
	public static HttpURLConnection getConnection(String url) {
		HttpURLConnection con = null;
		try {
			con = (HttpURLConnection) new URL(url).openConnection();
			con.setDoOutput(true);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return con;
	}
}
