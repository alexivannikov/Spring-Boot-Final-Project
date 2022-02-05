package root.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustomHeaders {
    private String accessToken;

    private String userInfo;
}