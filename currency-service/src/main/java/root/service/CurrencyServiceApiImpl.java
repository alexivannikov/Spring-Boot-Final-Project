package root.service;

import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import root.model.Exchange;
import root.model.Response;

import java.math.BigDecimal;

@Component
class CurrencyServiceApiImpl implements CurrencyServiceApi {
    private final String token;

    private final String url;

    public CurrencyServiceApiImpl(@Value("${token}") String token, @Value("${url}") String url) {
        this.token = token;
        this.url = url;
    }

    @Override
    public Response convert(BigDecimal amount, String targetCurrency) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity <Exchange> responseEntity = restTemplate.getForEntity(
                String.format(url, token, targetCurrency),
                Exchange.class
        );

        BigDecimal coefficient = responseEntity.getBody().getRates().get(targetCurrency);

        Response response = Response.builder()
                .amount(amount)
                .sourceCurrency("EUR")
                .resultAmount(amount.multiply(coefficient))
                .targetCurrency(targetCurrency)
                .build();

        return response;
    }
}
