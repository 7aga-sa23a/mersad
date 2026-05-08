package haga_talga.page;

public final class LoginPage extends Page {

    /**
     * Login page
     * - Doctor ID OR Email
     * - Password
     * - In case you forgot your password, please contact the faculty IT
     * - Signup instead
     */
    public LoginPage() {

    }

    @Override
    public String display() {
        // Dispaly the page details
        System.out.println("This is the login page.");
        System.out.println();

        // ...

        // Return the next page to navigate to
        return "DashboardPage";
    }
}
