package haga_talga.page;

import java.util.Scanner;

import haga_talga.model.Course;

public final class DeleteCoursePage extends Page {
    public DeleteCoursePage() {

    }

    /*
     * - ask use to enter the name of the course to delete and the course code to
     * confirm
     * - try to delete the course, if the course is not found, display an error
     * message and return to the dashboard
     */
    public String display() {
        // Input scanner
        Scanner scanner = new Scanner(System.in);

        // Prompt for course details
        System.out.print("Course code: ");
        String courseCode = scanner.nextLine().strip();

        System.out.println();

        // Confirm deletion
        System.out.println(
                "Are you sure you want to delete the course with code " + courseCode + "? (yes/no)");
        String confirmation = scanner.nextLine().strip().toLowerCase();

        if (!confirmation.equals("yes") && !confirmation.equals("y")) {
            System.out.println("Course deletion cancelled.");
            return "DashboardPage";
        } else {
            // Try to delete the course
            try {
                System.out.println("Deleting course...");
                Thread.sleep(1000);
                Course.deleteCourse(courseCode);
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println("An error has occured while deleting the course.");
                System.err.println(e.getMessage());
            } finally {
                System.out.println();
            }
            return "DashboardPage";
        }
    }
}
