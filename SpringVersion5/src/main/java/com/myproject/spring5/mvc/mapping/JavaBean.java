package com.myproject.spring5.mvc.mapping;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JavaBean {

	private String name = "김근영";

	private String birth = "19931119";

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
