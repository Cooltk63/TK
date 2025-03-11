<configuration>
    <!-- This property pulls in the custom pattern you want.
         It defaults to the specified value if logging.pattern.console is not set -->
    <springProperty name="CONSOLE_LOG_PATTERN"
                    source="logging.pattern.console"
                    defaultValue="%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level:%file:%line - %highlight(%msg){ERROR=red, WARN=yellow, INFO=yellow, DEBUG=green, TRACE=magenta}%n" />
    
    <appender name="Console" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>${CONSOLE_LOG_PATTERN}</pattern>
        </encoder>
    </appender>
    
    <root level="INFO">
        <appender-ref ref="Console"/>
    </root>
</configuration>