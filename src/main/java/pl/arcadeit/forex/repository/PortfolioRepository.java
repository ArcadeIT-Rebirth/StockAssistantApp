package pl.arcadeit.forex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.arcadeit.forex.domain.Portfolio;

public interface PortfolioRepository extends JpaRepository<Portfolio, String> {
}
