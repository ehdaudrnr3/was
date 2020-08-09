package com.was;

public class HttpParserTest {
	
//	@SuppressWarnings("resource")
//	private InputStream getInputStream() throws IOException {
//		String in = new StringBuilder()
//				.append("GET /service.Hello?name=hello HTTP/1.1").append(System.getProperty("line.separator"))
//				.append("Host: localhost:8080").append(System.getProperty("line.separator"))
//				.append("Connection: keep-alive").append(System.getProperty("line.separator"))
//				.append("Cache-Control: max-age=0").append(System.getProperty("line.separator"))
//				.append("Upgrade-Insecure-Requests: 1").append(System.getProperty("line.separator"))
//				.append("User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36").append(System.getProperty("line.separator"))
//				.append("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9").append(System.getProperty("line.separator"))
//				.append("Sec-Fetch-Site: none").append(System.getProperty("line.separator"))
//				.append("Sec-Fetch-Mode: navigate").append(System.getProperty("line.separator"))
//				.append("Sec-Fetch-User: ?1").append(System.getProperty("line.separator"))
//				.append("Sec-Fetch-Dest: document").append(System.getProperty("line.separator"))
//				.append("Accept-Encoding: gzip, deflate, br").append(System.getProperty("line.separator"))
//				.append("Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7").append(System.getProperty("line.separator"))
//				.append("Cookie: JSESSIONID=1FDE1CBCA8AD04088D95C9F5ED7D9DE6").append(System.getProperty("line.separator"))
//				.toString();
//		
//		File file = File.createTempFile(String.valueOf(in.hashCode()), ".tmp");
//		file.deleteOnExit();
//		FileOutputStream out = new FileOutputStream(file);
//		out.write(in.getBytes());
//		out.flush();
//		
//		return new FileInputStream(file);
//	}
//	
//	@Test
//	public void Header_테스트() throws Exception {
//		InputStream inputStream = this.getInputStream();
//		HttpHeaderSpecification parseHeader = HttpParser.parseHeader(inputStream);
//		
//		Assert.assertNotNull(parseHeader);
//		Assert.assertEquals("GET", parseHeader.get("method"));
//		Assert.assertEquals("8080", parseHeader.get("port"));
//		Assert.assertEquals("?name=hello", parseHeader.get("query"));
//	}
//	
//	@Test
//	public void Query_테스트() throws Exception {
//		InputStream inputStream = this.getInputStream();
//		HttpHeaderSpecification parseHeader = HttpParser.parseHeader(inputStream);
//		Map<String, String> params = HttpParser.parseQuery(parseHeader.get("query"));
//		
//		Assert.assertFalse(params.isEmpty());
//		Assert.assertEquals("hello", params.get("name"));
//	}
}
