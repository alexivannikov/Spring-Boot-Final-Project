package root.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import root.model.StockData;

@Component
class StockServiceApiImpl implements StockServiceApi {
    private final String token;
    private final String urlStockQuotes;
    private final String urlHistoricalStock;

    public StockServiceApiImpl(@Value("${token}") String token,
                               @Value("${url.quotes}") String urlStockQuotes,
                               @Value("${url.historical-quotes}") String urlHistoricalStock) {
        this.token = token;
        this.urlStockQuotes = urlStockQuotes;
        this.urlHistoricalStock = urlHistoricalStock;
    }

    @Override
    public StockData getStockQuotesByTicket(boolean getHistoricalData, String tickets) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<StockData> responseEntity = null;

        if(getHistoricalData){
            responseEntity = restTemplate.getForEntity(
                    String.format(urlHistoricalStock, tickets, token),
                    StockData.class
            );
        }
        else{
            responseEntity = restTemplate.getForEntity(
                    String.format(urlStockQuotes, tickets, token),
                    StockData.class
            );
        }

        return responseEntity.getBody();
    }
}
