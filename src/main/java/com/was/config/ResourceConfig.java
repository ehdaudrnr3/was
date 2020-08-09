package com.was.config;

import java.util.List;

import com.was.http.contants.HttpStatus;

public class ResourceConfig {
	
	private String host;
	
	private int port;
	
	private String subDir;
	
	private List<PageConfig> pages;

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getSubDir() {
		return subDir;
	}

	public List<PageConfig> getPages() {
		return pages;
	}
	
	public PageConfig findPageStatus(int statusCode) {
		 return pages.stream()
			.filter(page->page.getStatusCode() == statusCode)
			.findAny()
			.get();
	}
	
	public PageConfig findPage(String html) {
		PageConfig NOT_FOUND = this.findPageStatus(HttpStatus.NOT_FOUND);
		
		return pages.stream()
				.filter(page->page.getView().equals(html))
				.findAny()
				.orElse(NOT_FOUND);
	}
}
