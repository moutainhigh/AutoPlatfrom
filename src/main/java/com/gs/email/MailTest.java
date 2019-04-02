package com.gs.email;

import java.io.File;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

/**
 * 发送邮件测试类
 * @author Administrator
 *
 */
public class MailTest {

	public static void main(String[] args) {
//		MailSender mailSender = new MailSender();
//		Mail mail = new Mail();
//		mail.setRecipients("2364551314@qq.com,1945036941@qq.com");
//		mail.setSubject("邮件的主题");
//		mail.setType(Mail.TEXT);
//		Multipart multipart = new MimeMultipart();
//		BodyPart part1 = new MimeBodyPart();
//		try {
//			part1.setContent("我发送了一封邮件给你，请注意查收！！！", mail.getType());
//			multipart.addBodyPart(part1);
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}
//		mail.setMultipart(multipart);
//		mailSender.sendEmail(new File("src/com/jh/email/mail.properties"), mail);
		
		// 使用默认的配置文件
		com.gs.email.MailSender mailSender = new com.gs.email.MailSender();
		com.gs.email.Mail mail = new com.gs.email.Mail();
		mail.setRecipients("gzqm@126.com");
		mail.setSubject("邮件的主题");
		mail.setType(Mail.HTML);
		Multipart multipart = new MimeMultipart();
		BodyPart part1 = new MimeBodyPart();
		try {
			part1.setContent("<p>我发送了一封邮件给你，请注意查收！！！</p><a href='http://www.baidu.com'>百度</a>", mail.getType());
			multipart.addBodyPart(part1);
			BodyPart part2 = new MimeBodyPart();
			DataHandler dataHandler = new DataHandler(new FileDataSource(new File("src/360wallpaper.jpg")));
			part2.setDataHandler(dataHandler);
			multipart.addBodyPart(part2);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		mail.setMultipart(multipart);
		mailSender.sendEmailByType("163", mail, "gzqm@163.com", "gzqm163");
		
	}
}
