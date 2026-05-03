package src.page;

public final class EditAttendancePage extends Page {
    /**
     * Edit attendance for a day
     * - Shows databases for that day
     */
    public EditAttendancePage() {
        
    }

    public String display() {
        // Dispaly the page details
        System.out.println("This is the edit attendance page.");

        // ...

        // Return the next page to navigate to
        // In this case, there is no next page to navigate to
        return null;
    }
}