package pl.coderslab.charity.entity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.validation.PasswordsMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Component
@PasswordsMatch
public class PasswordForm {

    @NotNull
    private long id;
    @NotNull
    @Size(min=8, message = "{pl.coderslab.charity.user.password.size.min}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,30}$"
            , message = "{pl.coderslab.charity.user.password.pattern}")
    private String password;
    private String passwordConfirmation;

    public PasswordForm() {
    }

    public PasswordForm(@NotNull long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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


    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(password);
        this.passwordConfirmation = passwordEncoder.encode(passwordConfirmation);
    }
}
