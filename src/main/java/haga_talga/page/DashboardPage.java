package haga_talga.page;

import java.util.Scanner;

public final class DashboardPage extends Page {
    public DashboardPage() {

    }

    @Override
    public String display() {
        // Display dashboard options
        System.out.println("Welcome to the dashboard!");
        System.out.println("What do you wish to do today?");
        System.out.println("-----------------------------");
        System.out.println("1. Add a course");
        System.out.println("2. Display my courses");
        System.out.println("3. Take today's attendance");
        System.out.println("4. Edit attendance for a day");
        System.out.println("5. Exit program");
        System.out.println();
        System.out.println("Enter your choice: ");

        // Keep prompting the user to choose an option, until they make a valid choice
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine().strip().toLowerCase();
            System.out.println();

            // Validate the user's choice
            // return the next page to navigate to as a String

            // Option 1: Add a course
            if (option.startsWith("1") || option.contains("add")) {
                return "AddCoursePage";
            }
            // Option 2: Display my courses
            else if (option.startsWith("2") || option.contains("display") || option.contains("my")) {
                return "ShowCoursesPage";
            }
            // Option 3: Take today's attendance
            else if (option.startsWith("3") || option.contains("take") || option.contains("today")) {
                return "TakeAttendancePage";
            }
            // Option 4: Edit attendance for a day
            else if (option.startsWith("4") || option.contains("edit")) {
                return "EditAttendancePage";
            }
            // Option 5: Exit program
            else if (option.startsWith("5") || option.contains("exit")) {
                System.out.println("Exiting the program...\n");
                System.exit(0);
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
