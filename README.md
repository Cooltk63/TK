package com.fincore.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class provides the RedisRateLimiter bean.
 * Spring Cloud Gateway uses it to apply rate limits per user or per IP.
 *
 * Redis must be running for this to work.
 * If Redis is disabled (gateway.ratelimiter.enabled=false), the limiter won't be applied.
 */
@Configuration
public class RedisRateLimiterProvider {

    /**
     * Creates a RedisRateLimiter with:
     *  - Refill rate: 5 requests per second
     *  - Burst capacity: 10 requests at once
     *
     * @return RedisRateLimiter bean
     */
    @Bean
    public RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(5, 10);
    }
}