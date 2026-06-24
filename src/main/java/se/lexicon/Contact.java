package se.lexicon;

import java.util.HashSet;
import java.util.Set;

public class Contact {

    private final int id;
    private String name;
    private Set<String> emails;
    private Set<String> phoneNumbers; // to prevent duplicate phone numbers

    public Contact(int id, String name) {
        this.id = id;
        this.name = name;
        this.emails = new HashSet<>();
        this.phoneNumbers = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getEmails() {
        return emails;
    }

    public boolean addEmail(String email) {
        return emails.add(email);
    }

    public void removeEmail(String email) {
        emails.remove(email);
    }

    public Set<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public boolean addPhoneNumber(String phoneNumber) {
        return phoneNumbers.add(phoneNumber);
    }

    public void removePhoneNumber(String phoneNumber) {
        phoneNumbers.remove(phoneNumber);
    }

    // defines when 2 contacts objects are equal
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // same memory object, if they point to the same objects
        if(!(obj instanceof Contact contact)) return false; // if it is not a contact
        return id == contact.id; // if same id then equal
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    // how to show the contact
    @Override
    public String toString() {
        return String.format("""
            Name: %s
            Emails: %s
            Phone Numbers: %s
            """, name, emails, phoneNumbers);
    }
}
