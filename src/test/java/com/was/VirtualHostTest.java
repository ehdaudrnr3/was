package com.was;

public class VirtualHostTest {
	
	//@Test
//	public void 가상호스트_설정파일_테스트() throws IOException {
//		VirtualHost defaultHost = new VirtualHost().defaultHost();
//		VirtualHost otherHost = 
//				new VirtualHost("other.com", 8081, "other", "other_index.html", "other_404.html", "other_403.html", "other_500.html");
//        VirtualHost anotherHost = 
//        		new VirtualHost("another.com", 8082, "another", "another_index.html", "another_404.html", "another_403.html", "another_500.html");
//        
//        VirtualHost defaultLoad = ConfigLoader.load("localhost");
//        VirtualHost otherLoad = ConfigLoader.load("other.com");
//        VirtualHost anotherLoad = ConfigLoader.load("another.com");
//        
//        Assert.assertEquals(defaultHost.getPort(), defaultLoad.getPort());
//        Assert.assertEquals(otherHost.getPort(), otherLoad.getPort());
//        Assert.assertEquals(anotherHost.getPort(), anotherLoad.getPort());
//	}
//	
//	@Test
//	public void 호스트가_없는경우_디폴트_호스트로_변경() throws IOException {
//		VirtualHost defaultHost = new VirtualHost().defaultHost();
//		VirtualHost load = ConfigLoader.load("newhost.com");
//		
//		Assert.assertNotNull(load);
//		Assert.assertEquals(defaultHost.getDocumentRoot(), load.getDocumentRoot());
//	}
//	
//	@Test
//	public void OTHER_호스트_테스트() throws IOException {
//	}
//	
//	@Test
//	public void ANOTHER_호스트_테스트() throws IOException {
//	}
}
