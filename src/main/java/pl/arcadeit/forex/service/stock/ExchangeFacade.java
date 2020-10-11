package pl.arcadeit.forex.service.stock;

import pl.arcadeit.forex.model.stock.QuoteDTO;

import java.util.List;

public interface ExchangeFacade {

    List<QuoteDTO> getRealTimeQuotes();
}
