package pl.arcadeit.forex.service.spring.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.arcadeit.forex.domain.Asset;
import pl.arcadeit.forex.domain.Order;
import pl.arcadeit.forex.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceSpringDataTest {

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

    @InjectMocks
    OrderServiceSpringData service;

    @Mock
    OrderRepository repository;

    @Test
    void findAllOrders_shouldReturnOrder() {
        //given
        when(repository.findAllByOrderByIdAsc()).thenReturn(orders);
        //when
        List<Order> result = service.findAllOrders();
        //then
        assertEquals(orders, result);
        verify(repository, times(1)).findAllByOrderByIdAsc();
    }

    @Test
    void findOrderById_shouldReturnFirstOrder() {
        //given
        when(repository.findById(100)).thenReturn(Optional.of(firstOrder));
        //when
        Order result = service.findOrderById(100);
        //then
        assertEquals(firstOrder, result);
        verify(repository, times(1)).findById(100);
    }

    @Test
    void saveOrder() {
        //given
        //when
        service.saveOrder(firstOrder);
        service.saveOrder(secondOrder);
        //then
        verify(repository, times(1)).save(firstOrder);
        verify(repository, times(2)).save(any(Order.class));
    }

    @Test
    void deleteOrderById() {
        //given
        //when
        service.deleteOrderById(1);
        service.deleteOrderById(2);
        //then
        verify(repository, times(1)).deleteById(1);
        verify(repository, times(2)).deleteById(anyInt());
    }
}
