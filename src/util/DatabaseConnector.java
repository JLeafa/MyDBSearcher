package util;

import java.sql.*;

/**
 * DatabaseConnector
 */
public class DatabaseConnector {
    public static void main(String[] args) {
        final String url = "jdbc:sqlite:/Users/junyaogawa29/Documents/GitHub/JAkatsubaki/DBSearcherOnJava/src/sqlite_db/chinook.db";
        final String sql = "select * from customers";

        try (
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement ps = conn.prepareStatement(sql)){
                try (ResultSet rs = ps.executeQuery()){
                    while (rs.next()) {
                        System.out.println(rs.getInt("CustomerId"));
                    }
                }
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        System.out.println("hello world");
    }
}