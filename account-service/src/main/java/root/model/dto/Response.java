package root.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Response {
    private BigDecimal amount;

    private String sourceCurrency;

    private BigDecimal resultAmount;

    private String targetCurrency;
}
