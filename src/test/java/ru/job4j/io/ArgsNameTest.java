package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ArgsNameTest {
    @Test
    void whenGetFirst() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        assertThat(jvm.get("Xmx")).isEqualTo("512");
    }

    @Test
    void whenGetFirstReorder() {
        ArgsName jvm = ArgsName.of(new String[] {"-encoding=UTF-8", "-Xmx=512"});
        assertThat(jvm.get("Xmx")).isEqualTo("512");
    }

    @Test
    void whenMultipleEqualsSymbol() {
        ArgsName jvm = ArgsName.of(new String[] {"-request=?msg=Exit="});
        assertThat(jvm.get("request")).isEqualTo("?msg=Exit=");
    }

    @Test
    void whenGetNotExist() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512"});
        assertThatThrownBy(() -> jvm.get("Xms")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenWrongSomeArgument() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{}))
                .isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    void whenKeyNotExist(){
        assertThatThrownBy(() -> ArgsName.of(new String[] {"-=512"})).isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    void  whenValueNotExist() {
        assertThatThrownBy(() -> ArgsName.of(new String[] {"-Xmx="})).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void  whenNoStartParamSymbol() {
        assertThatThrownBy(() -> ArgsName.of(new String[] {"Xmx=512"})).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void  whenNoSeparatorSymbol() {
        assertThatThrownBy(() -> ArgsName.of(new String[] {"-Xmx 512"})).isInstanceOf(IllegalArgumentException.class);
    }
}
