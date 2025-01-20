package validationTest;
import validation.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InputValidatorTests {
    @Test
    public void testIsEmpty_NullInput(){
        assertTrue(InputValidator.isEmpty(null));
    }

    @Test
    public void testIsEmpty_EmptyInput(){
        assertTrue(InputValidator.isEmpty(""));
    }

    @Test
    public void testIsValidUserName_EmptyInput(){
        assertFalse(InputValidator.isValidUserName(""));
    }

    @Test
    public void testIsValidUserName_TooShortInput(){
        assertFalse(InputValidator.isValidUserName("am"));
    }

    @Test
    public void testIsValidUserName_ValidInput(){
        assertTrue(InputValidator.isValidUserName("amina"));
    }

    @Test
    public void testIsValidEmail_EmptyInput(){
        assertFalse(InputValidator.isValidEmail(""));
    }

    @Test
    public void testIsValidEmail_ValidEmail(){
        assertTrue(InputValidator.isValidEmail("test@domain.com"));
    }

    @Test
    public void testIsValidEmail_InvalidEmail(){
        assertFalse(InputValidator.isValidEmail("testdomain.com"));
    }

    @Test
    public void testIsValidPassword_EmptyInput(){
        assertFalse(InputValidator.isValidPassword(""));
    }

    @Test
    public void testIsValidPassword_TooShortInput(){
        assertFalse(InputValidator.isValidPassword("pass1"));
    }

    @Test
    public void testIsValidPassword_ValidPassword(){
        assertTrue(InputValidator.isValidPassword("testpassword"));
    }

    //testet e meposhtme per IsValidInput jane te vlefshme per metoden qe lidhet me registerForm
    @Test
    public void testIsValidInput_EmptyNameInput(){
        String result = InputValidator.isValidInput("", "test@domain.com", "testpassword");
        assertEquals("All fields must be filled.", result);
    }

    @Test
    public void testIsValidInput_EmptyEmailInput(){
        String result = InputValidator.isValidInput("testName", "", "testpassword");
        assertEquals("All fields must be filled.", result);
    }

    @Test
    public void testIsValidInput_EmptyPasswordInput(){
        String result = InputValidator.isValidInput("testName", "test@domain.com", "");
        assertEquals("All fields must be filled.", result);
    }

    @Test
    public void testIsValidInput_EmptyInput(){
        String result = InputValidator.isValidInput("", "", "");
        assertEquals("All fields must be filled.", result);
    }

    @Test
    public void testIsValidInput_InvalidNameInput(){
        String result = InputValidator.isValidInput("am", "test@domain.com", "testpassword");
        assertEquals("Name must be at least 3 characters long.", result);
    }

    @Test
    public void testIsValidInput_InvalidEmailAndPasswordInput(){
        String result = InputValidator.isValidInput("testName", "testdomain.com", "pass1");
        assertEquals("Email must be a valid email address. \n example@domain.com", result);
    }

    @Test
    public void testIsValidInput_InvalidPasswordInput(){
        String result = InputValidator.isValidInput("testName", "test@domain.com", "pass1");
        assertEquals("Password must be at least 6 characters long.", result);
    }

    @Test
    public void testIsValidInput_ValidInput(){
        String result = InputValidator.isValidInput("testName", "test@domain.com", "testpassword");
        assertNull(result);
    }

    //testet e meposhtme per IsValidInput jane te vlefshme per metoden qe lidhet me loginForm
    @Test
    public void testIsValidInputLogin_EmptyEmailInput(){
        String result = InputValidator.isValidInput("", "");
        assertEquals("All fields must be filled.", result);
    }

    @Test
    public void testIsValidInputLogin_InvalidEmailInput(){
        String result = InputValidator.isValidInput("testdomain.com", "testpassword");
        assertEquals("Email must be a valid email address. \n example@domain.com", result);
    }

    @Test
    public void testIsValidInputLogin_InvalidPasswordInput(){
        String result = InputValidator.isValidInput("test@domain.com", "pass1");
        assertEquals("Password must be at least 6 characters long.", result);
    }

    @Test
    public void testIsValidInputLogin_ValidInput(){
        String result = InputValidator.isValidInput("test@domain.com", "testpassword");
        assertNull(result);
    }
}
