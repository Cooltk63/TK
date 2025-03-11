<configuration>
  <!-- Define the conversion rule to force Logback to use its native HighlightCompositeConverter -->
  <conversionRule conversionWord="highlight"
                    converterClass="ch.qos.logback.classic.pattern.HighlightCompositeConverter" />

  <!-- Import the logging.pattern.console property from application.properties,
       with a default value in case it's not defined -->
  <springProperty name="CONSOLE_LOG_PATTERN"
                  source="logging.pattern.console"
                  defaultValue="%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level:%file:%line - %highlight(%msg){ERROR=red, WARN=yellow, INFO=yellow, DEBUG=green, TRACE=magenta}%n" />

  <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
    <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
      <!-- Use the property defined above -->
      <pattern>${CONSOLE_LOG_PATTERN}</pattern>
    </encoder>
  </appender>

  <root level="INFO">
    <appender-ref ref="CONSOLE" />
  </root>
</configuration>