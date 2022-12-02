package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class Count {
    @GuardedBy("this")
    private int value;

    public synchronized void increment() {
        this.value++;
    }

    public synchronized int get() {
        System.out.println("Привет");
        return this.value;
    }

    public static void main(String[] args) {
        System.out.println("Привет");
    }

}