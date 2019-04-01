package com.gs.email;

import java.io.File;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * 发送邮件的类
 * @author Administrator
 *
 */
public class MailSender {
	
	/**
	 * 自定义配置文件发送邮件
	 * @param file 配置文件
	 * @param mail 邮件类
	 */
	@SuppressWarnings("static-access")
	public void sendEmail(File file, com.gs.email.Mail mail) {
		// 读取配置文件
		Properties properties = com.gs.email.MailConfig.readProperties(file);
		// 根据读取到的配置文件获取到一个会话
		Session session = Session.getInstance(properties, new com.gs.email.MailAuth(com.gs.email.MailConfig.getString("userEmail"), com.gs.email.MailConfig.getString("password")));
		try {
			// 根据会话获取到一个邮件传输对象
			Transport transport = session.getTransport();
			// 连接邮件服务器
			transport.connect();
			// 根据Session创建一封邮件
			Message msg = new MimeMessage(session);
			// 设置邮件发送者，读取到配置文件中的发送人
			mail.setFrom(com.gs.email.MailConfig.getString("userEmail"));
			msg.setFrom(mail.getFrom());
			// 设置邮件主题
			msg.setSubject(mail.getSubject());
			// 设置邮件内容
			if (mail.getContent() != null) {
				// 如果内容不为空，则设置内容
				msg.setContent(mail.getContent(), mail.getType());
			} else {
				// 否则就设置附件
				msg.setContent(mail.getMultipart());
			}
			// 设置邮件的接收者
			msg.setRecipients(RecipientType.TO, mail.getRecipients());
			// 判断抄送人是否为空
			if (mail.getCcRecipients() != null) {
				msg.setRecipients(RecipientType.CC, mail.getCcRecipients());
			}
			// 判断密送人是否为空
			if (mail.getBccRecipients() != null) {
				msg.setRecipients(RecipientType.BCC, mail.getBccRecipients());
			}
			// 发送邮件
			transport.send(msg);
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据邮件类型发送邮件
	 * @param type 邮件类型，支持QQ邮件，126邮件，163邮件
	 * @param mail 邮件对象
	 * @param userEmail 邮箱地址
	 * @param password 授权码
	 */
	@SuppressWarnings("static-access")
	public void sendEmailByType(String type, com.gs.email.Mail mail, String userEmail, String password) {
		// 读取配置文件
		Properties properties = com.gs.email.MailConfig.readProperties(type, userEmail, password);
		// 根据读取到的配置文件获取到一个会话
		Session session = Session.getInstance(properties, new MailAuth(com.gs.email.MailConfig.getString("userEmail"), com.gs.email.MailConfig.getString("password")));
		try {
			// 根据会话获取到一个邮件传输对象
			Transport transport = session.getTransport();
			// 连接邮件服务器
			transport.connect();
			// 根据Session创建一封邮件
			Message msg = new MimeMessage(session);
			// 设置邮件发送者，读取到配置文件中的发送人
			mail.setFrom(MailConfig.getString("userEmail"));
			msg.setFrom(mail.getFrom());
			// 设置邮件主题
			msg.setSubject(mail.getSubject());
			// 设置邮件内容
			if (mail.getContent() != null) {
				// 如果内容不为空，则设置内容
				msg.setContent(mail.getContent(), mail.getType());
			} else {
				// 否则就设置附件
				msg.setContent(mail.getMultipart());
			}
			// 设置邮件的接收者
			msg.setRecipients(RecipientType.TO, mail.getRecipients());
			// 判断抄送人是否为空
			if (mail.getCcRecipients() != null) {
				msg.setRecipients(RecipientType.CC, mail.getCcRecipients());
			}
			// 判断密送人是否为空
			if (mail.getBccRecipients() != null) {
				msg.setRecipients(RecipientType.BCC, mail.getBccRecipients());
			}
			// 发送邮件
			transport.send(msg);
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
