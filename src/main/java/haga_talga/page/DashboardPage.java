package haga_talga.page;

import haga_talga.app.Main;
import haga_talga.util.Formatter;


public final class DashboardPage extends Page {
    public DashboardPage() {

    }

    @Override
    public String display() {
        // Display dashboard options
        Formatter.box("Welcome to the dashboard!\nWhat do you wish to do today?\n-----------------------------\n1. Add a course\n2. Display my courses\n3. Take today's attendance\n4. View attendance for a course\n5. Delete a course\n6. Exit program", "cyan", "single", "center", 140);
        Formatter.prompt("Enter your choice: ", "blue");

        // Keep prompting the user to choose an option, until they make a valid choice
        while (true) {
            String option = Main.scanner.nextLine().strip().toLowerCase();
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
            else if (option.startsWith("4") || option.contains("view")) {
                return "ShowCourseAttendancePage";
            }
            // Option 5: Delete a course
            else if (option.startsWith("5") || option.contains("delete")) {
                return "DeleteCoursePage";
            }
    
            // Option 7: Exit program
            else if (option.startsWith("6") || option.contains("exit")) {
                return "exit";
            }
            // Invalid option
            else {
                Formatter.error("Invalid option.");
                Formatter.error("Please enter the option number or name.\n");
            }

            // The loop will continue until the user makes a valid choice
        }
    }
}
