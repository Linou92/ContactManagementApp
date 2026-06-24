package se.lexicon;

public class ContactApp {

    private static final ContactService service = new ContactService();

    private static void run() {

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

    private static void searchContact(){
        String name = IO.readln("Enter name to search: ");
        var results = service.findByName(name);
        if (results.isEmpty()) {
            IO.println("Contact not found.");
            return;
        }
        IO.println("\n--- SEARCH RESULTS ---");
        Printer.printAllContacts(results);
    }

    private static void addContact(){
        String name = IO.readln("Enter name to add: ");
        Contact contact = service.createContact(name);

        while (true){
            String phone = IO.readln("Enter phone number to add (empty to stop): ");
            if(phone.isBlank()){
                break;
            }
            contact.addPhoneNumber(phone);
            String email = IO.readln("Enter email to add (empty to stop): ");
            if(email.isBlank()){
                break;
            }
            contact.addEmail(email);
        }
        IO.println("\n--- CONTACT ADDED SUCCESSFULLY ---\n");
    }
}
