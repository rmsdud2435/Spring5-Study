package com.myproject.spring5.mvc.validation;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidationController {

	// enforcement of constraints on the JavaBean arg require a JSR-303 provider on the classpath
	
	@GetMapping(value = "/validate", produces = "text/plain;charset=UTF-8")
	public String validate(@Valid JavaBean bean, BindingResult result) {

		if (result.hasErrors()) {
			return result.getFieldError().getDefaultMessage();
		} else {
			return "No errors";
		}
	}

}
