package de.denizugurlu.spring.notes.gatewayservice.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class GatewayRoutes {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/notes/**")
                        .filters(f ->f.stripPrefix(1))
//                        .uri("http://httpbin.org:80"))
                        .uri("lb://notes-service"))
                .build();
    }
}
