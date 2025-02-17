<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Properties>
        <!-- Force Enable ANSI Colors -->
        <Property name="log4j2.enableJansi">true</Property>
    </Properties>

    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout>
                <Pattern>%d{yyyy-MM-dd HH:mm:ss} %highlight(%-5level){TRACE:orange, DEBUG:yellow, INFO:cyan, WARN:red bold, ERROR:red bold} [%style(%file:%line){cyan}] :: %msg%n</Pattern>
            </PatternLayout>
        </Console>
    </Appenders>

    <Loggers>
        <Root level="trace">
            <AppenderRef ref="Console"/>
        </Root>
    </Loggers>
</Configuration>