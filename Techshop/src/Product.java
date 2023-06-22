import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Product {
    private String name;
    private String category;
    private String ram;
    private double price;

    public Product(String name) {
        this.name = name;
    }

    public Product(String name, String category, String ram) {
        this.name = name;
        this.category = category;
        this.ram = ram;
        this.price = readPriceFromFile(name);
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getRam() {
        return ram;
    }

    public double getPrice() {
        return price;
    }

    private double readPriceFromFile(String productName) {
        try (BufferedReader reader = new BufferedReader(new FileReader( productName + ".txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].trim().equalsIgnoreCase(productName.trim())) {
                    return Double.parseDouble(parts[1].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0.0; // Default price if not found or error occurred
    }
}
