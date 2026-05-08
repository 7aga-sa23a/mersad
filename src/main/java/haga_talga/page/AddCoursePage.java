package haga_talga.page;

import java.util.Scanner;

import haga_talga.model.Course;

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
    public String display() {
        // Input scanner
        Scanner scanner = new Scanner(System.in);

        // Prompt for course details
        System.out.print("Course name: ");
        String courseName = scanner.nextLine().strip();

        System.out.print("Course code: ");
        String courseCode = scanner.nextLine().strip();

        int courseYear;
        int courseSemester;

        while (true) {
            try {
                System.out.print("Course year: ");
                courseYear = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Invalid input for course year. Please enter a valid integer.");
                scanner.nextLine(); // Clear the invalid input
                continue; // Prompt again
            }
        }
        while(true) {
            try {
                System.out.print("Course semester: ");
                courseSemester = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Invalid input for course semester. Please enter a valid integer.");
                scanner.nextLine(); // Clear the invalid input
                continue; // Prompt again
            }
        }

        System.out.println();

        // Try to add the course
        try {
            System.out.println("Adding course...");
            Thread.sleep(1000);
            Course.addCourse(courseCode, courseName, courseYear, courseSemester);
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("An error has occured while adding the course.");
            System.err.println(e.getMessage());
        } finally {
            System.out.println();
        }

        return "DashboardPage";
    }
}
