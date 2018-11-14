package com.noc.security.core.validate.code.sms;

/**
 * 短信验证码发送默认实现，可覆盖
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机"+mobile+"发送短信验证码"+code);
    }

}
