import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactManagerGUI extends JFrame {
    private ContactManager contactManager;
    private DefaultListModel<Contact> listModel;
    private JList<Contact> contactList;

    public ContactManagerGUI() {
        contactManager = new ContactManager();
        listModel = new DefaultListModel<>();
        contactList = new JList<>(listModel);
        loadContactsToList();

        setTitle("Contact Manager");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(contactList), BorderLayout.CENTER);
        panel.add(createButtonPanel(), BorderLayout.SOUTH);

        setContentPane(panel);
        setVisible(true);
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addContact();
            }
        });

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editContact();
            }
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteContact();
            }
        });

        panel.add(addButton);
        panel.add(editButton);
        panel.add(deleteButton);

        return panel;
    }

    private void addContact() {
        ContactDialog dialog = new ContactDialog(this, "Add Contact");
        dialog.setVisible(true);
        if (dialog.isConfirmed()) {
            Contact contact = dialog.getContact();
            contactManager.addContact(contact);
            listModel.addElement(contact);
        }
    }

    private void editContact() {
        int selectedIndex = contactList.getSelectedIndex();
        if (selectedIndex != -1) {
            Contact contact = listModel.getElementAt(selectedIndex);
            ContactDialog dialog = new ContactDialog(this, "Edit Contact", contact);
            dialog.setVisible(true);
            if (dialog.isConfirmed()) {
                Contact updatedContact = dialog.getContact();
                contactManager.updateContact(selectedIndex, updatedContact);
                listModel.set(selectedIndex, updatedContact);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a contact to edit", "Edit Contact", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteContact() {
        int selectedIndex = contactList.getSelectedIndex();
        if (selectedIndex != -1) {
            contactManager.deleteContact(selectedIndex);
            listModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Select a contact to delete", "Delete Contact", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void loadContactsToList() {
        for (Contact contact : contactManager.getContacts()) {
            listModel.addElement(contact);
        }
    }
}
