package com.example.unitech.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

import java.util.Arrays;

public class QLogger {

    final Logger logger;

    public QLogger(Logger logger) {
        this.logger = logger;
    }

    public static QLogger getLogger(Class<?> clazz) {
        Logger logger = (Logger) LoggerFactory.getLogger(clazz);
        return new QLogger(logger);
    }

    private Object[] filterValues(Object... argArray) {
        return Arrays.stream(argArray)
                .map(a -> a).toArray();
    }

    public String getName() {
        return logger.getName();
    }

    public void trace(String format, Object... args) {
        if (logger.isTraceEnabled()) {
            logger.trace(format, filterValues(args));
        }
    }

    public void trace(Marker marker, String format, Object... args) {
        if (logger.isTraceEnabled(marker)) {
            logger.trace(marker, format, args);
        }
    }

    public void debug(String format, Object... args) {
        if (logger.isDebugEnabled()) {
            logger.debug(format, filterValues(args));
        }
    }

    public void debug(Marker marker, String format, Object... args) {
        if (logger.isDebugEnabled(marker)) {
            logger.debug(marker, format, args);
        }
    }

    public void info(String format, Object... args) {
        if (logger.isInfoEnabled()) {
            logger.info(format, filterValues(args));
        }
    }

    public void info(Marker marker, String format, Object... args) {
        if (logger.isInfoEnabled(marker)) {
            logger.info(marker, format, args);
        }
    }

    public void warn(String format, Object... args) {
        if (logger.isWarnEnabled()) {
            logger.warn(format, filterValues(args));
        }
    }

    public void warn(Marker marker, String format, Object... args) {
        if (logger.isWarnEnabled(marker)) {
            logger.warn(marker, format, args);
        }
    }

    public void error(String format, Object... args) {
        if (logger.isErrorEnabled()) {
            logger.error(format, filterValues(args));
        }
    }

    public void error(Marker marker, String format, Object... args) {
        if (logger.isErrorEnabled(marker)) {
            logger.error(marker, format, args);
        }
    }
}
