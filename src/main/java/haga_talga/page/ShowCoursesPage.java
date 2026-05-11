package haga_talga.page;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import haga_talga.model.Course;

public final class ShowCoursesPage extends Page {
    /**
     * Show students in course, and their attendance status
     */
    public ShowCoursesPage() {

    }

    @Override
    public String display() {
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
