package haga_talga.page;

import java.util.ArrayList;

import haga_talga.app.Main;
import haga_talga.util.Formatter;

public final class OnboardingPage extends Page {
    /**
     * Onboarding page
     * - Info about the program
     * - - Clarify what the program does
     * - - Only for doctors
     * - Login option
     * - Sign up option
     * - Exit option
     */
    public OnboardingPage() {

    }

    @Override
    public String display() {
        Formatter.header("ONBOARDING", "cyan", "single", "center", 140);

        // Display onboarding information
        Formatter.box("Welcome to the Attendance System.\nManage your courses, students, and attendance records efficiently through a simple and secure interface.\n", "cyan", "single", "center", 140);
        Formatter.box("Please, choose an option:\n-------------------------\n1. Login\n2. Sign up\n3. Exit program", "cyan", "single", "center", 140);
        Formatter.prompt("Enter your choice: ", "blue");

        // Keep prompting the user to choose an option, until they make a valid choice
        while (true) {
            String option = Main.scanner.nextLine().strip().toLowerCase();
            System.out.println();

            // Validate the user's choice
            // return the next page to navigate to as a String

            // Option 1: Login
            if (option.startsWith("1") || option.contains("log")) {
                return "LoginPage";
            }
            // Option 2: Sign up
            else if (option.startsWith("2") || option.contains("sign")) {
                return "SignupPage";
            }
            // Option 3: Exit program
            else if (option.startsWith("3") || option.contains("exit")) {
                return "exit";
            }
            // Invalid option
            else {
                System.out.println("Invalid option.");
                System.out.println("Please enter the option number or name.\n");
            }

            // The loop will continue until the user makes a valid choice
        }
    }
}
