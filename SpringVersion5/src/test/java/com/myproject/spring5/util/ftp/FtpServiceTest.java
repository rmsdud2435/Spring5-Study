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
		ftpContext.setServerIP("10.46.49.31");
		ftpContext.setPort(22);
		ftpContext.setUserId("adtkorea.com₩kr_erp_ftp");
		ftpContext.setPassword("!att$1adm");
		

			ftpService.setContext(ftpContext);

	}
}
