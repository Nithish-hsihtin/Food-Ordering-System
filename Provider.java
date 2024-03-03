package rental;

import java.sql.*;
import java.util.*;
public class Provider {
     public static boolean addProvider() {
  
    	 try {
    		 Scanner sc=new Scanner(System.in);
    		 Connection con=ConnectionDB.createConnection();
    		 validation valid=new validation();
    		 System.out.println("Enter Restaurent Name:");
    		 String resName=sc.nextLine();
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
  		 String ResId=(resName.charAt(0)+Integer.toString(RandomId)).toUpperCase();
  		 String MenuId=("m"+Integer.toString(RandomId)).toUpperCase();
  		 //System.out.print(ResId+" "+MenuId);
  		  String query="insert into restaurent values (?,?,?,?,?,?,?);";
  		 PreparedStatement st = con.prepareStatement(query);
  		 st.setString(1,ResId);
  		 st.setString(2, resName);
  		 st.setString(3, email);
  		 st.setString(4, address);
  		 st.setString(5,contact);
  		 st.setString(6, MenuId);
  		 st.setString(7, password);
  	     int row=st.executeUpdate();
  	     if(row==1) {
  	    	 System.out.println("Your restaurent ID is: "+ResId); 	    	
  	    	 return true;
  	     }else {
  	    	 return false;
  	     }
    	 }
    	 catch(Exception e) {
    		 return false;
    	 }
     }
     public static void verifyProvider() {
    	 Scanner sc=new Scanner(System.in);
    	 try {
    	 System.out.println("Enter your restaurent id:");
    	 String resId=sc.next();
    	 System.out.println("Enter your password:");
    	 String password=sc.next();
    		 Connection con=ConnectionDB.createConnection();
    		 String query="select restaurent_id,password,Menu_Id from restaurent where restaurent_id='"+resId+"';";
    		 Statement st=con.createStatement();
    		 ResultSet rs=st.executeQuery(query);
    		 rs.next();
    		 String MenuId=rs.getString(3);
    		 if(rs.getString(1).equals(resId) && rs.getString(2).equals(password)) {
    			System.out.println("Verification sucessfull...");
    			ProviderDao dao=new ProviderDao();
    			dao.choices(MenuId);
    		 }else {
    			 System.out.println("Please enter valid credentials...");
    			 verifyProvider();
    		 }
    	 }
    	 catch(Exception e) {
    		 e.printStackTrace();
    	 }
     }
}
