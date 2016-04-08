package database_connect;

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


public class client_program extends JFrame
{
	public static String HOST;
	public static final int PORT = 10000;
	public static String user_name = "";

	public static JTextField tf_name,tf_department,tf_age;
	public static JTextArea  ta;
	public static JScrollPane sp;
	public static JPanel pn,pn2;
	public static JLabel lb_name,lb_age,lb_department;
	public static JButton bt_view,bt_search,bt_close;

	private static Socket sc;
	private static BufferedReader br;
	private static PrintWriter pw;

	public client_program(){
		super("User " + user_name + "--- Connect to " + HOST);

		tf_name = new JTextField();
		tf_age = new JTextField();
		tf_department = new JTextField();
		ta = new JTextArea();
		sp = new JScrollPane(ta);
		pn = new JPanel();
		pn2 = new JPanel();
		bt_view = new JButton("View All");
		bt_search = new JButton("Search");
		bt_close = new JButton("Close");
		lb_name = new JLabel("Name?");
		lb_department = new JLabel("Department?");
		lb_age = new JLabel("Age?");

		//pn.add(bt_view);
		pn.add(bt_search);
		pn.add(bt_close);
		pn2.setLayout(new GridLayout(3,2));
		pn2.add(lb_name);
		pn2.add(tf_name);
		pn2.add(lb_age);
		pn2.add(tf_age);
		pn2.add(lb_department);
		pn2.add(tf_department);

		add(pn2, BorderLayout.NORTH);
		add(sp, BorderLayout.CENTER);
		add(pn, BorderLayout.SOUTH);

		//bt_view.addActionListener(new ViewAllData());
		bt_search.addActionListener(new SearchData());
		bt_close.addActionListener(new ClientEnd());
		bt_search.getRootPane().setDefaultButton(bt_view);
		addWindowListener(new SampleWindowListener());

		setSize(800,450);
		setVisible(true);
	}

	public static void main(String[] args)
	{
		HOST = args[0];
		client_program cp = new client_program();
	}

	/*
	public class ViewAllData implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try{
				//Message to server
				String str = "show";
				pw.println(str);
				ta.append("Client : [" + str + "]\n");
				pw.flush();
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	 */

	public class SearchData implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try{
				sc = new Socket(HOST,PORT);
				br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
				pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sc.getOutputStream())));

				while(true){
					try{
						String str = br.readLine();

						if(str == null){
							ta.append("Disconnected");
							break;
						}

						//System.out.println(str);
						ta.append(str + "\n");

						sp.getViewport().scrollRectToVisible(new Rectangle(0, Integer.MAX_VALUE - 1, 1, 1));
						ta.setCaretPosition(ta.getDocument().getLength());
					}
					catch(Exception ex){
						br.close();
						pw.close();
						sc.close();
						break;
					}

					String str;
					if(tf_name.getText().equals("") && tf_age.getText().equals("") && tf_department.getText().equals(""))
						str = "view";
					else
						str = "search";

					pw.println(str);
					pw.flush();

					StringBuffer str_buf = new StringBuffer();

					str = null;

					if(!(tf_name.getText().equals(""))){
						str_buf.append("student_name LIKE '%" + tf_name.getText() + "%' ");
					}
					if(!(tf_age.getText().equals(""))){
						if(str_buf.length() == 0)
							str_buf.append("age = " + tf_age.getText() + " ");
						else
							str_buf.append("AND age = " + tf_age.getText() + " ");
					}
					if(!(tf_department.getText().equals(""))){
						if(str_buf.length() == 0)
							str_buf.append("department = '" + tf_department.getText() + "'");
						else
							str_buf.append("AND department = '" + tf_department.getText() + "'");
					}

					str = str_buf.toString();
					pw.println(str);
					System.out.println(str);
					ta.append("Client : [search]\n");
					pw.flush();
					sc.close();
				}
			}
			catch(IOException ex){

			}
			catch(Exception ex){
				ex.printStackTrace();
			}

		}}
	public class ClientEnd implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}

	class SampleWindowListener extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			System.exit(0);
		}
	}
}
//file end
