package pl.arcadeit.forex.stock.entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Investment {

    private Stock stock;
    private int quantity;
    private double buyPrice;

    public Investment() {
    }

    public Investment(Stock stock, int quantity, double buyPrice) {
        this.stock = stock;
        this.quantity = quantity;
        this.buyPrice = buyPrice;
    }

    @Override
    public String toString() {
        return "Investment{" +
                "stock=" + stock +
                ", quantity=" + quantity +
                ", buyPrice=" + buyPrice +
                '}';
    }
}
