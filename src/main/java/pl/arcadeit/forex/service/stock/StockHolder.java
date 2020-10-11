package pl.arcadeit.forex.service.stock;

import pl.arcadeit.forex.model.stock.StockDTO;

import java.util.List;

public interface StockHolder {

    List<StockDTO> getStockList();
}
