package pl.arcadeit.forex.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import pl.arcadeit.forex.domain.Asset;
import pl.arcadeit.forex.domain.Investment;
import pl.arcadeit.forex.service.spring.data.InvestmentServiceSpringData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class InvestmentControllerTest {

    private final Investment firstInvestment = Investment.builder()
            .id(100)
            .quantity(1000)
            .asset(new Asset())
            .build();
    private final Investment secondInvestment = Investment.builder()
            .id(101)
            .quantity(1000)
            .asset(new Asset())
            .build();
    private final Investment thirdInvestment = Investment.builder()
            .id(102)
            .quantity(1000)
            .asset(new Asset())
            .build();

    private final List<Investment> investments = List.of(firstInvestment, secondInvestment, thirdInvestment);

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private InvestmentController investmentController;

    @Mock
    private InvestmentServiceSpringData investmentService;

    @BeforeEach
    void setUp() {
        investmentController = new InvestmentController(investmentService);
        final StandaloneMockMvcBuilder mvcBuilder = MockMvcBuilders.standaloneSetup(investmentController);
        mockMvc = mvcBuilder.build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllInvestments_shouldReturnInvestments() throws Exception {
        //given
        when(investmentService.findAllInvestments()).thenReturn(investments);

        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/investment/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        final List<Investment> result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<List<Investment>>() {
                });
        assertEquals(investments, result);
    }

    @Test
    void getInvestment_shouldReturnFirstInvestment() throws Exception {
        //given
        when(investmentService.findInvestmentById(100)).thenReturn(firstInvestment);
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/investment/id/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        final Investment result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<Investment>() {
                });
        assertEquals(firstInvestment, result);
    }

    @Test
    void addInvestment_shouldInvokePostSaveInvestmentOnce() throws Exception {
        //given
        doNothing().when(investmentService).saveInvestment(any(Investment.class));
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/investment/register")
                        .content(objectMapper.writeValueAsString(firstInvestment))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(investmentService, times(1)).saveInvestment(any(Investment.class));
    }

    @Test
    void updateInvestment_shouldInvokePutSaveInvestmentOnce() throws Exception {
        //given
        doNothing().when(investmentService).saveInvestment(any(Investment.class));
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/investment/register")
                        .content(objectMapper.writeValueAsString(firstInvestment))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(investmentService, times(1)).saveInvestment(any(Investment.class));
    }

    @Test
    void deleteInvestment_shouldInvokeDeleteInvestmentByIdOnce() throws Exception {
        //given
        doNothing().when(investmentService).deleteInvestmentById(anyInt());
        when(investmentService.findInvestmentById(anyInt())).thenReturn(firstInvestment);

        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/investment/remove/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        //then
        verify(investmentService, times(1)).deleteInvestmentById(anyInt());
    }
}
