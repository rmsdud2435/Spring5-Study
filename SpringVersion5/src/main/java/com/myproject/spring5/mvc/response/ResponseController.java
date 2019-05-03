package com.myproject.spring5.mvc.response;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/response", method=RequestMethod.GET)
public class ResponseController {

	//단순 값 return하기
	@GetMapping("/annotation")
	public String responseBody() {
		return "The String ResponseBody";
	}

	//JSP에서 인코딩세팅을 완료하여 보내면 받기만 하면 된다.
	@GetMapping("/charset/accept")
	public String responseAcceptHeaderCharset() {
		return "JSP에서 인코딩하였습니다.";
	}

	//Controller에서 인코딩세팅을 하여 받고 보낼 수 있다.
	@GetMapping(value="/charset/produce", produces="text/plain;charset=UTF-8")
	public String responseProducesConditionCharset() {
		return "Controller에서 인코딩하였습니다.";
	}

	//ResponseEntity를 이용하여 에러 던져주기
	/* HTTP 상태값 
	 * 1XX  작업을 진행 중이라는 의미. HTTP/1.0 이후 사용x
	 * 100  Continue
	 * 101  Switching Protocols
	 * 102  Processing
	 * 103  Checkpoint
	 * 
	 * 2XX  작업을 성공적으로 받았고,이해했으며,받아들여졌다는 의미. 200과 206을 제외하고는 볼 일이 거의 없는 코드   
	 * 200  OK
	 * 201     요청이 성공적으로 처리되어서 리소스가 만들어졌음을 의미
	 * 202     요청이 받아들여졌지만 처리되지 않았음을 의미
	 * 203  Non-Authoritative Information
	 * 204     성공적으로 처리했지만 컨텐츠를 제공 x 
	 * 205     서버가 요청을 성공적으로 처리했지만 콘텐츠를 표시X
	 * 206     서버가 GET 요청의 일부만 성공적으로 처리
	 * 
	 * 3XX  리다이렉션 완료. 클라이언트는 요청을 마치기 위해 추가 동작
	 * 다 많이 보지 못한 코드라 패스~
	 * 
	 * 4XX  클라이언트 요청 오류.
	 * 
	 * 400     서버가 요청의 구문을 인식X
	 * 401     권한없음 
	 * 402     결제필요
	 * 403     금지됨
	 * 404     찾을 수 없음
	 * 405     허용되지 않음(ex. put인데 get으로 요청
	 * 406     요청한 페이지가 여청한 콘텐프 특성으로 응답 할 수 없다.
	 * 407     프록시 인증 필요
	 * 408     요청처리 실패
	 * 409     충돌 
	 * 
	 * ...이하 생략 너무 많음(https://ko.wikipedia.org/wiki/HTTP_%EC%83%81%ED%83%9C_%EC%BD%94%EB%93%9C 참고하자)
	 * 
	 * 5XX 서버 오류. 서버가 유효한 요청을 명백하게 수행하지 못했음을 나타낸다
	 * 
	 * 500  서버애 오류가 발생하여 요청을 수행할 수 없다.
	 * 501  구현되지 않음
	 * 502 게이트웨이나 프록시 역할을 하고 있는 서버에서 잘못된 응답이 왔음
	 * 503  서버가 오버로드되었거나 유지관리를 위해 다운되어 서버를 이용할 수 없다.
	 * 504  게이트웨이 시간 초과
	 * 505 HTTP프로토콜 지원X
	 * 
	 */
	@GetMapping(value="/entity/status", produces="text/plain;charset=UTF-8")
	public ResponseEntity<String> responseEntityStatusCode() {
		return new ResponseEntity<String>("해당 페이지를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/entity/headers")
	public ResponseEntity<String> responseEntityCustomHeaders() {
		HttpHeaders headers 	= new HttpHeaders();
		Charset 	utf8 		= Charset.forName("UTF-8");
		MediaType 	mediaType 	= new MediaType("text", "plain", utf8);
		headers.setContentType(mediaType);
		
		return new ResponseEntity<String>("Content-Type=text/plain의 값으로 내려주겠습니다.",headers, HttpStatus.OK);
	}

}
