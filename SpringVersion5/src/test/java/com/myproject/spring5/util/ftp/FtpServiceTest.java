package com.myproject.spring5.util.ftp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "/test-applicationContext.xml")
@ContextConfiguration(classes = FtpBeanConfig.class)
public class FtpServiceTest {

	@Autowired
	private FtpService ftpService;
	
	@Test
	public void testFtpConnetion(){

		FtpContext ftpContext = new FtpContext();
		ftpContext.setServerIP("10.46.54.14");
		ftpContext.setPort(22);
		ftpContext.setUserId("wasadmin");
		ftpContext.setPassword("!adt0305");
		
		ftpService.setContext(ftpContext);
	}
}
