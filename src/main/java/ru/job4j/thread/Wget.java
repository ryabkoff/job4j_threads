package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        String fileName = "tmp_" + url.substring(url.lastIndexOf('/') + 1);
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            int downloadData = 0;
            long start = System.currentTimeMillis();
            long elapsed;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                downloadData += bytesRead;
                if (downloadData >= speed) {
                    elapsed = System.currentTimeMillis() - start;
                    if (elapsed < 1000) {
                        Thread.sleep(1000 - elapsed);
                    }
                    downloadData = 0;
                    start = System.currentTimeMillis();
                }
            }
        } catch (IOException  e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static boolean isSpeedValid(String s) {
        int value;
        try {
            value = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            value = -1;
        }
        return value > 0;
    }

    private static void validation(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Ќекорректное количество параметров!"
                    + "ѕервым параметром должен быть URL, вторым скорость");
        }
        if (!isSpeedValid(args[1])) {
            throw new IllegalArgumentException("—корость должна быть задана целым положительным числом!");
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        validation(args);
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}