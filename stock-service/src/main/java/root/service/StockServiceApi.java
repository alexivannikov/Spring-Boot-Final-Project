package root.service;

import root.model.StockData;

import java.util.List;

public interface StockServiceApi {

    public StockData getStockQuotesByTicket(boolean getHistoricalData, String tickets);
}
