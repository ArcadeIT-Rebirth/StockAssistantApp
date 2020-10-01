package pl.arcadeit.forex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.arcadeit.forex.domain.stock.Quote;
import pl.arcadeit.forex.model.stock.QuoteDTO;
import pl.arcadeit.forex.service.StockService;

import java.util.List;

@RestController
@RequestMapping("stock")
public class StockController {

    @Autowired
    StockService stockService;

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
