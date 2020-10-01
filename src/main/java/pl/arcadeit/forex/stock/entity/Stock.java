package pl.arcadeit.forex.stock.entity;

//Stock class contains for now only ticker and price levels

import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Getter @Setter
@Transactional
public class Stock {

    private String ticker;
    private ArrayList<Price> price;
    private ArrayList<StooqPrice> prices;


    public Stock(String ticker, ArrayList<StooqPrice> prices) {
        this.ticker = ticker;
        this.prices = prices;
    }

    public Stock(String ticker, Price price) {
        this.ticker = ticker;
        this.price = new ArrayList<>();
        this.price.add(price);
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
