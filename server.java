import java.util.*;
import java.io.*;
import java.net.*;
import java.sql.*;

public class server
{
	public static final int PORT = 10000;
	
	public static void main(String[] args)
	{
		server sv = new server();

		try{
			ServerSocket ss = new ServerSocket(PORT);

			System.out.println("Waiting...");
			while(true){
				try{
					Socket sc = ss.accept();
					System.out.println("Welcom!");

					Client cl = new Client(sc);
					cl.start();
				}
				catch(Exception e){
					System.out.println("Time out");
					break;
				}
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
}

class Client extends Thread
{
	private Socket sc;
	private BufferedReader br,br_tmp,brf;
	private PrintWriter pw;
	public static Connection con = null;
	public String reply = "";
	public FileOutputStream fos;
	public DataOutputStream dos;
	public String url;
	public String user;
	public String password;
	public File f;
	public Statement st;
	public ResultSet rs;
	public ResultSetMetaData rm;
	public int cnum;

	public Client(Socket s){
		sc = s;
		url = "jdbc:mysql://localhost/test";
		user = "";
		password = "";
		f = new File("./sample.dat");
	}

	public void run()
	{
		try{
			br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			br_tmp = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sc.getOutputStream())));
			brf = new BufferedReader(new FileReader(f));
			con = DriverManager.getConnection(url,user,password);
			st = con.createStatement();	
		}
		catch(FileNotFoundException e){
			System.out.println("File Not Found");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}

		while(true){
			
			try{				
				String str = br.readLine();
				if(str != null)
					System.out.println(str);

				switch(str){
					case "show":
						rs = st.executeQuery("SELECT * FROM students");
						rm = rs.getMetaData();
						cnum = rm.getColumnCount();
						while(rs.next()){
								for(int i=1; i<=cnum; i++){
									pw.print(rm.getColumnName(i) + " : " + rs.getObject(i) + " ");
							}
							pw.print("\n");
							pw.flush();
						}
						break;
					case "search":
						String str_tmp = br_tmp.readLine();
						rs = st.executeQuery("SELECT * FROM students WHERE " + str_tmp);
						rm = rs.getMetaData();
						cnum = rm.getColumnCount();
						while(rs.next()){
								for(int i=1; i<=cnum; i++){
									pw.print(rm.getColumnName(i) + " : " + rs.getObject(i) + " ");
							}
							pw.print("\n");
							pw.flush();
						}
						break;
					default:
						break;
				}
  				
				//pw.println("Server : Message \"" + str + "\" from client");
			}
			catch(IOException e){
				e.printStackTrace();
			}
			catch(Exception e){
				try{
					br.close();
					brf.close();
					br_tmp.close();
					pw.close();
					sc.close();
					break;
				}
				catch(IOException ex){
					ex.printStackTrace();
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
	}
}
