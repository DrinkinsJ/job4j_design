package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {
    @Test
    void whenPairWithCommentAndValueEmpty() {
        String path = "data/noValue.properties";
        Config config = new Config(path);
        config.load();
        assertThatThrownBy(() -> config.value("")).isInstanceOf(IllegalArgumentException.class).hasMessage("No value");
    }
    
    @Test 
    void whenPairWithCommentAndKeyEmpty(){
        String path = "data/noKey.properties";
        Config config = new Config(path);
        config.load();
        assertThatThrownBy(() -> config.value("")).isInstanceOf(IllegalArgumentException.class).hasMessage("No value");
    }
    
    @Test 
    void whenPairWithCommentAndKeyWithValueEmpty(){
        String path = "data/noKeyNoValue.properties";
        Config config = new Config(path);
        config.load();
        assertThatThrownBy(() -> config.value("")).isInstanceOf(IllegalArgumentException.class).hasMessage("No value");
    }

    @Test
    void whenPairWithCommentAndTwoSplitArguments(){
        String path = "data/moreOneValueRow.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key2")).isEqualTo("key3=value2");
    }
}
