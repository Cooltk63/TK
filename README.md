<?xml version="1.0" encoding="UTF-8"?>
<configuration>

    <!-- ========== 1️⃣ DEFINE GLOBAL PROPERTIES ========== -->
    <!-- Directory where log files will be stored -->
<!--    <property name="LOG_DIR" value="D:\media\BS\logs" />-->
    <springProperty name="APP_NAME" source="spring.application.name" />
    <springProperty name="LOG_DIR" source="logging.file.path"/>
    <!-- Maximum history of log files (days to retain) -->
    <property name="MAX_HISTORY" value="7" />

    <!-- Log file name pattern -->
    <property name="FILE_NAME_PATTERN" value="${LOG_DIR}/${APP_NAME}-%d{yyyy-MM-dd}.log" />

    <!-- ========== 2️⃣ CONSOLE LOGGING (COLORED OUTPUT) ========== -->
    <appender name="Console" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>
                %d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level:%file%line - %highlight(%msg){ERROR=red, WARN=yellow, INFO=cyan, DEBUG=green, TRACE=magenta}%n
            </pattern>
        </encoder>
    </appender>

    <!-- ========== 3️⃣ FILE LOGGING WITH ROTATION ========== -->
    <appender name="RollingFile" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!-- Main log file -->
        <file>${LOG_DIR}/${APP_NAME}.log</file>

        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>

        <!-- Rolling policy for daily rotation -->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!-- Create a new log file every day -->
            <fileNamePattern>${FILE_NAME_PATTERN}</fileNamePattern>

            <!-- Keep logs for the last 7 days -->
            <maxHistory>${MAX_HISTORY}</maxHistory>

            <!-- Compress old logs -->
            <cleanHistoryOnStart>true</cleanHistoryOnStart>
        </rollingPolicy>
    </appender>

    <!-- ========== 4️⃣ LOG LEVEL CONFIGURATIONS ========== -->
    <!-- TRACE (Gray) -->
    <logger name="com.yourpackage" level="trace" additivity="false">
        <appender-ref ref="Console"/>
        <appender-ref ref="RollingFile"/>
    </logger>

    <!-- DEBUG (Green) -->
    <logger name="com.yourpackage" level="debug" additivity="false">
        <appender-ref ref="Console"/>
        <appender-ref ref="RollingFile"/>
    </logger>

    <!-- INFO (Blue) -->
    <logger name="com.yourpackage" level="info" additivity="false">
        <appender-ref ref="Console"/>
        <appender-ref ref="RollingFile"/>
    </logger>

    <!-- WARN (Yellow) -->
    <logger name="com.yourpackage" level="warn" additivity="false">
        <appender-ref ref="Console"/>
        <appender-ref ref="RollingFile"/>
    </logger>

    <!-- ERROR (Bold Red) -->
    <logger name="com.yourpackage" level="error" additivity="false">
        <appender-ref ref="Console"/>
        <appender-ref ref="RollingFile"/>
    </logger>

    <!-- FATAL (Magenta) -->
    <logger name="com.yourpackage" level="fatal" additivity="false">
        <appender-ref ref="Console"/>
        <appender-ref ref="RollingFile"/>
    </logger>

    <!-- ========== 5️⃣ ROOT LOGGER (DEFAULT FOR ALL) ========== -->
    <root level="info">
        <appender-ref ref="Console"/>
        <appender-ref ref="RollingFile"/>
    </root>

</configuration>


I nned above .xml config inside application.property file with changines anything in exitsing functionality or anything if path related changes required you can change but do not change any functionality or single owrd or anything 
