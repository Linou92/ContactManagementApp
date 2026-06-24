package se.lexicon;

import java.util.List;

public class Printer {

    private static final ContactService service = new ContactService();

    public static void printMainMenu() {
        IO.println("""
                ===== CONTACT MANAGER =====
                1. List ALl Contacts
                2. Add Contact
                3. Search Contact
                4. Exit
                """);
    }

    public static void printAllContacts() {

        var contacts = service.getAllContacts();

        if (contacts.isEmpty()) {
            IO.println("No contacts found.");
            return;
        }

        IO.println("\n--- CONTACT LIST ---");

        contacts.stream()
                .sorted((c1, c2) -> {
                    int last = c1.getName().compareToIgnoreCase(c2.getName());
                    if (last != 0) return last;
                    else return c1.getName().compareToIgnoreCase(c2.getName());
                })
                .forEach(IO::println);
    }

    public static void searchContact(){
        String name = IO.readln("Enter name to search: ");
        var results = service.findByName(name);
        if (results.isEmpty()) {
            IO.println("Contact not found.");
            return;
        }
        IO.println("\n--- SEARCH RESULTS ---");
        results.forEach(IO::println);
    }
}
