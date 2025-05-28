int maxAttempts = 3;
int attempt = 0;
boolean success = false;

while (attempt < maxAttempts && !success) {
    try {
        channelSftp = (ChannelSftp) channel;
        channelSftp.cd("/");
        channelSftp.cd(folderPath);
        success = true; // If we reach here, cd was successful
    } catch (SftpException e) {
        attempt++;
        log.warn("Attempt " + attempt + " to change directory failed. Retrying...", e);
        if (attempt >= maxAttempts) {
            log.error("Failed to change directory after " + maxAttempts + " attempts");
            return 0;
        }
        try {
            TimeUnit.SECONDS.sleep(1); // Use TimeUnit for readability
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt(); // Restore interrupt status
            return 0;
        }
    }
}