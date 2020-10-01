package pl.arcadeit.forex.stock.service;

import pl.arcadeit.forex.stock.entity.Investment;

public interface InvestmentService {
    public void buyMore(int additionalQuantity, double additionalBuyPrice);
    public void sellSome(int soldQuantity);

}
