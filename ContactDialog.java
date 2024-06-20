import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactDialog extends JDialog {
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField emailField;
    private boolean confirmed;

    public ContactDialog(JFrame parent, String title) {
        super(parent, title, true);
        initialize();
    }

    public ContactDialog(JFrame parent, String title, Contact contact) {
        super(parent, title, true);
        initialize();
        nameField.setText(contact.getName());
        phoneField.setText(contact.getPhoneNumber());
        emailField.setText(contact.getEmailAddress());
    }

    private void initialize() {
        setLayout(new BorderLayout());

        nameField = new JTextField();
        phoneField = new JTextField();
        emailField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Phone:"));
        panel.add(phoneField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);

        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmed = true;
                setVisible(false);
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmed = false;
                setVisible(false);
            }
        });

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(getParent());
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public Contact getContact() {
        return new Contact(nameField.getText(), phoneField.getText(), emailField.getText());
    }
}
