import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Registration extends JFrame {
    private Menu menu;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JCheckBox adminCheckBox;
    private ProductCatalog productCatalog;

    public Registration(Menu menu, ProductCatalog productCatalog) {
        this.menu = menu;
        this.productCatalog = productCatalog;

        setTitle("User Registration");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel titleLabel = new JLabel("User Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(titleLabel, constraints);

        JLabel usernameLabel = new JLabel("Username:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(usernameLabel, constraints);

        usernameField = new JTextField(20); // Set the width to 20 columns
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(usernameField, constraints);

        JLabel passwordLabel = new JLabel("Password:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(passwordLabel, constraints);

        passwordField = new JPasswordField(20); // Set the width to 20 columns
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(passwordField, constraints);

        JLabel emailLabel = new JLabel("Email:");
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(emailLabel, constraints);

        emailField = new JTextField(20); // Set the width to 20 columns
        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(emailField, constraints);

        adminCheckBox = new JCheckBox("Admin Privileges");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        panel.add(adminCheckBox, constraints);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        panel.add(registerButton, constraints);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goBack();
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        panel.add(backButton, constraints);

        add(panel, BorderLayout.CENTER);
    }

    private void registerUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String email = emailField.getText();
        boolean isAdmin = adminCheckBox.isSelected();

        createRegistrationFile(username, email, password, isAdmin);

        JOptionPane.showMessageDialog(Registration.this, "Registration successful.", "Registration",
                JOptionPane.INFORMATION_MESSAGE);

        usernameField.setText("");
        passwordField.setText("");
        emailField.setText("");
        adminCheckBox.setSelected(false);
    }

    private void goBack() {
        menu.setVisible(true);
        dispose();
    }

    public static void createRegistrationFile(String username, String email, String password, boolean isAdmin) {
        String filePath = "C:/Users/Quirf Ivan A. Onag/homework/notepadv2.0/users.txt";

        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("User registration file created successfully.");
            } catch (IOException e) {
                System.out.println("Error creating user registration file: " + e.getMessage());
                return;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(username + ",");
            writer.write(password + ",");
            writer.write(email + ",");
            if (isAdmin) {
                writer.write("A");
            } else {
                writer.write("R");
            }
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.out.println("Error writing to user registration file: " + e.getMessage());
        }
    }
}
