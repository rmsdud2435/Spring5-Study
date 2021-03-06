package com.myproject.spring5.util.sftp;

import java.io.FileNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SftpBeanConfig.class)
public class SFtpServiceTest {

	static final Logger logger = LogManager.getLogger(SFtpServiceTest.class);
	
	@Autowired
	private SFtpService sFtpService;
	
	@Test
	public void testFtpConnetion(){

		SFtpContext sftpContext = new SFtpContext();
		sftpContext.setServerIP("10.46.54.14");
		sftpContext.setPort(22);
		sftpContext.setUserId("wasadmin");
		sftpContext.setPassword("!adt0305");
				
		try {
			sFtpService.setContext(sftpContext);
			sFtpService.uploadFile("C:/Users/gykim/Desktop/백업한 데이터/", "제거된 앱.html", "/jeus/jeus8/logs");
			sFtpService.downloadFile("/jeus/jeus8/logs", "제거된 앱.html", "C:/Users/gykim/Desktop/백업한 데이터/");
		} catch (JSchException e) {
			logger.error("SFTP 연결에 실패하였습니다.");
		} catch (FileNotFoundException | SftpException e) {
			logger.error("SFTP 업로드에 실패하였습니다.");
		}
	}
}
