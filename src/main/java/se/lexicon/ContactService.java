package se.lexicon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactService {

    private static final Map<Integer, Contact> contacts = new HashMap<>();
    private int id = 1;

    public Contact createContact(String name) {
        return new Contact(id++, name);
    }

    // add a contact to the hashmap
    public static void addContact(Contact contact) {
        contacts.put(contact.getId(), contact);
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

    // delete a contact by id if not already removed
    public boolean deleteContact(int id) {
        return contacts.remove(id) != null;
    }

    // update name
    public boolean updateName(int id, String name){
        Contact contact = contacts.get(id);
        if(contact == null) return false;
        if(name != null && !name.isBlank()) contact.setName(name);
        return true;
    }

    // add phone number
    public boolean addPhoneNumber(int id, String number){
        Contact contact = contacts.get(id);
        if(contact == null) return false;
        return contact.addPhoneNumber(number);
    }

    // add email
    public boolean addEmail(int id, String email){
        Contact contact = contacts.get(id);
        if(contact == null) return false;
        return contact.addEmail(email);
    }
}
