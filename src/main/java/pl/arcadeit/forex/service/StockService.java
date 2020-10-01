package pl.arcadeit.forex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.arcadeit.forex.domain.stock.Quote;
import pl.arcadeit.forex.model.stock.QuoteDTO;
import pl.arcadeit.forex.repository.QuoteRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {

    @Autowired
    QuoteRepository quoteRepository;

    @Value("${finnhub.token}")
    private String token;

    public QuoteDTO getQuoteByTicker(String ticker) {

        String sourceUrl = getQuoteSourceUrl(ticker, token);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<QuoteDTO> response = restTemplate.getForEntity(sourceUrl, QuoteDTO.class, QuoteDTO.class);

        Quote quote = convertQuoteDTOtoQuote(ticker, response.getBody());

        quoteRepository.save(quote);

        return response.getBody();
    }

    @Transactional
    public void saveQuote(Quote quote) {
        quoteRepository.save(quote);
    }

    private Quote convertQuoteDTOtoQuote(String ticker, QuoteDTO quoteDTO) {
        return Quote.builder()
                .ticker(ticker)
                .openPrice(quoteDTO.getOpenPrice())
                .closePrice(quoteDTO.getClosePrice())
                .highPrice(quoteDTO.getHighPrice())
                .lowPrice(quoteDTO.getLowPrice())
                .previousClosePrice(quoteDTO.getPreviousClosePrice())
                .date(quoteDTO.getDate())
                .build();
    }

    public List<Quote> getAllSavedQuotes() {
        return quoteRepository.findAll();
    }

    private String getQuoteSourceUrl(String symbol, String token) {
        String finnhubSourceUrl = "https://finnhub.io/api/v1/quote?";
        return finnhubSourceUrl + "symbol=" + symbol + "&token=" + token;
    }
}
