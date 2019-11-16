package jp.akatsubakij.server;

import java.sql.*;

/**
 * Created by Junya on 2017/06/28.
 */
public class DBConnection {
    Connection mConnection = null;
    Statement mStatement = null;
    private final int TIMEOUT = 30;

    private final String sqliteDatabaseDir = "/Users/junyaogawa29/Documents/GitHub/JAkatsubaki/DBSearcherOnJava/src/resource/sqlite_db";
    private final String sqliteDatabaseName = "chinook.db";

    public DBConnection(){
        try {
            Class.forName("org.sqlite.JDBC");
            mConnection = DriverManager.getConnection("jdbc:sqlite:" + sqliteDatabaseDir + "/" + sqliteDatabaseName);
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
