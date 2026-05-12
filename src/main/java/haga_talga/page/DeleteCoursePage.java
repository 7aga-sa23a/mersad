package haga_talga.page;

import haga_talga.app.Main;
import haga_talga.model.Course;
import haga_talga.util.Formatter;

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
        Formatter.header("DELETE COURSE", "cyan", "single", "center", 140);

        // Prompt for course code
        Formatter.prompt("Course code: ", "blue");
        String courseCode = Main.scanner.nextLine().strip();

        System.out.println();

        // Confirm deletion
        Formatter.warning("Are you sure you want to delete the course with code " + courseCode + "? (yes/no)");
        String confirmation = Main.scanner.nextLine().strip().toLowerCase();

        if (!confirmation.equals("yes") && !confirmation.equals("y")) {
            Formatter.info("Course deletion cancelled.");
            return "DashboardPage";
        } else {
            // Try to delete the course
            try {
                Formatter.typewriter("Deleting course...", 120, "blue");
                Thread.sleep(1000);
                Course.deleteCourse(courseCode);
                Formatter.success("Course has been deleted successfully!");
                Thread.sleep(2000);
            } catch (Exception e) {
                Formatter.error("An error has occured while deleting the course.\nAre you sure the course code is correct?");
            } finally {
                System.out.println();
            }
            return "DashboardPage";
        }
    }
}
