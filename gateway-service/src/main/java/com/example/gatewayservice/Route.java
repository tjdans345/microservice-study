package com.example.gatewayservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Route {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("member-service",
                        route -> route.path("/member-service")
                        .filters(f -> f.rewritePath("/member-service(?<segment>.*)","${segment}")).uri("lb://member-service")
                )
                .route("order-service",
                        route -> route.path("/order-service")
                                .filters(f -> f.rewritePath("/order-service(?<segment>.*)","${segment}")).uri("lb://order-service")
                )
                .build();
    }

}
