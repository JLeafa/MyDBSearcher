package jp.akatsubakij.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DatabaseConnector
 */
public class SampleDatabaseConnector {
    final String url = "jdbc:sqlite:src/resource/sqlite_db/chinook.db";

    public List<String> getEmployeeData(String condition) {
        List<String> ret = new ArrayList<String>();
        try {
            Connection conn = DriverManager.getConnection(url);
            String sql = "select * from employees";
            if (condition.length() > 0) {
                sql += (" where " + condition);
            }
            PreparedStatement ps = conn.prepareStatement(sql);
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    String row = "";
                    row += rs.getString("EmployeeId");
                    row += rs.getString("LastName");
                    row += rs.getString("FirstName");
                    row += rs.getString("Title");
                    row += rs.getString("ReportsTo");
                    row += rs.getString("BirthDate");
                    row += rs.getString("HireDate");
                    row += rs.getString("Address");
                    row += rs.getString("City");
                    row += rs.getString("State");
                    row += rs.getString("Country");
                    row += rs.getString("PostalCode");
                    row += rs.getString("Phone");
                    row += rs.getString("Fax");
                    row += rs.getString("Email");
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return ret;
    }
}
