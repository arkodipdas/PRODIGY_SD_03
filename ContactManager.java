import java.io.*;
import java.util.ArrayList;

public class ContactManager {
    private ArrayList<Contact> contacts;
    private static final String FILE_NAME = "contacts.ser";

    public ContactManager() {
        contacts = new ArrayList<>();
        loadContacts();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        saveContacts();
    }

    public void updateContact(int index, Contact contact) {
        contacts.set(index, contact);
        saveContacts();
    }

    public void deleteContact(int index) {
        contacts.remove(index);
        saveContacts();
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    private void saveContacts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(contacts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadContacts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            contacts = (ArrayList<Contact>) ois.readObject();
        } catch (FileNotFoundException e) {
            // File not found, start with an empty list
            contacts = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
