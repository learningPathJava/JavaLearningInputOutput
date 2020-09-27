package eu.crystal.system;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TestMain {
    public static void main(String[] args) throws IOException {
        String inputFile = "resources/test.txt";
        String nextExercise = "\n**********\n";

        /*
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(inputFile);
            readAsByte(inputStream);                        // byte
            // for writing FileOutputStream
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        */

        // from Java 7 try-with-resources - for Closeable interface
        // Streams are Closeable

        try (InputStream inputStream = new FileInputStream(inputFile)) {
            readAsByte(inputStream);                        // byte
            // for writing FileOutputStream
        }

        System.out.println(nextExercise);
        try (InputStream inputStream = new FileInputStream(inputFile)) {
            readAsByteArray(inputStream); // byte array
        }


        /*
         * BufferedInputStream  - for bytes stream
         * InputStreamReader    - for stream with characters
         */

        System.out.println(nextExercise);
        // Buffer for performance
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(inputFile))) {
            readAsByteArray(inputStream);                  // buffer
        }



        System.out.println(nextExercise);
        try (InputStream inputStream = new FileInputStream(inputFile)) {
            readAsChars(inputStream);    // OK for UTF-8
        }

        System.out.println(nextExercise);
        try (InputStream inputStream = new FileInputStream(inputFile)) {
            readAsLines(inputStream);
        }

        // from Java 8

        System.out.println(nextExercise);
        // Files.lines(Paths.get(inputFile)).forEach(line -> System.out.println(line))

        // with NIO
        try (Stream<String> input = Files.lines(Paths.get(inputFile))) {
            input.forEach(System.out::println);
        }

        // navigate in file system
        navigateInFileSystem(".");
    }

    /**
     * Read and List from text file
     *
     * OBS:
     * (char) - cast byte to char
     * - for ASCII OK
     * - for RO characters, not OK
     */
    private static void readAsByte(InputStream inputStream) throws IOException {
        int byteVal = inputStream.read();
        while (byteVal >= 0) {
            System.out.println((char) byteVal);
            byteVal = inputStream.read();
        }
    }

    private static void readAsByteArray(InputStream inputStream) throws IOException {
        //byte[] buffer = new byte[1024]
        byte[] buffer = new byte[10];

        int nrOfBytes = inputStream.read(buffer);
        while (nrOfBytes >= 0) {
            System.out.println( nrOfBytes + " characters were read");
            for (int i = 0; i < nrOfBytes; i++) {
                System.out.println((char) buffer[i]);
            }
            nrOfBytes = inputStream.read(buffer);
        }
    }

    /**
     * Save for read text
     * UTF-8
     */
    private static void readAsChars(InputStream inputStream) throws IOException {
        // we use decorator

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        // for write OutputStreamWriter (OutputStream)

        int character = inputStreamReader.read();
        while (character >= 0) {
            System.out.println((char) character);
            character = inputStreamReader.read();
        }
    }
    private static void readAsLines(InputStream inputStream) throws IOException {
        // Reader decorator for InputStream -> bytes to chars
        // BufferedReader decorator for Reader -> to lines

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        /*
        String line = bufferedReader.readLine();
        while (line != null) {
            System.out.println(line);
            line = bufferedReader.readLine();
        }
        */

        // from java 8
        bufferedReader.lines().forEach( line -> System.out.println(line));
    }

    /**
     * old style for navigate in file system
     */
    private static void navigateInFileSystem(String root) {
        File file = new File(root);
        navigateFile(file);
    }
    static void navigateFile(File file) {
        if(file.isDirectory()) {
            System.out.println(file.getAbsoluteFile() + " is directory");
            String[] subFiles = file.list();
            for (String f : subFiles) {
                navigateFile(new File(file, f));
                // file is parent, f is child
            }
        } else {
            System.out.println(file.getAbsoluteFile() + " is a file");
        }
    }
}
