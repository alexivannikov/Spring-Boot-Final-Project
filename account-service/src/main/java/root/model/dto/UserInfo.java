package root.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class UserInfo {
    private String id;

    private String email;
}