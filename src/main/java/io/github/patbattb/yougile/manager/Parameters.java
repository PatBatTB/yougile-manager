package io.github.patbattb.yougile.manager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class Parameters {

    private final int threadPool;
    private final int cycleTimeout;

    private final Logger logger = LogManager.getLogger(Parameters.class);

    public Parameters(Path configFile) {
        Properties properties = new Properties();
        try {
            properties.load(Files.newBufferedReader(configFile));
        } catch (IOException e) {
            logger.error("Config file {} couldn't read.", configFile, e);
            throw new RuntimeException(e);
        }
        threadPool = getPositiveIntProperty("threadPool", properties, 60);
        cycleTimeout = getPositiveIntProperty("cycleTimeout", properties, 60);
    }

    public int getThreadPool() {
        return threadPool;
    }

    public int getCycleTimeout() {
        return cycleTimeout;
    }

    private int getPositiveIntProperty(String fieldName, Properties properties, int defaultValue) {
        int value = getIntProperty(fieldName, properties, defaultValue);
        if (value <= 0) {
            logger.error("Property {} must be positive number. The default value will be using.", fieldName);
            return defaultValue;
        }
        return value;
    }

    private int getIntProperty(String fieldName, Properties properties, int defaultValue) {
        String value = properties.getProperty(fieldName);
        if (value == null) {
            logger.error("Property {} not found. The default value will be using.", fieldName);
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            logger.error("Property {} couldn't parse to int. The default value will be using.", fieldName);
        }
        return defaultValue;
    }
}
