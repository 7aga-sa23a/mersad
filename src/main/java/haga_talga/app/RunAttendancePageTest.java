package haga_talga.app;

import haga_talga.page.TakeAttendancePage;

/**
 * A dedicated test file just to run the TakeAttendancePage directly
 * without going through the rest of the Main app flow.
 */
public class RunAttendancePageTest {
    public static void main(String[] args) {
        System.out.println("Starting TakeAttendancePage Test...");
        System.out.println("========================================\n");
        
        // 1. Initialize the page
        TakeAttendancePage page = new TakeAttendancePage();
        
        // 2. Call the display method (this will trigger course selection, camera, etc.)
        String nextPage = page.display();
        
        // 3. Print the result after the page finishes its work
        System.out.println("\n========================================");
        System.out.println("Test Finished!");
        System.out.println("The page successfully returned. Next page to navigate to: " + nextPage);

    }
}
