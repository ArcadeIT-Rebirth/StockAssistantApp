package pl.arcadeit.forex.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.arcadeit.forex.domain.User;
import pl.arcadeit.forex.domain.UserRole;
import pl.arcadeit.forex.exception.UserException;
import pl.arcadeit.forex.repository.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Captor
    private ArgumentCaptor<User> captor;

    // region Test data

    private static final User USER_TEST_DATA_1 = User.builder().firstName("Jane").lastName("Doe")
            .email("jane.doe@ait.pl").walletNumber("1234567812345678").role(UserRole.USER).password("password").build();
    private static final User EDITED_USER_TEST_DATA_1 = User.builder().firstName("John").lastName("Smith")
            .email("jane.doe@ait.pl").walletNumber("1234567812345678").role(UserRole.USER).password("password").build();
    private static final User UPDATE_USER_TEST_DATA_1 = User.builder().firstName("John").lastName("Smith")
            .email("jane.doe@ait.pl").build();
    private static final String WRONG_EMAIL_DATA = "wrong.mail@ait.pl";

    // endregion

    // region Happy path

    @Test
    void shouldReturnUserByEmail() {
        // given
        when(userRepository.findById(any(String.class))).thenReturn(Optional.of(USER_TEST_DATA_1));

        // when
        final User actualResult = userService.getUserByEmail(USER_TEST_DATA_1.getEmail());

        // then
        assertThat(actualResult).isNotNull().isEqualTo(USER_TEST_DATA_1);
    }

    @Test
    void shouldUpdateUserFields() {
        // given
        when(userRepository.findById(USER_TEST_DATA_1.getEmail())).thenReturn(Optional.of(USER_TEST_DATA_1));

        // when
        userService.update(USER_TEST_DATA_1.getEmail(), UPDATE_USER_TEST_DATA_1);
        verify(userRepository).save(captor.capture());
        final User actualResult = captor.getValue();

        // then
        assertThat(actualResult).isNotNull().isEqualTo(EDITED_USER_TEST_DATA_1);
    }

    // endregion

    // region Border path



    // endregion

    // region Unhappy path

    @Test
    void shouldThrowUserExceptionWhenUserNotFound() {
        // given
        when(userRepository.findById(USER_TEST_DATA_1.getEmail())).thenReturn(Optional.empty());

        // when
        final Exception actualResult = Assertions.assertThrows(
                UserException.class,
                () -> userService.getUserByEmail(USER_TEST_DATA_1.getEmail()));

        // then
        assertThat(actualResult)
                .isNotNull()
                .isInstanceOf(UserException.class)
                .hasMessageContaining("Email address")
                .hasMessageContaining("is unknown");
    }

    @Test
    void shouldThrowUserExceptionWhenEmailAddressDoesNotMatch() {
        // given

        // when
        final Exception actualResult = Assertions.assertThrows(
                UserException.class,
                () -> userService.update(WRONG_EMAIL_DATA, USER_TEST_DATA_1));

        // then
        assertThat(actualResult).isNotNull().isInstanceOf(UserException.class).hasMessageContaining("email aren't equal");
    }

    // end region

    // region Support methods



    // endregion
}