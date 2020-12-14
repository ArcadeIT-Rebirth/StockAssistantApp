package pl.arcadeit.forex.service.spring.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.arcadeit.forex.domain.Asset;
import pl.arcadeit.forex.domain.Investment;
import pl.arcadeit.forex.repository.InvestmentRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvestmentServiceSpringDataTest {

    private final Investment firstInvestment = Investment.builder()
            .id(100)
            .quantity(1000)
            .asset(new Asset())
            .build();
    private final Investment secondInvestment = Investment.builder()
            .id(101)
            .quantity(1000)
            .asset(new Asset())
            .build();
    private final Investment thirdInvestment = Investment.builder()
            .id(102)
            .quantity(1000)
            .asset(new Asset())
            .build();

    private final List<Investment> investments = List.of(firstInvestment, secondInvestment, thirdInvestment);

    @InjectMocks
    InvestmentServiceSpringData service;

    @Mock
    InvestmentRepository repository;

    @Test
    void findAllInvestments_shouldReturnInvestments() {
        //given
        when(repository.findAllByOrderByIdAsc()).thenReturn(investments);
        //when
        List<Investment> result = service.findAllInvestments();
        //then
        assertEquals(investments, result);
        verify(repository, times(1)).findAllByOrderByIdAsc();
    }

    @Test
    void findInvestmentById_shouldReturnFirstInvestment() {
        //given
        when(repository.findById(100)).thenReturn(Optional.of(firstInvestment));
        //when
        Investment result = service.findInvestmentById(100);
        //then
        assertEquals(firstInvestment, result);
        verify(repository, times(1)).findById(100);
    }

    @Test
    void saveInvestment_shouldInvokeRepositorySave() {
        //given
        //when
        service.saveInvestment(firstInvestment);
        service.saveInvestment(secondInvestment);
        //then
        verify(repository, times(1)).save(firstInvestment);
        verify(repository, times(2)).save(any(Investment.class));
    }

    @Test
    void deleteInvestmentById() {
        //given
        //when
        service.deleteInvestmentById(1);
        service.deleteInvestmentById(2);
        //then
        verify(repository, times(1)).deleteById(1);
        verify(repository, times(2)).deleteById(anyInt());
    }
}
