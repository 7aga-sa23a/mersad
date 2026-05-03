package src.page;

public final class SignupPage extends Page {
    /**
     * Signup page
     * - Name (First & last only)
     * - Email
     * - Password
     * - Repeat password
     * - EXTRA: Simulate email and IT confirmation process
     * - - Example:
     * - - Your registration will be evaluated by the IT shortly…
     * - - Registration was successful!
     */
    public SignupPage() {
        
    }

    public String display() {
        // Dispaly the page details
        System.out.println("This is the signup page.");

        // ...

        // Return the next page to navigate to
        return "DashboardPage";
    }
}