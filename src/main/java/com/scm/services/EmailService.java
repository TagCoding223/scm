package com.scm.services;

import java.util.Map;

import jakarta.mail.MessagingException;

public interface EmailService {

    void sendEmail(String to, String subject, String body);

    void sendEmailWithTemplate(String to, String subject, Map<String, Object> variables) throws MessagingException;

}
