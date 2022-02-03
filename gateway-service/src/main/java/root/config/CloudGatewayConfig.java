package root.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CloudGatewayConfig {

    @Value("${service.stock.uri}")
    private String stockUri;

    @Value("${service.stock.audience}")
    private String stockAudience;

    @Value("${service.stock.path.stock-quotes}")
    private String stockQuotes;

    @Value("${service.stock.path.historical-quotes}")
    private String historicalQuotes;

    @Value("${service.transfer.audience}")
    private String transferAudience;

    @Value("${service.transfer.uri}")
    private String transferUri;

    @Value("${service.transfer.transfer-path}")
    private String transferPath;

    @Value("${service.transfer.bankbook-path}")
    private String bankBookPath;

    private final OAuth2ClientGatewayFilter filterFactory;

    CloudGatewayConfig(OAuth2ClientGatewayFilter filterFactory) {
        this.filterFactory = filterFactory;
    }

    @Bean
    RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route("get-stock-quotes", r -> r.path(stockQuotes)
                        .filters(f -> f.filter(filterFactory.apply(stockAudience)).removeRequestHeader("Cookie"))
                        .uri(stockUri))
                .route("get-historical-quotes", r -> r.path(historicalQuotes)
                        .filters(f -> f.filter(filterFactory.apply(stockAudience)).removeRequestHeader("Cookie"))
                        .uri(stockUri))
                .route("transfer", r -> r.path(transferPath)
                        .filters(f -> f.filter(filterFactory.apply(transferAudience)).removeRequestHeader("Cookie"))
                        .uri(transferUri))
                .route("bank-book", r -> r.path(bankBookPath)
                        .filters(f -> f.filter(filterFactory.apply(transferAudience)).removeRequestHeader("Cookie"))
                        .uri(transferUri))
                .build();
    }
}
