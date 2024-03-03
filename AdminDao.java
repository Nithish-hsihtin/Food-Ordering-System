package rental;

import java.sql.Connection;
import java.sql.*;
import java.util.Scanner;

public class AdminDao {
	static Connection con=ConnectionDB.createConnection();
    public static void choices() {
  	  System.out.println("1.Enter 1 see Number of restaurents available\n"
  	  		+ "2.Enter 2 to see frequently ordered customer\n"
  	  		+ "3.Enter 3 to remove hotel\n"
  	  		+ "4.Enter 4 to remove customer\n"
  	  		+ "5.Enter 5 to Exit...");
  	    int choice=sc.nextInt();
  	    switch(choice) {
  	    case 1:
  	     	resCount();
  	    case 2:
  	    	FrequentCus();
  	    case 3:
  	    	removeHotel();
  	    case 4:
  	    	removeCustomer();
  	    case 5:
  	    	System.exit(0);
  	    default:
  	    	System.out.println("Please enter valid choice...");
  	    	choices();
  	    }
    }
    static Scanner sc=new Scanner(System.in);
	public static void removeCustomer() {
		System.out.println("Enter the Customer Id you need to remove:");
		String cusId=sc.next();
		try {
			String query="delete from customer where customer_id='"+cusId+"';";
			Statement st=con.createStatement();
			int row=st.executeUpdate(query);
			System.out.println("Customer removed successfully...");
			choices();
		}
		catch(Exception e) {
			System.out.println("Customer not available");
			removeCustomer();
		}
	}
	public static void removeHotel() {
		System.out.println("Enter the Restaurent Id you need to remove:");
		String resId=sc.next();
        try {
        	String query="delete from restaurent where restaurent_id='"+resId+"';";
			Statement st=con.createStatement();
			int row=st.executeUpdate(query);
			System.out.println("Hotel removed successfully...");
			choices();
		}
		catch(Exception e) {
			System.out.println("Restaurent not available");
			removeHotel();
		}
	}
	public static void FrequentCus() {
		 try {
	        	String query="SELECT cus_id, COUNT(cus_id) AS order_count\r\n"
	        			+ "FROM orders\r\n"
	        			+ "GROUP BY cus_id\r\n"
	        			+ "HAVING COUNT(cus_id) = (\r\n"
	        			+ "    SELECT MAX(order_count) FROM (\r\n"
	        			+ "        SELECT COUNT(cus_id) AS order_count\r\n"
	        			+ "        FROM orders\r\n"
	        			+ "        GROUP BY cus_id\r\n"
	        			+ "    ) AS counts\r\n"
	        			+ ");";
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(query);
				rs.next();
				System.out.println(".....The Customer Id "+rs.getString(1)+" have ordered "+rs.getInt(2)+" times.....");
				choices();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
	}
	public static void resCount() {
		try {
        	String query="select count(restaurent_id) from restaurent;";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			System.out.println("......Number of restaurents available are "+rs.getInt(1)+"......");
			choices();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
