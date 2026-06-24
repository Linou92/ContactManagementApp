package se.lexicon;

public class ContactApp {

    private static void run() {

        int choice;

        do{
            Printer.printMainMenu();
            choice = Integer.parseInt(IO.readln("Choose an option: "));

            switch (choice) {
                case 1 -> Printer.printAllContacts();
                case 2 -> ContactService.addContact();
                case 3 -> Printer.searchContact();
                case 4 -> IO.println("Exit");
                default -> IO.println("Invalid option, try again.");
            }
        } while (choice != 4);
    }

}
