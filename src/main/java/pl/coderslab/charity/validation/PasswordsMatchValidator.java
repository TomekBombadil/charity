package pl.coderslab.charity.validation;

import org.springframework.security.access.method.P;
import pl.coderslab.charity.entity.PasswordForm;
import pl.coderslab.charity.entity.RegistrationForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, Object> {


    @Override
    public void initialize(PasswordsMatch constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if(o instanceof RegistrationForm) {
            RegistrationForm registrationForm = (RegistrationForm) o;
            return registrationForm.getPassword().equals(registrationForm.getPasswordConfirmation());
        }
        PasswordForm passwordForm = (PasswordForm) o;
        return passwordForm.getPassword().equals(passwordForm.getPasswordConfirmation());
    }
}
