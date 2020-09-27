package pl.arcadeit.forex.stock.entity;

// API is getting back only price levels, so I created Price class

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Price {
    private double o;
    private double c;
    private double h;
    private double l;
    private double pc;
    private long t;

    public Price() {
    }

    @Override
    public String toString() {
        return "Price{" +
                "open=" + o +
                ", close=" + c +
                ", high=" + h +
                ", low=" + l +
                ", previous close=" + pc +
                ", timestamp=" + t +
                '}';
    }
}
