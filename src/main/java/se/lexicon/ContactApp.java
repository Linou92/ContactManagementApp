package se.lexicon;

import java.util.ArrayList;
import java.util.List;

public class ContactApp {

    private static final ContactService service = new ContactService();

    static void run() {

        int choice;

        do{
            Printer.printMainMenu();
            choice = Integer.parseInt(IO.readln("Choose an option: "));

            switch (choice) {
                case 1 -> Printer.printAllContacts(service.getAllContacts());
                case 2 -> addContact();
                case 3 -> searchContact();
                case 4 -> IO.println("Exit");
                default -> IO.println("Invalid option, try again.");
            }
        } while (choice != 4);
    }

    private static void contactMenu(Contact contact) {

        int choice;

        do {
            IO.print(contact);
            Printer.printContactMenu();
            choice = Integer.parseInt(IO.readln("Choose an option: "));
            switch (choice) {
                case 1 -> addPhone(contact);
                case 2 -> editPhone(contact);
                case 3 -> deletePhone(contact);
                case 4 -> addEmail(contact);
                case 5 -> editEmail(contact);
                case 6 -> deleteEmail(contact);
                case 7 -> editName(contact);
                case 8 -> {
                    deleteContact(contact);
                    return;
                }
                case 9 -> {
                    IO.println("Back to main menu");
                    return;
                }
                default -> IO.println("Invalid option");
            }
        } while (choice != 9);
    }

    private static void searchContact(){
        String name = IO.readln("Enter name to search: ");
        var results = service.findByName(name);
        if (results.isEmpty()) {
            IO.println("Contact not found.");
            return;
        }
        IO.println("\n--- SEARCH RESULTS ---");
        for (Contact contact : results) {
            IO.println(contact.getId() + ". " + contact.getName());
        }
        int id = Integer.parseInt(IO.readln("Select contact id: "));
        Contact contact = service.findById(id);
        if (contact != null) {
            contactMenu(contact);
        }
        else  {
            IO.println("Invalid selection.");
        }
    }

    private static void addContact() {
        String name = IO.readln("Enter name to add: ");
        Contact contact = service.createContact(name);
        addPhone(contact);
        addEmail(contact);
        IO.println("\n--- CONTACT ADDED SUCCESSFULLY ---\n");
    }

    private static void addPhone(Contact contact) {
        while (true) {
            String phone = IO.readln("Enter phone to add (empty to stop): ");
            if (phone.isBlank()) break;
            try {
                InputValidator.isValidPhoneNumber(phone);
                contact.addPhoneNumber(phone);
            } catch (IllegalArgumentException e) {
                IO.println(e.getMessage());
            }
        }
    }

    private static void editPhone(Contact contact) {
        List<String> phones = new ArrayList<>(contact.getPhoneNumbers());
        for (int i = 0; i < phones.size(); i++) {
            IO.println((i + 1) + ". " + phones.get(i));
        }
        int index = Integer.parseInt(IO.readln("Select phone to edit: ")) - 1;
        if (index < 0 || index >= phones.size()) {
            IO.println("Invalid selection.");
            return;
        }
        String oldPhone = phones.get(index);
        String newPhone = IO.readln("Enter new phone: ");
        try {
            InputValidator.isValidPhoneNumber(newPhone);
            contact.removePhoneNumber(oldPhone);
            contact.addPhoneNumber(newPhone);
            IO.println("Phone updated!");
        } catch (IllegalArgumentException e) {
            IO.println(e.getMessage());
        }
    }

    private static void deletePhone(Contact contact) {
        List<String> phones = new ArrayList<>(contact.getPhoneNumbers());
        if (phones.isEmpty()) {
            IO.println("No phone numbers to delete.");
            return;
        }
        for (int i = 0; i < phones.size(); i++) {
            IO.println((i + 1) + ". " + phones.get(i));
        }
        int choice = Integer.parseInt(IO.readln("Select phone to delete: ")) - 1;
        if (choice < 0 || choice >= phones.size()) {
            IO.println("Invalid selection.");
            return;
        }
        String removed = phones.get(choice);
        contact.removePhoneNumber(removed);
        IO.println("Phone deleted!");
    }

    private static void addEmail(Contact contact) {
        while (true) {
            String email = IO.readln("Enter email to add (empty to stop): ");
            if (email.isBlank()) break;
            try {
                InputValidator.isValidEmail(email);
                contact.addEmail(email);
            } catch (IllegalArgumentException e) {
                IO.println(e.getMessage());
            }
        }
    }

    private static void editEmail(Contact contact) {
        List<String> emails = new ArrayList<>(contact.getEmails());
        for (int i = 0; i < emails.size(); i++) {
            IO.println((i + 1) + ". " + emails.get(i));
        }
        int index = Integer.parseInt(IO.readln("Select email to edit: ")) - 1;
        if (index < 0 || index >= emails.size()) {
            IO.println("Invalid selection.");
            return;
        }
        String oldEmail = emails.get(index);
        String newEmail = IO.readln("Enter new email: ");
        try {
            InputValidator.isValidEmail(newEmail);
            contact.removeEmail(oldEmail);
            contact.addEmail(newEmail);
            IO.println("Email updated!");
        } catch (IllegalArgumentException e) {
            IO.println(e.getMessage());
        }
    }

    private static void deleteEmail(Contact contact) {
        List<String> emails = new ArrayList<>(contact.getEmails());
        if (emails.isEmpty()) {
            IO.println("No emails to delete.");
            return;
        }
        for (int i = 0; i < emails.size(); i++) {
            IO.println((i + 1) + ". " + emails.get(i));
        }
        int choice = Integer.parseInt(IO.readln("Select email to delete: ")) - 1;
        if (choice < 0 || choice >= emails.size()) {
            IO.println("Invalid selection.");
            return;
        }
        String removed = emails.get(choice);
        contact.removeEmail(removed);
        IO.println("Email deleted!");
    }

    private static void editName(Contact contact) {
        String name = IO.readln("Enter new name: ");
        contact.setName(name);
        IO.println("Name updated!");
    }

    private static void deleteContact(Contact contact) {
        boolean removed = service.deleteContact(contact.getId());
        IO.println(removed ? "Contact deleted!" : "Failed to delete contact!");
    }
}
