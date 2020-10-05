package pl.arcadeit.forex.service.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arcadeit.forex.domain.stock.Exchange;
import pl.arcadeit.forex.exception.stock.ExchangeCodeException;
import pl.arcadeit.forex.repository.ExchangeRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ExchangeService {

    ExchangeRepository exchangeRepository;

    @Autowired
    public void setExchangeRepository(ExchangeRepository exchangeRepository) {
        this.exchangeRepository = exchangeRepository;
    }

    public Exchange saveOrUpdateExchange(Exchange exchange) {
        try {
            exchange.setCode(exchange.getCode().toUpperCase());
            return exchangeRepository.save(exchange);
        } catch (Exception e) {
            throw new ExchangeCodeException("Exchange CODE ;" + exchange.getCode() + "'already exist.");
        }
    }

    public void deleteExchangeByCode(String code) {
        Optional<Exchange> exchange = exchangeRepository.findById(code);

        if (exchange.isEmpty())
            throw new ExchangeCodeException("Cannot delete exchange with CODE '" + code + "' this exchange doesn't exist.");

        exchangeRepository.delete(exchange.get());
    }

    public Exchange findExchangeByCode(String code) {
        Optional<Exchange> exchange = exchangeRepository.findById(code);
        if (exchange.isEmpty())
            throw new ExchangeCodeException("Exchange CODE '" + code + "' doesn't exist.");

        return exchange.get();
    }

    public Iterable<Exchange> findAllExchanges() {
        return exchangeRepository.findAll();
    }
}
