import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProductCatalog extends JFrame {
    private Menu menu;
    private Cart cart;

    public ProductCatalog(Menu menu) {
        this.menu = menu;

        setTitle("Product Catalog");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new BorderLayout());

        String directoryPath = "products/";
        displayProducts(directoryPath, panel);

        JButton cartButton = new JButton("View Cart");
        cartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCart();
            }
        });
        panel.add(cartButton, BorderLayout.SOUTH);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panel.add(exitButton, BorderLayout.NORTH);

        add(panel, BorderLayout.CENTER);
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
