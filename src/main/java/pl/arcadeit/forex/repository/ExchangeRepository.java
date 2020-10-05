package pl.arcadeit.forex.repository;

import org.springframework.data.repository.CrudRepository;
import pl.arcadeit.forex.domain.stock.Exchange;

public interface ExchangeRepository extends CrudRepository<Exchange, String> {
}
