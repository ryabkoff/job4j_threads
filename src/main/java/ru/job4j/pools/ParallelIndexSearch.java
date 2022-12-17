package ru.job4j.pools;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelIndexSearch<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final T value;
    private final int from;
    private final int to;
    public static final int LINEAR_SIZE = 10;

    public ParallelIndexSearch(T[] array, T value) {
        this.array = array;
        this.value = value;
        this.from = 0;
        this.to = array.length - 1;
    }

    public ParallelIndexSearch(T[] array, T value, int from, int to) {
        this.array = array;
        this.value = value;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if (to - from  <= LINEAR_SIZE) {
            return findByLinear(array, value);
        }
        return findByParallel(array, value);
    }

    private int findByLinear(T[] array, T value) {
        int index = -1;
        for (int i = from; i <= to ; i++) {
            if (value.equals(array[i])) {
                index = i;
                break;
            }
        }
        return index;
    }

    private int findByParallel(T[] array, T value) {
        int mid = from + (to - from) / 2;
        ParallelIndexSearch<T> left = new ParallelIndexSearch<>(array, value, from, mid);
        ParallelIndexSearch<T> right = new ParallelIndexSearch<>(array, value, mid + 1, to);
        left.fork();
        right.fork();
        int indexLeft = left.join();
        int indexRight = right.join();
        return (indexLeft == -1) ? indexRight : indexLeft;
    }

    public static <T> int find(T[] array, T value) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelIndexSearch<>(array, value));
    }
}
