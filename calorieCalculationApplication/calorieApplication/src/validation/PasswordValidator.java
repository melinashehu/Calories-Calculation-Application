package validation;

public class PasswordValidator {
    public static boolean isStrongPassword(String password) {
        String passwordReg = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$";
        return password !=null && password.matches(passwordReg);
    }


}
