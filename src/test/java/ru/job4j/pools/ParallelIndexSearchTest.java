package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ParallelIndexSearchTest {

    @Test
    void whenFindIntegerLinear() {
        Integer[] array = new Integer[] {1, 2, 3, 4, 5};
        int index = ParallelIndexSearch.find(array, 3);
        assertThat(index).isEqualTo(2);
    }

    @Test
    void whenFindIntegerParallel() {
        Integer[] array = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int index = ParallelIndexSearch.find(array, 11);
        assertThat(index).isEqualTo(10);
    }

    @Test
    void whenFindStringLinear() {
        String[] array = new String[] {"a", "b", "c", "d", "e"};
        int index = ParallelIndexSearch.find(array, "b");
        assertThat(index).isEqualTo(1);
    }

    @Test
    void whenFindStringParallel() {
        String[] array = new String[] {"a", "b", "c", "d", "e" , "f", "g", "h", "i", "j", "k"};
        int index = ParallelIndexSearch.find(array, "b");
        assertThat(index).isEqualTo(1);
    }

    @Test
    void whenStringNotFoundLinear() {
        String[] array = new String[] {"a", "b", "c", "d", "e"};
        int index = ParallelIndexSearch.find(array, "x");
        assertThat(index).isEqualTo(-1);
    }

    @Test
    void whenStringNotFoundParallel() {
        String[] array = new String[] {"a", "b", "c", "d", "e" , "f", "g", "h", "i", "j", "k"};
        int index = ParallelIndexSearch.find(array, "x");
        assertThat(index).isEqualTo(-1);
    }

    @Test
    void whenArrayContainsSameString() {
        String[] array = new String[] {"a", "b", "c", "d", "e" , "f", "g", "h", "i", "c", "k"};
        int index = ParallelIndexSearch.find(array, "c");
        assertThat(index).isEqualTo(2);
    }
}