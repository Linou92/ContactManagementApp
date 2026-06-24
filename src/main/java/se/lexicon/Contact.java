package se.lexicon;

import java.util.HashSet;
import java.util.Set;

public class Contact {

    private final int id;
    private String name;
    private Set<String> emails;
    private Set<String> phoneNumbers; // to prevent duplicate phone numbers

    public Contact(int id, String name, String email, Set<String> phoneNumbers) {
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

    public void addEmail(String email) {
        this.emails.add(email);
    }

    public void removeEmail(String email) {
        this.emails.remove(email);
    }

    public Set<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void addPhoneNumber(String phoneNumber) {
        this.phoneNumbers.add(phoneNumber);
    }

    public void removePhoneNumber(String phoneNumber) {
        this.phoneNumbers.remove(phoneNumber);
    }

    // defines when 2 contacts objects are equal
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // same memory object, if they point to the same objects
        if(!(obj instanceof Contact contact)) return false; // if it is not a contact
        return id == contact.id; // if same id then equal
    }

    // how to show the contact
    public String toString() {
        return String.format("""
            ID: %d
            Name: %s %s
            Emails: %s
            Phone Numbers: %s
            """, id, name, emails, phoneNumbers);
    }
}
