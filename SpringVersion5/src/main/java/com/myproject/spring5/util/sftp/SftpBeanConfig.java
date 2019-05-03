package com.myproject.spring5.util.sftp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SftpBeanConfig {

	@Bean
	public SFtpService sFtpService(){
		return new SFtpService();
	}
}
