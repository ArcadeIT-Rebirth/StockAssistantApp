package pl.arcadeit.forex.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.arcadeit.forex.domain.User;
import pl.arcadeit.forex.domain.UserRole;
import pl.arcadeit.forex.exception.UserException;
import pl.arcadeit.forex.model.LoginForm;
import pl.arcadeit.forex.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.Objects.nonNull;

/*
    Service layer to user handling
 */

@Slf4j
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(final UserRepository userRepository, final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createNewUser(final User user) {
        ifUserExist(getUserByEmail(user.getEmail()));
        encodePassword(user);
        user.setRole(UserRole.USER);
        log.info(String.format("INFO: Created user %s with role %s.", user.getEmail(), user.getRole()));
        return userRepository.save(user);
    }

    public User getUserByEmail(final String email) {        //TODO: change to private when initialization will be removed
        return userRepository.findById(email)
                .orElse(null);
    }

    private void ifUserExist(final User user) {
        if (nonNull(user)) {
            throw new UserException(String.format("User with email %s already exists", user.getEmail()));
        }
    }

    private void ifNoUserFound(final User user) {
        if (!nonNull(user)) {
            throw new UserException("User does not exist");
        }
    }

    private void encodePassword(final User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    public User findUserByEmail(final String email) {
        final User foundUser = getUserByEmail(email);
        ifNoUserFound(foundUser);
        return foundUser;
    }


    public User logIn(final LoginForm loginForm) {
        final User foundUser = getUserByEmail(loginForm.getEmail());
        if (nonNull(foundUser) && passwordEncoder.matches(loginForm.getPassword(), foundUser.getPassword())) {
            log.info(String.format("INFO: Logged in user %s with role %s.", foundUser.getEmail(), foundUser.getRole()));
            return foundUser;
        }
        throw new UserException("Incorrect email or password.");
    }

    public void update(final String email, final User user) {
        isEmailValid(email, user.getEmail());
        final User foundUser = getUserByEmail(email);
        ifNoUserFound(foundUser);
        editFields(foundUser, user);
    }

    private void isEmailValid(final String email, final String userEmail) {
        if (!email.equals(userEmail))
            throw new UserException("Email and user email aren't equal.");
    }

    private void editFields(final User actualUser, final User editFields) {
        if (nonNull(editFields.getFirstName())) {
            actualUser.setFirstName(editFields.getFirstName());
        }
        if (nonNull(editFields.getLastName())) {
            actualUser.setLastName(editFields.getLastName());
        }
        userRepository.save(actualUser);
    }

    public void changeUserRole(final User user, final UserRole roleGranted) {
        user.setRole(roleGranted);
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
}
