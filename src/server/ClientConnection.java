package server;

import java.io.*;
import java.net.Socket;

/**
 * Created by Junya on 2017/06/28.
 */
public class ClientConnection implements Runnable  {
    private Socket mSocket;
    private BufferedReader mBufferedReader;
    private PrintWriter mPrintWriter;

    ClientConnection(Socket sc){
        this.mSocket = sc;
    }

    @Override
    public void run() {
        try{
            mBufferedReader = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
            mPrintWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(mSocket.getOutputStream())));
        }
        catch (IOException e){
            e.printStackTrace();
        }

        while(true){
            try{
                String receivedMessage = mBufferedReader.readLine();
                mPrintWriter.println("Hello");
                mPrintWriter.flush();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
