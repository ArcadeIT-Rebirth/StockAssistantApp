package pl.arcadeit.forex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.arcadeit.forex.domain.Order;
import pl.arcadeit.forex.service.OrderService;
import pl.arcadeit.forex.service.spring.data.OrderServiceSpringData;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    OrderService service;

    @Autowired
    public OrderController(OrderServiceSpringData service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return service.findAllOrders();
    }

    @GetMapping("/id/{orderId}")
    public Order getOrder(@PathVariable int orderId) {
        Order order = service.findOrderById(orderId);
        if (order == null) {
            throw new RuntimeException("No such Id in database");
        }
        return order;
    }

    @PostMapping("/register")
    public Order addOrder(@RequestBody Order order) {
        order.setId(0);
        service.saveOrder(order);
        return order;
    }

    @PutMapping("/register")
    public Order updateOrder(@RequestBody Order order) {
        service.saveOrder(order);
        return order;
    }

    @DeleteMapping("/remove/{orderId}")
    public String deleteOrder(@PathVariable int orderId) {
        Order order = service.findOrderById(orderId);
        if (order == null) {
            throw new RuntimeException("No such id in database");
        }
        service.deleteOrderById(orderId);
        return "Order successfully deleted";
    }

}
