package com.fincore.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * GatewayRoutesConfig
 * --------------------
 * This class defines all the routes (paths) for the API Gateway.
 * Instead of hardcoding each microservice route in Java, we read them from
 * application.properties so you can easily add/remove services without touching code.
 *
 * The gateway:
 *  1. Matches incoming request paths (e.g., /orders/**, /products/**)
 *  2. Rewrites the path so the service receives a clean request (without the prefix)
 *  3. Optionally applies rate limiting
 *  4. Logs the request for debugging
 *  5. Forwards it to the correct microservice via Kubernetes DNS
 */
@Slf4j
@Configuration
public class GatewayRoutesConfig {

    /**
     * List of microservice names, comma-separated in application.properties
     * Example in properties:
     * gateway.services=orders,products,users
     *
     * Spring automatically converts "orders,products,users"
     * into a List<String> like ["orders", "products", "users"]
     */
    @Value("#{'${gateway.services}'.split(',')}")
    private List<String> services;

    /**
     * Controls whether Redis-based rate limiting is enabled.
     * Example in properties:
     * gateway.ratelimiter.enabled=true
     */
    @Value("${gateway.ratelimiter.enabled:false}")
    private boolean isRateLimiterEnabled;

    private final RedisRateLimiterProvider redisRateLimiterProvider;

    public GatewayRoutesConfig(RedisRateLimiterProvider redisRateLimiterProvider) {
        this.redisRateLimiterProvider = redisRateLimiterProvider;
    }

    /**
     * This bean defines all the routes for the API Gateway.
     * It loops through each service in the "gateway.services" list and creates a route.
     *
     * @param builder Spring's RouteLocatorBuilder used to define routes
     * @return A RouteLocator containing all routes
     */
    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();

        for (String service : services) {
            // Trim spaces in case you put "orders, products" in properties
            String trimmedService = service.trim();

            // Kubernetes service DNS name format: http://<service-name>:<port>
            String serviceUri = "http://" + trimmedService + ":8080";

            log.info("Configuring route for service: {} -> {}", trimmedService, serviceUri);

            routes.route(trimmedService, r -> r
                    // Match any path starting with "/<service-name>/..."
                    .path("/" + trimmedService + "/**")
                    .filters(f -> {
                        // 1️⃣ Rate Limiting (optional)
                        if (isRateLimiterEnabled) {
                            f.requestRateLimiter(c ->
                                    c.setRateLimiter(redisRateLimiterProvider.redisRateLimiter()));
                        }

                        // 2️⃣ Rewrite path so microservice doesn't get "/orders/orders"
                        // Example: /orders/list → /list
                        f.rewritePath("/" + trimmedService + "/(?<segment>.*)", "/${segment}");

                        // 3️⃣ Simple logging filter
                        f.filter((exchange, chain) -> {
                            log.info("Gateway forwarding request to [{}]: {}",
                                    trimmedService, exchange.getRequest().getURI());
                            return chain.filter(exchange);
                        });

                        return f;
                    })
                    // 4️⃣ Final destination for the route
                    .uri(serviceUri)
            );
        }

        // Build and return all routes
        return routes.build();
    }
}