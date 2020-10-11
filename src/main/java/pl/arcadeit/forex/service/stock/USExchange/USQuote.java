package pl.arcadeit.forex.service.stock.USExchange;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.arcadeit.forex.model.stock.QuoteDTO;

@Service
public class USQuote {

    @Value("${finnhub.token}")
    private static String token;
    private static RestTemplate restTemplate = new RestTemplate();

    public static QuoteDTO getTodayQuote(String ticker) {
        String url = "https://finnhub.io/api/v1/quote?symbol=" + ticker +"&token=" + token;
        ResponseEntity<QuoteDTO> result = restTemplate.getForEntity(url, QuoteDTO.class);

        return result.getBody();
    }
}
