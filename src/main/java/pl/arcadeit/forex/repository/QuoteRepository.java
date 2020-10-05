package pl.arcadeit.forex.repository;

import org.springframework.data.repository.CrudRepository;
import pl.arcadeit.forex.domain.stock.Quote;


public interface QuoteRepository extends CrudRepository<Quote, Long> {
}
