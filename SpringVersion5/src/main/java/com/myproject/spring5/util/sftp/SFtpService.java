package com.myproject.spring5.util.sftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SFtpService {

	static final Logger logger = LogManager.getLogger(SFtpService.class);
	
	private JSch 		jsch;
	private Session 	session;
	private Channel 	channel;
	private ChannelSftp channelSftp;
	
	public SFtpService(){
		logger.info("SFtpService is autowired");
	}
	
	
	public void setContext(SFtpContext sftpContext) throws JSchException{
		
		String 	serverIP 	= sftpContext.getServerIP();
		String 	userId 		= sftpContext.getUserId();
		int		port 		= sftpContext.getPort();
		String	password	= sftpContext.getPassword();
		
		try {
			jsch = new JSch();
			session = jsch.getSession(userId, serverIP, port);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.setPassword(password);
	        session.connect();
	
	        channel = session.openChannel("sftp");
	        channel.connect();
	        channelSftp = (ChannelSftp) channel;
		} catch (JSchException e) {
			logger.error("아래의 사유로 연결에 실패하였습니다.원인 >>>>> {}",e.getMessage());
			throw new JSchException(e.getMessage());	//추후 프로젝트만의 exceptiom으로 변경
		}     
	}
	
    /**
     * @param filePath 업로드 할 파일 위치
     * @param fileName 업로드 할 파일 명
     * @param uploadPath 업로드 될 위치
     * @throws SftpException 
     * @throws FileNotFoundException 
     */
	public void uploadFile(String filePath, String fileName, String uploadPath) throws SftpException, FileNotFoundException{

        FileInputStream in = null;
        
        try{ 
            in = new FileInputStream(filePath + fileName);
            channelSftp.cd(uploadPath);
            channelSftp.put(in,fileName);
        }catch(SftpException | FileNotFoundException e){
        	logger.error("아래의 사유로 업로드에 실패하였습니다. 원인 >>>>> {}",e.getMessage());
        	throw new SftpException(1, e.getMessage());	//추후 프로젝트만의 exception으로 변경
        }finally{
            try{
                in.close();
            } catch(IOException e){
                logger.error("연결을 닫는데 실패하였습니다. 원인 >>>>> {}",e.getMessage());
            }
        }
	}
	
    /**
     * @param filePath 다운로드 할 파일 위치
     * @param fileName 다운로드 할 파일 명
     * @param downloadPath 다운로드 될 위치
     * @throws SftpException 
     * @throws FileNotFoundException 
     */
	public void downloadFile(String filePath, String fileName, String downloadPath) throws SftpException{

		InputStream  in = null;
        
        try{ 
            channelSftp.cd(filePath);
            in = channelSftp.get(fileName);
            
            File file = new File(downloadPath + fileName);
            FileUtils.copyInputStreamToFile(in, file);
        }catch(SftpException | IOException e){
        	logger.error("아래의 사유로 다운로드에 실패하였습니다. 원인 >>>>> {}",e.getMessage());
        	throw new SftpException(1, e.getMessage());	 //추후 프로젝트만의 exception으로 변경
        }finally{
            try{
                in.close();
            } catch(IOException e){
                logger.error("연결을 닫는데 실패하였습니다. 원인 >>>>> {}",e.getMessage());
            }
        }
	}
	
	public void disconnect(){
		channelSftp.quit();
	    session.disconnect();
	    logger.info("SFtpService is successfuly ended");
	}
}
