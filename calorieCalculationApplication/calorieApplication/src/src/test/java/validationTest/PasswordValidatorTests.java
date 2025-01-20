package validationTest;
import validation.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class PasswordValidatorTests {
    @Test
    public void testIsStrongPassword_NullInput(){
        boolean result = PasswordValidator.isStrongPassword(null);
        assertFalse(result);
    }

    @Test
    public void testIsStrongPassword_TooShortInput(){
        boolean result = PasswordValidator.isStrongPassword("am12");
        assertFalse(result);
    }

    @Test
    public void testIsStrongPassword_NoSpecialCharacterInput(){
        boolean result = PasswordValidator.isStrongPassword("test12345");
        assertFalse(result);
    }

    @Test
    public void testIsStrongPassword_StrongPasswordInput(){
        boolean result = PasswordValidator.isStrongPassword("20Janar@+");
        assertTrue(result);
    }

    @Test
    public void testIsStrongPassword_WeakPasswordInput(){
        boolean result = PasswordValidator.isStrongPassword("Abc123");
        assertFalse(result);
    }
}
