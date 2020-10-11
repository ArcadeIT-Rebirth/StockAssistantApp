package pl.arcadeit.forex.service.stock;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ExchangeFacadeLocator {

    @Autowired
    private ApplicationContext context;
    private Map<String, ExchangeFacade> facadeMap;

    public ExchangeFacade getExchangeFacadeBySymbol(String exchangeSymbol) {
        checkExchangeFacade();
        return facadeMap.get(exchangeSymbol);
    }

    private void checkExchangeFacade() {
        if (facadeMap != null)
            return;
        facadeMap = context.getBeansOfType(ExchangeFacade.class);
    }
}
