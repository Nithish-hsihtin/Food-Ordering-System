package rental;
import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		//Auth authentication=new Auth();
		System.out.println("Enter 1 for Provider\nEnter 2 for Customer\nEnter 3 for Admin");
		int userDetail =sc.nextInt();
		if(userDetail==1) {
            Auth.ProviderAuth();
		}
		else if(userDetail==2) {
			Auth.CustomerAuth();
		}
		else if(userDetail==3) {
			Auth.AdminAuth();
		}
		else {
			System.out.println("Please Enter valid Input...");
			}
  }
}
