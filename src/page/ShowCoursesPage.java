package src.page;

public final class ShowCoursesPage extends Page {
    /**
     * Show courses
     * - Displays a list of courses added
     * - - Number of registered students
     * - - Other course details…
     * - Edit course
     * - Delete course
     * - Option to view course attendance on a specific day
     * - Option to view student attendance percentage
     * - - 35% ≥ Red (Danger)
     * - - 35% - 70% Yellow (Normal)
     * - - 70% ≤ Green (Safe)
     */
    public ShowCoursesPage() {
        
    }

    public String display() {
        // Dispaly the page details
        System.out.println("This is the show courses page.");

        // ...

        // Return the next page to navigate to
        // In this case, there is no next page to navigate to
        return null;
    }
}
