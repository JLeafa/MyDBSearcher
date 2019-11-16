package jp.akatsubakij.server;

import java.io.*;
import java.net.Socket;

import jp.akatsubakij.config.CommonMessages;
import jp.akatsubakij.util.ConnectionLog;
import jp.akatsubakij.util.SampleDatabaseConnector;
import jp.akatsubakij.common.packet.*;

/**
 * Created by Junya on 2017/06/28.
 */
public class ClientConnection implements Runnable  {
    private Socket mSocket;
    private ObjectOutputStream mObjectOutputStream;
    private ObjectInputStream mObjectInputStream;
    private Long mThreadId;

    ClientConnection(Socket sc){
        this.mSocket = sc;
        this.mThreadId = Thread.currentThread().getId();
    }

    @Override
    public void run() {
        try{
            mObjectInputStream = new ObjectInputStream(mSocket.getInputStream());
            mObjectOutputStream = new ObjectOutputStream(mSocket.getOutputStream());

            while(true){
                try{
                    // send "hello"
                    HelloPacket pck = new HelloPacket();
                    pck.setmUserUniqueId(this.mThreadId.intValue());
                    pck.setmMessage(CommonMessages.mHelloMessage2Client);

                    mObjectOutputStream.writeObject(pck);
                    mObjectOutputStream.flush();

                    pck = (HelloPacket)mObjectInputStream.readObject();
                    if(pck.getmMessage().equals(CommonMessages.mAckMessageFromClient)) {
                        ConnectionLog.MessageLog(pck.getmMessage());
                        pck.setmMessage(CommonMessages.mEstablishMessage2Client);
                        mObjectOutputStream.writeObject(pck);
                        mObjectOutputStream.flush();
                        break;
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            while (!this.mSocket.isClosed()) {
                SqlObjectPacket pck_sql = (SqlObjectPacket)mObjectInputStream.readObject();
                ConnectionLog.MessageLog("Client ==> " + pck_sql.getmQuery());
                if(pck_sql.getmMessageType().equals(CommonMessages.mEndOfConnectionMessageType)) {
                    break;
                }

                SampleDatabaseConnector dbconnector = new SampleDatabaseConnector();
                pck_sql.setmQueryResult(dbconnector.getEmployeeData(""));
                mObjectOutputStream.writeObject(pck_sql);
                mObjectOutputStream.flush();
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
                mObjectInputStream.close();
                mObjectOutputStream.close();
                mSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
