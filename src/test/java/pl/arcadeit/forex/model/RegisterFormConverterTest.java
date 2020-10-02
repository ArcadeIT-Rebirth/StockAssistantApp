package pl.arcadeit.forex.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.arcadeit.forex.domain.User;
import pl.arcadeit.forex.exception.UserException;

import static org.assertj.core.api.Assertions.assertThat;

class RegisterFormConverterTest {

    // region Test data

    private RegisterFormConverter converter;
    private static final String FIRST_NAME_DATA = "Jane";
    private static final String LAST_NAME_DATA = "Doe";
    private static final String EMAIL_DATA = "jane.doe@ait.pl";
    private static final String PASSWORD_DATA = "password";
    private static final RegisterForm REGISTER_FORM_TEST_DATA = RegisterForm.builder().firstName(FIRST_NAME_DATA)
            .lastName(LAST_NAME_DATA).email(EMAIL_DATA).password(PASSWORD_DATA).build();
    private static final User REGISTERED_USER_TEST_DATA = User.builder().firstName(FIRST_NAME_DATA)
            .lastName(LAST_NAME_DATA).email(EMAIL_DATA).password(PASSWORD_DATA).build();

    // endregion

    // region Set up tests

    @BeforeEach
    void setup() {
        converter = new RegisterFormConverter();
    }

    // endregion

    // region Happy path

    @Test
    void shouldConvertRegisterFormToUser() {
        // given

        // when
        final User actualResult = converter.convertToUser(REGISTER_FORM_TEST_DATA);

        // then
        assertThat(actualResult).isNotNull().isEqualTo(REGISTERED_USER_TEST_DATA);
    }

    // endregion

    // region Unhappy path

    @Test
    void shouldThrowUserExceptionWhenRegisterFormIsNull() {
        // given

        // when
        final Exception actualResult = Assertions.assertThrows(
                UserException.class,
                () -> converter.convertToUser(null));

        // then
        assertThat(actualResult)
                .isNotNull()
                .isInstanceOf(UserException.class)
                .hasMessageContaining("form is empty");
    }

    // endregion

}