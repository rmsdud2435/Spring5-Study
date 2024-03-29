package com.myproject.spring5.mvc.form;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myproject.spring5.util.extentions.ajax.AjaxUtils;

@Controller
@RequestMapping("/form")
@SessionAttributes("formBean") //@ModelAttribute나  model.addAttribute()를 사용해서 객체를 저장할 경우 세션에 저장한다. 이후 세션에서 호출하여 사용가능하다.
public class FormController {

	/*
	 * @ModelAttribute의 기능
	 * - 웹페이지에서 넘어오는 데이터들을 데이터 전달객체(Bean 객체)로 자동으로 묶음(변수명과 데이터key값(name)매칭)
	 * - 넘어온 데이터를 다시 view페이지로 넘김(옵션을 통해 객체이름 변경가능)
	 * - @RequestMapping로 호출받지 않아도, @RequestMapping호출 받은 메소드와 같은 클래스 내부에 있다면 @RequestMapping가 return될때 @ModelAttribute가 붙은 객체를 같이 가져감.
	 */
	@ModelAttribute
	public void ajaxAttribute(WebRequest request, Model model) {
		model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
	}

	@ModelAttribute("formBean")
	public FormBean createFormBean() {
		return new FormBean();
	}
	
	@GetMapping
	public void form() {
		System.out.println("!!!!!");
	}

	@PostMapping
	public String processSubmit(@Valid FormBean formBean, BindingResult result, @ModelAttribute("ajaxRequest") boolean ajaxRequest, Model model, RedirectAttributes redirectAttrs) {
		if (result.hasErrors()) {
			return null;
		}
		// Typically you would save to a db and clear the "form" attribute from the session 
		// via SessionStatus.setCompleted(). For the demo we leave it in the session.
		String message = "Form submitted successfully.  Bound " + formBean;
		// Success response handling
		if (ajaxRequest) {
			// prepare model for rendering success message in this request
			model.addAttribute("message", message);
			return null;
		} else {
			// store a success message for rendering on the next request after redirect
			// redirect back to the form to render the success message along with newly bound values
			redirectAttrs.addFlashAttribute("message", message);
			return "redirect:/form";			
		}
	}
	
}
