package com.example.logging;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class ColorFormatter extends Formatter {
    // ANSI color codes
    private static final String RESET = "\u001B[0m";
    private static final String BLUE = "\u001B[34m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";
    private static final String BOLD_RED = "\u001B[1;31m";

    @Override
    public String format(LogRecord record) {
        String color;
        switch (record.getLevel().getName()) {
            case "INFO":
                color = BLUE;
                break;
            case "WARNING":
                color = YELLOW;
                break;
            case "SEVERE":
                color = RED;
                break;
            default:
                color = RESET;
        }

        return String.format("%s[%s] %s%s%n", color, record.getLevel(), record.getMessage(), RESET);
    }
}



main

package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.logging.LogManager;

@SpringBootApplication
public class MainApplication {
    static {
        try {
            LogManager.getLogManager().readConfiguration(
                MainApplication.class.getClassLoader().getResourceAsStream("logging.properties")
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);

        // Test logging
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainApplication.class.getName());
        logger.info("This is an INFO message");
        logger.warning("This is a WARNING message");
        logger.severe("This is an ERROR message");
    }
}