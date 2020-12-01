package pl.arcadeit.forex.service;

import pl.arcadeit.forex.domain.Portfolio;

import java.util.List;

public interface PortfolioService {
    public List<Portfolio> findAllPortfolios();

    public Portfolio findPortfolioById(int id);

    public void savePortfolio(Portfolio portfolio);

    public void deletePortfolioById(int id);
}
