package pl.arcadeit.forex.controller;

import org.springframework.web.bind.annotation.*;
import pl.arcadeit.forex.domain.Portfolio;
import pl.arcadeit.forex.service.PortfolioService;
import pl.arcadeit.forex.service.spring.data.PortfolioServiceSpringData;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PortfolioController {

    PortfolioService service;

    public PortfolioController(PortfolioServiceSpringData service) {
        this.service = service;
    }

    @GetMapping("/portfolios")
    public List<Portfolio> getAllPortfolios() {
        return service.findAllPortfolios();
    }

    @GetMapping("/portfolios/{portfolioId}")
    public Portfolio getPortfolio(@PathVariable int portfolioId) {
        Portfolio portfolio = service.findPortfolioById(portfolioId);
        if (portfolio == null) {
            throw new RuntimeException("Id not found");
        }
        return portfolio;
    }

    @PostMapping("/portfolios")
    public Portfolio addPortfolio(@RequestBody Portfolio portfolio) {
        portfolio.setId(0);
        service.savePortfolio(portfolio);
        return portfolio;
    }

    @PutMapping("/portfolios")
    public Portfolio updatePortfolio(@RequestBody Portfolio portfolio) {
        service.savePortfolio(portfolio);
        return portfolio;
    }

    @DeleteMapping("/portfolios/{portfolioId}")
    public String deletePortfolio(@PathVariable int portfolioId) {
        Portfolio portfolio = service.findPortfolioById(portfolioId);
        if (portfolio == null) {
            throw new RuntimeException("No such Id in database");
        }
        service.deletePortfolioById(portfolioId);
        return "Portfolio successfully deleted";
    }
}
