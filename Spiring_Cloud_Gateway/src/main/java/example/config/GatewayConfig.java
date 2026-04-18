package example.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                // Category Management Service
                .route("category_service", r -> r.path("/categories/**")
                        .uri("http://localhost:8081")) // Category Service port
                // Element Management Service
                .route("element_service", r -> r.path("/elements/**")
                        .uri("http://localhost:8083")) // Element Service port
                .build();
    }
}
