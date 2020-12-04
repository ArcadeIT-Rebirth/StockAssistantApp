package pl.arcadeit.forex.service.spring.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arcadeit.forex.domain.Order;
import pl.arcadeit.forex.repository.OrderRepository;
import pl.arcadeit.forex.service.OrderService;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceSpringData implements OrderService {
    OrderRepository orderRepository;

    @Autowired
    public OrderServiceSpringData(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Order findOrderById(int id) {
        Optional<Order> result = orderRepository.findById(id);
        Order order = null;
        if (result.isPresent()) {
            order = result.get();
        } else {
            throw new RuntimeException("Id not found");
        }
        return order;
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void deleteOrderById(int id) {
        orderRepository.deleteById(id);
    }
}
