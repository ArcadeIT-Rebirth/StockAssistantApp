package pl.arcadeit.forex.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.arcadeit.forex.domain.User;
import pl.arcadeit.forex.domain.UserRole;
import pl.arcadeit.forex.repository.UserRepository;

import javax.transaction.Transactional;

/*
    Initializing two users.
 */

@Component
@Transactional
public class InitializeUser implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;

    private static final User ADMIN_USER = User.builder()
            .email("admin@ait.pl")
            .firstName("Super")
            .lastName("Admin")
            .password("$uP3r4Dm!N")
            .role(UserRole.ADMIN)
            .accountNumber("111111111111111")
            .build();
    private static final User NORMAL_USER = User.builder()
            .email("user@ait.pl")
            .firstName("Normal")
            .lastName("User")
            .password("N0rM@lU$3r")
            .role(UserRole.USER)
            .accountNumber("5486321843215484521")
            .build();

    public InitializeUser(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent context) {
        userRepository.save(ADMIN_USER);
        userRepository.save(NORMAL_USER);
    }
}
