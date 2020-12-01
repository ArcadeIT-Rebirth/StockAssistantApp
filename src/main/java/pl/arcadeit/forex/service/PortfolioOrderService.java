package pl.arcadeit.forex.service;

import pl.arcadeit.forex.domain.*;

import java.time.LocalDate;

public class PortfolioOrderService {

    private Portfolio portfolio;
    private PriceGeneratorService priceGeneratorService;

    public PortfolioOrderService(Portfolio portfolio, PriceGeneratorService priceGeneratorService) {
        this.portfolio = portfolio;
        this.priceGeneratorService = priceGeneratorService;
    }

    public void placeOrder(Order order) {
        if (validateOrder(order)) {
            Investment investment = new Investment(order.getAsset(), order.getQuantity(), LocalDate.now());
            if (order.isBuy()) {
                portfolio.addInvestment(investment);
            } else {
                portfolio.removeInvestment(investment);
            }
        }
    }

    private boolean validateOrder(Order order) {
        return order.isBuy() ? validateBuyOrder(order) : validateSellOrder(order);
    }

    private boolean validateBuyOrder(Order order) {
        boolean enoughCash = order.getQuantity() * order.getPrice() <= portfolio.getCash();
        boolean validPrice = order.getPrice() >= priceGeneratorService.getCurrentPrice(LocalDate.now(), order.getAsset());

        return enoughCash && validPrice;
    }

    private boolean validateSellOrder(Order order) {
        boolean enoughAsset = order.getQuantity() <= getQuantityOfAsset(order.getAsset());
        boolean validPrice = order.getPrice() <= priceGeneratorService.getCurrentPrice(LocalDate.now(), order.getAsset());

        return enoughAsset && validPrice;
    }

    private int getQuantityOfAsset(Asset asset) {
        for (Investment investment : portfolio.getInvestmentList()) {
            if (investment.getAsset().equals(asset)) return investment.getQuantity();
        }
        return 0;
    }

}
