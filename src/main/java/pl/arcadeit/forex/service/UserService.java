package pl.arcadeit.forex.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.arcadeit.forex.domain.User;
import pl.arcadeit.forex.exception.UserException;
import pl.arcadeit.forex.model.LoginForm;
import pl.arcadeit.forex.repository.UserRepository;

import javax.transaction.Transactional;

import static java.util.Objects.nonNull;

/*
    Service layer to user handling
 */

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
        isUserExisting(user.getEmail());
        encodePassword(user);
        return userRepository.save(user);
    }

    public boolean isUserPresent(final String email) {
        return userRepository.findById(email).isPresent();
    }

    private void isUserExisting(final String userEmail) {
        if (userRepository.findById(userEmail).isPresent()) {
            throw new UserException(String.format("User with email %s already exists", userEmail));
        }
    }

    private void encodePassword(final User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    public User getUserByEmail(final String email) {
        return userRepository.findById(email)
                .orElseThrow(() -> new UserException(String.format("Email address %s is unknown", email)));
    }

    public User logIn(final LoginForm loginForm) {
        final User user = getUserForLogIn(loginForm.getEmail());
        if (passwordEncoder.matches(loginForm.getPassword(), user.getPassword())) {
            return user;
        } else {
            throw new UserException("Incorrect email or password.");
        }
    }

    private User getUserForLogIn(final String email) {
        return userRepository.findById(email)
                .orElseThrow(() -> new UserException("Incorrect email or password."));
    }

    public void update(final String email, final User user) {
        if (!user.getEmail().equals(email)) {
            throw new UserException("Email and user email aren't equal.");
        }
        isUserExisting(email);
        editFields(user);
    }

    private void editFields(final User editFields) {
        final User user = getUserByEmail(editFields.getEmail());
        if (nonNull(editFields.getFirstName())) {
            user.setFirstName(editFields.getFirstName());
        }
        if (nonNull(editFields.getLastName())) {
            user.setLastName(editFields.getLastName());
        }
        userRepository.save(user);
    }
}
