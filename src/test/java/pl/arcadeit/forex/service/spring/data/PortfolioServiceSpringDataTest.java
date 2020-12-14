package pl.arcadeit.forex.service.spring.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.arcadeit.forex.domain.Portfolio;
import pl.arcadeit.forex.repository.PortfolioRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PortfolioServiceSpringDataTest {

    private final Portfolio firstPortfolio = Portfolio.builder()
            .id(100)
            .cash(1000)
            .build();
    private final Portfolio secondPortfolio = Portfolio.builder()
            .id(200)
            .cash(1200)
            .build();
    private final Portfolio thirdPortfolio = Portfolio.builder()
            .id(300)
            .cash(1500)
            .build();

    private final List<Portfolio> portfolios = List.of(firstPortfolio, secondPortfolio, thirdPortfolio);

    @InjectMocks
    PortfolioServiceSpringData service;

    @Mock
    PortfolioRepository repository;

    @Test
    void findAllPortfolios() {
        //given
        when(repository.findAllByOrderByIdAsc()).thenReturn(portfolios);
        //when
        List<Portfolio> result = service.findAllPortfolios();
        //then
        assertEquals(portfolios, result);
        verify(repository, times(1)).findAllByOrderByIdAsc();
    }

    @Test
    void findPortfolioById() {
        //given
        when(repository.findById(100)).thenReturn(Optional.of(firstPortfolio));
        //when
        Portfolio result = service.findPortfolioById(100);
        //then
        assertEquals(firstPortfolio, result);
        verify(repository, times(1)).findById(100);
    }

    @Test
    void savePortfolio() {
        //given
        //when
        service.savePortfolio(firstPortfolio);
        service.savePortfolio(secondPortfolio);
        //then
        verify(repository, times(1)).save(firstPortfolio);
        verify(repository, times(2)).save(any(Portfolio.class));
    }

    @Test
    void deletePortfolioById() {
        //given
        //when
        service.deletePortfolioById(1);
        service.deletePortfolioById(2);
        //then
        verify(repository, times(1)).deleteById(1);
        verify(repository, times(2)).deleteById(anyInt());
    }
}
