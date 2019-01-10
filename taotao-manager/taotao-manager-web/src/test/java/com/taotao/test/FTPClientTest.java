package com.taotao.test;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import com.taotao.utils.FtpUtil;

public class FTPClientTest {
	@Test
	public void testFtpUtilDown() throws Exception {
		//FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\ZHANG\\Pictures\\Saved Pictures\\amei.jpg"));
		//FtpUtil.uploadFile("192.168.194.133", 21, "ftpuser", "root", "/home/ftpuser/www/", "2010-1-9", "fir2st.jpg", inputStream);
		FtpUtil.downloadFile("192.168.194.133", 21, "ftpuser", "root", "/home/ftpuser/www/", "xiaolv.jpg", "d:\\");
	}
	
	@Test
	public void testFtpUtilUp() throws Exception {
		FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\ZHANG\\Pictures\\Saved Pictures\\amei.jpg"));
		FtpUtil.uploadFile("192.168.194.133", 21, "ftpuser", "root", "/home/ftpuser/www/", "2010-1-9", "fir3st.jpg", inputStream);
	}
	
	@Test
	public void testFtp() throws Exception {
		// 1、连接ftp服务器
		FTPClient ftpClient = new FTPClient();
		ftpClient.connect("192.168.194.133", 21);
		// 2、登录ftp服务器
		ftpClient.login("ftpuser", "root");
		// 3、读取本地文件
		FileInputStream inputStream = new FileInputStream(
				new File("D:\\wo.txt"));
		// 4、上传文件
		ftpClient.enterLocalPassiveMode();
		// 1）指定上传目录
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/");
		// 2）指定文件类型
		ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
		// 第一个参数：文件在远程服务器的名称
		// 第二个参数：文件流
		ftpClient.storeFile("hello.txt", inputStream);
		// 5、退出登录
		ftpClient.logout();
	}
}
