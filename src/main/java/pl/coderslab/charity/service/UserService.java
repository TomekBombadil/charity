package pl.coderslab.charity.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.RegistrationForm;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.entity.VerificationToken;
import pl.coderslab.charity.error.UserAlreadyExistException;
import pl.coderslab.charity.error.UserNotExistException;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.repository.VerificationTokenRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, VerificationTokenRepository verificationTokenRepository
            , PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerNewUserAccount(RegistrationForm registrationForm) throws UserAlreadyExistException {
        if (emailExist(registrationForm.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email adress: " + registrationForm.getEmail());
        }
        User user = registrationForm.toUser(passwordEncoder);
        return userRepository.save(user);
    }

    public boolean emailExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public User getUser(String token) {
        return verificationTokenRepository.findByToken(token).getUser();
    }

    public VerificationToken getVerificationToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    public void saveRegisteredUser(User user) {
        userRepository.save(user);
    }

    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        verificationTokenRepository.save(myToken);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()->{throw new UserNotExistException("User not exists: " + email + "!");});
    }

    public User getById(long id) {
        return userRepository.findById(id).orElseThrow(() -> {throw new UserNotExistException("User not exists: " + id + "!");});
    }
}
