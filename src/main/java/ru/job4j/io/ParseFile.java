package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public final class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    private String content(Predicate<Character> filter) throws IOException {
        StringBuilder output = new StringBuilder();
        int data;
        try (BufferedInputStream i = new BufferedInputStream(
                new FileInputStream(file))) {
            while ((data = i.read()) > 0) {
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }
        }
        return output.toString();
    }

    public String getContent() throws IOException {
        return content(ch -> true);
    }

    public String getContentWithoutUnicode() throws IOException {
       return content(ch -> ch < 0x80);
    }
}