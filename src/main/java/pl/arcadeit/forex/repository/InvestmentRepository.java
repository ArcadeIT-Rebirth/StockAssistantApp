package pl.arcadeit.forex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.arcadeit.forex.domain.Investment;

import java.util.List;

public interface InvestmentRepository extends JpaRepository<Investment, Integer> {

    public List<Investment> findAllByOrderByIdAsc();
}
