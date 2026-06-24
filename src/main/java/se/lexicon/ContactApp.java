package se.lexicon;

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
                case 2 -> addEmail(contact);
                case 3 -> editName(contact);
                case 4 -> {
                    deleteContact(contact);
                    return;
                }
                case 5 -> IO.println("Back to main menu");
                default -> IO.println("Invalid option");
            }

        } while (choice != 5);
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
            IO.println(contact.getId() + ": " + contact.getName());
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
            String phone = IO.readln("Enter phone (empty to stop): ");
            if (phone.isBlank()) break;
            try {
                InputValidator.isValidPhoneNumber(phone);
                contact.addPhoneNumber(phone);
            } catch (IllegalArgumentException e) {
                IO.println(e.getMessage());
            }
        }
    }

    private static void addEmail(Contact contact) {
        while (true) {
            String email = IO.readln("Enter email (empty to stop): ");
            if (email.isBlank()) break;
            try {
                InputValidator.isValidEmail(email);
                contact.addEmail(email);
            } catch (IllegalArgumentException e) {
                IO.println(e.getMessage());
            }
        }
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
