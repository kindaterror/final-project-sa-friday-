import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Cart extends JFrame {
    private ProductCatalog productCatalog;
    private List<Product> cartItems;
    private JPanel cartPanel;
    private JButton backButton;
    private JButton checkoutButton;
    private checkout checkoutFrame;

    public Cart(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
        this.cartItems = new ArrayList<>();
          this.checkoutFrame = new checkout(this); 

        setTitle("Cart");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());

        // Create a panel to display the cart items
        cartPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        cartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Scroll pane for the cart panel
        JScrollPane scrollPane = new JScrollPane(cartPanel);

        // Create the checkout button
        checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkout();
            }
        });

        // Create the back button
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showProductCatalog();
                dispose();
            }
        });

        // Panel to hold the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(checkoutButton);
        buttonPanel.add(backButton);

        // Add components to the frame
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void addProduct(Product product) {
        cartItems.add(product);

        JLabel nameLabel = new JLabel(product.getName());
        nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD, 14));

        JLabel priceLabel = new JLabel("$" + product.getPrice());
        priceLabel.setFont(priceLabel.getFont().deriveFont(Font.PLAIN, 14));

        cartPanel.add(nameLabel);
        cartPanel.add(priceLabel);

        cartPanel.revalidate();
        cartPanel.repaint();
    }

    public String getProductsAsString() {
        StringBuilder sb = new StringBuilder();
        for (Product product : cartItems) {
            sb.append(product.getName()).append("\n");
        }
        return sb.toString();
    }

    private void checkout() {
         checkoutFrame.setVisible(true);
        this.setVisible(false);
    }

    private void showProductCatalog() {
        if (productCatalog != null) {
            productCatalog.showProductCatalog();
        }
    }
}
