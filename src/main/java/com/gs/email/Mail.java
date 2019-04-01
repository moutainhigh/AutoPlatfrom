package com.gs.email;

import javax.mail.Address;
import javax.mail.Multipart;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * 邮件信息类，配置邮件的一些信息
 * 
 * @author Administrator
 *
 */
public class Mail {

	public static final String HTML = "text/html;charset=utf-8"; // 邮件类型是HTML类型
	public static final String TEXT = "text/plain;charset=utf-8"; // 邮件类型是普通的文本类型

	private String from; // 发送人
	private String recipients; // 接收人
	private String ccRecipients; // 抄送人
	private String bccRecipients; // 密送人
	private String subject; // 邮件主题
	private String content; // 邮件内容
	private String type; // 邮件类型
	private Multipart multipart; // 附件

	public Address getFrom() {
		if (from != null) {
			try {
				return InternetAddress.parse(from)[0];
			} catch (AddressException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public Address[] getRecipients() {
		if (recipients != null) {
			try {
				return InternetAddress.parse(recipients);
			} catch (AddressException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}

	public Address[] getCcRecipients() {
		if (ccRecipients != null) {
			try {
				return InternetAddress.parse(ccRecipients);
			} catch (AddressException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void setCcRecipients(String ccRecipients) {
		this.ccRecipients = ccRecipients;
	}

	public Address[] getBccRecipients() {
		if (bccRecipients != null) {
			try {
				return InternetAddress.parse(bccRecipients);
			} catch (AddressException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void setBccRecipients(String bccRecipients) {
		this.bccRecipients = bccRecipients;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Multipart getMultipart() {
		return multipart;
	}

	public void setMultipart(Multipart multipart) {
		this.multipart = multipart;
	}

}
