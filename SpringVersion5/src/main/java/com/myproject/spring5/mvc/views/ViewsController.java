package com.myproject.spring5.mvc.views;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/views/*")
public class ViewsController {

	//return을 통해 페이지 매핑하는 방식
	@GetMapping("html")
	public String prepare(Model model) {
		model.addAttribute("name", "gykim");
		model.addAttribute("birth", "19931119");
		return "views/html";
	}
	
	//만약 리턴을 하지 않는다면 컨텍스트 루트 하위 경로와 같은 값을 매핑 시킨다. ex) 172.0.0.1:8080/spring5/views/viewName -> WEB-INF/views/viewName.jsp에 매핑
	@GetMapping("/viewName")
	public void usingRequestToViewNameTranslator(Model model) {
		model.addAttribute("name", "gykim");
		model.addAttribute("birth", "19931119");
	}

	//일반적인 Restful 방식을 이용한 방식
	@GetMapping("pathVariables/{name}/{birth}")
	public String pathVars(@PathVariable String name, @PathVariable String birth) {

		return "views/html";
	}

	//javax.validation 라이브러리를 이용한 방식. JavaBean클래스를 보면 @NotNull 등 어노이태이션을 통해 값들을 체크 할 수 있다.
	/*
	 * 어노테이션 규칙을 안지키거나 return JSP페이지에서 다른 변수 호출 시
	 * 심각: Servlet.service() for servlet jsp threw exception
	 * javax.el.PropertyNotFoundException: Property 'foo' not found on type com.myproject.spring5.mvc.views.JavaBean
	 * 와 같이 에러가 난다.
	 */	
	@GetMapping("dataBinding/{name}/{birth}")
	public String dataBinding(@Valid JavaBean javaBean, Model model) {
		
		return "views/dataBinding";
	}

}
