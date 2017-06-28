package server;

import java.sql.*;

/**
 * Created by Junya on 2017/06/28.
 */
public class DBConnection {
    Connection mConnection = null;
    Statement mStatement = null;
    private final int TIMEOUT = 30;

    DBConnection(){
        try {
            Class.forName("org.sqlite.JDBC");
            mConnection = DriverManager.getConnection("jdbc:sqlite:E:/SQL/test/sample.db");
            mStatement = mConnection.createStatement();
            mStatement.setQueryTimeout(this.TIMEOUT);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public String getResultOfQuery(String query){
        StringBuilder ret = new StringBuilder();
        try {
            ResultSet mResultSet = mStatement.executeQuery(query);
            ResultSetMetaData mMetaData = mResultSet.getMetaData();
            while(mResultSet.next()){
                int range = mMetaData.getColumnCount();
                for(int i=1; i<=range; i++){
                    ret.append(mResultSet.getString(i) + ",");
                }
                ret.append("\n");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try{
                if(mStatement != null) mStatement.close();
                if(mConnection != null) mConnection.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return ret.toString();
    }
}
