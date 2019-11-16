package jp.akatsubakij.client;

// import java.awt.BorderLayout;
// import java.awt.GridLayout;
// import java.awt.Rectangle;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.awt.event.WindowAdapter;
// import java.awt.event.WindowEvent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

import jp.akatsubakij.common.packet.HelloPacket;
import jp.akatsubakij.common.packet.SqlObjectPacket;

// import javax.swing.JButton;
// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JPanel;
// import javax.swing.JScrollPane;
// import javax.swing.JTextArea;
// import javax.swing.JTextField;

import jp.akatsubakij.config.CommonMessages;
import jp.akatsubakij.util.ConnectionLog;

/**
 * client side class
 */

public class ClientMainProcess {
	public static void main(String args[]) {
		String HOST = args[0];
		Random rnd  = new Random();
		Integer UID = rnd.nextInt(10) + 100;
		int PORT = 10000;
		Socket sc = null;
		ObjectOutputStream mObjectOutputStream = null;
		ObjectInputStream mObjectInputStream = null;

		try {
			sc = new Socket(HOST, PORT);
			mObjectOutputStream = new ObjectOutputStream(sc.getOutputStream());
			mObjectInputStream = new ObjectInputStream(sc.getInputStream());

			while (true) {
				try {
					HelloPacket pck = (HelloPacket)mObjectInputStream.readObject();
					ConnectionLog.MessageLog(pck.getmMessage());

					if (pck.getmMessage().equals(CommonMessages.mHelloMessage2Client)) {
						pck.setmDestinationId(UID);
						pck.setmMessage(CommonMessages.mAckMessageFromClient);
						mObjectOutputStream.writeObject(pck);
						mObjectOutputStream.flush();
					} else if (pck.getmMessage().equals(CommonMessages.mEstablishMessage2Client)) {
						ConnectionLog.MessageLog("Write down message !!");
						ConnectionLog.MessageLog("[Sample DB Table Employees]");
						break;
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					mObjectInputStream.close();
					mObjectOutputStream.close();
					sc.close();
					break;
				}
			}

			String line = "";
			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				System.out.print('>');
				line = stdin.readLine();
				SqlObjectPacket sql_pck = new SqlObjectPacket();

				if(line.equals(CommonMessages.mServerStopCommand)) {
					sql_pck.setmMessageType(CommonMessages.mEndOfConnectionMessageType);
					mObjectOutputStream.writeObject(sql_pck);
					mObjectOutputStream.flush();
					break;
				}

				sql_pck.setmQuery(line);
				mObjectOutputStream.writeObject(sql_pck);
				mObjectOutputStream.flush();

				SqlObjectPacket res = (SqlObjectPacket)mObjectInputStream.readObject();
				for (String s : res.getmQueryResult()) {
					ConnectionLog.MessageLog("Server ==> " + s);
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try{
				mObjectInputStream.close();
				mObjectOutputStream.close();
				sc.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
