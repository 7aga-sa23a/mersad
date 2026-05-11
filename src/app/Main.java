package src.app;

/**
 * <h1>Mersad</h1>
 * This program is a Java OOP Attendance System using QR code <br>
 * simulation to manage students, materials, and attendance tracking.
 * 
 * <h3>Usage</h3>
 * <ul>
 *   <li><b>Compile:</b> <code>mvn compile</code></li>
 *   <li><b>Convert to .jar:</b> <code>mvn package</code></li>
 *   <li><b>Compile & run:</b> <code>mvn exec:java</code></li>
 *   <li><b>Hide info messages:</b> add -q to the command</li>
 *   <li><b>Show debug messages:</b> add -X to the command</li>
 * </ul>
 *
 * @author 7aga-sa23a
 */
public class Main {
    /**
     * Program flow.
     * 
     * @param args
     */
    public static void main(String[] args) {
        // Start the program flow
        Main main = new Main();

        main.onboardingPage();
    }

    /**
     * Onboarding page
     * - Info about the program
     * - - Clarify what the program does
     * - - Only for doctors
     * - Login option
     * - Sign up option
     * - Exit option
     */
    private void onboardingPage() {
        // Implement the onboarding page logic
        System.out.println("Program structure is ready.");
    }

    /**
     * Login page
     * - Doctor ID OR Email
     * - Password
     * - In case you forgot your password, please contact the faculty IT
     * - Signup instead
     */
    private void loginPage() {
        // Implement the login page logic
    }

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
    private void signupPage() {
        // Implement the signup page logic
    }

    /**
     * Dashboard
     * - Add course
     * - Show courses
     * - Take attendance for today
     * - Edit attendance for a day
     * - - Shows databases for that day
     * - Exit program
     */
    private void dashboardPage() {
        // Implement the dashboard page logic
    }

    /**
     * Add course
     * - name
     * - Year
     * - Semester
     * - Code
     */
    private void addCoursePage() {
        // Implement the add course page logic
    }

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
    private void showCoursesPage() {
        // Implement the show courses page logic
    }

    /**
     * Take attendance
     * - Code scanner class
     * - Connected with JSON database
     */
    private void takeAttendancePage() {
        // Implement the take attendance page logic
    }

    /**
     * Edit attendance for a day
     * - Shows databases for that day
     */
    private void editAttendancePage() {
        // Implement the edit attendance page logic
    }
}