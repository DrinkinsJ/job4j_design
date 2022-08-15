package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {

    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkNameWithOutSeparator() {
        NameLoad nameLoad = new NameLoad();
        String name = "IvanName";
        assertThatThrownBy(() -> nameLoad.parse(name)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(
                        "this name: %s does not contain the symbol \"=\"", name);
    }

    @Test
    void checkExtraSeparator() {
        NameLoad nameLoad = new NameLoad();
        String name = "=Name";
        assertThatThrownBy(() -> nameLoad.parse(name)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: %s does not contain a key",
                        name);
    }

    @Test
    void checkEmptyName() {
        NameLoad nameLoad = new NameLoad();
        String name = "Ivan=";
        assertThatThrownBy(() -> nameLoad.parse(name)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: %s does not contain a value",
                        name);
    }
}