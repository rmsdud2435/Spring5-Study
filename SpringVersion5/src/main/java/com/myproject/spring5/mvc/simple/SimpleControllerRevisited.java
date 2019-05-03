package com.myproject.spring5.mvc.simple;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleControllerRevisited {

	//헤더에 이것저것 세팅해서 보내고 싶을때, 인코딩깨질때나 값을 제대로 못받을때 아래와 같이 세팅해야한다.
	@GetMapping(path="/simple/revisited", produces="text/plain;charset=UTF-8", headers="Accept=text/plain")
	public String simple() {
		return "헤더값 text/plain만 Accept하겠습니다.";
	}

}
