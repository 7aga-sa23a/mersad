package haga_talga.page;

import haga_talga.model.Doctor;
import java.util.Scanner;

// this class is for login of doctors.
public class LoginPage extends Page {
    // this is doctor's attributes
    String ID, password;

    // take input from user in the default constructor
    public LoginPage() {
    }

    public String display() {
        Scanner sc = new Scanner(System.in);

        // take Doctor ID and save it in ID variable and all should be unique and
        // numbers
        System.out.println("Enter Your ID: ");
        String ID = sc.nextLine();

        // take Doctor password and save it in password variable and all should be
        System.out.println("Enter Your Password: ");
        String password = sc.nextLine();

        // create doctor object and save it in json file
        Doctor doctor = new Doctor();
        int login = doctor.login(ID, password);

        while (login <= 0) {

            if (login == 0) {
                System.out.println("Invalid ID. Please try again.");
            } else if (login == -1) {
                System.out.println("Invalid password. Please try again.");
            } else if (login == -2) {
                System.out.println("Data file not found. Please contact support.");
                break;
            } else {
                System.out.println("Error reading data file. Your account is not found.");
                break;
            }

            System.out.println("Enter Your ID: ");
            ID = sc.nextLine();
            System.out.println("Enter Your Password: ");
            password = sc.nextLine();
            login = doctor.login(ID, password);
        }
        if (login == 1) {
            System.out.println("Login successful!");
            return "DashboardPage";
        } else if (login == 2) {
            // sign up page
            return "SignupPage";
        } else {
            return "OnboardingPage";
        }   

    }
}
