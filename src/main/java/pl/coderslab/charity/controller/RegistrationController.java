package pl.coderslab.charity.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Authority;
import pl.coderslab.charity.entity.RegistrationForm;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.AuthorityService;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;
import java.util.Arrays;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    public final UserService userService;
    public final PasswordEncoder passwordEncoder;
    public final AuthorityService authorityService;

    public RegistrationController(UserService userService, PasswordEncoder passwordEncoder
            , AuthorityService authorityService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityService;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "register";
    }

    @PostMapping
    public String processRegistrationForm(@ModelAttribute("registrationForm") @Valid RegistrationForm registrationForm, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        registrationForm.setAuthorities(Arrays.asList(authorityService.findRoleUser()));
        User user = userService.save(registrationForm.toUser(passwordEncoder));
        return "redirect:/login";
    }
}
