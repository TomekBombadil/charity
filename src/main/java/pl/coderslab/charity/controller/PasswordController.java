package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.PasswordForm;
import pl.coderslab.charity.entity.RegistrationForm;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.entity.VerificationToken;
import pl.coderslab.charity.error.UserNotExistException;
import pl.coderslab.charity.event.OnPasswordChangeEvent;
import pl.coderslab.charity.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping(value = "/password", produces = "text/html; charset=utf8")
public class PasswordController {

    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/email")
    public String showEmailForm() {
        return "passwordEmail";
    }

    @PostMapping(value ="/email")
    public String processEmailForm(@RequestParam("email") String email, HttpServletRequest request){
        if(!userService.emailExist(email)) {
            throw new UserNotExistException("Email not exists: " + email + "!");
        }
        eventPublisher.publishEvent(new OnPasswordChangeEvent(this, userService.getUserByEmail(email)
                , request.getContextPath()));
        return "passwordEmailSent";
    }

    @GetMapping(value="/change")
    public String showPasswordChangeForm(@RequestParam("token") String token, Model model){
        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            model.addAttribute("message", "Invalid token");
            return "redirect:/badUser";
        }

        User user = verificationToken.getUser();
        if(verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            model.addAttribute("message", "Token expired");
            return "redirect:/badUser";
        }
        PasswordForm passwordForm = new PasswordForm(user.getId());
        model.addAttribute("passwordForm", passwordForm);
        return "passwordChangeForm";
    }

    @PostMapping(value = "/change")
    public String processPasswordChangeForm(@ModelAttribute("passwordForm") @Valid PasswordForm passwordForm
            , BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "passwordChangeForm";
        }
        User user = userService.getById(passwordForm.getId());
        passwordForm.encodePassword(passwordEncoder);
        user.setPassword(passwordForm.getPassword());
        userService.saveRegisteredUser(user);
        return "passwordChanged";
    }
}
