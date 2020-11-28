package pl.arcadeit.forex.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long id;
    @Column(name = "price")
    @Positive()
    private double price;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "is_buy_order")
    private boolean buy;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "asset", referencedColumnName = "asset_id")
    private Asset asset;

    public Order(Asset asset, boolean buy, double price, int quantity) {
        this.asset = asset;
        this.buy = buy;
        this.price = price;
        this.quantity = quantity;
    }


}
