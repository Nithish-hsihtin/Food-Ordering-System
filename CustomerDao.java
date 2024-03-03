package rental;

import java.sql.*;
import java.util.*;
import java.util.Map.Entry;
import java.sql.Statement;

public class CustomerDao {
	static Connection con=ConnectionDB.createConnection();
      public static void choices(String cusId) {
    	  Scanner sc=new Scanner(System.in);
    	  System.out.println("1.Enter 1 to Order Food\n"
    	  		+ "2.Enter 2 to Cancel Order\n"
    	  		+ "3.Enter 3 to Exit");
    	    int choice=sc.nextInt();
    	    switch(choice) {
    	    case 1:
    	    	OrderFood(cusId);
    	    case 2:
    	    	CancelOrder(cusId);
    	    case 3:
    	    	System.exit(0);
    	    default:
    	    	System.out.println("Please enter valid choice...");
    	    }
      }
      public static void OrderFood(String cusId) {
    	  Scanner sc=new Scanner(System.in);
    	  try {
    	  String query="select restaurent_name,restaurent_id from restaurent;";
    	  Statement st=con.createStatement();
    	  ResultSet rs=st.executeQuery(query);
    	  while(rs.next()) {
    		  System.out.println(rs.getString(1)+" - "+rs.getString(2));
    	  }
    	  }
    	  catch(Exception e) {
    		  e.printStackTrace();
    	  }
         double b=DisplayFood();
    	 Random random =new Random();
 	     int OId=random.nextInt(100)+100;
  		 String OrderId=("oid"+Integer.toString(OId)).toUpperCase();
  		 System.out.println("Order placed succesfully\nYour order_Id is : "+OrderId);
  		 System.out.println("Total bill is "+b);
  		 try {
  			 String query="insert into orders values (?,?,?);";
  			 PreparedStatement st=con.prepareStatement(query);
  			 st.setString(1, OrderId);
  			 st.setString(2, cusId);
  			 st.setDouble(3,b);
  			st.executeUpdate();
  		 }
  		 catch(Exception e){
  			 e.printStackTrace();
  		 }
  		 choices(cusId);
      }
      public static void CancelOrder(String cusId) {
    	  Scanner sc=new Scanner(System.in);
    	  System.out.println("Please enter your order Id: ");
    	  String oid=sc.next();
    	  try {
    		  String query="delete from orders where order_id=? and cus_id=? ;";
    		  PreparedStatement st=con.prepareStatement(query);
    		  st.setString(1, oid);
    		  st.setString(2, cusId);
    		 int row= st.executeUpdate();
    		 if(row==1) {
    			 System.out.println("Order canceled Successfully...");
    			 choices(cusId);
    		 }else {
    			 System.out.println("Enter valid order id...");
    			 CancelOrder(cusId);
    		 }
    	  }
    	  catch(Exception e) {
    		  System.out.println("Enter valid order id...");
 			 CancelOrder(cusId);
    	  }
      }
	  public static double DisplayFood() {
		  Scanner sc=new Scanner(System.in);
    	  System.out.println("Enter any Restaurent Id from above list: ");
    	  String RestId=sc.nextLine().toUpperCase();
    	  double totalBill=0;
    		  try {
    			  String query="select food_name,price from menu where menu_id=(select menu_id from restaurent where restaurent_id='"+RestId+"');";
    			  Statement st=con.createStatement();
    		      ResultSet rs=st.executeQuery(query);
    		      Map<String,Double>m=new HashMap<String,Double>();
    		      while(rs.next()) {
    		    	  System.out.println(rs.getString(1)+" - "+rs.getDouble(2));
    		    	  String x=rs.getString(1).replaceAll(" ", "");
    		    	  m.put(x,rs.getDouble(2));
    		      }
    		      boolean b=true;
    		      do {
    		    	    totalBill += Ordering(m);
    		    	    System.out.println("Wanna order more...[y/n] ");
    		    	    char choice = sc.next().charAt(0);
    		    	    if (choice == 'n') {
    		    	        break;
    		    	    } else if (choice != 'y') {
    		    	        System.out.println("Enter valid choice...");
    		    	    }
    		    	} while (true);

    		    	return totalBill;
    		  }
    		  catch(Exception e) {
    			 // e.printStackTrace();
    			  System.out.println("Hotel name not Available....");
    			  return 0;
    		  }
    	  }
	  public static int Ordering(Map<String,Double> m) {
		  Scanner sc=new Scanner(System.in);
		  System.out.println("Select any food from above list:");
	      String food=sc.nextLine();
	      System.out.println("Enter the quantity of the food:");
	      int quantity=sc.nextInt();
	      int f=0,bill=0;
	      food=food.replaceAll(" ", "");
	      for(Map.Entry<String,Double> e:m.entrySet()) {
	    	  if(e.getKey().equalsIgnoreCase(food)) {
	    	      f=1;
	    		  bill+=e.getValue()*quantity;
	    	  }
	       }
	      if(f==0) {
	    	  System.out.println("Food not found...");
	    	  Ordering(m);
	      }
	      return bill;
	  }
}
