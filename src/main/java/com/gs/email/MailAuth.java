package com.gs.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 邮箱认证的类
 * @author Administrator
 *
 */
public class MailAuth extends Authenticator {

	/**
	 * 用户的邮箱
	 */
	private String userEmail;
	/**
	 * 用户的密码，如果是QQ邮箱，则这个是QQ邮箱生成的授权码
	 */
	private String password;
	
	public MailAuth(String userEmail, String password) {
		this.userEmail = userEmail;
		this.password = password;
	}
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userEmail, password);
	}
	
	
}
