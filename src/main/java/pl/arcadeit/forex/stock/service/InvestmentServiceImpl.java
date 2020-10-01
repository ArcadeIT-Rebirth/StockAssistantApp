package pl.arcadeit.forex.stock.service;

import pl.arcadeit.forex.stock.entity.Investment;

public class InvestmentServiceImpl implements InvestmentService {

    private Investment investment;

    public InvestmentServiceImpl(Investment investment) {
        this.investment = investment;
    }

    //todo error messages

    @Override
    public void buyMore(int additionalQuantity, double additionalBuyPrice) {
        if (additionalQuantity > 0 && additionalBuyPrice > 0) {
            double price = (investment.getQuantity() * investment.getBuyPrice()) + (additionalQuantity * additionalBuyPrice)
                    / (investment.getQuantity() + additionalQuantity);
            investment.setBuyPrice(price);
            investment.setQuantity(investment.getQuantity() + additionalQuantity);
        }
    }

    @Override
    public void sellSome(int soldQuantity) {
        if (soldQuantity > 0 && soldQuantity <= investment.getQuantity())
        investment.setQuantity(investment.getQuantity() - soldQuantity);
    }
}
