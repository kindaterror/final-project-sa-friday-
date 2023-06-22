public class Product {
    private String name;
    private String category;
    private String ram;

    public Product(String name) {
        this.name = name;
    }

    public Product(String name, String category, String ram) {
        this.name = name;
        this.category = category;
        this.ram = ram;
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
}
