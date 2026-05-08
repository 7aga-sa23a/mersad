package haga_talga.page;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

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
        // Dispaly the page details
        System.out.println("This is the show courses page.");
        System.out.println();

        try {

            System.out.println("Showing courses details...");
            System.out.println();
            Thread.sleep(1000);

            ObjectMapper mapper = new ObjectMapper();
            File file = new File("src/main/resources/courses.json");
            List<Course> courses = Course.loadCourses(mapper, file);

            System.out.println("Showing details for " + courses.size() + " courses....");
            System.out.println();
            System.out.println("-----------------------------");
            System.out.println("-----------------------------");
            System.out.println();
            Thread.sleep(1000);

            for (Course course : courses) {
                System.out.println("Showing details for course " + course.getName() );
                System.out.println("Course name: " + course.getName());
                System.out.println("Course code: " + course.getID());
                System.out.println("Course year: " + course.getYear());
                System.out.println("Course semester: " + course.getSemester());
                System.out.println("Number of registered students: " + course.Students.size());
                System.out.println();
                System.out.println("-----------------------------");
                System.out.println();
            }

            Thread.sleep(2000);

        } catch (Exception e) {
            System.out.println("An error has occured while showing the course details.");
            System.err.println(e.getMessage());
        } finally {
            System.out.println();
        }
        return "DashboardPage";
    }
}
