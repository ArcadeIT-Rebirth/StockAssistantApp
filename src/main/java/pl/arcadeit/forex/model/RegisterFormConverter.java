package pl.arcadeit.forex.model;

import org.springframework.stereotype.Component;
import pl.arcadeit.forex.domain.User;

@Component
public class RegisterFormConverter {

    public User convertToUser(final RegisterForm user) {
        return User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

}
