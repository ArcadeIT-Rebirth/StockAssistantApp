package pl.arcadeit.forex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.arcadeit.forex.model.stock.QuoteDTO;
import pl.arcadeit.forex.service.stock.ExchangeService;

import java.util.List;

@RestController
@RequestMapping("/api/stockQuotation")
public class StockQuotationController {

    @Autowired
    ExchangeService exchangeService;

    @GetMapping("/{exchangeSymbol}")
    public List<QuoteDTO> getTodayQuotesForExchange(@PathVariable String exchangeSymbol) {
        return exchangeService.getTodayQuotesForExchange(exchangeSymbol);
    }
}
