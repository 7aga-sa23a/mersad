package haga_talga.page;

import haga_talga.model.Doctor;
import java.util.Scanner;

// this class is for signup of doctors.
public class SignupPage extends Page {

    // this is doctor's attributes
    String username, password, repeatedPassword, ID;

    // take input from user in the default constructor
    public SignupPage() {
    }

    public String display() {
        Scanner sc = new Scanner(System.in);

        // take Doctor name and save it in username variable
        System.out.println("Enter username: ");
        this.username = sc.nextLine();

        // take Doctor ID and save it in ID variable and all should be unique and
        // numbers
        System.out.println("Enter ID: ");
        this.ID = sc.nextLine();

        // take Doctor password and save it in password variable, and ask to repeat the
        // password until it matches with the first one.
        System.out.println("Enter password: ");
        this.password = sc.nextLine();

        while (true) {
            System.out.println("Enter password again: ");
            this.repeatedPassword = sc.nextLine();

            // if it matches save it in json file
            if (this.password.equals(this.repeatedPassword)) {
                Doctor doctor = new Doctor();
                int login = doctor.signup(this.username, this.ID, this.password);

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