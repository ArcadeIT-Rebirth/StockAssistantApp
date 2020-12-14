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
import pl.arcadeit.forex.domain.Portfolio;
import pl.arcadeit.forex.service.spring.data.PortfolioServiceSpringData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PortfolioControllerTest {

    private final Portfolio firstPortfolio = Portfolio.builder()
            .id(100)
            .cash(1000)
            .build();
    private final Portfolio secondPortfolio = Portfolio.builder()
            .id(200)
            .cash(1200)
            .build();
    private final Portfolio thirdPortfolio = Portfolio.builder()
            .id(300)
            .cash(1500)
            .build();

    private final List<Portfolio> portfolios = List.of(firstPortfolio, secondPortfolio, thirdPortfolio);

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private PortfolioController portfolioController;

    @Mock
    private PortfolioServiceSpringData portfolioService;

    @BeforeEach
    void setUp() {
        portfolioController = new PortfolioController(portfolioService);
        final StandaloneMockMvcBuilder mvcBuilder = MockMvcBuilders.standaloneSetup(portfolioController);
        mockMvc = mvcBuilder.build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllPortfolios_shouldReturnPortfolios() throws Exception {
        //given
        when(portfolioService.findAllPortfolios()).thenReturn(portfolios);

        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/portfolio/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        //then
        final List<Portfolio> result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<List<Portfolio>>() {
                });
        assertEquals(portfolios, result);
    }

    @Test
    void getPortfolio_shouldReturnFirstPortfolio() throws Exception {
        //given
        when(portfolioService.findPortfolioById(100)).thenReturn(firstPortfolio);

        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/portfolio/id/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        //then
        final Portfolio result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<Portfolio>() {
                });
        assertEquals(firstPortfolio, result);
    }

    @Test
    void addPortfolio_shouldInvokePostSavePortfolioOnce() throws Exception {
        //given
        doNothing().when(portfolioService).savePortfolio(any(Portfolio.class));
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/portfolio/register")
                        .content(objectMapper.writeValueAsString(firstPortfolio))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(portfolioService, times(1)).savePortfolio(any(Portfolio.class));
    }

    @Test
    void updatePortfolio_shouldInvokePutSavePortfolioOnce() throws Exception {
        //given
        doNothing().when(portfolioService).savePortfolio(any(Portfolio.class));
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/portfolio/register")
                        .content(objectMapper.writeValueAsString(firstPortfolio))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(portfolioService, times(1)).savePortfolio(any(Portfolio.class));
    }

    @Test
    void deletePortfolio_shouldInvokeDeletePortfolioByIdOnce() throws Exception {
        //given
        doNothing().when(portfolioService).deletePortfolioById(anyInt());
        when(portfolioService.findPortfolioById(anyInt())).thenReturn(firstPortfolio);

        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/portfolio/remove/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(portfolioService, times(1)).deletePortfolioById(anyInt());
    }
}
