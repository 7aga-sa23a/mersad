package src.page;

public final class DashboardPage extends Page {
    /**
     * Dashboard
     * - Add course
     * - Show courses
     * - Take attendance for today
     * - Edit attendance for a day
     * - - Shows databases for that day
     * - Exit program
     */
    public DashboardPage() {
        
    }

    public String display() {
        // Dispaly the page details
        System.out.println("This is the dashboard page.");

        // ...

        // Return the next page to navigate to
        return "AddCoursePage";
    }
}
