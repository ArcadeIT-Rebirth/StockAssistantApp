package pl.arcadeit.forex.stock.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

@Getter @Setter
@Transactional
public class StooqPrice extends Price {
    private int volume;
    private int date;

    public StooqPrice(double open, double high, double low, double close, int volume, int date) {
        super(open, high, low, close);
        this.volume = volume;
    }

    @Override
    public String toString() {
        return super.toString() +
                "volume=" + volume;
    }
}
