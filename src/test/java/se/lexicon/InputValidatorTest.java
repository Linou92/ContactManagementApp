package se.lexicon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {

    // -------------------------
    // NAME VALIDATION
    // -------------------------

    @Test
    void validateName_positive() {
        assertDoesNotThrow(() -> InputValidator.isValidName("John Doe"));
    }

    @Test
    void validateName_negative_null() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> InputValidator.isValidName(null));

        assertEquals("❌ Name must not be empty", exception.getMessage());
    }

    @Test
    void validateName_negative_blank() {
        assertThrows(IllegalArgumentException.class,
                () -> InputValidator.isValidName("   "));
    }

    // -------------------------
    // PHONE VALIDATION
    // -------------------------

    @Test
    void validatePhone_positive() {
        assertDoesNotThrow(() ->
                InputValidator.isValidPhoneNumber("+46 70 123 45 67"));
    }

    @Test
    void validatePhone_negative_letters() {
        assertThrows(IllegalArgumentException.class,
                () -> InputValidator.isValidPhoneNumber("abc123"));
    }

    @Test
    void validatePhone_negative_tooShort() {
        assertThrows(IllegalArgumentException.class,
                () -> InputValidator.isValidPhoneNumber("123"));
    }

    // -------------------------
    // EMAIL VALIDATION
    // -------------------------

    @Test
    void validateEmail_positive() {
        assertDoesNotThrow(() ->
                InputValidator.isValidEmail("test@example.com"));
    }

    @Test
    void validateEmail_negative_missingAt() {
        assertThrows(IllegalArgumentException.class,
                () -> InputValidator.isValidEmail("testemail.com"));
    }

    @Test
    void validateEmail_negative_null() {
        assertThrows(IllegalArgumentException.class,
                () -> InputValidator.isValidEmail(null));
    }
}