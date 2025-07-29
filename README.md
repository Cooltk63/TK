# ========== Application Configuration ==========
spring.application.name=product-service  # Unique service name in Kubernetes

# ========== Server Configuration ==========
server.port=8080                         # Local port application will run on

# ========== Oracle DB Configuration ==========
spring.datasource.url=jdbc:oracle:thin:@//ORACLE-HOST:1521/SERVICENAME
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password

# Recommended Oracle JDBC driver class
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

# Connection Pooling (HikariCP is default in Spring Boot 2.0+)
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.idle-timeout=30000
spring.datasource.hikari.connection-timeout=30000
spring.datasource.hikari.max-lifetime=1800000

# JPA/Hibernate Settings
spring.jpa.show-sql=true                     # Show SQL in logs (disable in prod)
spring.jpa.hibernate.ddl-auto=none           # Use 'none' in prod, 'update' in dev
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.Oracle12cDialect

# ========== Kubernetes Discovery Settings ==========
spring.cloud.kubernetes.discovery.enabled=true             # Enable discovery
spring.cloud.kubernetes.discovery.all-namespaces=false     # Limit to same namespace
spring.cloud.kubernetes.discovery.service-labels[app]=product-service

# ========== Health Checks ==========
management.endpoint.health.probe.enabled=true
management.health.probes.enabled=true
management.endpoint.health.show-details=always
management.endpoints.web.exposure.include=health,info

# ========== Logging ==========
logging.level.org.springframework=INFO
logging.level.com.yourcompany=DEBUG

# ========== Kubernetes ConfigMap/Secret Mounting ==========
# These can be used in Kubernetes to override sensitive info (e.g., DB credentials)
# Spring Cloud Kubernetes automatically maps these if mounted correctly

# You can use placeholders like:
# spring.datasource.username=${DB_USER}
# spring.datasource.password=${DB_PASS}

# Then inject them via K8s Secret/ConfigMap to keep credentials secure.