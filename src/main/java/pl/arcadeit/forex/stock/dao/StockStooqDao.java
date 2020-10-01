package pl.arcadeit.forex.stock.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.arcadeit.forex.stock.entity.Stock;
import pl.arcadeit.forex.stock.entity.StooqPrice;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


@Repository
public class StockStooqDao implements StockDao{

    @Autowired
    public StockStooqDao() {
    }

    public StooqPrice callSinglePrice(String ticker, LocalDate localDate) {

        //prepare date format add 0 in month and day if less than 10
        String date = "" + localDate.getYear()
                + (localDate.getMonthValue()>9?"":"0") + localDate.getMonthValue()
                + (localDate.getDayOfMonth()>9?"":"0") + localDate.getDayOfMonth();

        //creating URL address
        String token = "&u=d79zx7w75zs7wcezo8089c6m"; //token used for file configuration
        String address = "https://stooq.pl/db/d/?d=" + date + "&t=d" + token;

        //reading values from file and converting to StooqPrice
        try {
            URL url = new URL(address);
            Scanner scanner = new Scanner(url.getPath());

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.startsWith(ticker)) {
                    String[] price = line.split(",");
                    return new StooqPrice(Double.valueOf(price[4]),
                            Double.valueOf(price[5]), 
                            Double.valueOf(price[6]),
                            Double.valueOf(price[7]), 
                            Integer.valueOf(price[8]), 
                            Integer.valueOf(price[2]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //get last 30 quotations
    public ArrayList<StooqPrice> getActualQuotes (String ticker) {
        ArrayList<StooqPrice> stooqPrices = new ArrayList<>();
        LocalDate localDate = LocalDate.now();

        for (int i = 1; i <= 30; i++) {
            stooqPrices.add(callSinglePrice(ticker, localDate.minusDays(i)));
        }
        return stooqPrices;
    }

    @Override
    public Stock findStockByTicker(String ticker) {
        Stock stock = new Stock(ticker, getActualQuotes(ticker));
        System.out.println(stock);
        return stock;
    }
}
