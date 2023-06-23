import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ProductCatalog extends JFrame {
    private static final String USERS_FILE = "users.txt";
    private Menu menu;
    private Cart cart;

    public ProductCatalog(Menu menu) {
        this.menu = menu;

        setTitle("Product Catalog");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());
        this.setLocationRelativeTo(null); // center form in the screen.

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
        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAdminPage();
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel buttonPanel = new JPanel(); // Button panel
        buttonPanel.add(cartButton);

        if (Login.user != null && Login.user.isAdmin()) {
            buttonPanel.add(adminButton);
        }

        buttonPanel.add(exitButton);

        panel.add(buttonPanel, BorderLayout.SOUTH); // Add button panel to the SOUTH position

        add(panel, BorderLayout.CENTER);
    }

    private void openAdminPage() {
        // Redirect to the admin class or page
        Admin admin = new Admin();
        //admin.setVisible(true);
        setVisible(false);
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

    public static boolean checkAdminStatus(String email) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(USERS_FILE));
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length >= 4 && parts[2].equals(email)) {
                    if (parts[3].equals("A")) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
