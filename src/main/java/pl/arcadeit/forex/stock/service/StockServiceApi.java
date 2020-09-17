package pl.arcadeit.forex.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arcadeit.forex.stock.dao.StockDao;
import pl.arcadeit.forex.stock.entity.Stock;

@Service
public class StockServiceApi implements StockService {

    private final StockDao stockDao;

    @Autowired
    public StockServiceApi(StockDao stockDao) {
        this.stockDao = stockDao;
    }

    @Override
    public Stock findStockByTicker(String ticker) {
        return stockDao.findStockByTicker(ticker);
    }
}
