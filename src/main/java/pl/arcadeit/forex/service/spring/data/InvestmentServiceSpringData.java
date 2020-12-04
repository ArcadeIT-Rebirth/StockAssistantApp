package pl.arcadeit.forex.service.spring.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arcadeit.forex.domain.Investment;
import pl.arcadeit.forex.repository.InvestmentRepository;
import pl.arcadeit.forex.service.InvestmentService;

import java.util.List;
import java.util.Optional;

@Service
public class InvestmentServiceSpringData implements InvestmentService {

    InvestmentRepository investmentRepository;

    @Autowired
    public InvestmentServiceSpringData(InvestmentRepository investmentRepository) {
        this.investmentRepository = investmentRepository;
    }

    @Override
    public List<Investment> findAllInvestments() {
        return investmentRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Investment findInvestmentById(int id) {
        Optional<Investment> result = investmentRepository.findById(id);
        Investment investment = null;
        if (result.isPresent()) {
            investment = result.get();
        } else {
            throw new RuntimeException("Id not found");
        }
        return investment;
    }

    @Override
    public void saveInvestment(Investment investment) {
        investmentRepository.save(investment);
    }

    @Override
    public void deleteInvestmentById(int id) {
        investmentRepository.deleteById(id);
    }
}
