package pl.arcadeit.forex.stock.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import pl.arcadeit.forex.stock.entity.FinnhubPrice;
import pl.arcadeit.forex.stock.entity.Stock;

@Repository
public class StockFinnhubDao implements StockDao {

    @Autowired
    public StockFinnhubDao() {
    }

    public FinnhubPrice callGetPrice(String ticker) {

        //creating REST template
        RestTemplate rest = new RestTemplate();

        //creating URL address
        String token = "btg8qk748v6r32agbnh0"; //token used for API connection
        String address = "https://finnhub.io/api/v1/quote?symbol=" + ticker + "&token=" + token;

        //sending request
        ResponseEntity<String> exchange = rest.exchange(
                address,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                String.class);

        try {
            //creating and returning Price POJO of asked stock
            return new ObjectMapper().readValue(exchange.getBody(),FinnhubPrice.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Stock findStockByTicker(String ticker) {
        Stock stock = new Stock(ticker,callGetPrice(ticker));
        System.out.println(stock);
        return stock;
    }
}
