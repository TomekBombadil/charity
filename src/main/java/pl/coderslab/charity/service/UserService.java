package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    public final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
