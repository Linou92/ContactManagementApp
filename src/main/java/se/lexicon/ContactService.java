package se.lexicon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactService {

    private final Map<Integer, Contact> contacts = new HashMap<>();
    private int id = 1;

    public Contact createContact(String name) {
        Contact contact = new Contact(id++, name);
        contacts.put(contact.getId(), contact);
        return contact;
    }

    // return a copy of the list
    public List<Contact> getAllContacts() {
        return new ArrayList<>(contacts.values());
    }

    // find contact by name => return list of contacts with name
    public List<Contact> findByName(String name) {
        return contacts.values().stream()
                .filter(c -> c.getName().toLowerCase()
                        .contains(name.toLowerCase()))
                .toList();
    }

    // find contact by id
    public Contact findById(int id) {
        return contacts.get(id);
    }

    // delete a contact by id if not already removed
    public boolean deleteContact(int id) {
        return contacts.remove(id) != null;
    }
}
