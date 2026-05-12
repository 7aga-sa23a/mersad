package haga_talga.page;

import haga_talga.app.Main;
import haga_talga.model.Doctor;
import haga_talga.util.Formatter;


// this class is for signup of doctors.
public class SignupPage extends Page {

    // this is doctor's attributes
    String username, password, repeatedPassword, ID;

    // take input from user in the default constructor
    public SignupPage() {
    }

    public String display() {
        Formatter.header("SIGNUP", "cyan", "single", "center", 140);

        // take Doctor name and save it in username variable
        Formatter.prompt("Enter username: ", "blue");
        String username = Main.scanner.nextLine();

        // take Doctor ID and save it in ID variable and all should be unique and
        // numbers
        Formatter.prompt("Enter ID: ", "blue");
        String ID = Main.scanner.nextLine();

        // take Doctor password and save it in password variable, and ask to repeat the
        // password until it matches with the first one.
        Formatter.prompt("Enter password: ", "blue");
        String password = Main.scanner.nextLine();

        while (true) {
            Formatter.prompt("Enter password again: ", "blue");
            String repeatedPassword = Main.scanner.nextLine();

            // if it matches save it in json file
            if (password.equals(repeatedPassword)) {
                Doctor doctor = new Doctor();
                int login = doctor.signup(username, ID, password);

                if (login == 2) {
                    // login page
                    return "LoginPage";
                }
                Formatter.success("account created successfully");
                return "DashboardPage";
            }
            // if it does not match ask to enter password again
            else {
                Formatter.error("Passwords do not match. Please enter password again: ");
            }
        }
    }

}