package pl.coderslab.charity.service;

import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public interface EmailService {

    void sendMessage(String to, String subject, String text, String pathToAttachment) throws MessagingException;

}
