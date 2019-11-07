package server;

import java.io.*;
import java.net.Socket;

import util.ConnectionLog;

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

            while(true){
                try{                
                    mPrintWriter.println("hello");
                    mPrintWriter.flush();

                    String response = mBufferedReader.readLine();
                    if(response.equals("hello response")) {
                        System.out.println(response);
                        mPrintWriter.println("done");
                        mPrintWriter.flush();
                        break;
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            while (!this.mSocket.isClosed()) {
                String str = mBufferedReader.readLine();
                System.out.println("Client ==> " + str);
                if(str.equals("bye")) {
                    break;
                }
                mPrintWriter.println("Server has received your message!!");
                mPrintWriter.flush();
            }

            ConnectionLog.MessageLog("Socket is closed");
        }
        catch (Exception e){
            try {
                mSocket.close();
            } catch (Exception ee) {
                //TODO: handle exception
                ee.printStackTrace();
            }
        }
        finally {
            try {
                mBufferedReader.close();
                mPrintWriter.close();
                mSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
