import java.util.*;
import java.io.*;
import java.net.*;
import java.sql.*;

/**
* Server side class
*/
public class server_program
{
	public static final int PORT = 10000;

	/**
	* main class
	* open socket
	* @param args String[]
	*/
	public static void main(String[] args)
	{
		Sytem.out.println("Authentification to login with Adminisrator");
		Scanner scan_pass = new Scanner(System.in);
		String password = scan_pass.next();

		server_program sv = new server_program();

		try{
			ServerSocket ss = new ServerSocket(PORT);

			System.out.println("Waiting...");
			while(true){
				try{
					Socket sc = ss.accept();
					System.out.println("Welcom!");

					Client cl = new Client(sc, password);
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

/**
* Thread class tethered 1 client
*/
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

	public Statement st;
	public ResultSet rs;
	public ResultSetMetaData rm;
	public int cnum;

	/**
	* constructor
	* @param s Socket
	*/
	public Client(Socket s, String pass){
		sc = s;
		url = "jdbc:mysql://localhost/test";
		user = "root";
		this.password = pass;
	}

	/**
	* override method that execute a thread
	*/
	public void run()
	{
		try{
			br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			br_tmp = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sc.getOutputStream())));
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
					case "view":
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
