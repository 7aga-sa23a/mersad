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
        String courseName = scanner.nextLine();

        System.out.print("Course code: ");
        String courseCode = scanner.nextLine();

        System.out.print("Course year: ");
        int courseYear = scanner.nextInt();

        System.out.print("Course semester: ");
        int courseSemester = scanner.nextInt();

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
