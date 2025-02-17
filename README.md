# Root Logger - Set to TRACE to enable all levels
rootLogger.level = trace
rootLogger.appenderRefs = console
rootLogger.appenderRef.console.ref = Console

# Console Appender
appender.console.type = Console
appender.console.name = Console
appender.console.target = SYSTEM_OUT
appender.console.layout.type = PatternLayout
appender.console.layout.pattern = %d{yyyy-MM-dd HH:mm:ss} %-5level [%style{%C{1}:%L}{cyan}] :: %style{%msg%n}{trace:cyan, debug:magenta, info:blue, warn:yellow, error:red, fatal:bold red}