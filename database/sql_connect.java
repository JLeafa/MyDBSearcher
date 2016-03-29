package database;

import java.io.*;
import java.sql.*;

public class sql_connect{
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

	public sql_connect()
	{
		url = "";
		user = "";
		password = "";
		f = new File("./sample.dat");
	}

    public void required_query(String qry_from_client){
	try{
		

		con = DriverManager.getConnection(url,user,password);
		st = con.createStatement();
		
		String qry = qry_from_client;
		rs = st.executeQuery(qry);

		ResultSetMetaData rm = rs.getMetaData();
		int cnum = rm.getColumnCount();
		
	try{
			fos = new FileOutputStream(f);
			dos = new DataOutputStream(fos);
			while(rs.next()){
				for(int i=1; i<=cnum ; i++)
					dos.writeChars(rm.getColumnName(i) + " : " + rs.getObject(i) + " ");

				dos.writeChars("\n");
			}
		}
		catch(FileNotFoundException e){
			System.out.println("Not File Found");
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}
	catch(SQLException e){
		e.printStackTrace();
	}
	finally{
		try{
			if(con != null)
				con.close();
			rs.close();
			st.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	}

}
