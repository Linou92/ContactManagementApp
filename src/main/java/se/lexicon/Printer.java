package se.lexicon;

import java.util.List;

public class Printer {

    public static void printMainMenu() {
        IO.println("""
                ===== CONTACT MANAGER APP =====
                1. List ALl Contacts
                2. Add Contact
                3. Search Contact
                4. Exit
                """);
    }

    public static void printContactMenu() {
        IO.println("""
                --- CONTACT MENU ---
                1. Add Phone Number
                2. Add Email
                3. Edit Name
                4. Delete Contact
                5. Back
                """);
    }

    public static void printAllContacts(List<Contact> contacts) {

        if (contacts.isEmpty()) {
            IO.println("No contacts found.");
            return;
        }
        IO.println("\n--- ALL CONTACT LIST ---");
        contacts.stream()
                .sorted((c1, c2) ->
                        c1.getName().compareToIgnoreCase(c2.getName()))
                .forEach(IO::println);
    }
}
