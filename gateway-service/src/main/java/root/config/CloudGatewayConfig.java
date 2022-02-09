package root.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CloudGatewayConfig {
    @Value("${service.account.uri}")
    private String accountUri;

    @Value("${service.account.path}")
    private String accountPath;

    @Value("${service.account.audience}")
    private String accountAudience;

    @Value("${service.currency.uri}")
    private String currencyUri;

    @Value("${service.currency.path}")
    private String currencyPath;

    @Value("${service.currency.audience}")
    private String currencyAudience;

    @Value("${service.stock.uri}")
    private String stockUri;

    @Value("${service.stock.path}")
    private String stockPath;

    @Value("${service.stock.audience}")
    private String stockAudience;

    private final OAuth2ClientGatewayFilter filterFactory;

    CloudGatewayConfig(OAuth2ClientGatewayFilter filterFactory) {
        this.filterFactory = filterFactory;
    }

    @Bean
    RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route("account-service", r -> r.path(accountPath)
                        .filters(f -> f.filter(filterFactory.apply(accountAudience)).removeRequestHeader("Cookie"))
                        .uri(accountUri))
                .route("currency-service", r -> r.path(currencyPath)
                        .filters(f -> f.filter(filterFactory.apply(currencyAudience)).removeRequestHeader("Cookie").stripPrefix(1))
                        .uri(currencyUri))
                .route("stock-service", r -> r.path(stockPath)
                        .filters(f -> f.filter(filterFactory.apply(stockAudience)).removeRequestHeader("Cookie").stripPrefix(1))
                        .uri(stockUri))
                .build();
    }
}
