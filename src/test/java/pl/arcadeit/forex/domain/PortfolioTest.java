package pl.arcadeit.forex.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PortfolioTest {

    private int firstQuantity = 1000;
    private int fourthQuantity = 500;

    private final Investment firstInvestment = Investment.builder()
            .id(100)
            .quantity(firstQuantity)
            .asset(new Asset("First"))
            .build();
    private final Investment secondInvestment = Investment.builder()
            .id(101)
            .quantity(1000)
            .asset(new Asset("Second"))
            .build();
    private final Investment thirdInvestment = Investment.builder()
            .id(102)
            .quantity(1000)
            .asset(new Asset("Third"))
            .build();
    private final Investment fourthInvestment = Investment.builder()
            .id(102)
            .quantity(fourthQuantity)
            .asset(new Asset("First"))
            .build();

    private List<Investment> listOfTwoInvestments =
            new ArrayList<>(List.of(firstInvestment, secondInvestment));
    private List<Investment> listOfThreeInvestments =
            new ArrayList<>(List.of(firstInvestment, secondInvestment, thirdInvestment));

    private Portfolio firstPortfolio = Portfolio.builder()
            .id(1)
            .investmentList(listOfTwoInvestments)
            .build();
    private Portfolio secondPortfolio = Portfolio.builder()
            .id(1)
            .investmentList(listOfThreeInvestments)
            .build();

    @Test
    void addInvestment_shouldAddThirdInvestmentToFirstPortfolio() {
        //when
        firstPortfolio.addInvestment(thirdInvestment);
        //then
        assertEquals(3, firstPortfolio.getInvestmentList().size());
        assertEquals(secondPortfolio, firstPortfolio);
    }

    @Test
    void addInvestment_shouldIncreaseQuantityOfFirstAsset() {
        //when
        firstPortfolio.addInvestment(fourthInvestment);
        //then
        assertEquals(2, firstPortfolio.getInvestmentList().size());
        assertEquals(firstQuantity + fourthQuantity,
                firstPortfolio.getInvestmentList().get(0).getQuantity());
    }

    @Test
    void removeInvestment_shouldDecreaseQuantityOfFirstAsset() {
        //when
        firstPortfolio.removeInvestment(fourthInvestment);
        //
        assertEquals(2, firstPortfolio.getInvestmentList().size());
        assertEquals(firstQuantity - fourthQuantity,
                firstPortfolio.getInvestmentList().get(0).getQuantity());
    }
}
