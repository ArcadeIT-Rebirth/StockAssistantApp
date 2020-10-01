package pl.arcadeit.forex.mapstruct;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.arcadeit.forex.domain.User;
import pl.arcadeit.forex.domain.UserRole;
import pl.arcadeit.forex.model.UserDTOImpl;
import pl.arcadeit.forex.model.UserModel;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDTOImplTest {

    private UserDTOImpl userDTO;

    // region Set up

    @BeforeEach
    void setUp() {
        userDTO = new UserDTOImpl();
    }

    // endregion

    // region Test data

    private static final String FIRST_NAME = "Jane";
    private static final String LAST_NAME = "Doe";
    private static final String EMAIL_ADDRESS = "jane.doe@ait.pl";
    private static final User USER_TEST_DATA = User.builder().firstName(FIRST_NAME).lastName(LAST_NAME)
            .password("password").role(UserRole.USER).walletNumber("1234567812345678").email(EMAIL_ADDRESS).build();
    private static final UserModel USER_MODEL_TEST_DATA = UserModel.builder().firstName(FIRST_NAME)
            .lastName(LAST_NAME).email(EMAIL_ADDRESS).build();
    private static final User CONVERTED_USER_TEST_DATA = User.builder().firstName(FIRST_NAME)
            .lastName(LAST_NAME).email(EMAIL_ADDRESS).build();

    // endregion

    // region Happy path

    @Test
    void shouldConvertUserToUserModel() {
        // given

        // when
        final UserModel actualResult = userDTO.userToUserModel(USER_TEST_DATA);

        // then
        assertThat(actualResult).isNotNull().isEqualTo(USER_MODEL_TEST_DATA);
    }

    @Test
    void shouldConvertUserModelToUser() {
        // given

        // when
        final User actualResult = userDTO.userModelToUser(USER_MODEL_TEST_DATA);

        // then
        assertThat(actualResult).isNotNull().isEqualTo(CONVERTED_USER_TEST_DATA);
    }

    // endregion

    // region Unhappy path

    @Test
    void shouldReturnNullWhenUserModelIsNull() {
        // given

        // when
        final User actualResult = userDTO.userModelToUser(null);

        //then
        assertThat(actualResult).isNull();
    }

    @Test
    void shouldReturnNullWhenUserIsNull() {
        // given

        // when
        final UserModel actualResult = userDTO.userToUserModel(null);

        // then
        assertThat(actualResult).isNull();
    }

    // endregion
}
