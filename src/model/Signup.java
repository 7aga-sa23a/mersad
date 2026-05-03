package src.model;

import java.util.ArrayList;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// this class is for signup of doctors.
public class Signup {

    // this is doctor's attributes
    String username, password, repeatedPasswordc, ID;
    ArrayList<String> courses = new ArrayList<String>();

    // take input from user in the default constructor
    public Signup() {
        Scanner sc = new Scanner(System.in);

        String fileName = "data/doctors.json";
        JsonArray doctorsArray;

        try (FileReader reader = new FileReader(fileName)) {
            doctorsArray = JsonParser.parseReader(reader).getAsJsonArray();
        } catch (Exception e) {
            doctorsArray = new JsonArray();
        }

        // take Doctor name and save it in username variable
        System.out.println("Enter username: ");
        this.username = sc.nextLine();

        // take Doctor ID and save it in ID variable and all should be unique and
        // numbers
        System.out.println("Enter ID: ");
        while (true) {

            this.ID = sc.nextLine();
            boolean uniqueID = true;
            for (JsonElement element : doctorsArray) {
                String id = element.getAsJsonObject()
                        .get("ID")
                        .getAsString();

                if (id.equals(this.ID)) {
                    uniqueID = false;
                    break;
                }
            }
            if (!uniqueID) {
                System.out.println("ID already exists. Please enter a unique ID: ");
                continue;
            }
            if (this.ID.matches("[0-9]+")) {
                break;
            } else {
                System.out.println("ID should be numbers only. Please enter ID again: ");
            }
        }

        // take Doctor password and save it in password variable, and ask to repeat the
        // password until it matches with the first one.
        System.out.println("Enter password: ");
        this.password = sc.nextLine();
        while (true) {
            System.out.println("Enter password again: ");
            this.repeatedPasswordc = sc.nextLine();

            // if it matches save it in json file
            if (this.password.equals(this.repeatedPasswordc)) {
                System.out.println("account created successfully");

                Gson gson = new GsonBuilder().setPrettyPrinting().create();

                try (FileReader reader = new FileReader(fileName)) {
                    JsonElement element = JsonParser.parseReader(reader);

                    if (element.isJsonArray()) {
                        doctorsArray = element.getAsJsonArray();
                    } else {
                        doctorsArray = new JsonArray();
                    }

                } catch (IOException e) {

                    doctorsArray = new JsonArray();
                }

                JsonElement doctorJson = gson.toJsonTree(this);
                doctorsArray.add(doctorJson);

                try (FileWriter writer = new FileWriter(fileName)) {
                    gson.toJson(doctorsArray, writer);
                } catch (IOException e) {
                    System.out.println("Error saving doctors data");
                    e.printStackTrace();
                }

                break;
            }

            // if it does not match ask to enter password again
            else {
                System.out.println("Passwords do not match. Please enter password again: ");
            }
        }

        sc.close();
    }

}