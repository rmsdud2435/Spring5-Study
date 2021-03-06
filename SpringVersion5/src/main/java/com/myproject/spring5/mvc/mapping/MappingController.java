package com.myproject.spring5.mvc.mapping;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller는 페이지 이동시에 사용, @RestController는 데이터 받을 떄 사용
@RestController
public class MappingController {

	@GetMapping(value = "/mapping/path", produces="text/plain;charset=UTF-8")
	public String byPath() {
		return "String 값을 주겠습니다.";
	}

	@GetMapping(value = "/mapping/path/*", produces="text/plain;charset=UTF-8")
	public String byPathPattern(HttpServletRequest request) {
		return "URL ('" + request.getRequestURI() + "')에 대해 매핑 되었습니다.";
	}

	/*
	 * 해당 url에 params에 들어온 값이 해당값으로 들어오면 여기로 매핑. 
	 * 매우 위험한 방법인듯.
	 * 예를 들어 params="apple"과 params="!banana이 있는데 apple로 들어오면 둘다 만족을 하여 에러가 난다."
	 */
	@GetMapping(path="/mapping/parameter", params="apple", produces="text/plain;charset=UTF-8")
	public String byParameter() {
		return "params의 값이 apple과 매핑되었습니다.";
	}

	@GetMapping(path="/mapping/parameter", params="!apple", produces="text/plain;charset=UTF-8")
	public String byParameterNegation() {
		return "params의 값이 apple과 매핑되지 않았습니다.";
	}

	/*
	 * 해당 url에 헤더로 아래의 값이 들어오면 여기로 매핑. 
	 * 매우 위험한 방법인듯.
	 * headers="SecretHeader"처럼 굳이 =을 통해여 값을 줄 필요없음
	 * 예를 들어 headers="SecretHeader=apple"과 headers="SecretHeader=!banana"이 있는데 SecretHeader=apple로 들어오면 둘다 만족을 하여 에러가 난다."
	 */
	@GetMapping(path="/mapping/header", headers="SecretHeader=apple", produces="text/plain;charset=UTF-8")
	public String byHeader() {
		return "헤더에 SecretHeader값이 존재하여 여기로 들어왔습니다.";
	}

	@GetMapping(path="/mapping/header", headers="!SecretHeader", produces="text/plain;charset=UTF-8")
	public String byHeaderNegation() {
		return "헤더에 SecretHeader값이 존재하지 않아 여기로 들어왔습니다.";
	}

	@PostMapping(path="/mapping/consumes", consumes=MediaType.APPLICATION_JSON_VALUE, produces="text/json;charset=UTF-8")
	public String byConsumes(@RequestBody JavaBean javaBean) {
		return "JavaBean의 데이터는 '" + javaBean + "')";
	}

	@GetMapping(path="/mapping/produces", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public JavaBean byProducesJson() {
		return new JavaBean();
	}

	@GetMapping(path="/mapping/produces", produces=MediaType.APPLICATION_XML_VALUE)
	public JavaBean byProducesXml() {
		return new JavaBean();
	}

}
