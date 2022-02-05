package root.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class StockData {
    private Meta meta;

    private List<Ticket> data;
}
