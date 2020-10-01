package pl.arcadeit.forex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.arcadeit.forex.domain.stock.Quote;


public interface QuoteRepository extends JpaRepository<Quote, Long> {
}
