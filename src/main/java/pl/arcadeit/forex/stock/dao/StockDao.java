package pl.arcadeit.forex.stock.dao;

import pl.arcadeit.forex.stock.entity.Stock;

public interface StockDao {
    Stock findStockByTicker(String ticker);
}
