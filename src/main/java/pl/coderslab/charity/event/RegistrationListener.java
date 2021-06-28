package pl.coderslab.charity.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.EmailService;
import pl.coderslab.charity.service.UserService;

import javax.mail.MessagingException;
import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;


    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        try {
            this.confirmRegistration(event);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) throws MessagingException {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);
        emailService.sendMessage(user.getEmail()
                , "Registration Confirmation"
                , "http://localhost:8080" + event.getAppUrl() + "/registration/confirm?token=" + token
                , null
        );
    }
}
