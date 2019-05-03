package com.myproject.spring5.mvc.simple;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

	//간단한 값 보내기
	@GetMapping("/simple")
	public String simple() {
		return "Hello world!";
	}

}
