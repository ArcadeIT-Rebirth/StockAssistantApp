package pl.arcadeit.forex.stock.entity;

// API is getting back only price levels, so I created Price class

import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

@Getter @Setter
@Transactional
public class Price {
    private double o;
    private double c;
    private double h;
    private double l;


    public Price() {
    }

    public Price(double o, double c, double h, double l) {
        this.o = o;
        this.c = c;
        this.h = h;
        this.l = l;
    }

    @Override
    public String toString() {
        return "Price" +
                "open=" + o +
                ", close=" + c +
                ", high=" + h +
                ", low=" + l;
    }
}
