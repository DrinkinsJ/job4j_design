package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;


@Disabled("Test disabled")
class PatternGeneratorTest {

    private final String pattern = "I am a ${name}, Who are ${subject}? ";

    @Test
    void whenPatternEqualPatternGeneratorOutput() {
        Map<String, String> arguments = new HashMap<>();
        arguments.put("name", "Igor");
        arguments.put("subject", "you");
        PatternGenerator patternGenerator = new PatternGenerator();
        assertThat(patternGenerator.produce(pattern, arguments)).isEqualTo("I am a Igor, Who are you? ");
    }

    @Test
    void whenPatternNotContainKey() {
        Map<String, String> arguments = new HashMap<>();
        PatternGenerator patternGenerator = new PatternGenerator();
        assertThatThrownBy(() -> patternGenerator.produce(pattern, arguments)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenPatternNotContainValue() {
        Map<String, String> arguments = new HashMap<>();
        arguments.put("name", null);
        arguments.put("subject", null);
        PatternGenerator patternGenerator = new PatternGenerator();
        assertThatThrownBy(() -> patternGenerator.produce(pattern, arguments)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenPatternTemplateIsNull() {
        Map<String, String> arguments = new HashMap<>();
        arguments.put("name", "Igor");
        arguments.put("subject", "you");
        PatternGenerator patternGenerator = new PatternGenerator();
        assertThatThrownBy(() -> patternGenerator.produce(null, arguments)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenPatternArgumentsIsNull() {
        PatternGenerator patternGenerator = new PatternGenerator();
        assertThatThrownBy(() -> patternGenerator.produce(pattern, null)).isInstanceOf(IllegalArgumentException.class);
    }
}