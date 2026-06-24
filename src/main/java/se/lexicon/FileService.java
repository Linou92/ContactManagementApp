package se.lexicon;

import java.io.*;
import java.util.*;

public class FileService {

    private static final String FILE_NAME = "contacts.csv";

    // -------------------------
    // SAVE (EXPORT)
    // -------------------------
    public static void save(List<Contact> contacts) {

        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Contact c : contacts) {
                String emails = String.join(",", c.getEmails());
                String phones = String.join(",", c.getPhoneNumbers());
                writer.printf("%d;%s;%s;%s%n",
                        c.getId(),
                        c.getName(),
                        emails,
                        phones
                );
            }
        } catch (IOException e) {
            IO.println("Error saving contacts: " + e.getMessage());
        }
    }

    // -------------------------
    // LOAD (IMPORT)
    // -------------------------
    public static List<Contact> load() {
        List<Contact> contacts = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return contacts;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                Contact contact = new Contact(id, name);
                if (!parts[2].isBlank()) {
                    String[] emails = parts[2].split(",");
                    for (String e : emails) {
                        contact.addEmail(e);
                    }
                }
                if (!(parts.length < 4) && !parts[3].isBlank()) {
                    String[] phones = parts[3].split(",");
                    for (String p : phones) {
                        contact.addPhoneNumber(p);
                    }
                }
                contacts.add(contact);
            }
        } catch (IOException e) {
            IO.println("Error loading contacts: " + e.getMessage());
        }
        return contacts;
    }
}