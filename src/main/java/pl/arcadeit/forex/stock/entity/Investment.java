package pl.arcadeit.forex.stock.entity;

public class Investment {
    private Stock stock;
    private int quantity;
    private double buyPrice;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Investment(Stock stock, int quantity, double buyPrice) {
        this.stock = stock;
        this.quantity = quantity;
        this.buyPrice = buyPrice;
    }

    public void buyMore(int additionalQuantity, double additionalBuyPrice) {
        double price = (getQuantity() * getBuyPrice()) + (additionalQuantity * additionalBuyPrice)
                / (getQuantity() + additionalQuantity);
        setBuyPrice(price);
        setQuantity(getQuantity() + additionalQuantity);
    }

    public void sellSome(int soldQuantity) {
        setQuantity(getQuantity() - soldQuantity);
    }

    public Stock getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getBuyPrice() {
        return buyPrice;
    }


}
