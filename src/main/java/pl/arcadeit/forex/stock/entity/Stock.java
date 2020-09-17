package pl.arcadeit.forex.stock.entity;

//Stock class contains for now only ticker and price levels

public class Stock {
    private String ticker;
    private Price price;

    public Stock(String ticker, Price price) {
        this.ticker = ticker;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "ticker='" + ticker + '\'' +
                ", price=" + price +
                '}';
    }

    public Stock(String ticker) {
        this.ticker = ticker;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

}
