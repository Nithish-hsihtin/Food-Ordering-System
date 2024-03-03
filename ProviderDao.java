package rental;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ProviderDao {
	  static Connection con=ConnectionDB.createConnection();
       public static void choices(String MenuId) throws SQLException {
    	   Scanner sc=new Scanner(System.in);
    	   System.out.println("1.Enter 1 to Add food\n2.Enter 2 to Remove food\n"
    	   		+ "3.Enter 3 to Update price\n"
    	   		+ "4.Enter 4 to Exit");
    	   int choice=sc.nextInt();
    	   switch(choice) {
    	   case 1:
    		   addFood(MenuId);
    	   case 2:
    		   removeFood(MenuId);
    	   case 3:
    		   updatePrice(MenuId);
    	   case 4:
    		   con.close();
    		   System.exit(0);
    	   default:
    		   System.out.println("Please Enter valid choice...");
    		   choices(MenuId);
    	   }
       }
       public static void addFood(String MenuId) {
    	   Scanner sc=new Scanner(System.in);
    	   System.out.println("Enter Food name you want to add: ");
    	   String foodName=sc.nextLine();
    	   System.out.println("Enter the price for the food you added: ");
    	   double foodPrice=sc.nextDouble();
    	   try {
    	   String query="insert into menu values(?,?,?);";
    	   PreparedStatement st=con.prepareStatement(query);
    	   st.setString(1, MenuId);
    	   st.setString(2, foodName);
    	   st.setDouble(3, foodPrice);
    	   int row=st.executeUpdate();
    	   System.out.println("Food Added Successfully...");
    	   choices(MenuId);
    	   }
    	   catch(Exception e) {
    		   System.out.println("Please enter valid details...");
    		   try {
				choices(MenuId);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
    	   }
       }
       public static void removeFood(String MenuId) {
    	   Scanner sc=new Scanner(System.in);
    	   System.out.println("Enter the FoodName You want to Remove: ");
    	   String foodName=sc.nextLine();
    	   try {
    		   String query="delete from menu where menu_id=? and food_name=?;";
        	   PreparedStatement st=con.prepareStatement(query);
        	   st.setString(1, MenuId);
        	   st.setString(2, foodName);
        	   int row=st.executeUpdate();
        	   System.out.println("Food removed successfully...");
        	   choices(MenuId);
    	   }
    	   catch(Exception e) {
    		   System.out.println("Food not found...");
    		   try {
				choices(MenuId);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
    	   }
       }
       public static void updatePrice(String MenuId) {
    	   Scanner sc=new Scanner(System.in);
    	   System.out.println("Enter the foodName in Which price has to be Updated: ");
    	   String foodName=sc.nextLine();
    	   System.out.println("Enter the Updated price for the Food: ");
    	   double foodPrice=sc.nextDouble();
    	   try {
    		   String query="update menu set price=? where menu_id=? and food_name=?;";
        	   PreparedStatement st=con.prepareStatement(query);
        	   st.setDouble(1, foodPrice);
        	   st.setString(2, MenuId);
        	   st.setString(3, foodName);
        	   int row=st.executeUpdate();
        	   System.out.println("Food price updated successfully...");
        	   choices(MenuId);
    	   }
    	   catch(Exception e) {
    		   e.printStackTrace();
    	   }
       }
}
