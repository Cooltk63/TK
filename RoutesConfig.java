package com.fincore.gateway.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Minimal dynamic routes:
 * - For each name in gateway.services, we expose /{name}/**.
 * - In local dev, send to http://localhost:<default-port>
 * - In Kubernetes, set the URI to http://{name}:{port} (Cluster DNS)
 */
@Configuration
public class RoutesConfig {

    @Value("${gateway.services}")
    private String servicesCsv;

    @Value("${gateway.service.default-port:8081}")
    private int defaultPort;

    @Value("${spring.profiles.active:dev}")
    private String profile;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        var rs = builder.routes();
        for (String raw : servicesCsv.split(",")) {
            String svc = raw.trim();
            if (svc.isEmpty()) continue;

            String uri;
            if ("dev".equalsIgnoreCase(profile)) {
                // local dev -> change port per service if you want
                uri = "http://localhost:" + defaultPort;
            } else {
                // k8s -> service DNS name + port
                uri = "http://" + svc + ":" + defaultPort;
            }

            rs.route(svc, r -> r
                    .path("/" + svc + "/**")
                    .filters(f -> f.rewritePath("/" + svc + "/(?<segment>.*)", "/${segment}"))
                    .uri(uri)
            );
        }
        return rs.build();
    }
}


