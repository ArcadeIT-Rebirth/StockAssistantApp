package pl.arcadeit.forex.service.stock.USExchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arcadeit.forex.exception.stock.ExchangeException;
import pl.arcadeit.forex.model.stock.QuoteDTO;
import pl.arcadeit.forex.model.stock.StockDTO;
import pl.arcadeit.forex.service.stock.Exchange;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class USExchange implements Exchange {

    private final USStock usStock;

    @Autowired
    public USExchange(USStock usStock) {
        this.usStock = usStock;
    }

    @Override
    public List<QuoteDTO> getCurrentQuotation() {
        List<StockDTO> stockList = usStock.getStockList();
        if (stockList.isEmpty())
            throw new ExchangeException("Stock list is empty.");

        return stockList.stream().map(stockDTO -> USQuote.getTodayQuote(stockDTO.getSymbol())).collect(Collectors.toList());
    }
}
