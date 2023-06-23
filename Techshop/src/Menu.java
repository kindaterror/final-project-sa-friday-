import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private Registration registration;
    private Login login;
    private ProductCatalog productCatalog;

    public Menu() {
        setTitle("TECH SHOP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new BorderLayout());
        this.setLocationRelativeTo(null); // center form in the screen.

        JPanel panel = new JPanel(new GridLayout(4, 1));

        JLabel titleLabel = new JLabel("TECH SHOP");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titleLabel);

        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setBackground(Color.WHITE);
        registerButton.setPreferredSize(new Dimension(120, 30));
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showRegistration();
            }
        });
        panel.add(registerButton);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(Color.WHITE);
        loginButton.setPreferredSize(new Dimension(120, 30));
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showLogin();
            }
        });
        panel.add(loginButton);
        
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 16));
        exitButton.setBackground(Color.WHITE);
        exitButton.setPreferredSize(new Dimension(120, 30));
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panel.add(exitButton);

        add(panel, BorderLayout.CENTER);
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
