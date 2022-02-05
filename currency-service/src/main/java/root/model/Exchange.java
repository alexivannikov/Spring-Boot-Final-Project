package root.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class Exchange {
    private String base;

    private String date;

    private Map<String, BigDecimal> rates;
}
