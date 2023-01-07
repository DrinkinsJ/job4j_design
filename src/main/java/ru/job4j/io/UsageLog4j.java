package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");

        byte a = 8;
        short b = 32;
        int c = 64;
        long d = 66;
        float e = 180.0f;
        double f = 29.0d;
        boolean g = true;
        char h = 'h';
        LOG.debug("Primitive type info a : {}, b : {} c : {}, d : {}, e : {}, f : {},"
                        + " g : {}, h : {},",
                a, b, c, d, e, f, g, h);
    }
}