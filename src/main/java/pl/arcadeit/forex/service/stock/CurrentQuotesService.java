package pl.arcadeit.forex.service.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arcadeit.forex.model.stock.QuoteDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurrentQuotesService {

    StockService stockService;

    QuoteService quoteService;

    ExchangeService exchangeService;

    @Autowired
    public void setStockService(StockService stockService) {
        this.stockService = stockService;
    }

    @Autowired
    public void setQuoteService(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @Autowired
    public void setExchangeService(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    public List<QuoteDTO> getCurrentQuotes() {
        return new ArrayList<>();
    }
}
