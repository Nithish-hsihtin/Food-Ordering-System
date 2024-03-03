package rental;

import java.sql.Connection;
import java.sql.DriverManager;

  class ConnectionDB  {
	   static String url = "jdbc:mysql://localhost:3306/restaurent";
        static String user = "root";
        static String password = "Nithish@13kumar";
        static Connection con;
        public static Connection createConnection() {
             try {
            	 con=DriverManager.getConnection(url, user, password);
             }
             catch(Exception e) {
            	 e.printStackTrace();
             }
             return con;
        }
}