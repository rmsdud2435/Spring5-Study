package com.myproject.spring5.accessmodifier;

import java.util.Calendar;

public class PublicClass {
	public 	String publicString 	= "public";	

	public String print(){
		return publicString;
	}

	public void setPublicString(String publicString) {
		Calendar calendar = Calendar.getInstance();

		this.publicString = publicString + calendar.getTime();
		
	}
}
