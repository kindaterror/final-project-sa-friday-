import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private Menu menu;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private ProductCatalog productCatalog;

    public Login(Menu menu, ProductCatalog productCatalog) {
        this.menu = menu;
        this.productCatalog = productCatalog;

        setTitle("User Login");
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

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });
        panel.add(loginButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goBack();
            }
        });
        panel.add(backButton);

        add(panel, BorderLayout.CENTER);
    }

    private void loginUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        boolean isValidCredentials = checkCredentials(username, password);

        if (isValidCredentials) {
            JOptionPane.showMessageDialog(Login.this, "Login successful.", "Login",
                    JOptionPane.INFORMATION_MESSAGE);
            usernameField.setText("");
            passwordField.setText("");
            emailField.setText("");

            if (productCatalog == null) {
                productCatalog = new ProductCatalog(menu);
            }
            productCatalog.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(Login.this, "Invalid username or password.", "Login Error",
                    JOptionPane.ERROR_MESSAGE);
            passwordField.setText("");
        }
    }

    private boolean checkCredentials(String username, String password) {
        return username.equals("admin") && password.equals("password");
    }

    private void goBack() {
        menu.setVisible(true);
        dispose();
    }
}
