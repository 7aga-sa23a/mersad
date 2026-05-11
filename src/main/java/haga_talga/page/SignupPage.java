package haga_talga.page;

import haga_talga.app.Main;
import haga_talga.model.Doctor;


// this class is for signup of doctors.
public class SignupPage extends Page {

    // this is doctor's attributes
    String username, password, repeatedPassword, ID;

    // take input from user in the default constructor
    public SignupPage() {
    }

    public String display() {
        // take Doctor name and save it in username variable
        System.out.println("Enter username: ");
        String username = Main.scanner.nextLine();

        // take Doctor ID and save it in ID variable and all should be unique and
        // numbers
        System.out.println("Enter ID: ");
        String ID = Main.scanner.nextLine();

        // take Doctor password and save it in password variable, and ask to repeat the
        // password until it matches with the first one.
        System.out.println("Enter password: ");
        String password = Main.scanner.nextLine();

        while (true) {
            System.out.println("Enter password again: ");
            String repeatedPassword = Main.scanner.nextLine();

            // if it matches save it in json file
            if (password.equals(repeatedPassword)) {
                Doctor doctor = new Doctor();
                int login = doctor.signup(username, ID, password);

                if (login == 2) {
                    // login page
                    return "LoginPage";
                }
                System.out.println("account created successfully");
                return "DashboardPage";
            }
            // if it does not match ask to enter password again
            else {
                System.out.println("Passwords do not match. Please enter password again: ");
            }
        }
    }

}