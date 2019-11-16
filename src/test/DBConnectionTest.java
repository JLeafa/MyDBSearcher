package test;

import jp.akatsubakij.server.DBConnection;

/**
 * DBConnectionTest
 */
public class DBConnectionTest {
  public static void main(String[] args) {
    DBConnection test = new DBConnection();
    System.out.println(test.getResultOfQuery("select * from albums"));
  }
}
