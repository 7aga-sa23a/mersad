package haga_talga.page;

import java.io.FileReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import haga_talga.app.Main;
import haga_talga.model.Course;

public final class ShowCourseAttendancePage extends Page {

    /**
     * Show students in course, and their attendance status
     */
    public ShowCourseAttendancePage() {

    }

    @Override
    public String display() {
        while (true) {
            // ask doctor to enter course id to show its student attendance
            System.out.print("Enter course ID to show its attendance or enter 'back' to return to the dashboard: ");
            String courseId = Main.scanner.nextLine();
    
            // If user types back (to return to dashboard)
            if (courseId.equalsIgnoreCase("back")) {
                return "DashboardPage";
            }
    
            // Get course info
            String courseName = "";
            try {
                var coursesList = Course.loadCourses();
    
                for (var course : coursesList) {
                    if (course.getID().equals(courseId)) {
                        courseName = course.getName();
                    }
                }
            } catch (Exception e) {
                System.out.println("An error has occured while fetching course information.");
                System.out.println(e.getMessage());
                return "ShowCourseAttendancePage";
            }
    
            // If no course was found with this ID
            if (courseName.equals("")) {
                System.out.println("Course with this ID wasn't found.");
                return "ShowCourseAttendancePage";
            }
    
            // Get course attendance
            String courseFile = "src/main/resources/CourseStudent/" + courseName + " student.json";
            try (FileReader reader = new FileReader(courseFile)) {
                JsonObject courseObject = JsonParser.parseReader(reader).getAsJsonObject();
                JsonArray students = courseObject.getAsJsonArray("Students");
    
                int index = 1;
                for (JsonElement studentElement : students) {
                    JsonObject student = studentElement.getAsJsonObject();
    
                    String name = student.get("name").getAsString();
                    int attendanceDays = student.get("attendedDays").getAsInt();
    
                    System.out.println(index + ". Student " + name + " with ID " + student.get("id").getAsString() + " attended " + attendanceDays + " day(s)");

                    index++;
                }
            } catch (Exception e) {
                System.out.println("An error has occured while fetching course attendance.");
                System.out.println(e.getMessage());
                return "ShowCourseAttendancePage";
            }
        }
    }
}
