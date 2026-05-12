package haga_talga.page;

import haga_talga.app.Main;
import haga_talga.model.Doctor;
import haga_talga.util.Formatter;

// this class is for login of doctors.
public class LoginPage extends Page {
    // this is doctor's attributes
    String ID, password;

    // take input from user in the default constructor
    public LoginPage() {
    }

    public String display() {
        Formatter.header("LOGIN", "cyan", "single", "center", 140);

        // take Doctor ID and save it in ID variable and all should be unique and
        // numbers
        Formatter.prompt("Enter Your ID: ", "blue");
        String ID = Main.scanner.nextLine();

        // take Doctor password and save it in password variable and all should be
        Formatter.prompt("Enter Your Password: ", "blue");
        String password = Main.scanner.nextLine();

        // create doctor object and save it in json file
        Doctor doctor = new Doctor();
        int login = doctor.login(ID, password);

        while (login <= 0) {

            if (login == 0) {
                Formatter.error("Invalid ID. Please try again.");
            } else if (login == -1) {
                Formatter.error("Invalid password. Please try again.");
            } else if (login == -2) {
                Formatter.error("Data file not found. Please contact support.");
                break;
            } else {
                Formatter.error("Error reading data file. Your account is not found.");
                break;
            }

            Formatter.prompt("Enter Your ID: ", "blue");
            ID = Main.scanner.nextLine();
            Formatter.prompt("Enter Your Password: ", "blue");
            password = Main.scanner.nextLine();
            login = doctor.login(ID, password);
        }
        if (login == 1) {
            Formatter.success("Login successful!");
            return "DashboardPage";
        } else if (login == 2) {
            // sign up page
            return "SignupPage";
        } else {
            return "OnboardingPage";
        }   

    }
}
