package pl.arcadeit.forex.service;

import pl.arcadeit.forex.domain.Order;

import java.util.List;

public interface OrderService {
    public List<Order> findAllOrders();

    public Order findOrderById(int id);

    public void saveOrder(Order order);

    public void deleteOrderById(int id);
}
