package ru.job4j.concurrent;

public class Wget {
    public static void main(String[] args) {
        System.out.println("1");
        Thread thread = new Thread(
                () -> {
                    for (int index = 0; index <= 100; index++) {
                        try {
                            Thread.sleep(1000);
                            System.out.print("\rLoading : " + index + "%");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        System.out.println("2");
        thread.start();
        System.out.println("3");
    }
}
