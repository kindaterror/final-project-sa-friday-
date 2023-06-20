import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registration extends JFrame {
    private Menu menu;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private ProductCatalog productCatalog;

    public Registration(Menu menu, ProductCatalog productCatalog) {
        this.menu = menu;
        this.productCatalog = productCatalog;

        setTitle("User Registration");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(4, 2));

        JLabel usernameLabel = new JLabel("Username:");
        panel.add(usernameLabel);

        usernameField = new JTextField();
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        panel.add(passwordField);

        JLabel emailLabel = new JLabel("Email:");
        panel.add(emailLabel);

        emailField = new JTextField();
        panel.add(emailField);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        panel.add(registerButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goBack();
            }
        });
        panel.add(backButton);

        add(panel, BorderLayout.CENTER);
    }

    private void registerUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String email = emailField.getText();

        JOptionPane.showMessageDialog(Registration.this, "Registration successful.", "Registration",
                JOptionPane.INFORMATION_MESSAGE);

        usernameField.setText("");
        passwordField.setText("");
        emailField.setText("");
    }

    private void goBack() {
        menu.setVisible(true);
        dispose();
    }
}
