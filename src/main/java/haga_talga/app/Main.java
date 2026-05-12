package haga_talga.app;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import haga_talga.page.AddCoursePage;
import haga_talga.page.DashboardPage;
import haga_talga.page.DeleteCoursePage;
import haga_talga.page.LoginPage;
import haga_talga.page.OnboardingPage;
import haga_talga.page.Page;
import haga_talga.page.ShowCourseAttendancePage;
import haga_talga.page.ShowCoursesPage;
import haga_talga.page.SignupPage;
import haga_talga.page.TakeAttendancePage;
import haga_talga.util.Formatter;

// Run the program using the following command: java Main.java
// Or use this shortcut in VS Code: Ctrl + F5
/**
 * This program is a Java OOP Attendance System using QR code simulation to
 * manage students, materials, and attendance tracking.
 *
 * @author 7aga-sa23a
 * @since 2026-04-24
 * @version v0.3.0
 */
public class Main {

    public static final Scanner scanner = new Scanner(System.in);

    /**
     * This variable maps page names to page objects so they can be easily
     * called by just their name. It also calls page constructors so that pages
     * are ready to use.
     */
    private static final Map<String, Object> pageMap = new HashMap<>(
            Map.of(
                    "OnboardingPage", new OnboardingPage(),
                    "LoginPage", new LoginPage(),
                    "SignupPage", new SignupPage(),
                    "DashboardPage", new DashboardPage(),
                    "AddCoursePage", new AddCoursePage(),
                    "ShowCoursesPage", new ShowCoursesPage(),
                    "TakeAttendancePage", new TakeAttendancePage(),
                    "ShowCourseAttendancePage", new ShowCourseAttendancePage(),
                    "DeleteCoursePage", new DeleteCoursePage()));

    public Main() {
    }

    /**
     * The main method that handles the program flow.
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        // Enforce UTF-8 encoding for ASCII art
        enforceUTF8();
        
        Formatter.clearScreen();

        // Starting page
        final OnboardingPage onboardingPage = (OnboardingPage) pageMap.get("OnboardingPage");
        String nextPageName = onboardingPage.display().strip();

        // While there is a page to navigate to
        while (nextPageName != null) {
            // If the page to navigate to exists in the page map
            if (pageMap.containsKey(nextPageName)) {
                // Get the page object
                Object nextPageObject = pageMap.get(nextPageName);

                // All page classes inherit from a super class called "Page" which has a
                // "display" method. The display method can not be used directly from the page
                // map since the compiler does not know for sure if this object is a page, for
                // this reason we check if the object is indeed a page.
                if (nextPageObject instanceof Page) {
                    Formatter.typewriter("Loading next Page...", 120, "blue");
                    Formatter.success("Loaded!");
                    Formatter.sleep(1000);
                    Formatter.clearScreen();
                    
                    Page nextPage = (Page) nextPageObject;
                    nextPageName = nextPage.display();
                } else {
                    // If the object is not a page, then it is not a valid page
                    Formatter.error("Invalid page: " + nextPageName);
                    Formatter.prompt("Exiting program...", "blue");
                    System.exit(1);
                }
            } // If the page to navigate to is "exit", then exit the program
            else if (nextPageName.toLowerCase().contains("exit")) {
                Formatter.prompt("Exiting program...", "blue");
                scanner.close();
                System.exit(0);
            } // If the page to navigate to does not exist in the page map, then it is not valid
            else {
                Formatter.error("Invalid page: " + nextPageName);
                Formatter.prompt("Exiting program...", "blue");
                scanner.close();
                System.exit(2);
            }
        }
    }

    private static void enforceUTF8() throws Exception {
        if (System.getProperty("os.name", "").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "chcp 65001").inheritIO().start().waitFor();
        }
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        System.setErr(new java.io.PrintStream(System.err, true, "UTF-8"));
    }
}
