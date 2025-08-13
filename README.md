# List all microservices you want the gateway to route to
# No need to touch Java code when adding/removing services
gateway.services=orders,products,users,inventory,shipping,billing,notifications,analytics,reviews,search,catalog,profile,payments,auth,tracking

# Enable or disable Redis-based rate limiting
gateway.ratelimiter.enabled=true

# Redis connection details
spring.redis.host=localhost
spring.redis.port=6379