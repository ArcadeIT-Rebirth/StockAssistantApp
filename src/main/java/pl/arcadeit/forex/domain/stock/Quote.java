package pl.arcadeit.forex.domain.stock;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity(name="quote")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quote {

    @Id
    @Column(name = "quote_id")
    @GeneratedValue
    private long quoteId;

    @Column(name = "ticker")
    private String ticker;

    @Column(name = "open_price")
    private float openPrice;

    @Column(name = "close_price")
    private float closePrice;

    @Column(name = "hight_price")
    private float highPrice;

    @Column(name = "low_price")
    private float lowPrice;

    @Column(name = "previous_close_price")
    private float previousClosePrice;

    @Column(name = "quote_date")
    private Date date;
}
