import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class ProductCatalog extends JFrame {
    private Menu menu;
    private Cart cart;

    public ProductCatalog(Menu menu) {
        this.menu = menu;

        setTitle("Product Catalog");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new BorderLayout()); // Main panel

        String directoryPath = "products/";
        displayProducts(directoryPath, panel);

        JPanel searchPanel = new JPanel(new FlowLayout()); // Panel for search bar
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        panel.add(searchPanel, BorderLayout.NORTH); // Add search panel to the NORTH position

        JButton cartButton = new JButton("View Cart");
        cartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCart();
            }
        });

      JButton adminButton = new JButton("Admin");
        if (isAdminUser()) {
            adminButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    openAdminPage();
                }

                private void openAdminPage() {
                }
            });
            panel.add(adminButton);
        }

        // Add other components to the panel...
    }

    private boolean isAdminUser() {
        String filePath = "users.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            if (line != null) {
                if (line.equals("A")) {
                    return true;  // User has admin role (A)
                } else if (line.equals("R")) {
                    return false; // User has regular role (R)
                } else {
                    System.out.println("Unknown role: " + line);
                    // Perform actions for unknown role or handle the case accordingly
                }
            } else {
                System.out.println("File is empty.");
                // Perform actions when file is empty or handle the case accordingly
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            // Handle file reading error
        }

        // Default behavior if the file couldn't be read or an error occurred
        return false;
    }
    
    private void displayProducts(String directoryPath, JPanel panel) {
        JPanel productPanel = new JPanel(new GridLayout(0, 1, 0, 5));

        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    String productName = file.getName();
                    Product product = new Product(productName);

                    JPanel filePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    filePanel.add(new JLabel(productName));

                    JButton addToCartButton = new JButton("Add to Cart");
                    addToCartButton.setPreferredSize(new Dimension(100, 20));
                    addToCartButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            addToCart(product);
                        }
                    });

                    filePanel.add(addToCartButton);
                    productPanel.add(filePanel);
                }
            }
        }

        JScrollPane scrollPane = new JScrollPane(productPanel);
        panel.add(scrollPane, BorderLayout.CENTER);
    }

    public void addToCart(Product product) {
        if (cart == null) {
            cart = new Cart(this);
        }
        cart.addProduct(product);
        JOptionPane.showMessageDialog(ProductCatalog.this, "Product added to cart.", "Add to Cart",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void showCart() {
        if (cart == null) {
            cart = new Cart(this);
        }
        cart.setVisible(true);
        setVisible(false);
    }

    public void showProductCatalog() {
        setVisible(true);
    }
}
