package root.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Meta {
    private int requested;

    private int returned;

    private String ticker;

    private String name;
}
