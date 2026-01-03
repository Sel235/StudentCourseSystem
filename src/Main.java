/**
 * The entry point of the Student Registration System application.
 * Initializes and starts the Command Line Interface (CLI).
 */
public class Main {

    /**
     * The main method that launches the application.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        StudentSystemCLI app = new StudentSystemCLI();
        app.start();
    }
}