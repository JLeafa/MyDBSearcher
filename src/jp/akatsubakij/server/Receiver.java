package jp.akatsubakij.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.lang.Thread;

import jp.akatsubakij.util.ConnectionLog;
import jp.akatsubakij.server.*;

/**
 * Created by Junya on 2017/06/28.
 */
public class Receiver {
    private ServerSocket mServerSocket;
    private Socket mSocket;
    private int LISTEN_PORT = 10000;

    public void exec(){
        try {
            mServerSocket = new ServerSocket(LISTEN_PORT);
        }
        catch (SocketTimeoutException e) {
            ConnectionLog.MessageLog("Timeout");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        while (true) {
            ConnectionLog.MessageLog("Listening with PORT " + LISTEN_PORT + "...");
            try {
                mSocket = mServerSocket.accept();
            } catch (IOException e) {
                //TODO: handle exception
                ConnectionLog.MessageLog("Cannot accept");
            }
            new Thread(new ClientConnection(mSocket)).start();
        }
    }
}
