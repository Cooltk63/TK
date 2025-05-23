Vulnerability
J2EE Bad Practices: Threads

Vulnerability Description in Detail
The method sftpFile() in IRISServiceImpl.java calls sleep()  on line 571. Thread management in a web application is forbidden in some circumstances and is always highly error prone.

Likely Impact
The method sftpFile() in CriMarrSerivceImpl.java calls sleep()  on line 74. Thread management in a web application is forbidden in some circumstances and is always highly error prone.

Recommendation
Avoid managing threads directly from within the web application. Instead use standards such as message driven beans and the EJB timer service that are provided by the application container.

Code Imapcted:

 try {
            JSch jsch = new JSch();
            session = jsch.getSession(sftpUser, sftpHost, sftpPort);
            session.setPassword(sftpPass);
            Properties config = new Properties();   // java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            log.info("Session Connected ...!");
            channel = session.openChannel("sftp");
            channel.connect();
            log.info("Creating the Folder on Path :" + folderPath);
            // Create directory if not created
            folderPath = folderPath.replace("\\", "/");
            log.info("Creating the Folder on Path :" + folderPath);
            channelExec = (ChannelExec) session.openChannel("exec");
            channelExec.setCommand("mkdir -p " + folderPath);  // -p if not create, create folder
            channelExec.connect();

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                return 0;
            }
            channelSftp = (ChannelSftp) channel;
            channelSftp.cd("/");
            channelSftp.cd(folderPath);

            help me to resolve this error and keep in mind I am uisng the java 8
