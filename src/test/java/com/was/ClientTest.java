package com.was;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.was.http.servlet.BaseServlet;
import com.was.http.servlet.ServletRegistration;
import com.was.http.util.WebClient;
import com.was.virtualhost.VirtualHost;

import service.Hello;

public class ClientTest {
	
	@Before
	public void setup() throws IOException {
		ServletRegistration servletRegistration = new ServletRegistration();
    	servletRegistration.put("/", new BaseServlet());
    	servletRegistration.put("Hello", new com.was.http.servlet.Hello());
    	servletRegistration.put("service.Hello", new Hello());
    	
        (new Thread(new Runnable(){
            public void run() {
                try{
                	String host = "localhost";
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
	public void 디폴트_응답페이지_테스트() throws IOException {
		HttpURLConnection connection = WebClient.getConnection("http://localhost:8080");
		String response = WebClient.sendGet(connection);
		
		Assert.assertThat(connection.getResponseCode(), CoreMatchers.is(200));
		Assert.assertThat(response, CoreMatchers.containsString("Hello"));
	}
	
	@Test
	public void POST_전송_500_에러_테스트() throws IOException {
		HttpURLConnection connection = WebClient.getConnection("http://localhost:8080");
		String response = WebClient.sendPost(connection);
		
		Assert.assertThat(response, CoreMatchers.is(""));
		Assert.assertThat(connection.getResponseCode(), CoreMatchers.is(500));
		Assert.assertThat(connection.getResponseMessage(), CoreMatchers.containsString("Internal Server Error"));
	}
	
	@Test
	public void NOT_FOUND_404_에러_테스트() throws IOException {
		HttpURLConnection connection = WebClient.getConnection("http://localhost:8080/test");

		Assert.assertThat(connection.getResponseCode(), CoreMatchers.is(404));
		Assert.assertThat(connection.getResponseMessage(), CoreMatchers.containsString("Not found"));
	}
	
	@Test
	public void FORBIDDEN_403_에러_테스트() throws IOException {
		HttpURLConnection exeError = WebClient.getConnection("http://localhost:8080/test.exe");
		HttpURLConnection dirError = WebClient.getConnection("http://localhost:8080/../");
		
		Assert.assertThat(exeError.getResponseCode(), CoreMatchers.is(403));
		Assert.assertThat(exeError.getResponseMessage(), CoreMatchers.containsString("Forbidden"));
		Assert.assertThat(dirError.getResponseCode(), CoreMatchers.is(403));
		Assert.assertThat(dirError.getResponseMessage(), CoreMatchers.containsString("Forbidden"));
	}
}
