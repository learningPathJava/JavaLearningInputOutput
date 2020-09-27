package eu.crystal.system.io.exception;

import lombok.Lombok;
import lombok.SneakyThrows;

import java.io.IOException;
import java.text.ParseException;

public class TestIOExceptions {
    @SneakyThrows
    public static void main(String[] args) {
        // throwsCheckedAndDeclares();
        //throwsCheckedAndHandles();
        //throwsUncheckedAndSkips();

        //sneakyThrowsCheckedAndSkips();
        //sneakyThrowsCheckedAndSkipsWithLombok();

        filtersExceptions("0.txt");
    }

    public static void throwsCheckedAndDeclares() throws IOException {
        throw new IOException("Checked exception");
    }

    public static void throwsCheckedAndHandles() {
        try {
            throw new IOException("Checked exception");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void throwsUncheckedAndSkips() {
        throw new RuntimeException("Unchecked exception");
    }

    @SneakyThrows
    public static void sneakyThrowsCheckedAndSkips() {
        throw new IOException("Checked exception");
    }

    public static void sneakyThrowsCheckedAndSkipsWithLombok() {
        try {
            throw new IOException("Checked exception");
        } catch (IOException e) {
            Lombok.sneakyThrow(e);
        }
    }

    @SneakyThrows({IOException.class, ParseException.class})
    public static void filtersExceptions(String fileName) {
        if (fileName.startsWith("0")) {
            throw new ParseException("test", 1);
        } else {
            throw new IOException();
        }
    }
}
