import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Admin {
    private JTextField productNameField;

    public Admin() {
        JFrame frame = new JFrame("Admin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel();

        productNameField = new JTextField(20);
        panel.add(productNameField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String productName = productNameField.getText();
                createProductFile(productName);
            }
        });
        panel.add(loginButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void createProductFile(String productName) {
        String filePath = "C:/Users/Quirf Ivan A. Onag/homework/notepadv2.0/products/" + productName + ".txt";

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
            String price = "Product Price";
            String description = "Product Description";

            writer.write(name + ",");
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
