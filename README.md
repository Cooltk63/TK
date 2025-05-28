int maxRetries = 3;
int retryDelay = 1000; // 1 second
boolean success = false;

for (int i = 0; i < maxRetries; i++) {
    try {
        channelSftp.cd("/");
        channelSftp.cd(folderPath);
        success = true;
        break;
    } catch (SftpException e) {
        log.warn("Attempt {} failed to change directory. Retrying in {} ms...", (i + 1), retryDelay);
        try {
            Thread.sleep(retryDelay); // still uses sleep but in a controlled retry block
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt(); // good practice
            return 0;
        }
    }
}

if (!success) {
    log.error("Failed to access folder after retries.");
    return 0;
}