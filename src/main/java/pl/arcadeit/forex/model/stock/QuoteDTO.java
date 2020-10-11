package pl.arcadeit.forex.model.stock;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class QuoteDTO {

    @JsonSetter("o")
    private float openPrice;

    @JsonSetter("c")
    private float closePrice;

    @JsonSetter("h")
    private float highPrice;

    @JsonSetter("l")
    private float lowPrice;

    @JsonSetter("pc")
    private float previousClosePrice;

    @JsonSetter("t")
    private long timestamp;

    @JsonGetter("open price")
    public float getOpenPrice() {
        return openPrice;
    }

    @JsonGetter("close price")
    public float getClosePrice() {
        return closePrice;
    }

    @JsonGetter("high price")
    public float getHighPrice() {
        return highPrice;
    }

    @JsonGetter("low price")
    public float getLowPrice() {
        return lowPrice;
    }

    @JsonGetter("previous close price")
    public float getPreviousClosePrice() {
        return previousClosePrice;
    }

    @JsonGetter("timestamp")
    public long getTimestamp() {
        return timestamp;
    }

    @JsonGetter("date")
    public Date getDate() {
        return new Date(timestamp*1000L);
    }
}
