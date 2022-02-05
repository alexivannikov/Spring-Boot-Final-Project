package root.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class AuthRequest {
    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("client_secret")
    private String clientSecret;

    @JsonProperty("audience")
    private String audience;

    @JsonProperty("grant_type")
    private String grantType;

    @Override
    public String toString() {
        return "{" +
                "clientId=\"" + clientId + '\"' +
                ", clientSecret=\"" + clientSecret + '\"' +
                ", audience=\"" + audience + '\"' +
                ", grantType=\"" + grantType + '\"' +
                '}';
    }
}
