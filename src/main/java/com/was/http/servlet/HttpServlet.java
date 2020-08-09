package com.was.http.servlet;

import java.io.File;
import java.io.InputStream;

import com.was.config.ConfigLoader;
import com.was.config.PageConfig;
import com.was.config.ResourceConfig;
import com.was.http.util.FileUtil;

public abstract class HttpServlet implements SimpleServlet {
	
	private ResourceConfig resourceConfig;
	
	public HttpServlet() {
		this.resourceConfig = ConfigLoader.getInstance().getResourceConfig();
	}
	
	protected File getFile(String html) throws Exception{
		PageConfig page = resourceConfig.findPage(html);
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("templates/"+resourceConfig.getSubDir()+"/"+page.getView());
		if(inputStream != null) {
			File tempFile = File.createTempFile(String.valueOf(inputStream.hashCode()), ".tmp");
	        tempFile.deleteOnExit();
	        
	        FileUtil.copyInputStreamToFile(inputStream, tempFile);
			return tempFile;
		}
		return null;
	}
	
	protected File getFile(int statusCode) throws Exception{
		PageConfig pageConfig = resourceConfig.findPageStatus(statusCode);
		return getFile(pageConfig.getView());
	}

}
