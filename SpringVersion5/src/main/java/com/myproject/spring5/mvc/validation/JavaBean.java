package com.myproject.spring5.mvc.validation;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class JavaBean {
	
	@NotNull(message = "숫자는 필수 값입니다.")
	@Max(value = 5, message= "숫자는 5이하의 값이여야 합니다.")
	private Integer number;

	@NotNull(message = "날짜는 필수 값입니다.")
	@Future(message = "날짜는 오늘 이후의 값이여야 합니다.")
	@DateTimeFormat(iso=ISO.DATE)
	private Date date;

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
