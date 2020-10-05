package pl.arcadeit.forex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.arcadeit.forex.domain.stock.Quote;
import pl.arcadeit.forex.model.stock.QuoteDTO;
import pl.arcadeit.forex.service.stock.CurrentQuotesService;
import pl.arcadeit.forex.service.stock.StockService;

import java.util.List;

@RestController
@RequestMapping("stock")
public class CurrentQuotesController {


    StockService stockService;

    CurrentQuotesService currentQuotesServices;

    @Autowired
    public void setStockService(StockService stockService) {
        this.stockService = stockService;
    }

    @Autowired
    public void setCurrentQuotesServices(CurrentQuotesService currentQuotesServices) {
        this.currentQuotesServices = currentQuotesServices;
    }

    @GetMapping("/quote/{exchangeSymbol}")
    public List<QuoteDTO> getCurrentQuotes(@PathVariable String exchangeSymbol) {
        return currentQuotesServices.getCurrentQuotes();
    }

    @GetMapping("/quote/{symbol}")
    public QuoteDTO getQuote(@PathVariable String symbol) {
        QuoteDTO quote = stockService.getQuoteByTicker(symbol);
        return quote;
    }

    @GetMapping("/quote")
    public List<Quote> getQuote() {
        return stockService.getAllSavedQuotes();
    }
}
