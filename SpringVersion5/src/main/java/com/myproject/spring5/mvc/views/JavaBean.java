package com.myproject.spring5.mvc.views;

import javax.validation.constraints.NotNull;

public class JavaBean {
	
	@NotNull
	private String name;

	@NotNull
	private String birth;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

}
