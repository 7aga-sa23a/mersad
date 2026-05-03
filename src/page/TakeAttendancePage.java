package src.page;

public final class TakeAttendancePage extends Page {
    /**
     * Take attendance
     * - Code scanner class
     * - Connected with JSON database
     */
    public TakeAttendancePage() {
        
    }

    public String display() {
        // Dispaly the page details
        System.out.println("This is the take attendance page.");

        // ...

        // Return the next page to navigate to
        // In this case, there is no next page to navigate to
        return null;
    }
}
