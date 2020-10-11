package pl.arcadeit.forex.service.stock;

public enum ExchangeSymbol {
    US("USExchangeFacade"),
    WA("WAExchangeFacade");

    private String classImpl;
    ExchangeSymbol(String facadeImpl) {
        this.classImpl = facadeImpl;
    }

    public String getClassImpl() {
        return classImpl;
    }
}
