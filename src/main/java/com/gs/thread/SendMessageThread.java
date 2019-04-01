package com.gs.thread;

import com.gs.common.message.IndustrySMS;

import java.util.List;

/**
 * Created by Administrator on 2017-05-19. 发送短信的线程
 */
public class SendMessageThread implements Runnable  {

    private String phone; // 要发送的手机号‘，’号隔开

    private String content; // 短信的内容

    public SendMessageThread(String phone, String content) {
        this.phone = phone;
        this.content = content;
    }

    @Override
    public void run() {
        String to = phone;
        String smsContent = content;
        IndustrySMS is = new IndustrySMS(to, smsContent);
        is.execute();
    }
}
