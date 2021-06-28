package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.File;

@Component
//@ConfigurationProperties(prefix = "spring.mail.custom-properties")
//@Validated
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;
    @NotBlank
    private String messageFrom = "Charity Donation <no_reply@charitydonation.com>";//nie działała mi konfiguracja tego w pliku

    public void setMessageFrom() {
        this.messageFrom = messageFrom;
    }

    public void sendMessage(String to, String subject, String text, String pathToAttachment) throws MessagingException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(messageFrom);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text);

        if (pathToAttachment != null) {
            FileSystemResource fileSystemResource = new FileSystemResource(new File(pathToAttachment));
            mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
        }
        emailSender.send(mimeMessage);

    }
}
