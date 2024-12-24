package validation;
import java.util.regex.Pattern;
public class InputValidator {

    public static boolean isEmpty(String input){
        return input==null || input.trim().isEmpty();
    }

    public static boolean isValidUserName(String userName){
        return !isEmpty(userName) && userName.length()>=3;
    }

    public static boolean isValidEmail(String email){
        String emailReg = "^[A-Za-z0-9+_.-]+@(.+)$";
        return !isEmpty(email) && Pattern.matches(emailReg, email);
    }

    public static boolean isValidPassword(String password){
        return !isEmpty(password) && password.length()>=6;
    }
}
