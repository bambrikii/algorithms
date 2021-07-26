package org.bambrikii.examples.graphs.trie;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TrieTest {
    public static Collection<Object[]> params() {
        List<Object[]> params = new ArrayList<>();
        params.add(new Object[]{new String[]{"asd", "qwe"}, "qwe", true});
        params.add(new Object[]{new String[]{"asd", "qwe"}, "zxc", false});
        return params;
    }

    @ParameterizedTest
    @MethodSource("params")
    public void should1(String[] strings, String find, boolean expected) {
        Trie trie = new Trie('-');
        for (String str : strings) {
            trie.insert(str);
        }
        assertThat(trie.find(find)).isEqualTo(expected);
    }
}
