package pl.arcadeit.forex.stock.entity;

import java.util.Date;

public class Order {
    private Stock stock;
    private int quantity;
    private double price;
    private Date expiration;

    public Order(Stock stock, int quantity, double price, Date expiration) {
        this.stock = stock;
        this.quantity = quantity;
        this.price = price;
        this.expiration = expiration;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
}
