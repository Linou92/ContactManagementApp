package se.lexicon;

import java.util.List;

public class Printer {

    public static void printMainMenu() {
        IO.println("""
                ===== CONTACT MANAGER =====
                1. List ALl Contacts
                2. Add Contact
                3. Search Contact
                4. Exit
                """);
    }

    public static void printAllContacts(List<Contact> contacts) {

        if (contacts.isEmpty()) {
            IO.println("No contacts found.");
            return;
        }
        IO.println("\n--- ALL CONTACT LIST ---");
        contacts.forEach(IO::println);
    }
}
