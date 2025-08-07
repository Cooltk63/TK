Vulnerability
Command Injection


Vulnerability Description in Detail
The method sftpBidFiles() in ImportBidDataServiceImpl.java calls ProcessBuilder() with a command built from untrusted data. This call can cause the program to execute malicious commands on behalf of an attacker.

Likely Impact
The method sftpBidFiles() in ImportBidDataServiceImpl.java calls ProcessBuilder() with a command built from untrusted data. This call can cause the program to execute malicious commands on behalf of an attacker.

Recommendation
Do not allow users to have direct control over the commands executed by the program. In cases where user input must affect the command to be run, use the input only to make a selection from a predetermined set of safe commands. If the input appears to be malicious, the value passed to the command execution function should either default to some safe selection from this set or the program should decline to execute any command at all. In cases where user input must be used as an argument to a command executed by the program, this approach often becomes impractical because the set of legitimate argument values is too large or too hard to keep track of. Developers often fall back on implementing a deny list in these situations. A deny list is used to selectively reject or escape potentially dangerous characters before using the input. Any list of unsafe characters is likely to be incomplete and will be heavily dependent on the system where the commands are executed. A better approach is to create a list of characters that are permitted to appear in the input and accept input composed exclusively of characters in the approved set. An attacker may indirectly control commands executed by a program by modifying the environment in which they are executed. The environment should not be trusted and precautions should be taken to prevent an attacker from using some manipulation of the environment to perform an attack. Whenever possible, commands should be controlled by the application and executed using an absolute path. In cases where the path is not known at compile time, such as for cross-platform applications, an absolute path should be constructed from trusted values during execution. Command values and paths read from configuration files or the environment should be sanity-checked against a set of invariants that define valid values. Other checks can sometimes be performed to detect if these sources may have been tampered with. For example, if a configuration file is world-writable, the program might refuse to run. In cases where information about the binary to be executed is known in advance, the program may perform checks to verify the identity of the binary. If a binary should always be owned by a particular user or have a particular set of access permissions assigned to it, these properties can be verified programmatically before the binary is executed. Although it may be impossible to completely protect a program from an imaginative attacker bent on controlling the commands the program executes, be sure to apply the principle of least privilege wherever the program executes an external command: do not hold privileges that are not essential to the execution of the command.





Source Code ::

         // Decryption command
                            List<String> command = Arrays.asList(
                                    JAVA_PATH, "-jar", UTILITY_PATH,
                                    "DECRYPTION",
                                    encryptedFile,
                                    PRIVATE_KEY,
                                    PASSPHRASE,
                                    PUBLIC_KEY
                            );
                            log.info("Decryption command ::" + command);
                            ProcessBuilder processBuilder = new ProcessBuilder(command);
                            processBuilder.redirectErrorStream(true); // Merge stdout and stderr

                            log.info("Executing local decryption command for: " + encryptedFile);
                            Process process = null;

                            how to resolve this issue
                            
