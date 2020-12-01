package pl.arcadeit.forex.service.spring.data;

import org.springframework.beans.factory.annotation.Autowired;
import pl.arcadeit.forex.domain.Portfolio;
import pl.arcadeit.forex.repository.PortfolioRepository;
import pl.arcadeit.forex.service.PortfolioService;

import java.util.List;
import java.util.Optional;

public class PortfolioServiceSpringData implements PortfolioService {

    PortfolioRepository portfolioRepository;

    @Autowired
    public PortfolioServiceSpringData(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    @Override
    public List<Portfolio> findAllPortfolios() {
        return portfolioRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Portfolio findPortfolioById(int id) {
        Optional<Portfolio> result = portfolioRepository.findById(id);
        Portfolio portfolio = null;
        if (result.isPresent()) {
            portfolio = result.get();
        } else {
            throw new RuntimeException("Id not found");
        }
        return portfolio;
    }

    @Override
    public void savePortfolio(Portfolio portfolio) {
        portfolioRepository.save(portfolio);
    }

    @Override
    public void deletePortfolioById(int id) {
        portfolioRepository.deleteById(id);
    }
}
