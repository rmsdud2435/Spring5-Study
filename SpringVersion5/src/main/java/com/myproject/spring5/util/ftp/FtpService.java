package com.myproject.spring5.util.ftp;

import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FtpService {

	static final Logger logger = LogManager.getLogger(FtpService.class);
	
	public FtpService(){
		logger.info("FtpService is autowired");
	}
	
	
	public FTPClient setContext(FtpContext ftpContext){
		
		String 	serverIP = ftpContext.getServerIP();
		int		port = ftpContext.getPort();
		String 	userId = ftpContext.getUserId();
		String	password = ftpContext.getPassword();
		
		FTPClient 	ftpClient 	= new FTPClient();			
		int 		reply;
		
		try{
			ftpClient.connect(serverIP, port);
			
			reply = ftpClient.getReplyCode();	
			if(!FTPReply.isPositiveCompletion(reply)){
				ftpClient.disconnect();
				logger.error("Fail to connect! Error Code = " + reply);
				throw new Exception();
			}

			if(!ftpClient.login(userId, password)){
				ftpClient.logout();
				logger.error("Fail to login!");
				throw new Exception();
			}
			
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.enterLocalActiveMode();
			
			logger.info("ftp accessed successfully....");
		}catch(Exception e){
			logger.error("Error occured while setting ftp context....");
		} finally {
			if(ftpClient!=null && ftpClient.isConnected()){
				try{ 
					ftpClient.disconnect(); 
				} 
				catch(IOException e){
					logger.error("Fail to disconnect!");
				}
			}
		}
		return ftpClient;
	}
	
}
