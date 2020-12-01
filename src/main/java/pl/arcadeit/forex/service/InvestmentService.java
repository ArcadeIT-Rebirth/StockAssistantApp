package pl.arcadeit.forex.service;

import pl.arcadeit.forex.domain.Investment;

import java.util.List;

public interface InvestmentService {
    public List<Investment> findAllInvestments();

    public Investment findInvestmentById(int id);

    public void saveInvestment(Investment investment);

    public void deleteInvestmentById(int id);
}
