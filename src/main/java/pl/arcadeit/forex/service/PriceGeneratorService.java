package pl.arcadeit.forex.service;

import pl.arcadeit.forex.domain.Asset;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class PriceGeneratorService {

    public PriceGeneratorService() {
    }

    //daily change in range +/-4,2%
    public double getCurrentPrice(LocalDate today, Asset asset) {
        LocalDate baseDate = LocalDate.of(2018, 01,01);

        if (today.isEqual(baseDate)) {
            return asset.getName().hashCode() % 200 * 1.0;
        } else if (today.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            return getCurrentPrice(today.minusDays(1), asset);
        } else if (today.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            return getCurrentPrice(today.minusDays(2), asset);
        } else {
            return multiplier(today, asset) * getCurrentPrice(today.minusDays(1), asset);
        }
    }

    //in range 0.958 - 1.042
    private double multiplier(LocalDate localDate, Asset asset) {
        int day = localDate.getDayOfYear();
        int hash = asset.hashCode();

        int firstMultiplier = day * hash % 52;
        int secondMultiplier = day * hash % 26;
        int thirdMultiplier = day * hash % 9;

        //range 0-84
        int sumOfMultipliers = firstMultiplier + secondMultiplier + thirdMultiplier;
        int sign = sumOfMultipliers % 2;

        int modifier = sumOfMultipliers - 42;

        return sign == 0 ? 1 + modifier / 1000 : 1 - modifier / 1000;
    }

}
