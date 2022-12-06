package ru.job4j;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class CASCountTest {

    @Test
    public void whenFirst200Second100() throws InterruptedException {
        CASCount count = new CASCount();
        Thread first = new Thread(() -> IntStream.range(0, 200).forEach(x -> count.increment()));
        Thread second = new Thread(() -> IntStream.range(0, 100).forEach(x -> count.increment()));
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(count.get()).isEqualTo(300);
    }

}