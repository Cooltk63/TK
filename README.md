# ========================= 1️⃣ LOG FILE LOCATION =========================
# Windows: D:/media/BS/logs
# Linux: /media/bs/logs (Uncomment for Linux)
logging.file.path=D:/media/BS/logs
# logging.file.path=/media/bs/logs

# Log file name based on spring.application.name
logging.file.name=${logging.file.path}/${spring.application.name}.log

# ========================= 2️⃣ LOG ROLLING POLICIES =========================
# Keep logs for 7 days (Old logs auto-deleted)
logging.logback.rollingpolicy.max-history=7

# Log file rotation based on size (Each file max 10MB)
logging.logback.rollingpolicy.max-file-size=10MB

# Roll logs daily (Time-based rolling)
logging.logback.rollingpolicy.file-name-pattern=${logging.file.path}/${spring.application.name}-%d{yyyy-MM-dd}.%i.log.zip

# ========================= 3️⃣ LOG FORMATTING =========================
# Console log pattern (Color-coded output)
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level:%file:%line - %highlight(%msg){ERROR=red, WARN=yellow, INFO=cyan, DEBUG=green, TRACE=magenta}%n

# File log pattern
logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n