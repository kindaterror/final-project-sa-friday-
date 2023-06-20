import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private Registration registration;
    private Login login;
    private ProductCatalog productCatalog;

    public Menu() {
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 1));

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showRegistration();
            }
        });
        panel.add(registerButton);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showLogin();
            }
        });
        panel.add(loginButton);
        
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panel.add(exitButton);

        add(panel, BorderLayout.CENTER);
        
        // Customizing the JFrame title
        setTitle("TECH SHOP");
    }

    private void showRegistration() {
        if (registration == null) {
            registration = new Registration(this, productCatalog);
        }
        registration.setVisible(true);
        setVisible(false);
    }

    private void showLogin() {
        if (login == null) {
            login = new Login(this, productCatalog);
        }
        login.setVisible(true);
        setVisible(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Menu menu = new Menu();
                menu.setVisible(true);
            }
        });
    }
}
