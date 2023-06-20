import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class UserRegistrationTxtfile {
    public static void createFile(String username, String email, String password) {
        String filePath = "C:/Users/Quirf Ivan A. Onag/Desktop/homework/Loginfiles/new register/"+ username +".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Username: " + username);
            writer.newLine();
            writer.write("Password: " + password);
            writer.newLine();
            writer.write("Email: " + email);
            writer.newLine();
            writer.flush();
            System.out.println("User registration file created successfully.");
        } catch (IOException e) {
            System.out.println("Error creating user registration file: " + e.getMessage());
        }
    }
}
