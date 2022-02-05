package root.service;

import root.model.Response;

import java.math.BigDecimal;

public interface CurrencyServiceApi {
    public Response getStockQuotesByTicket(BigDecimal amount, String targetCurrency);
}
