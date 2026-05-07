package haga_talga.page;

import java.util.Scanner;

import haga_talga.model.Course;

public final class ShowCoursesPage extends Page {
    /**
     * Show courses
     * - Displays a list of courses added
     * - - Number of registered students
     * - - Other course details…
     * - Edit course
     * - Delete course
     * - Option to view course attendance on a specific day
     * - Option to view student attendance percentage
     * - - 35% ≥ Red (Danger)
     * - - 35% - 70% Yellow (Normal)
     * - - 70% ≤ Green (Safe)
     */
    public ShowCoursesPage() {

    }

    @Override
    public String display() {
        // Prepare the scanner
        Scanner scanner = new Scanner(System.in);
        String input;

        // Prompt the user for the desired course's information
        System.out.println("Course Code: ");
        String courseCode = scanner.nextLine().strip();

        System.out.print("New course name: ");
        String courseName = scanner.nextLine().strip();

        int courseYear;
        do {
            System.out.print("New course year: ");
            if (scanner.hasNextInt()) {
                courseYear = scanner.nextInt();
                break;
            } else {
                scanner.next();
                System.out.println("Please enter a valid year.");
            }
        } while (true);

        int courseSemester;
        do {
            System.out.print("New course semester: ");
            if (scanner.hasNextInt()) {
                courseSemester = scanner.nextInt();
                break;
            } else {
                scanner.next();
                System.out.println("Please enter a valid semester.");
            }
        } while (true);

        System.out.println();

        // Try to edit the course
        try {
            System.out.println("Editing course...");
            Thread.sleep(1000);
            Course.editCourse(courseCode, courseName, courseYear, courseSemester);
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("An error has occured while editing the course.");
            System.err.println(e.getMessage());
        } finally {
            System.out.println();
        }

        return "DashboardPage";
    }
}
