package pl.arcadeit.forex.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import pl.arcadeit.forex.model.RegisterFormConverter;
import pl.arcadeit.forex.model.UserDTO;
import pl.arcadeit.forex.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    // region SetupAll tests

    private static ObjectMapper mapper;
    private static MockMvc mockMvc;

    @Mock
    private static UserDTO userDTO;

    @Mock
    private static RegisterFormConverter converter;

    @Mock
    private static UserService service;

    @BeforeAll
    static void setUp() {
        UserController userController = new UserController(service, userDTO, converter);
        final StandaloneMockMvcBuilder mvcBuilder = MockMvcBuilders.standaloneSetup(userController);
        mockMvc = mvcBuilder.build();
        mapper = new ObjectMapper();
    }

    // endregion

    // region Test data

    // endregion

    // region Happy path

    // endregion

    // region Unhappy path

    // endregion

    // region Support methods

    // endregion
}