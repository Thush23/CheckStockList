package com.example.stockchecklist;

public class IexTradingStocks {

    private String symbol;
    private String companyName;
    private String primaryExchange;
    private double iexRealTimePrice;

    public IexTradingStocks(String symbol, String companyName, String primaryExchange, double iexRealTimePrice) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.primaryExchange = primaryExchange;
        this.iexRealTimePrice = iexRealTimePrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPrimaryExchange() {
        return primaryExchange;
    }

    public void setPrimaryExchange(String primaryExchange) {
        this.primaryExchange = primaryExchange;
    }

    public double getIexRealTimePrice() {
        return iexRealTimePrice;
    }

    public void setIexRealTimePrice(double iexRealTimePrice) {
        this.iexRealTimePrice = iexRealTimePrice;
    }
}
