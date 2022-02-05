package root.controller;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import root.model.StockData;
import root.service.StockServiceApi;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StockController {
    private final StockServiceApi stockServiceApi;

    @PostMapping(value = {"/get-stock-quotes", "/get-historical-quotes"}, produces = "application/json", consumes = "application/x-www-form-urlencoded")
    public StockData getStockQuotes(HttpServletRequest request, @RequestBody String requestParameter) {
        boolean getHistoricalData = false;
        String tickets = requestParameter.substring(5).replaceAll("%2C", ",");

        if(request.getRequestURI().contains("historical")){
            getHistoricalData = true;
        }

        return stockServiceApi.getStockQuotesByTicket(getHistoricalData, tickets);
    }
}
