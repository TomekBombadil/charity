package pl.coderslab.charity.entity;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.validation.PasswordsMatch;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@PasswordsMatch
public class RegistrationForm {

    @NotNull
    @Size(min=4, message = "{pl.coderslab.charity.user.username.size.min}")
    private String username;
    @NotNull
    @Email(message = "{pl.coderslab.charity.user.email.pattern}")
    private String email;
    @NotNull
    @Size(min=8, message = "{pl.coderslab.charity.user.password.size.min}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,30}$"
            , message = "{pl.coderslab.charity.user.password.pattern}")
    private String password;
    private String passwordConfirmation;
    private List<Authority> authorities = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public User toUser(PasswordEncoder passwordEncoder){
        return new User(username, email, passwordEncoder.encode(password), authorities);
    }
}
