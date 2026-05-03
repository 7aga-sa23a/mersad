package src.model;

import java.util.Scanner;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileReader;

// this class is for login of doctors.
public class Login {
    // this is doctor's attributes
    String ID, password;

    // take input from user in the default constructor
    public Login() {
        Scanner sc = new Scanner(System.in);

        // take Doctor ID and save it in ID variable and all should be unique and
        // numbers
        System.out.println("Enter Your ID: ");
        this.ID = sc.nextLine();

        // read the json file and check if the ID exists in the file
        String fileName = "doctors.json";

        JsonArray doctorsArray;

        try (FileReader reader = new FileReader(fileName)) {
            doctorsArray = JsonParser.parseReader(reader).getAsJsonArray();
        } catch (Exception e) {
            System.out.println("File error or empty database");
            sc.close();
            return;
        }

        // if the ID exists ask for password and check if it matches with the password
        // in the file, if it matches print login successful, if it does not match print
        // incorrect password and contact IT in the next 24 hours or try again, if the
        // ID does not exist ask to enter ID again or type 'signup' to create a new
        // account.
        boolean found = false, sign_up = false;

        while (!found && !sign_up) {

            for (JsonElement element : doctorsArray) {

                String id = element.getAsJsonObject()
                        .get("ID")
                        .getAsString();

                if (id.equals(this.ID)) {
                    found = true;
                    break;
                }
            }
            if (this.ID.equals("signup")) {
                @SuppressWarnings("unused")
                Signup doctor1 = new Signup();
                sign_up = true;
                break;
            }

            if (!found) {
                System.out.println("ID not found. Try again or type 'signup' to create a new account: ");
                this.ID = sc.nextLine();
            }
        }

        // if the ID exists ask for password and check if it matches with the password
        // in the file, if it matches print login successful, if it does not match print
        // incorrect password and contact IT in the next 24 hours or try again.
        if (!sign_up) {

            // take Doctor password and save it in password variable
            System.out.println("Enter Your password: ");
            this.password = sc.nextLine();
            sc.close();
            boolean passwordMatch = false;
            for (JsonElement element : doctorsArray) {

                String id = element.getAsJsonObject()
                        .get("ID")
                        .getAsString();

                if (id.equals(this.ID)) {
                    String storedPassword = element.getAsJsonObject()
                            .get("password")
                            .getAsString();

                    if (storedPassword.equals(this.password)) {
                        passwordMatch = true;
                        break;
                    }
                }
            }
            if (passwordMatch) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Incorrect password. contact IT in the next 24 hours or try again.");
            }
        }
    }
}