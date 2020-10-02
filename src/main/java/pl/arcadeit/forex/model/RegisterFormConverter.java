package pl.arcadeit.forex.model;

import org.springframework.stereotype.Component;
import pl.arcadeit.forex.domain.User;
import pl.arcadeit.forex.exception.UserException;

import static java.util.Objects.nonNull;

@Component
public class RegisterFormConverter {

    public User convertToUser(final RegisterForm user) {
        if (!nonNull(user)) {
            throw new UserException("Register form is empty");
        }
        return User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

}
