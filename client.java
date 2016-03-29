import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;


public class client extends JFrame implements Runnable
{
	public static String HOST;
	public static final int PORT    = 10000;
	public static String user_name = "";

	private JTextField tf_name,tf_department,tf_age;
	private JTextArea  ta;
	private JScrollPane sp;
	private JPanel pn,pn2;
	private JButton bt_view,bt2;
	private JButton bt_search;
	private JLabel lb_name,lb_age,lb_department;

	private Socket sc;
	private BufferedReader br;
	private PrintWriter pw;

	public static void main(String[] args)
	{
		HOST = args[0];
		client cl = new client();
	}

	public client()
	{
		super("User --- Connect to " + HOST);

		tf_name = new JTextField();
		tf_age = new JTextField();
		tf_department = new JTextField();
		ta = new JTextArea();
		sp = new JScrollPane(ta);
		pn = new JPanel();
		pn2 = new JPanel();
		bt_view = new JButton("View All");
		bt_search = new JButton("Search");
		bt2 = new JButton("Close");
		lb_name = new JLabel("Name?");
		lb_department = new JLabel("Department?");
		lb_age = new JLabel("Age?");
	
		pn.add(bt_view);
		pn.add(bt_search);
		pn.add(bt2);
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

		bt_view.addActionListener(new ViewAllData());
		bt_search.addActionListener(new SearchData());
		bt2.addActionListener(new ClientEnd());
		bt_view.getRootPane().setDefaultButton(bt_view);
		addWindowListener(new SampleWindowListener());

		setSize(800,450);
		setVisible(true);

		Thread th = new Thread(this);
		th.start();

		/*
		try{
			th.join();
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		*/
	}

	public void run()
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

					ta.append(str + "\n");
					
					sp.getViewport().scrollRectToVisible(new Rectangle(0, Integer.MAX_VALUE - 1, 1, 1));
					ta.setCaretPosition(ta.getDocument().getLength());
				}
				catch(Exception e){
					br.close();
					pw.close();
					sc.close();
					break;
				}
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

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

	public class SearchData implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try{
				String str = "search";
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
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}

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
