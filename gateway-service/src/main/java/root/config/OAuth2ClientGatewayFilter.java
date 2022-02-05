package root.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import root.controller.HomeController;
import root.model.AuthRequest;
import root.model.AuthResponse;
import root.model.CustomHeaders;
import root.model.UserInfo;

import java.util.ArrayList;

@Component
@Slf4j
class OAuth2ClientGatewayFilter extends AbstractGatewayFilterFactory<String> {
    private static final String GRANT_TYPE = "client_credentials";

    @Value("${spring.security.oauth2.client.provider.auth0.issuer-uri}")
    private String tokenUri;

    private final ReactiveClientRegistrationRepository clientRegistrationRepository;
    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public OAuth2ClientGatewayFilter(ReactiveClientRegistrationRepository clientRegistrationRepository,
                                     WebClient webClient, ObjectMapper objectMapper) {
        super(String.class);
        this.clientRegistrationRepository = clientRegistrationRepository;
        this.webClient = webClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public GatewayFilter apply(String audience) {
        return (exchange, chain) -> exchange.getPrincipal()
                .filter(principal -> principal instanceof OAuth2AuthenticationToken)
                .cast(OAuth2AuthenticationToken.class)
                .flatMap(authentication -> authorizedClient(authentication, audience))
                .map(customHeaders -> withBearerAuth(exchange, customHeaders))
                .defaultIfEmpty(exchange).flatMap(chain::filter);
    }

    private Mono<CustomHeaders> authorizedClient(OAuth2AuthenticationToken oauth2Authentication, String audience) {
        String clientRegistrationId = oauth2Authentication.getAuthorizedClientRegistrationId();
        Mono<AuthRequest> requestMono = clientRegistrationRepository
                .findByRegistrationId(clientRegistrationId)
                .flatMap(cr -> authRequest(cr, audience));

        String userInfoStr = convertUserInfo(oauth2Authentication);
        return webClient.post()
                .uri(tokenUri + "oauth/token")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(requestMono, AuthRequest.class)
                .retrieve()
                .bodyToMono(AuthResponse.class)
                .map(AuthResponse::getAccessToken)
                .map(token -> CustomHeaders.builder().accessToken(token).userInfo(userInfoStr).build());
    }

    private Mono<AuthRequest> authRequest(ClientRegistration clientRegistration, String audience) {
        return Mono.just(AuthRequest.builder()
                .clientId(clientRegistration.getClientId())
                .clientSecret(clientRegistration.getClientSecret())
                .audience(audience)
                .grantType(GRANT_TYPE)
                .build());
    }

    private ServerWebExchange withBearerAuth(ServerWebExchange exchange, CustomHeaders customHeaders) {
        return exchange.mutate()
                .request(r -> r.headers(headers -> headers.setBearerAuth(customHeaders.getAccessToken()))
                        .header("user-info", customHeaders.getUserInfo()))
                .build();
    }

    private String convertUserInfo(OAuth2AuthenticationToken oauth2Authentication) {
        String userId = (String) oauth2Authentication.getPrincipal().getAttributes().get("sub");
        String userEmail = oauth2Authentication.getPrincipal().getAttributes().get("email").toString();

        UserInfo userInfo = UserInfo.builder().id(userId).email(userEmail).build();

        try {
            return objectMapper.writeValueAsString(userInfo);
        } catch (JsonProcessingException e) {
            log.error("Convert User Info error!", e);
            return null;
        }
    }
}
