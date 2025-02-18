
for beow format getting error
<appender name="Console" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>
                %d{yyyy-MM-dd HH:mm:ss} [%thread] %style(%-5level){FATAL=red, ERROR=bold_red, WARN=bright_yellow, INFO=cyan, DEBUG=bright_green, TRACE=bright_magenta} %cyan(%logger{36}) - %msg%n
            </pattern>
        </encoder>
    </appender>
	
Logging system failed to initialize using configuration from 'null'
java.lang.IllegalStateException: Logback configuration error detected: 
2025-02-18 12:44:12 [main] %PARSER_ERROR[style] o.s.boot.SpringApplication - Application run failed
java.lang.IllegalStateException: java.lang.IllegalStateException: Logback configuration error detected: 
ERROR in ch.qos.logback.core.pattern.parser.Compiler@6cc558c6 - There is no conversion class registered for composite conversion word [style]
ERROR in ch.qos.logback.core.pattern.parser.Compiler@6cc558c6 - Failed to create converter for [%style] keyword
