package se.lexicon;

public class InputValidator {

    public static void isValidName(String name) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("❌ Name must not be empty");
        }
    }

    public static void isValidPhoneNumber(String phone) {
        if(!(phone == null || phone.matches("[0-9+\\- ]{6,20}"))){
            throw new IllegalArgumentException("❌ Invalid phone number");
        }
    }

    public static void isValidEmail(String email) {
        if (!(email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))){
            throw new IllegalArgumentException("❌ Invalid email address");
        }
    }
}
