package root.service;

import root.model.Response;

import java.math.BigDecimal;

public interface CurrencyServiceApi {
    public Response convert(BigDecimal amount, String targetCurrency);
}
