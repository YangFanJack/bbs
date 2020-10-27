package com.jack.service;

public interface MailSenderSrvService {
    void sendEmail(String recipient,String subject,String content);
}
