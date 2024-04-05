package dev.cere.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {
    @Value("${services.content.uri}")
    private String contentServiceUri;

    @Value("${services.users.uri}")
    private String usersServiceUri;

    @Value("${services.recommendations.uri}")
    private String recommendationsServiceUri;

    @Value("${services.reviews.uri}")
    private String reviewsServiceUri;

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/content/*").or().path("/content").uri(contentServiceUri))
                .route(r -> r.path("/users/*").or().path("/users").uri(usersServiceUri))
                .route(
                        r ->
                                r.path("/recommendations/*")
                                        .or()
                                        .path("/recommendations")
                                        .uri(recommendationsServiceUri))
                .route(r -> r.path("/reviews/*").or().path("/reviews").uri(reviewsServiceUri))
                .build();
    }
}
