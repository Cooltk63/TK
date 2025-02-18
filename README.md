# ========================= 3️⃣ LOG FORMATTING =========================
# Console log pattern (Color-coded output)
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level:%file:%line - %highlight(%msg){ERROR=red, WARN=yellow, INFO=cyan, DEBUG=green, TRACE=magenta}%n

# File log pattern (Plain text without color codes)
logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n