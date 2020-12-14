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
import pl.arcadeit.forex.domain.Order;
import pl.arcadeit.forex.service.spring.data.OrderServiceSpringData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    private final Order firstOrder = Order.builder()
            .id(100l)
            .asset(new Asset("First Asset"))
            .price(100)
            .quantity(10)
            .build();
    private final Order secondOrder = Order.builder()
            .id(200l)
            .asset(new Asset("Second Asset"))
            .price(25)
            .quantity(20)
            .build();
    private final Order thirdOrder = Order.builder()
            .id(300l)
            .asset(new Asset("Third Asset"))
            .price(12)
            .quantity(45)
            .build();

    private final List<Order> orders = List.of(firstOrder, secondOrder, thirdOrder);

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private OrderController orderController;

    @Mock
    private OrderServiceSpringData orderService;

    @BeforeEach
    void setUp() {
        orderController = new OrderController(orderService);
        final StandaloneMockMvcBuilder mvcBuilder = MockMvcBuilders.standaloneSetup(orderController);
        mockMvc = mvcBuilder.build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllOrders_shouldReturnOrders() throws Exception {
        //given
        when(orderService.findAllOrders()).thenReturn(orders);

        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                    .get("/api/order/all")
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        final List<Order> result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<List<Order>>() {
                });
        assertEquals(orders, result);
    }

    @Test
    void getOrder_shouldReturnFirstOrder() throws Exception {
        //given
        when(orderService.findOrderById(100)).thenReturn(firstOrder);

        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/order/id/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        final Order result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<Order>() {
                });
        assertEquals(firstOrder, result);
    }

    @Test
    void addOrder_shouldInvokePostSaveOrderOnce() throws Exception {
        //given
        doNothing().when(orderService).saveOrder(any(Order.class));
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/order/register")
                        .content(objectMapper.writeValueAsString(firstOrder))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(orderService, times(1)).saveOrder(any(Order.class));
    }

    @Test
    void updateOrder_shouldInvokePutSaveOrderOnce() throws Exception {
        //given
        doNothing().when(orderService).saveOrder(any(Order.class));
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/order/register")
                        .content(objectMapper.writeValueAsString(firstOrder))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(orderService, times(1)).saveOrder(any(Order.class));
    }

    @Test
    void deleteOrder_shouldInvokeDeleteOrderByIdOnce() throws Exception {
        //given
        doNothing().when(orderService).deleteOrderById(anyInt());
        when(orderService.findOrderById(anyInt())).thenReturn(firstOrder);

        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/order/remove/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        //then
        verify(orderService, times(1)).deleteOrderById(anyInt());
    }
}
