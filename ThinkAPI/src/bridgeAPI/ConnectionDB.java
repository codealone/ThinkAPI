package bridgeAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bridgeModule.Item;

public class ConnectionDB {
	
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_CONNECTION="jdbc:mysql://localhost:3306/thinkdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "optimus";
    
    public Connection getconnection() throws Exception {
    	  Connection dbConnection = null;
          try {
              Class.forName(DB_DRIVER);
          } catch (ClassNotFoundException e) {
              System.out.println(e.getMessage() + "here");
          }
          try {
              dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
              System.out.println("Connection is successfull");
              return dbConnection;
          } catch (SQLException e) {
        	  
              System.out.println(e.getMessage());
          }
          return dbConnection;
      }
    
    public void closeconnection(Connection conn) {
    	try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}