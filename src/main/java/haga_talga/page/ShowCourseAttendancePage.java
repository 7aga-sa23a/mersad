package haga_talga.page;

import java.io.FileReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import haga_talga.app.Main;
import haga_talga.model.Course;
import haga_talga.util.Formatter;

public final class ShowCourseAttendancePage extends Page {

    /**
     * Show students in course, and their attendance status
     */
    public ShowCourseAttendancePage() {

    }

    @Override
    public String display() {
        Formatter.header("SHOW ATTENDANCE", "cyan", "single", "center", 140);

        while (true) {
            // ask doctor to enter course id to show its student attendance
            Formatter.prompt("Enter course ID to show its attendance or enter 'back' to return to the dashboard: ", "blue");
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
                Formatter.error("An error has occured while fetching course information.");
                return "ShowCourseAttendancePage";
            }

            // If no course was found with this ID
            if (courseName.equals("")) {
                Formatter.error("Course with this ID wasn't found.");
                return "ShowCourseAttendancePage";
            }

            // Get course attendance
            String courseFile = "src/main/resources/CourseStudent/" + courseName + " student.json";
            try (FileReader reader = new FileReader(courseFile)) {
                JsonObject courseObject = JsonParser.parseReader(reader).getAsJsonObject();
                JsonArray students = courseObject.getAsJsonArray("Students");

                int index = 1, n = students.size();
                String[] indices = new String[n], names = new String[n], ids = new String[n], attendance = new String[n];
                for (JsonElement studentElement : students) {
                    JsonObject student = studentElement.getAsJsonObject();

                    String name = student.get("name").getAsString();
                    int attendanceDays = student.get("attendedDays").getAsInt();

                    indices[index - 1] = String.valueOf(index);
                    names[index - 1] = name;
                    ids[index - 1] = student.get("id").getAsString();
                    attendance[index - 1] = String.valueOf(attendanceDays);
                    String studentInfo = index + ". Student " + name + " with ID " + student.get("id").getAsString() + " attended " + attendanceDays + " day(s)";

                    index++;
                }

                String[][] rows = new String[n][4];

                for (int i = 0; i < n; i++) {
                    rows[i][0] = indices[i];
                    rows[i][1] = names[i];
                    rows[i][2] = ids[i];
                    rows[i][3] = attendance[i];
                }
                Formatter.table(new String[]{"Index", "Name", "ID", "Attendance"}, rows, "cyan");
            } catch (Exception e) {
                Formatter.error("An error has occured while fetching course attendance.\nAre you sure you have taken attendance for this course before?");
                return "ShowCourseAttendancePage";
            }
        }
    }
}
