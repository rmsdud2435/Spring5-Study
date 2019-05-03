package com.myproject.spring5.util.sftp;

public class SFtpContext {
	
	private String 	serverIP = "";
	private int		port;
	private String 	userId = "";
	private String	password = "";
	
	public String getServerIP() {
		return serverIP;
	}
	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "FtpContext [serverIP=" + serverIP + ", port=" + port + ", userId=" + userId + ", password=" + password
				+ "]";
	}
	
}
