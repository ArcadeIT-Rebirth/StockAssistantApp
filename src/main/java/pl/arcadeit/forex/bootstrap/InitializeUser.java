package pl.arcadeit.forex.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.arcadeit.forex.domain.User;
import pl.arcadeit.forex.domain.UserRole;
import pl.arcadeit.forex.service.UserService;

/*
    Initializing two users.
 */

@Component
public class InitializeUser implements ApplicationListener<ContextRefreshedEvent> {

    private final UserService userService;

    private static final User ADMIN_USER = User.builder()
            .email("admin@ait.pl")
            .firstName("Super")
            .lastName("Admin")
            .password("$uP3r4Dm!N")
            .role(UserRole.ADMIN)
            .walletNumber("111111111111111")
            .build();
    private static final User NORMAL_USER = User.builder()
            .email("user@ait.pl")
            .firstName("Normal")
            .lastName("User")
            .password("N0rM@lU$3r")
            .role(UserRole.USER)
            .walletNumber("5486321843215484521")
            .build();

    public InitializeUser(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent context) {
        userService.createNewUser(ADMIN_USER);
        userService.createNewUser(NORMAL_USER);
    }
}
