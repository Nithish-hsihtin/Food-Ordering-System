package rental;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

public class Admin {
	public static boolean addAdmin() {
        
   	 try {
   		 Scanner sc=new Scanner(System.in);
   		 Connection con=ConnectionDB.createConnection();
   		 validation valid=new validation();
   		 System.out.println("Enter Admin Name:");
   		 String AdminName=sc.nextLine();
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
   		 
        Random random =new Random();
	     int RandomId=random.nextInt(100)+100;
 		 String AdminId=("a"+Integer.toString(RandomId)).toUpperCase();
 
 		  String query="insert into admin values (?,?,?,?);";
 		 PreparedStatement st = con.prepareStatement(query);
 		 st.setString(1,AdminId);
 		 st.setString(2, AdminName);
 		 st.setString(3, email);
 		 st.setString(4, password);
 	     int row=st.executeUpdate();
 	     if(row==1) {
 	    	 System.out.println("Your Admin ID is: "+AdminId);
 	    	 return true;
 	     }else {
 	    	 return false;
 	     }
   	 }
   	 catch(Exception e) {
   		 return false;
   	 }
    }
    public static void verifyAdmin() {
   	 Scanner sc=new Scanner(System.in);
   	 try {
   	 System.out.println("Enter your Admin id:");
   	 String Id=sc.next();
   	 System.out.println("Enter your password:");
   	 String password=sc.next();
   		 Connection con=ConnectionDB.createConnection();
   		 String query="select admin_id,password from admin where admin_id='"+Id+"';";
   		 Statement st=con.createStatement();
   		 ResultSet rs=st.executeQuery(query);
   		 rs.next();
   		 if(rs.getString(1).equals(Id) && rs.getString(2).equals(password)) {
   			System.out.println("Verification sucessfull...");
   			AdminDao adm=new AdminDao();
   			adm.choices();
   		 }else {
   			 System.out.println("Please enter valid credentials...");
   			 verifyAdmin();
   		 }
   	 }
   	 catch(Exception e) {
   		 e.printStackTrace();
   	 }
    }
}
