package login;
import entity.*;
/**
 *  Keeps the logged user in a session in order to use the application features.
 *  This class should NOT be modified without careful consideration.
 *  IMPORTANT: Any changes to this class may impact the other parts of the application.
 */

/**
 * @author :Amina
 */
public class UserSession {
    private static User loggedInUser;

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User user) {
        loggedInUser = user;
    }

    public static void clearSession(){
        loggedInUser = null;
    }
}
