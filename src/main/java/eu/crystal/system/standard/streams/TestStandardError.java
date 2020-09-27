package eu.crystal.system.standard.streams;

import java.io.FileInputStream;

public class TestStandardError {
    public static void main(String[] args) {
        /*
         * System.err is similar to System.out but it is most commonly used
         * inside the catch section of the try / catch block.
         */
        String file = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
        } catch (Exception e) {
            System.err.println("Error in code: " + e);
        }
    }
}
