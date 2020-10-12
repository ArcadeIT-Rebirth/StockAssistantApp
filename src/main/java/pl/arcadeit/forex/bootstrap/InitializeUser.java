package pl.arcadeit.forex.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.arcadeit.forex.domain.User;
import pl.arcadeit.forex.domain.UserRole;
import pl.arcadeit.forex.service.UserService;

import static java.util.Objects.nonNull;

/*
    Initializing two users.
 */

@Profile("develop")
@Component
public class InitializeUser implements ApplicationListener<ContextRefreshedEvent> {

    private final UserService userService;

    private static final User ADMIN_USER = User.builder()
            .email("admin@ait.pl")
            .firstName("Super")
            .lastName("Admin")
            .password("$uP3r4Dm!N")
            .role(UserRole.ADMIN)
            .walletNumber("1111111111111111")
            .build();
    private static final User NORMAL_USER = User.builder()
            .email("user@ait.pl")
            .firstName("Normal")
            .lastName("User")
            .password("N0rM@lU$3r")
            .role(UserRole.USER)
            .walletNumber("5486321843215484")
            .build();

    public InitializeUser(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent context) {
        if (!nonNull(userService.getUserByEmail(ADMIN_USER.getEmail()))) {
            userService.createNewUser(ADMIN_USER);
        }
        if (!nonNull(userService.getUserByEmail(NORMAL_USER.getEmail()))) {
            userService.createNewUser(NORMAL_USER);
        }
    }
}
