package com.newhopebootcamps.spark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloLogback {
    private static final Logger logger = LoggerFactory.getLogger(HelloLogback.class);

    public static void main(String[] args) {
        System.out.println("Hello Logback!" + args[0]);
        for (int i = 0; i < 200000; i++) {
            logger.debug("Entering application.");
            logger.trace("Entering application.");
            logger.info("Entering application.");
            logger.error("Entering application.");
            logger.info(">>> Entering application.");
        }
    }
}
