package eu.crystal.system.bytes.streams;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/* Superclasses InputStream and OutputStream */

/*
 * Java requires that we must either declare or handle a checked exception.
 * Otherwise, the code won't compile.
 *  But @SneakyThrows lets us bypass this rule.
 */

public class TestByteStreams {
    @SneakyThrows
    public static void main(String[] args) {
        readByteToByteFromFile();
        readByteToByteFromFileJDK7();

        readBufferFromFile();
    }

    /*
     * FileInputStream and FileOutputStream are concrete implementations
     * to the abstract classes InputStream and OutputStream,
     * to support I/O from disk files.
     */
    private static void readByteToByteFromFile() throws IOException {
        int byteRead;

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("resources/test.txt"); // Open stream
            while ( (byteRead = inputStream.read()) >= 0 ) {
                System.out.println( (char) byteRead );
            }
        } finally {
            try {
                if ( inputStream != null ) inputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    private static void readByteToByteFromFileJDK7() throws IOException {
        int byteRead;

        // JDK 1.7 try-with-resources syntax
        try ( FileInputStream inputStream = new FileInputStream ( "resources/test.txt" )) {
            while ( (byteRead = inputStream.read()) >= 0 ) {
                System.out.println( (char) byteRead );
            }
        } // Automatically closes all opened resource in try (...).
    }

    private static void readBufferFromFile() throws FileNotFoundException {

    }
}
