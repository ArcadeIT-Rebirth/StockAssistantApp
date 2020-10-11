package pl.arcadeit.forex.service.stock.USExchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arcadeit.forex.model.stock.QuoteDTO;
import pl.arcadeit.forex.service.stock.ExchangeFacade;

import java.util.List;

@Service
public class USExchangeFacade implements ExchangeFacade {

    private USExchange usExchange;

    public USExchangeFacade(@Autowired USExchange usExchange) {
        this.usExchange = usExchange;
    }

    @Override
    public List<QuoteDTO> getRealTimeQuotes() {
        return usExchange.getCurrentQuotation();
    }
}
