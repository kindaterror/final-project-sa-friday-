import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ProductDetails extends JFrame {
    private ProductCatalog productCatalog;
    private JButton backButton;
    private JButton addToCartButton;

    public ProductDetails(ProductCatalog productCatalog, String productName, String category, String ram) {
        this.productCatalog = productCatalog;

        setTitle(productName + " Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());

        JLabel nameLabel = new JLabel("Product Name: " + productName);
        JLabel categoryLabel = new JLabel("Category: " + category);
        JLabel ramLabel = new JLabel("RAM: " + ram);

        JPanel detailsPanel = new JPanel(new GridLayout(4, 1));
        detailsPanel.add(nameLabel);
        detailsPanel.add(categoryLabel);
        detailsPanel.add(ramLabel);

        addToCartButton = new JButton("Add to Cart");
        addToCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addToCart(new Product(productName, category, ram));
            }
        });

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showProductCatalog();
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addToCartButton);
        buttonPanel.add(backButton);

        add(detailsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addToCart(Product product) {
        if (productCatalog != null) {
            productCatalog.addToCart(product);
            JOptionPane.showMessageDialog(ProductDetails.this, "Product added to cart.", "Add to Cart",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showProductCatalog() {
        if (productCatalog != null) {
            productCatalog.showProductCatalog();
        }
    }
}
