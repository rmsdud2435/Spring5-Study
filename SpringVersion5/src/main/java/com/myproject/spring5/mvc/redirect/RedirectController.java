package com.myproject.spring5.mvc.redirect;

import javax.inject.Inject;

import org.joda.time.LocalDate;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

//리다이렉트는 일반적으로 사용자의 조작을 거치지 않고 서버 내에서 컨트롤러끼리 값을 주고받으며 2개 이상을 태우고 싶을때 사용된다. 
@Controller
@RequestMapping("/redirect")
public class RedirectController {
	
	//Java에서 제공하는 Calendar, Date를 사용하지 않고 org.joda.time.LocalDate를 추가해서 사용하는 이유는 https://d2.naver.com/helloworld/645609의 내용참고
	private final ConversionService conversionService;

	/*
	 * @Inject의 유무
	 * 
	 * 만약 다른 클래스에서 RedirectController redirectController = new RedirectController()라고 호출을 하는것이라면 
	 * @Inject가 있고 없고는 크게 상관이 없다.
	 * 하지만 스프링 프로젝트가 실행이 되면 web.xml에서 부터 읽어들어가면서 @Controller등이 Bean 객체로 등록이 될텐데 그때 Inject이 없다면 conversionService변수는
	 * null이 된다.
	 * 
	 */
	@Inject
	public RedirectController(ConversionService conversionService) {
		this.conversionService = conversionService;
	}

	// RedirectAttributes는 addAttribute하더라도 return시에 {}로 사용되면 빼고 전달한다.
	@GetMapping("/uriTemplate")
	public String uriTemplate(RedirectAttributes redirectAttrs) {
		redirectAttrs.addAttribute("account", "김근영");  					// Used as URI template variable
		redirectAttrs.addAttribute("date", new LocalDate());  	// Appended as a query parameter
		return "redirect:/redirect/{account}";
	}

	//UriComponentsBuilder은 url자체를 만들어서 보내는 방식으로 fromPath를 통해 경로를 지정하고 queryParam을 통해 변수를 지정한다. 개인적으론 RedirectAttributes가 더 난듯 
	@GetMapping("/uriComponentsBuilder")
	public String uriComponentsBuilder() {
		String date = this.conversionService.convert(new LocalDate(), String.class);
		UriComponents redirectUri = UriComponentsBuilder.fromPath("/redirect/{account}").queryParam("date", date).build().expand("김근영").encode();
		return "redirect:" + redirectUri.toUriString();
	}

	//@PathVariable는 Rest형식의 변수받는 용도, @RequestParam은 get방식의 변수받는 용도
	@GetMapping("/{account}")
	public String show(@PathVariable String account, @RequestParam(required=false) LocalDate date) {
		return "redirect/redirectResults";
	}

}
