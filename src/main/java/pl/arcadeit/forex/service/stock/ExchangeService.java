package pl.arcadeit.forex.service.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arcadeit.forex.model.stock.QuoteDTO;

import java.util.List;

@Service
public class ExchangeService {

    ExchangeFacade exchangeFacade;
    ExchangeFacadeLocator exchangeFacadeLocator;

    public ExchangeService(@Autowired ExchangeFacade exchangeFacade, @Autowired ExchangeFacadeLocator exchangeFacadeLocator) {
        this.exchangeFacade = exchangeFacade;
        this.exchangeFacadeLocator = exchangeFacadeLocator;
    }

    public List<QuoteDTO> getTodayQuotesForExchange(String exchangeSymbol) {
        ExchangeFacade exchangeFacade = exchangeFacadeLocator.getExchangeFacadeBySymbol(
                ExchangeSymbol.valueOf(exchangeSymbol).getClassImpl()
            );

        return exchangeFacade.getRealTimeQuotes();
    }
}
