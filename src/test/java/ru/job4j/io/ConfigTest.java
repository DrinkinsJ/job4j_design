package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConfigTest {
    @Test
    void whenPairWithCommentAndValueEmpty() {
        try {
            String path = "data/noValue.properties";
            Config config = new Config(path);
            config.load();
        } catch (IllegalArgumentException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    void whenPairWithCommentAndKeyEmpty() {
        try {
            String path = "data/noKey.properties";
            Config config = new Config(path);
            config.load();
        } catch (IllegalArgumentException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    void whenPairWithCommentAndKeyWithValueEmpty() {
        try {
            String path = "data/noKeyNoValue.properties";
            Config config = new Config(path);
            config.load();
        } catch (IllegalArgumentException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    void whenPairWithCommentAndTwoSplitArguments() {
        String path = "data/moreOneValueRow.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key2")).isEqualTo("key3=value2");
    }
}
