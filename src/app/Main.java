package src.app;

import src.page.Page;
import src.page.OnboardingPage;
import src.page.LoginPage;
import src.page.SignupPage;
import src.page.DashboardPage;
import src.page.AddCoursePage;
import src.page.ShowCoursesPage;
import src.page.TakeAttendancePage;
import src.page.EditAttendancePage;

import java.util.Map;
import java.util.HashMap;

// Run the program using the following command: java Main.java
// Or use this shortcut in VS Code: Ctrl + F5

/**
 * This program is a Java OOP Attendance System using QR code
 * simulation to manage students, materials, and attendance tracking.
 * 
 * @author 7aga-sa23a
 * @since 2026-04-24
 * @version v0.3.0
 */
public class Main {
    /**
     * This variable maps page names to page objects so they can be easily called by
     * just their name. It also calls page constructors so that pages are ready to
     * use.
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
                    "EditAttendancePage", new EditAttendancePage()));

    public Main() {}

    /**
     * The main method that handles the program flow.
     * 
     * @param args
     */
    public static void main(String[] args) {
        // Starting page
        final OnboardingPage onboardingPage = (OnboardingPage) pageMap.get("OnboardingPage");
        String nextPageName = onboardingPage.display();

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
                    Page nextPage = (Page) nextPageObject;
                    nextPageName = nextPage.display();
                } else {
                    // If the object is not a page, then it is not a valid page
                    System.out.println("Invalid page: " + nextPageName);
                    System.out.println("Exiting program...");
                    System.exit(1);
                }
            } else {
                // If the page to navigate to does not exist in the page map, then it is not a
                // valid page
                System.out.println("Invalid page: " + nextPageName);
                System.out.println("Exiting program...");
                System.exit(2);
            }
        }
    }
}