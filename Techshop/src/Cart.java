import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Cart extends JFrame {
    private ProductCatalog productCatalog;
    private List<Product> products;

    public Cart(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
        products = new ArrayList<>();

        setTitle("Cart");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new BorderLayout());

        JPanel productPanel = new JPanel(new GridLayout(0, 1, 0, 5));
        JScrollPane scrollPane = new JScrollPane(productPanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back to Catalog");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showProductCatalog();
            }
        });
        panel.add(backButton, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);
    }

    public void addProduct(Product product) {
        products.add(product);
        updateProductPanel();
    }

    private void updateProductPanel() {
        JPanel productPanel = new JPanel(new GridLayout(0, 1, 0, 5));

        for (Product product : products) {
            JPanel filePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            filePanel.add(new JLabel(product.getName()));

            productPanel.add(filePanel);
        }

        JScrollPane scrollPane = new JScrollPane(productPanel);
        getContentPane().removeAll();
        add(scrollPane, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void showProductCatalog() {
        if (productCatalog != null) {
            productCatalog.showProductCatalog();
        }
        dispose();
    }
}
