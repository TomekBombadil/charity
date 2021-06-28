package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.charity.service.EmailService;

import javax.mail.MessagingException;

@Controller
public class EmailTestController {

    private EmailService emailService;

    public EmailTestController(EmailService emailService) {
        this.emailService = emailService;
    }

    @RequestMapping(value = "/email", method = RequestMethod.GET)
    @ResponseBody
    public void sendMessage() throws MessagingException {
        emailService.sendMessage("duce71@gmail.com", "test", "Jakiś tekst", null);
    }
}
