){faint,green,red}2025-02-17 17:03:07  INFO [%boldGreen(HrmsController.java:42)] :: disableAnsi=true(Log Class type: class org.apache.logging.log4j.core.Logger

above log I am getting for Log : log.info("Log Class type: "+log.getClass());  

<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss}  %-5level[%boldGreen(%file:%line)] :: %highlight(%msg%n){faint,green,red}"/>
        </Console>
    </Appenders>
    <Loggers>
        <Root level="info">
            <AppenderRef ref="Console"/>
        </Root>
    </Loggers>
</Configuration>


In this configuration or pattern I need the logs color as per the log level


		log.trace("Trace Message!"); = Orange
        log.debug("Debug Message!"); = Yellow
        log.info("Info Message!"); = skyblue
        log.warn("Warn Message!");= bold red
