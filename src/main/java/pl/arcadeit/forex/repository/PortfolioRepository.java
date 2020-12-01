package pl.arcadeit.forex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.arcadeit.forex.domain.Portfolio;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {

    public List<Portfolio> findAllByOrderByIdAsc();
}
