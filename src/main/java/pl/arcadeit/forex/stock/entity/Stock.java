package pl.arcadeit.forex.stock.entity;

//Stock class contains for now only ticker and price levels

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Stock {

    private String ticker;
    private Price price;

    public Stock(String ticker, Price price) {
        this.ticker = ticker;
        this.price = price;
    }

    public Stock(String ticker) {
        this.ticker = ticker;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "ticker='" + ticker + '\'' +
                ", price=" + price +
                '}';
    }
}
