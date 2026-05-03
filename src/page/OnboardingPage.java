package src.page;

import java.util.Scanner;

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

    public String display() {
        // Display onboarding information
        System.out.println("Welcome to the Attendance System.\n" +
                "Manage your courses, students, and attendance records " +
                "efficiently through a simple and secure interface.\n");

        // Display onboarding options
        System.out.println("Good day doctor!");
        System.out.println("Please, choose an option:");
        System.out.println("-------------------------");
        System.out.println("1. Login");
        System.out.println("2. Sign up");
        System.out.println("3. Exit program");
        System.out.println();
        System.out.println("Enter your choice: ");

        // Keep prompting the user to choose an option, until they make a valid choice
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine().strip().toLowerCase();

            // Validate the user's choice
            // return class::method is like returning a pointer to the method

            // Option 1: Login
            if (option.startsWith("1") || option.contains("log")) {
                scanner.close();
                return "LoginPage";
            }
            // Option 2: Sign up
            else if (option.startsWith("2") || option.contains("sign")) {
                scanner.close();
                return "SignupPage";
            }
            // Option 3: Exit program
            else if (option.startsWith("3") || option.contains("exit")) {
                scanner.close();
                System.out.println("\nExiting the program...!\n");
                System.exit(0);
            }
            // Invalid option
            else {
                System.out.println("\nInvalid option.");
                System.out.println("Please enter the option number or name.\n");
            }

            // The loop will continue until the user makes a valid choice
        }
    }
}
