package pl.arcadeit.forex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.arcadeit.forex.domain.Order;

public interface OrderRepository extends JpaRepository<Order, String> {
}
