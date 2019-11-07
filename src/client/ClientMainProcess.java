package client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * client side class
 */

public class ClientMainProcess {
	public static void main(String args[]) {
		String HOST = args[0];
		int PORT = 10000;
		Socket sc = null;
		BufferedReader br = null;
		PrintWriter pw = null;

		try {
			sc = new Socket(HOST, PORT);
			br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sc.getOutputStream())));

			while (true) {
				try {
					String str = br.readLine();
					System.out.println(str);

					if (str.equals("hello")) {
						String res = "hello response";
						pw.println(res);
						pw.flush();
					} else if (str.equals("done")) {
						System.out.println("Write down message !!");
						break;
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					br.close();
					pw.close();
					sc.close();
					break;
				}
			}

			String line = "";
			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				line = stdin.readLine();
				pw.println(line);
				pw.flush();

				if(line.equals("bye")) {
					break;
				}
				String str = br.readLine();
				System.out.println("Server ==> " + str);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try{
				br.close();
				pw.close();
				sc.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
