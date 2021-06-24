package pl.coderslab.charity.validation;

import pl.coderslab.charity.entity.RegistrationForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, Object> {


    @Override
    public void initialize(PasswordsMatch constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        RegistrationForm registrationForm = (RegistrationForm) o;
        return registrationForm.getPassword().equals(registrationForm.getPasswordConfirmation());
    }
}
