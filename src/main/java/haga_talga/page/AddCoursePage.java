package haga_talga.page;

import haga_talga.app.Main;
import haga_talga.model.Course;

import haga_talga.util.Formatter;

public final class AddCoursePage extends Page {
    /**
     * Add course
     * - name
     * - Year
     * - Semester
     * - Code
     */
    public AddCoursePage() {

    }
    @Override
    public void showHeader() {
        System.out.println("*********************************");
        System.out.println("*     ADD NEW COURSE MENU       *");
        System.out.println("*********************************");
    }

    @Override
    public String display() {
        Formatter.header("ADD COURSE", "cyan", "single", "center", 140);

        // Input scanner
        // Prompt for course details
        Formatter.prompt("Course Name: ", "blue");
        String courseName = Main.scanner.nextLine().strip();

        Formatter.prompt("Course code: ", "blue");
        String courseCode = Main.scanner.nextLine().strip();

        int courseYear;
        int courseSemester;

        while (true) {
            try {
                Formatter.prompt("Course year: ", "blue");
                courseYear = Main.scanner.nextInt();
                break;
            } catch (Exception e) {
                Formatter.error("Invalid input for course year. Please enter a valid integer.");
                Main.scanner.nextLine(); // Clear the invalid input
            }
        }
        while(true) {
            try {
                Formatter.prompt("Course semester: ","blue");
                courseSemester = Main.scanner.nextInt();
                break;
            } catch (Exception e) {
                Formatter.error("Invalid input for course semester. Please enter a valid integer.");
                Main.scanner.nextLine(); // Clear the invalid input
            }
        }

        System.out.println();

        // Try to add the course
        try {
            Formatter.typewriter("Adding course...", 120, "blue");
            Thread.sleep(1000);
            Course.addCourse(courseCode, courseName, courseYear, courseSemester);
            Thread.sleep(2000);
        } catch (Exception e) {
            Formatter.error("An error has occured while adding the course.");
        } finally {
            System.out.println();
        }

        Main.scanner.nextLine(); // Clear the newline character from the input buffer
        return "DashboardPage";
    }
}
