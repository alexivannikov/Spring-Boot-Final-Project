package root.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import root.model.Response;
import root.service.CurrencyServiceApi;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ConvertController {
    private final CurrencyServiceApi currencyServiceApi;

    @PostMapping(value = "/convert", produces = "application/json", consumes = {"text/plain", "application/x-www-form-urlencoded"})
    public Response getStockQuotes(@RequestBody String requestParameter) {
        String converterDetails = "";

        if(requestParameter.contains("data")){
            converterDetails = requestParameter.substring(5).replaceAll("%2C", ",");
        }
        else{
            converterDetails = requestParameter;
        }

        String firstDetail = converterDetails.substring(0, converterDetails.indexOf(","));
        BigDecimal amount = new BigDecimal(Double.valueOf(firstDetail));
        String targetCurrency = converterDetails.substring(converterDetails.indexOf(",") + 1);

        return currencyServiceApi.convert(amount, targetCurrency);
    }
}
