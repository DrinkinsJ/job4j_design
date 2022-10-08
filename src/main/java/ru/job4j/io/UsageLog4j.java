package ru.job4j.io;

import org.slf4j.*;

public class UsageLog4j {


    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");

        String firstname = "Igor";
        String surname = "Dorokhin";
        String patronymic = "Vitalevich";
        int weigh = 66;
        int height = 180;
        int age = 29;
        String eyeColor = "brown";
        String hairColor = "brown";
        LOG.debug("User info firstname : {}, surname : {} patronymic : {}, weigh : {}, height : {}, age : {},"
                        + " eyeColor : {}, hairColor : {},",
                firstname, surname, patronymic, weigh, height, age, eyeColor, hairColor);
    }
}