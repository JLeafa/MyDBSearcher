package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import util.ConnectionLog;

/**
 * Created by Junya on 2017/06/28.
 */
public class Receiver {
    private ServerSocket mServerSocket;
    private Socket mSocket;
    private static final int LISTEN_PORT = 10000;

    public void exec(){
        try {
            mServerSocket = new ServerSocket(LISTEN_PORT);
            ConnectionLog.MessageLog("Listening with PORT " + LISTEN_PORT + "...");
            mSocket = mServerSocket.accept();
            new ClientConnection(mSocket).run();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
