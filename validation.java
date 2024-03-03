package rental;

import java.util.regex.*;    
import java.util.*;
public class validation {
    public static boolean emailValid(String email) {
    	 String regex = "^(.+)@(.+)$"; 
    	 Pattern pattern = Pattern.compile(regex);
    	 Matcher matcher = pattern.matcher(email); 
    	 if(matcher.matches()) { 
    		 return true;
    	 }else {
    		 System.out.println("Please enter valid mail Id:");
    		 return false;
    	 }
    }
    public static boolean passValid(String pass) {
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
        Pattern p = Pattern.compile(regex);
        Matcher m=p.matcher(pass);
        if(m.matches()) {
    	return true;
        }else {
        	 System.out.println("Please enter Strong password.\nContains at least one numeric digit.\r\n"
        	 		+ "Contains at least one lowercase letter.\r\n"
        	 		+ "Contains at least one uppercase letter.\r\n"
        	 		+ "Contains at least one special character.\r\n"
        	 		+ "Does not contain any whitespace.\r\n"
        	 		+ "Has a length between 8 and 20 charactersr");
        	return false;
        }
    }
    public static boolean contactValid(String cont) {
    	if(cont.matches("\\d{10}")) {
    	    return true;
    	}else {
    		 System.out.println("Please enter valid contact:");
    		return false;
    	}
    }
}
