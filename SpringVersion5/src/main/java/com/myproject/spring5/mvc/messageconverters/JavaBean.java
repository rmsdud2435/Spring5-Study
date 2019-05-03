package com.myproject.spring5.mvc.messageconverters;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JavaBean {
	
	@NotNull
	private String name;

	@NotNull
	private String birth;

	public JavaBean() {
	}

	public JavaBean(String name, String birth) {
		this.name = name;
		this.birth = birth;
	}

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
	
	@Override
	public String toString() {
		return "JavaBean {name=[" + name + "], birth=[" + birth + "]}";
	}

}
