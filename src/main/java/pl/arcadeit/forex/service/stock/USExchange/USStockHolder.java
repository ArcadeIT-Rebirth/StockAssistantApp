package pl.arcadeit.forex.service.stock.USExchange;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.arcadeit.forex.exception.stock.ExchangeException;
import pl.arcadeit.forex.model.stock.StockDTO;
import pl.arcadeit.forex.service.stock.StockHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class USStockHolder implements StockHolder {

    @Value("${finnhub.token}")
    private String token;

    private List<StockDTO> stockList = new ArrayList<>();
    private RestTemplate restTemplate = new RestTemplate();

    public void setStockList(List<StockDTO> stockList) {
        this.stockList = stockList;
    }

    @Override
    public List<StockDTO> getStockList() {
        if (stockList.isEmpty())
            downloadStockList();

        return stockList;
    }

    private void downloadStockList() {
        try {
            String url = "https://finnhub.io/api/v1/stock/symbol?exchange=US&token" + token;
            ResponseEntity<StockDTO[]> result = restTemplate.getForEntity(url, StockDTO[].class);

            StockDTO[] stocks = result.getBody();
            if (stocks == null)
                throw new Exception();

            setStockList(Arrays.stream(stocks).collect(Collectors.toList()));
        } catch (Exception e) {
            throw new ExchangeException("Stock list can not be downloaded.");
        }
    }


}
