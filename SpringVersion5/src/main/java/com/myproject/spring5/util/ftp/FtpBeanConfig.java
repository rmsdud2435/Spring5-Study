package com.myproject.spring5.util.ftp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FtpBeanConfig {

	@Bean
	public FtpService ftpService(){
		return new FtpService();
	}
}
