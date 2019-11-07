package server;

import java.lang.Runnable;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import util.*;

/**
 * ServerPrompt
 */
public class ServerPrompt implements Runnable{

    @Override
    public void run() {
        String inputMessage = "";
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        while (!inputMessage.equals("server stop")) {
            try {
                inputMessage = stdin.readLine();
            } catch (Exception e) {
                //TODO: handle exception
                e.printStackTrace();
            }
        }

        ConnectionLog.MessageLog("Server stopping ...");
        System.exit(0);
    }
    
}