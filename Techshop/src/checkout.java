import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class checkout extends JFrame {
    private JTextField nameField;
    private JTextArea addressArea;
    private JTextArea productsArea;
    private Cart cart;

    public checkout(Cart cart) {
        this.cart = cart;

        setTitle("Checkout");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
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

        JLabel nameLabel = new JLabel("Name:");
        formPanel.add(nameLabel, gbc);

        gbc.gridy++;
        JLabel addressLabel = new JLabel("Address:");
        formPanel.add(addressLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(10, 0, 0, 10);

        nameField = new JTextField();
        formPanel.add(nameField, gbc);

        gbc.gridy++;
        addressArea = new JTextArea();
        JScrollPane addressScrollPane = new JScrollPane(addressArea);
        addressScrollPane.setPreferredSize(new Dimension(200, 100));
        formPanel.add(addressScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel productsLabel = new JLabel("Selected Products:");
        formPanel.add(productsLabel, gbc);

        gbc.gridy++;
        productsArea = new JTextArea();
        productsArea.setEditable(false);
        JScrollPane productsScrollPane = new JScrollPane(productsArea);
        productsScrollPane.setPreferredSize(new Dimension(300, 100));
        formPanel.add(productsScrollPane, gbc);

        panel.add(formPanel, BorderLayout.CENTER);

        JButton proceedToPaymentButton = new JButton("Proceed to Payment");
        proceedToPaymentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performCheckout();
                openPaymentFrame();
            }
        });

        JButton backButton = new JButton("Exit");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCart();
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(proceedToPaymentButton);
        buttonPanel.add(backButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);
    }

    private void performCheckout() {
        String name = nameField.getText();
        String address = addressArea.getText();

        // Create the checkout message
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n");
        sb.append("Address: ").append(address).append("\n\n");
        sb.append("Products:\n").append(cart.getProductsAsString());

        // Save the checkout details to a text file
        String directory = "C:/Users/Quirf Ivan A. Onag/final-project-sa-friday-/Techshop/checkout/"; // Update with your desired directory path
        String fileName = directory + name + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Show a success message
        String message = "Checkout details saved to file: " + fileName;
        JOptionPane.showMessageDialog(checkout.this, message, "Checkout", JOptionPane.INFORMATION_MESSAGE);

        // Clear the input fields
        nameField.setText("");
        addressArea.setText("");
    }

    private void openPaymentFrame() {
        Payment paymentFrame = new Payment(cart);
        paymentFrame.setVisible(true);
    }

    private void showCart() {
        cart.setVisible(true);
    }

    public void updateProductsArea() {
        productsArea.setText(cart.getProductsAsString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Cart cart = new Cart(); // Pass the product catalog reference if available
                checkout checkoutFrame = new checkout(cart);
                checkoutFrame.updateProductsArea(); // Update the products area
                checkoutFrame.setVisible(true);
            }
        });
    }
}
