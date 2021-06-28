package pl.coderslab.charity.controller;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import pl.coderslab.charity.entity.Authority;
import pl.coderslab.charity.entity.RegistrationForm;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.entity.VerificationToken;
import pl.coderslab.charity.event.OnRegistrationCompleteEvent;
import pl.coderslab.charity.service.AuthorityService;
import pl.coderslab.charity.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Locale;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityService authorityService;
    private ApplicationEventPublisher eventPublisher;

    public RegistrationController(UserService userService, PasswordEncoder passwordEncoder
            , AuthorityService authorityService, ApplicationEventPublisher eventPublisher) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityService;
        this.eventPublisher = eventPublisher;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "register";
    }

    @PostMapping
    public String processRegistrationForm(@ModelAttribute("registrationForm") @Valid RegistrationForm registrationForm
            , BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "register";
        }
        registrationForm.setAuthorities(Arrays.asList(authorityService.findRoleUser()));
        User user = userService.registerNewUserAccount(registrationForm);
        try {
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), request.getContextPath()));
        } catch (RuntimeException re) {
            return "emailErrorPage";
        }
        return "redirect:/login";
    }

    @GetMapping(value = "/confirm")
    public String confirmRegistration(WebRequest webRequest, Model model, @RequestParam("token") String token) {
        Locale locale = webRequest.getLocale();
        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            model.addAttribute("message", "Invalid token");
            return "redirect:/badUser.html";
        }

        User user = verificationToken.getUser();
        if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            model.addAttribute("message", "Token expired");
            return "redirect:/badUser.html";
        }
        user.setEnabled(true);
        userService.saveRegisteredUser(user);
        return "redirect:/login";
    }


}
