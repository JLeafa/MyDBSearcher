package jp.akatsubakij.server;

import java.lang.Runnable;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import jp.akatsubakij.util.*;
import jp.akatsubakij.config.CommonMessages;

/**
 * ServerPrompt
 */
public class ServerPrompt implements Runnable{

    @Override
    public void run() {
        String inputMessage = "";
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        while (!inputMessage.equals(CommonMessages.mServerStopCommand)) {
            try {
                System.out.print(">");
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
