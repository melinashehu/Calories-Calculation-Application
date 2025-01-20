package validation;

/**
 *  Validates password input taken from the register/login form.
 *  This class should NOT be modified without careful consideration.
 *  Any changes to this class can impact the other parts of the application.
 */

/**
 * @author :Amina
 */
public class PasswordValidator {
    public static boolean isStrongPassword(String password) {
        String passwordReg = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$";
        return password !=null && password.matches(passwordReg);
    }
}
