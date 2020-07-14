
### 실행방법
```
java -jar was.jar
```

#### 1. HTTP/1.1 의 Host 헤더를 해석하세요.
JSON 파일로 각 가상호스트의 호스트 파일을 등록하여 서버 실행시 실행 매개변수를 입력하여 
호스트를 주소를 지정할 수 있습니다.

```
java -jar was.jar other.com
```
이와 같이 호스트 주소를 입력받아서 가상호스트를 처리하였습니다.


#### 2. 다음 사항을 설정 파일로 관리하세요.

##### 가상호스트의 설정파일(Config.json)
```JSON
{
	"virtualHosts" : [
		{
			"host" : "other.com",
			"port" : 8081,
			"documentRoot" : "other",
			"indexHtml" : "other_index.html",
			"notFound" : "other_404.html",
			"forbidden" : "other_403.html",
			"serverError" : "other_500.html"
		},
		{
			"host" : "another.com",
			"port" : 8082,
			"documentRoot" : "another",
			"indexHtml" : "another_index.html",
			"notFound" : "another_404.html",
			"forbidden" : "another_403.html",
			"serverError" : "another_500.html"
		}
	]
}
```
![htmlPath](./htmlPath.png)

#### 3. 403, 404, 500 오류를 처리합니다.
서버실행시 디폴트는 8080 포트로 동작하게 됩니다.
SimpleServlet 구현체인 BaseServlet에서 403, 404, 500 오류에 대한 출력할 html를 출력합니다.
#### 4. 다음과 같은 보안 규칙을 둡니다.
SimpleServlet 구현체인 BaseServlet에서 .. 혹은 .exe로 끝나는 uri 값을 구분하여 가상호스트 Config 파일에 등록되어 있는 forbidden html을 적절히 출력합니다.
#### 5. logback 프레임워크 http://logback.qos.ch/를 이용하여 다음의 로깅 작업을 합니다.
전체 로그에 대한 정보는 ./logs/serverlog.log 파일에 로그가 쌓이게 되고 에러에 대한 정보는 ./logs/error.log 파일에 기록되도록 logback.xml을 구성하였습니다.

#### 6. 간단한 WAS 를 구현합니다.
기본적으로 jar파일 실행시 가상호스트에 대한 매개변수를 입력하지 않으면 8080 포트로 디폴트 html를 출력하도록 구성했습니다. <br />
```java
    ServletRegistration servletRegistration = new ServletRegistration();
    servletRegistration.put("/", new BaseServlet());
    servletRegistration.put("Hello", new com.was.http.servlet.Hello());
    servletRegistration.put("service.Hello", new Hello());
```
SimpleServlet 구현체를 매핑하고 설정파일 혹은 클래스에 대한 Servlet를 매핑하기 위해 main단에서 서블릿 구현체를 등록하여 서블릿 정보를 불러올 수 있도록 관리하는 클래스를 별도로 구성하는 방법으로 구상했으나 이점에 대해서 마땅한 방법을 찾이 못하였던 것 같습니다.


#### 7. 현재 시각을 출력하는 SimpleServlet 구현체를 작성하세요.
원래는 SimpleServlet 구현체에서 가상호스트에 등록된 html을 불러와 thymeleaf처럼 어떤 특정 매핑 값을 대체하여 현재시각 불러오도록 처리하고 싶었지만 이부분에서도 결국 합의점을 찾지 못하여 body 값을 String으로 구성하여 출력하는 방법으로 밖에 처리하지 못하였습니다.

#### 8. 앞에서 구현한 여러 스펙을 검증하는 테스트 케이스를 JUnit4 를 이용해서 작성하세요.
적절한 html을 테스트하기 위해서 ServerSocket 테스트 코드 상에서 실행되어야 하는데 각각의 테스트안에서 포트 충돌이 발생하여 해당 원인을 찾지 못하여 
부분적으로만 테스트 코드를 처리하였습니다.
구현하지 못하였습니다.

#### 사용 라이브러리 및 빌드도구
+ junit
+ com.google.code.gson
+ org.slf4j
+ ch.qos.logback
+ Maven
