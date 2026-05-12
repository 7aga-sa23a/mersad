package haga_talga.page;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import haga_talga.app.Main;
import haga_talga.model.Course;
import haga_talga.util.Formatter;

public final class ShowCoursesPage extends Page {

    /**
     * Show students in course, and their attendance status
     */
    public ShowCoursesPage() {

    }

    @Override
    public String display() {
        Formatter.header("SHOW COURSES", "cyan", "single", "center", 140);

        try {
            Formatter.typewriter("Showing courses details...", 100, "blue");
            System.out.println();
            Thread.sleep(1000);

            List<Course> courses = Course.loadCourses();

            if (courses.isEmpty()) {
                Formatter.warning("No courses found.");
                return "DashboardPage";
            }

            Formatter.typewriter("Showing details for " + courses.size() + " courses...", 50, "blue");
            System.out.println();
            Formatter.prompt("-----------------------------", "blue");
            Formatter.prompt("-----------------------------", "blue");
            System.out.println();
            Thread.sleep(1000);

            String[] headers = new String[]{"Name", "Code", "Year", "Semester", "Registered students"};
            String[][] rows = new String[courses.size()][5];

            int index = 1;
            for (Course course : courses) {
                rows[index - 1][0] = course.getName();
                rows[index - 1][1] = course.getID();
                rows[index - 1][2] = String.valueOf(course.getYear());
                rows[index - 1][3] = String.valueOf(course.getSemester());
                rows[index - 1][4] = String.valueOf(course.Students.size());

                index++;
            }

            Formatter.table(headers, rows, "cyan");

            Formatter.info("Press Enter to return to Dashboard...");
            Main.scanner.nextLine();
        } catch (Exception e) {
            Formatter.error("An error has occured while showing the course details.");
        } finally {
            System.out.println();
        }
        return "DashboardPage";
    }
}
