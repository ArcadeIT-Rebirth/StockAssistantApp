package pl.arcadeit.forex.stock.entity;

import java.util.Date;

public class Transaction {
    private Portfolio portfolio;
    private Stock stock;
    private int quantity;
    private double price;
    private Date transactionDate;

    public Transaction(Portfolio portfolio, Stock stock, int quantity, double price, Date transactionDate) {
        this.portfolio = portfolio;
        this.stock = stock;
        this.quantity = quantity;
        this.price = price;
        this.transactionDate = transactionDate;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public Stock getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }
}