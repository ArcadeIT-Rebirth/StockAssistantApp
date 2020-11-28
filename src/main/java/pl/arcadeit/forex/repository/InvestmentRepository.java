package pl.arcadeit.forex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.arcadeit.forex.domain.Investment;

public interface InvestmentRepository extends JpaRepository<Investment, String> {
}
