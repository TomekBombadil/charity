package pl.coderslab.charity.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.EmailService;
import pl.coderslab.charity.service.UserService;

import javax.mail.MessagingException;
import java.util.UUID;

@Component
public class PasswordChangeListener {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @EventListener
    public void listenPasswordChangeEvent(OnPasswordChangeEvent event) throws MessagingException {
        this.sendPasswordChangeLink(event);
    }

    private void sendPasswordChangeLink(OnPasswordChangeEvent event) throws MessagingException {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);
        emailService.sendMessage(user.getEmail()
                ,"Change password"
                ,"http://localhost:8080" + event.getAppUrl() + "/password/change?token=" + token
                ,null);
    }
}
