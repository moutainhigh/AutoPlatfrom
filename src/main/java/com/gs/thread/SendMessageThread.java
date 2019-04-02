package com.gs.thread;

import com.gs.common.message.IndustrySMS;

import java.util.List;

/**
 *
 * @author Administrator
 * @date 2017-05-19
 */
public class SendMessageThread implements Runnable  {
    /**
     * 要发送的手机号‘，’号隔开
     */
    private String phone;
    /**
     * 短信的内容
     */
    private String content;

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
