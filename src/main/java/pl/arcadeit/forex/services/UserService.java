package pl.arcadeit.forex.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arcadeit.forex.exceptions.UserNameException;
import pl.arcadeit.forex.model.User;
import pl.arcadeit.forex.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Transactional
    public User saveOrUpdateUser(User user) {
        try {
            // chech user name
            return userRepository.save(user);
        } catch (Exception e) {
            throw new UserNameException("User Name '" + user.getName() + "' already exist.");
        }
    }

    @Transactional void delete(Long userId) {
        User user = userRepository.getOne(userId);

        if (user != null)
            userRepository.delete(user);
    }
}
