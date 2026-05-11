package haga_talga.page;
import haga_talga.app.Main;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public final class ShowCourseAttendancePage extends Page {
    /**
     * Show students in course, and their attendance status
     */
    public ShowCourseAttendancePage() {
        
    }

    public String display() {
        // ask doctor to enter course id to show its student attendance
        System.out.print("Enter course ID to show its attendance or enter 'back' to return to the dashboard: ");
        String courseId = Main.scanner.nextLine();
        if (courseId.equalsIgnoreCase("back")) {
            DashboardPage dashboardPage = new DashboardPage();
            dashboardPage.display();
            return "DashboardPage";
        }
        else {
            String fileName = "src/main/resources/CourseStudent/Data Structures student.json";
            try (FileReader reader = new FileReader(fileName)) {

                JsonObject courseObject = JsonParser.parseReader(reader).getAsJsonObject();
                String code = courseObject.get("courseCode").getAsString();

                if (code.equals(courseId)) {
                   // System.out.println("Course Code: " + code);
                    JsonArray students = courseObject.getAsJsonArray("Students");
                    for (JsonElement studentElement : students) {
                        JsonObject student = studentElement.getAsJsonObject();
                        String name = student.get("name").getAsString();
                        int attendanceDays = student.get("attendedDays").getAsInt();
                        System.out.println(
                                "Student Name: "
                                        + name
                                        + ", Attendance Days: "
                                        + attendanceDays);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                return "ShowCourseAttendancePage";
            }
        }
        // Return the next page to navigate to
        System.out
                .println("Enter 'back' to return to the dashboard or enter another course ID to show its attendance: ");
        String nextAction = Main.scanner.nextLine();
        if (nextAction.equalsIgnoreCase("back")) {
            DashboardPage dashboardPage = new DashboardPage();
            dashboardPage.display();
            return "DashboardPage";
        } else {
            return "ShowCourseAttendancePage";
        }

        // ...

        // Return the next page to navigate to
        // In this case, there is no next page to navigate to
    
    }
    
}
