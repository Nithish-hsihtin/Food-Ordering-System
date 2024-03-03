package rental;

import java.util.Scanner;

public class Auth {
     public static void ProviderAuth() {
    	 Provider p=new Provider();
    	 Scanner sc=new Scanner(System.in);
			System.out.println("Enter 1 for Signup and 2 for Login");
			int getProvider=sc.nextInt();
			if(getProvider==1) {
				boolean b=p.addProvider();
				if(b) {
					System.out.println("Restaurent added succesfully");
					p.verifyProvider();
				}else {
					System.out.println("please try again...");
				}
			}
			else if(getProvider==2) {
			 p.verifyProvider();
			}
     }
     public static void CustomerAuth() {
    	 Customer c=new Customer();
    	 Scanner sc=new Scanner(System.in);
    	 System.out.println("Enter 1 for Signup and 2 for Login");
			int getCustomer=sc.nextInt();
         if(getCustomer==1) {
				boolean b=c.addCustomer();
				if(b) {
					System.out.println("Customer signup succesfull");
					c.verifyCustomer();
				}else {
					System.out.println("please try again...");
				}
			}
			else if(getCustomer==2) {
				c.verifyCustomer();
			}
     }
     public static void AdminAuth() {
    	 Admin a=new Admin();
    	 Scanner sc=new Scanner(System.in);
    	 System.out.println("Enter 1 for Signup and 2 for Login");
			int get=sc.nextInt();
         if(get==1) {
				boolean b=a.addAdmin();
				if(b) {
					System.out.println("Customer signup succesfull");
					a.verifyAdmin();
				}else {
					System.out.println("please try again...");
				}
			}
			else if(get==2) {
				a.verifyAdmin();
			}
     }
}
