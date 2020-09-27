package eu.crystal.system.standard.streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestStandardInputOutput {
    public static void main(String[] args) throws IOException {
        String name;

        // Standard Input
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( System.in) );

        // Standard Output
        System.out.println( "Your name: " );

        name = bufferedReader.readLine();

        // Standard Output
        System.out.println( "Your name is " + name);
    }
}
