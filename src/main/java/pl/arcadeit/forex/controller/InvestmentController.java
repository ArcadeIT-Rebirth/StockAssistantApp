package pl.arcadeit.forex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.arcadeit.forex.domain.Investment;
import pl.arcadeit.forex.service.spring.data.InvestmentServiceSpringData;

import java.util.List;

@RestController
@RequestMapping("/api/investment")
public class InvestmentController {

    InvestmentServiceSpringData service;

    @Autowired
    public InvestmentController(InvestmentServiceSpringData service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Investment> getAllInvestments() {
        return service.findAllInvestments();
    }

    @GetMapping("/id/{investmentId}")
    public Investment getInvestment(@PathVariable int investmentId) {
        Investment investment = service.findInvestmentById(investmentId);
        if (investment == null) {
            throw new RuntimeException("No such Id in database");
        }
        return investment;
    }

    @PostMapping("/register")
    public Investment addInvestment(@RequestBody Investment investment) {
        investment.setId(0);
        service.saveInvestment(investment);
        return investment;
    }

    @PutMapping("/register")
    public Investment updateInvestment(@RequestBody Investment investment) {
        service.saveInvestment(investment);
        return investment;
    }

    @DeleteMapping("/remove/{investmentId}")
    public String deleteInvestment(@PathVariable int investmentId) {
        Investment investment = service.findInvestmentById(investmentId);
        if (investment == null) {
            throw new RuntimeException("Id not found");
        }
        service.deleteInvestmentById(investmentId);
        return "Investment successfully deleted";
    }

}
