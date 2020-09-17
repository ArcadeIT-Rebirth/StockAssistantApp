package pl.arcadeit.forex.stock.service;

import pl.arcadeit.forex.stock.entity.Stock;

public interface StockService {
    Stock findStockByTicker(String ticker);
}
