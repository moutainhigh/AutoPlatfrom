package com.gs.thread;

import com.gs.bean.info.SendRemind;
import com.gs.common.Constants;
import com.gs.email.Mail;
import com.gs.email.MailSender;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017-05-04. 发送邮件的线程
 */
public class SendEmailThread implements Runnable {

    private List<Mail> mails;

    public SendEmailThread(List<Mail> mails) {
        this.mails = mails;
    }

    @Override
    public void run() {
        for (Mail mail : mails) {
            MailSender mailSender = new MailSender();
            mailSender.sendEmailByType(Constants.MAIL_TYPE, mail, Constants.MAIL_SENDER, Constants.MAIL_PASSWORD);
        }

    }
}
