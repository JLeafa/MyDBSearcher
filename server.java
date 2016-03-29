import java.util.*;
import java.io.*;
import java.net.*;
import database.sql_connect;

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
	private sql_connect db;

	public Client(Socket s){
		sc = s;
	}

	public void run()
	{
		try{
			br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			br_tmp = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sc.getOutputStream())));
			db = new sql_connect();
			File f = new File("./sample.dat");
			brf = new BufferedReader(new FileReader(f));
			
		}
		catch(FileNotFoundException e){
			System.out.println("File Not Found");
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
						db.required_query("SELECT * FROM students");
						break;
					case "search":
						String str_tmp = br_tmp.readLine();
						db.required_query("SELECT * FROM students WHERE " + str_tmp);
						break;
					default:
						break;
				}

				String str_f = brf.readLine();
				while(str_f != null){
    				pw.println(str_f);
    				pw.flush();
				    str_f = brf.readLine();
  				}
  				str_f = null;
  				
				
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