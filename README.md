<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <conversionRule conversionWord="highlight"
        converterClass="ch.qos.logback.classic.pattern.HighlightCompositeConverter" />

    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>
                %d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level - %highlight(%msg){ERROR=red, INFO=yellow}%n
            </pattern>
        </encoder>
    </appender>

    <root level="info">
        <appender-ref ref="CONSOLE"/>
    </root>
</configuration>