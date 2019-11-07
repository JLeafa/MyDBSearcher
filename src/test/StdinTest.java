package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StdinTest {
    public static void main(String[] args) {
        String line = "";
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        while (!line.equals("EOF")) {
            try {
                line = stdin.readLine();
                System.out.println("Server ==> " + line);
            } catch (Exception e) {
                //TODO: handle exception
                e.printStackTrace();
            }
        }
    }
}
