import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Login extends JFrame {
    private static final String USERS_FILE = "users.txt";
    private Menu menu;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private ProductCatalog productCatalog;
    static User user;

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

        // JLabel emailLabel = new JLabel("Email:");
        // panel.add(emailLabel);

        // emailField = new JTextField();
        // panel.add(emailField);

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
            //emailField.setText("");

            String email = getEmail(username, password);
            String adminStatus = getAdminPrivelege(username, password);

            boolean isAdmin = false;
            if (adminStatus.equals("A")) {
                isAdmin = true;
            }

            user = new User(email, password, username, isAdmin);

            System.out.println("hellooo");

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
 

    public static boolean checkCredentials(String username, String password) {
        
        try {
            List<String> lines = Files.readAllLines(Paths.get(USERS_FILE));
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts[0].equals(username) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getEmail(String username, String password) {
    
    try {
        List<String> lines = Files.readAllLines(Paths.get(USERS_FILE));
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts[0].equals(username) && parts[1].equals(password)) {
                return parts[1];
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
        return "";
    }

    public static String getAdminPrivelege(String username, String password) {
    
    try {
        List<String> lines = Files.readAllLines(Paths.get(USERS_FILE));
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts[0].equals(username) && parts[1].equals(password)) {
                return parts[3];
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
        return "";
    }

    

    private void goBack() {
        menu.setVisible(true);
        dispose();
    }


}
