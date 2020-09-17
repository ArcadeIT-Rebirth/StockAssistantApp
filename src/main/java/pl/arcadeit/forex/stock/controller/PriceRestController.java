package pl.arcadeit.forex.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.arcadeit.forex.stock.entity.Stock;
import pl.arcadeit.forex.stock.service.StockService;

@RestController
@RequestMapping("/api")
public class PriceRestController {

    private final StockService stockService;

    @Autowired
    public PriceRestController(StockService stockService) {
        this.stockService = stockService;
    }
    /*
    setting mapping to get quotations from API. qouteTicker is short name of listed company eg. on NASDAQ or NYSE
    examples of tickers: GOOG for Alphabet, APPL for Apple, MSFT for Microsoft
    */
    @GetMapping("/quotation/{quoteTicker}")
    public Stock getContact(@PathVariable String quoteTicker) {

        Stock stock = stockService.findStockByTicker(quoteTicker);

        return stock;
    }
}
