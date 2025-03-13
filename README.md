<configuration>

    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss} [%thread] %highlight(%level):%cyan(%file):%line - %msg%n</pattern>
        </encoder>
    </appender>

    <logger name="your.package.name" level="DEBUG">
        <appender-ref ref="CONSOLE"/>
    </logger>

    <root level="INFO">
        <appender-ref ref="CONSOLE"/>
    </root>

    <springProfile name="dev">
        <root level="DEBUG">
            <appender-ref ref="CONSOLE"/>
        </root>
    </springProfile>

</configuration>
