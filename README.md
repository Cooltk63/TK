# ==============================
# 1️⃣ LOG FILE LOCATION (Based on OS)
# ==============================

# Define log directory path dynamically for Windows and Linux
logging.file.path=${LOG_DIR:${sys:os.name}.contains('Windows') ? 'C:/media/bs' : '/media/bs'}

# Log file name (Rolling logs per day)
logging.file.name=${logging.file.path}/application.log

# Log level configuration for different packages
logging.level.root=INFO
logging.level.com.yourpackage=TRACE  # Adjust for your package

# ==============================
# 2️⃣ LOG FORMAT (With Colors)
# ==============================

# Console Log Pattern (Colored Output)
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} [%thread] %highlight(%-5level){FATAL=red, ERROR=bold_red, WARN=yellow, INFO=cyan, DEBUG=green, TRACE=magenta} %cyan(%logger{36}) - %msg%n

# File Log Pattern
logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n

# ==============================
# 3️⃣ LOG ROTATION (Daily Rolling)
# ==============================

# Log file rotation strategy
logging.logback.rollingpolicy.file-name-pattern=${logging.file.path}/application-%d{yyyy-MM-dd}.log
logging.logback.rollingpolicy.max-history=7  # Keep logs for last 7 days
logging.logback.rollingpolicy.clean-history-on-start=true  # Delete old logs