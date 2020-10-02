package pl.arcadeit.forex.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.arcadeit.forex.domain.User;
import pl.arcadeit.forex.domain.UserRole;
import pl.arcadeit.forex.exception.UserException;
import pl.arcadeit.forex.model.LoginForm;
import pl.arcadeit.forex.repository.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Captor
    private ArgumentCaptor<User> captor;

    // region Test data

    private static final String JOHN = "John";
    private static final String SMITH = "Smith";
    private static final String JANE_DOE_MAIL = "jane.doe@ait.pl";
    private static final String PASSWORD = "password";

    private static final User USER_TEST_DATA_1 = User.builder().firstName("Jane").lastName("Doe")
            .email(JANE_DOE_MAIL).walletNumber("1234567812345678").role(UserRole.USER).password(PASSWORD).build();
    private static final User EDITED_USER_TEST_DATA_1 = User.builder().firstName(JOHN).lastName(SMITH)
            .email(JANE_DOE_MAIL).walletNumber("1234567812345678").role(UserRole.USER).password(PASSWORD).build();
    private static final User UPDATE_USER_TEST_DATA_1 = User.builder().firstName(JOHN).lastName(SMITH)
            .email(JANE_DOE_MAIL).build();
    private static final String WRONG_EMAIL_DATA = "wrong.mail@ait.pl";
    private static final LoginForm LOGIN_FORM_TEST_DATA = LoginForm.builder().email(JANE_DOE_MAIL)
            .password(PASSWORD).build();
    private static final User REGISTER_USER_TEST_DATA = User.builder().firstName(JOHN).lastName(SMITH)
            .email(JANE_DOE_MAIL).password(PASSWORD).build();

    // endregion

    //TODO: createUser tests

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

    @Test
    void shouldLogInUser() {
        // given
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(userRepository.findById(anyString())).thenReturn(Optional.of(USER_TEST_DATA_1));

        // when
        final User actualResult = userService.logIn(LOGIN_FORM_TEST_DATA);

        // then
        assertThat(actualResult)
                .isNotNull()
                .isEqualTo(USER_TEST_DATA_1);
    }

    @Test
    void shouldCreateNewUser() {
        // given
        when(userRepository.save(any(User.class))).thenReturn(EDITED_USER_TEST_DATA_1);
        when(userRepository.findById(anyString())).thenReturn(Optional.empty());

        // when
        final User actualResult = userService.createNewUser(REGISTER_USER_TEST_DATA);

        // then
        assertThat(actualResult)
                .isNotNull()
                .isEqualTo(EDITED_USER_TEST_DATA_1);
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

    @Test
    void shouldThrowUserExceptionWhenUserDoesNotExist() {
        // given
        when(userRepository.findById(any(String.class))).thenReturn(Optional.empty());

        // when
        final Exception actualResult = Assertions.assertThrows(
                UserException.class,
                () -> userService.update(USER_TEST_DATA_1.getEmail(), USER_TEST_DATA_1));

        // then
        assertThat(actualResult)
                .isNotNull()
                .isInstanceOf(UserException.class)
                .hasMessageContaining("does not exist");
    }

    @Test
    void shouldThrowUserExceptionWhenPasswordDoesNotMatch() {
        // given
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);
        when(userRepository.findById(anyString())).thenReturn(Optional.of(USER_TEST_DATA_1));

        // when
        final Exception actualResult = Assertions.assertThrows(
                UserException.class,
                () ->userService.logIn(LOGIN_FORM_TEST_DATA));

        // then
        assertThat(actualResult)
                .isNotNull()
                .isInstanceOf(UserException.class)
                .hasMessageContaining("Incorrect email or password");
    }

    @Test
    void shouldThrowUserExceptionWhenUserAlreadyExists() {
        // given
        when(userRepository.findById(anyString())).thenReturn(Optional.of(USER_TEST_DATA_1));

        // when
        final Exception actualResult = Assertions.assertThrows(
                UserException.class,
                () -> userService.createNewUser(USER_TEST_DATA_1));

        // then
        assertThat(actualResult)
                .isNotNull()
                .isInstanceOf(UserException.class)
                .hasMessageContaining("already exists");
    }

    // end region

    // region Support methods



    // endregion
}