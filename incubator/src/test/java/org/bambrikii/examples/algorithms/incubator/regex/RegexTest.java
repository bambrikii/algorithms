package org.bambrikii.examples.algorithms.incubator.regex;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RegexTest {
    @Test
    public void shouldCompileAndMatch() {
        Regex re = new Regex("((ab)|(cd))");
        assertThat(re.test("abacd")).isTrue();
    }

    @Test
    public void shouldCompileAndMatch2() {
        Regex re = new Regex("^((ab)(cd))$");
        assertThat(re.test("abcd")).isTrue();
    }
}
