package pl.arcadeit.forex.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "{name.required}")
    @Column(name = "name")
    private String name;
    @Positive(message = "{cash.positive}")
    @Column(name = "cash")
    private double cash;
    @OneToMany(mappedBy = "portfolio")
    private List<Investment> investmentList;

    public Portfolio(String name, double cash) {
        this.name = name;
        this.cash = cash;
        this.investmentList = new ArrayList<>();
    }

    public void addInvestment(Investment investment) {
        for (Investment inv : investmentList) {
            if (inv.getAsset().equals(investment.getAsset())) {
                inv.setQuantity(inv.getQuantity() + investment.getQuantity());
                return;
            }
        }
    }

    public void removeInvestment(Investment investment) {
        for (Investment inv : investmentList) {
            if (inv.getAsset().equals(investment.getAsset())) {
                inv.setQuantity(inv.getQuantity() - investment.getQuantity());
                return;
            }
        }
    }


}
