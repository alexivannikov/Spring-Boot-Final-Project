package root.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserInfo {
    private String id;

    private String email;

    private List<String> roles;
}
