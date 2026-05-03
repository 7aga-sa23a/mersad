package src.page;

public final class AddCoursePage extends Page {
    /**
     * Add course
     * - name
     * - Year
     * - Semester
     * - Code
     */
    public AddCoursePage() {
        
    }

    public String display() {
        // Dispaly the page details
        System.out.println("This is the add course page.");

        // ...

        // Return the next page to navigate to
        // In this case, there is no next page to navigate to
        return null;
    }
}
