package pl.arcadeit.forex.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Entity(name = "investments")
@Data
@NoArgsConstructor
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "investment_id")
    private long id;
    @NotNull(message = "{quantity.required")
    @Positive(message = "{quantity.positive}")
    @Column(name = "quantity")
    private int quantity;
    @NotNull(message = "{date.required}")
    @Column(name = "date_of_buy")
    private LocalDate bought;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "asset", referencedColumnName = "asset_id")
    private Asset asset;
    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    public Investment(Asset asset, int quantity, LocalDate bought) {
        this.asset = asset;
        this.quantity = quantity;
        this.bought = bought;
    }
}
