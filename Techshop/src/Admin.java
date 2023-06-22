import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Admin {
    private JTextField productNameField;
    private JTextField priceField;
    private JTextField descriptionField;

    public Admin() {
        JFrame frame = new JFrame("Admin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel productNameLabel = new JLabel("Product Name:");
        panel.add(productNameLabel, gbc);

        gbc.gridy++;
        JLabel priceLabel = new JLabel("Price:");
        panel.add(priceLabel, gbc);

        gbc.gridy++;
        JLabel descriptionLabel = new JLabel("Description:");
        panel.add(descriptionLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        productNameField = new JTextField(20);
        panel.add(productNameField, gbc);

        gbc.gridy++;
        priceField = new JTextField(10);
        panel.add(priceField, gbc);

        gbc.gridy++;
        descriptionField = new JTextField(30);
        panel.add(descriptionField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        JButton addProductButton = new JButton("Add product");
        addProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String productName = productNameField.getText();
                String price = priceField.getText();
                String description = descriptionField.getText();
                createProductFile(productName, price, description);
            }
        });
        panel.add(addProductButton, gbc);

        gbc.gridy++; // Increment the row position
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the Admin window
                Menu menu = new Menu();
                menu.setVisible(true); // Show the Menu window
            }
        });
        panel.add(backButton, gbc);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void createProductFile(String productName, String price, String description) {
        String filePath = "C:/Users/Quirf Ivan A. Onag/final-project-sa-friday-/Techshop/products/" + productName + ".txt";

        File file = new File(filePath);
        File directory = file.getParentFile();
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created) {
                System.out.println("Failed to create directory");
                return;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            String name = "Product Name";
            String priceHeader = "Product Price";
            String descriptionHeader = "Product Description";

            writer.write(name + ",");
            writer.write(priceHeader + ",");
            writer.write(descriptionHeader + ",");
            writer.newLine();

            writer.write(productName + ",");
            writer.write(price + ",");
            writer.write(description + ",");
            writer.newLine();
            writer.flush();

            System.out.println("Product file created successfully.");
        } catch (IOException e) {
            System.out.println("Error creating product file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Admin admin = new Admin();
    }

    public void setVisible(boolean b) {
    }
}
