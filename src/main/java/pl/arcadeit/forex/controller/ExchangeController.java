package pl.arcadeit.forex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.arcadeit.forex.domain.stock.Exchange;
import pl.arcadeit.forex.service.MapValidationErrorService;
import pl.arcadeit.forex.service.stock.ExchangeService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/exchange")
public class ExchangeController {

    private ExchangeService exchangeService;

    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    public void setExchangeService(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @Autowired
    public void setMapValidationErrorService(MapValidationErrorService mapValidationErrorService) {
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @PostMapping("")
    public ResponseEntity<?> createNewExchange(@Valid @RequestBody Exchange exchange, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if (errorMap != null) return errorMap;

        Exchange newExchange = exchangeService.saveOrUpdateExchange(exchange);
        return new ResponseEntity<Exchange>(newExchange, HttpStatus.CREATED);
    }

    @GetMapping("/{exchangeCode}")
    public ResponseEntity<?> getExchangeByCode(@PathVariable String exchangeCode) {
        Exchange exchange = exchangeService.findExchangeByCode(exchangeCode);
        return new ResponseEntity<Exchange>(exchange, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Exchange> getAlExchanges() {
        return exchangeService.findAllExchanges();
    }

    @DeleteMapping("/{exchangeCode}")
    public ResponseEntity<?> deleteExchange(@PathVariable String exchangeCode) {
        exchangeService.deleteExchangeByCode(exchangeCode);
        return new ResponseEntity<String>("Exchange with CODE: " + exchangeCode + " was deleted successfully", HttpStatus.OK);
    }
}
