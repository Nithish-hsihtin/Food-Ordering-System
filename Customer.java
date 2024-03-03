package rental;

import java.sql.*;
import java.util.*;
public class Customer {
	public static boolean addCustomer() {
           
    	 try {
    		 Scanner sc=new Scanner(System.in);
    		 Connection con=ConnectionDB.createConnection();
    		 validation valid=new validation();
    		 System.out.println("Enter Customer Name:");
    		 String CusName=sc.nextLine();
    		 System.out.println("Enter your email id:");
    		 boolean mail=false;
    		 String email="";
    		 while(!mail) {
    		  email=sc.nextLine();
    		  mail=valid.emailValid(email);
    		 }
    		 System.out.println("Enter your password:");
    		 boolean pass=false;
    		 String password="";
    		 while(!pass) {
    		 password=sc.nextLine();
    		  pass=valid.passValid(password);
    		 }
    		 System.out.println("Enter your address:");
    		 String address=sc.nextLine();
    		 System.out.println("Enter your contact:");
    		 boolean cont=false;
    		 String contact="";
    		 while(!cont) {
    			 contact=sc.next();
    			 cont=valid.contactValid(contact);
    		 }
    		 
         Random random =new Random();
 	     int RandomId=random.nextInt(100)+100;
  		 String CusId=(CusName.charAt(0)+Integer.toString(RandomId)).toUpperCase();
  
  		  String query="insert into customer values (?,?,?,?,?,?);";
  		 PreparedStatement st = con.prepareStatement(query);
  		 st.setString(1,CusId);
  		 st.setString(2, CusName);
  		 st.setString(3, email);
  		 st.setString(4, contact);
  		 st.setString(5,address);
  		 st.setString(6, password);
  	     int row=st.executeUpdate();
  	     if(row==1) {
  	    	 System.out.println("Your Customer ID is: "+CusId);
  	    	 return true;
  	     }else {
  	    	 return false;
  	     }
    	 }
    	 catch(Exception e) {
    		 return false;
    	 }
     }
     public static void verifyCustomer() {
    	 Scanner sc=new Scanner(System.in);
    	 try {
    	 System.out.println("Enter your Customer id:");
    	 String cusId=sc.next();
    	 System.out.println("Enter your password:");
    	 String password=sc.next();
    		 Connection con=ConnectionDB.createConnection();
    		 String query="select customer_id,password from customer where customer_id='"+cusId+"';";
    		 Statement st=con.createStatement();
    		 ResultSet rs=st.executeQuery(query);
    		 rs.next();
    		 if(rs.getString(1).equals(cusId) && rs.getString(2).equals(password)) {
    			System.out.println("Verification sucessfull...");
    			CustomerDao cus=new CustomerDao();
    			cus.choices(cusId);
    		 }else {
    			 System.out.println("Please enter valid credentials...");
    			 verifyCustomer();
    		 }
    	 }
    	 catch(Exception e) {
    		 e.printStackTrace();
    	 }
     }
}
