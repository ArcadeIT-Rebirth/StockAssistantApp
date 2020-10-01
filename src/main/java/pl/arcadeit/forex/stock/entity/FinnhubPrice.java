package pl.arcadeit.forex.stock.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

@Getter @Setter
@Transactional
public class FinnhubPrice extends Price {
    private double pc;
    private long t;

    @Override
    public String toString() {
        return super.toString() +
                ", previous close=" + pc +
                ", timestamp=" + t +
                '}';
    }
}
