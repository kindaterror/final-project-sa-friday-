import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Payment extends JFrame {
    private JTextField paymentMethodField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private Cart cart;
    private int fileCounter;

    public Payment(Cart cart) {
        this.cart = cart;
        this.fileCounter = 1;

        setTitle("Payment Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());
        this.setLocationRelativeTo(null); // center form in the screen.

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 0, 10);

        JLabel paymentMethodLabel = new JLabel("Payment Method:");
        formPanel.add(paymentMethodLabel, gbc);

        gbc.gridy++;
        JLabel emailLabel = new JLabel("Email:");
        formPanel.add(emailLabel, gbc);

        gbc.gridy++;
        JLabel passwordLabel = new JLabel("Password:");
        formPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(10, 0, 0, 10);

        paymentMethodField = new JTextField();
        formPanel.add(paymentMethodField, gbc);

        gbc.gridy++;
        emailField = new JTextField();
        formPanel.add(emailField, gbc);

        gbc.gridy++;
        passwordField = new JPasswordField();
        formPanel.add(passwordField, gbc);

        panel.add(formPanel, BorderLayout.CENTER);

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performCheckout();
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCart();
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(checkoutButton);
        buttonPanel.add(backButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);
    }

    private void performCheckout() {
        String paymentMethod = paymentMethodField.getText();
        String userName = emailField.getText();
        String password = new String(passwordField.getPassword());

        // Create the checkout message
        StringBuilder sb = new StringBuilder();
        sb.append("Payment Method: ").append(paymentMethod).append("\n");
        sb.append("Username: ").append(userName).append("\n");
        sb.append("Password: ").append(password);

        // Generate a unique file name based on the user's name and counter
        String fileName = getUniqueFileName(userName);

        // Save the checkout details to a text file
        String directory = "C:/Users/Quirf Ivan A. Onag/final-project-sa-friday-/Techshop/payment detail/"; // Update with your desired directory path
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(directory + fileName))) {
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Show a success message
        String message = "Checkout details saved to file: " + fileName;
        JOptionPane.showMessageDialog(Payment.this, message, "Checkout", JOptionPane.INFORMATION_MESSAGE);

        // Clear the input fields
        paymentMethodField.setText("");
        emailField.setText("");
        passwordField.setText("");

        // Open the cart
        if (cart != null) {
            cart.showCart();
        }
    }

    private String getUniqueFileName(String userName) {
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String fileName = userName.replaceAll("[^a-zA-Z0-9]", "") + "_" + fileCounter + "_" + timeStamp + ".txt";
        fileCounter++;
        return fileName;
    }

    private void showCart() {
        if (cart != null) {
            cart.setVisible(true);
        }
    }
}
