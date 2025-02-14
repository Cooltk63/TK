logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} [%t] %-5level %logger{36} - %msg%n


<configuration>
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36}.%M(%file:%line) - %msg%n</pattern>
        </encoder>
    </appender>

    <root level="info">
        <appender-ref ref="CONSOLE" />
    </root>
</configuration>

logback-spring.xml