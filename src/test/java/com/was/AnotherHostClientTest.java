package com.was;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.was.HttpServer;
import com.was.ResourceLoader;
import com.was.http.servlet.BaseServlet;
import com.was.http.servlet.ServletRegistration;
import com.was.http.util.WebClient;
import com.was.virtualhost.VirtualHost;

import service.Hello;

public class AnotherHostClientTest {
	
	@Before
	public void setup() throws IOException {
		ServletRegistration servletRegistration = new ServletRegistration();
    	servletRegistration.put("/", new BaseServlet());
    	servletRegistration.put("Hello", new com.was.http.servlet.Hello());
    	servletRegistration.put("service.Hello", new Hello());
    	
        (new Thread(new Runnable(){
            public void run() {
                try{
                	String host = "another.com";
                	VirtualHost virtualHost = ResourceLoader.load(host);
                    HttpServer server = new HttpServer();
                    server.setServletRegistration(servletRegistration);
                    server.setVirtualHosts(virtualHost);
                    server.start();
                } catch(Exception ex){
                	ex.printStackTrace();
                }
            };
        })).start();
	}
	
	@Test
	public void 인덱스_응답페이지_테스트() throws IOException {
		HttpURLConnection connection = WebClient.getConnection("http://localhost:8082");
		String response = WebClient.sendGet(connection);
		
		Assert.assertThat(connection.getResponseCode(), CoreMatchers.is(200));
		Assert.assertThat(response, CoreMatchers.containsString("Hello"));
	}
	
	@Test
	public void 서버에러_응답페이지_테스트() throws IOException {
		HttpURLConnection connection = WebClient.getConnection("http://localhost:8082");
		WebClient.sendPost(connection);
		
		Assert.assertThat(connection.getResponseCode(), CoreMatchers.is(500));
	}
	
	@Test
	public void NOT_FOUND_응답페이지_테스트() throws IOException {
		HttpURLConnection connection = WebClient.getConnection("http://localhost:8082/test");
		WebClient.sendGet(connection);
		
		Assert.assertThat(connection.getResponseCode(), CoreMatchers.is(404));
	}
	
	@Test
	public void FORBIDDEN_응답페이지_테스트() throws IOException {
		HttpURLConnection connection = WebClient.getConnection("http://localhost:8082/test.exe");
		WebClient.sendGet(connection);
		
		Assert.assertThat(connection.getResponseCode(), CoreMatchers.is(403));
	}
}
