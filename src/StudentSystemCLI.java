import java.util.ArrayList;
import java.util.Scanner;

public class StudentSystemCLI {
    private ArrayList<Student> allStudents = new ArrayList<Student>();
    private ArrayList<Instructor> allInstructors = new ArrayList<Instructor>();
    private CourseCatalog catalog = new CourseCatalog();
    private Registration registration = new Registration(catalog);
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        preLoadData();
        boolean running = true;
        while (running) {
            printMenu();
            String input = scanner.nextLine();
            try {
                switch (input) {
                    case "1": listCourses(); break;
                    case "2": addCourse(); break;
                    case "3": listAllStudents(); break;
                    case "4": createStudent(); break;
                    case "5": registerStudent(); break;
                    case "6": dropStudent(); break;
                    case "7": viewStudentCourses(); break;
                    case "8": calculateTuition(); break;
                    case "9": updateCourseCapacity(); break;
                    case "10": running = false; break;
                    default: System.out.println("Invalid option. Try again.");
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    public void printMenu(){
        System.out.println("\n=== STUDENT REGISTRATION SYSTEM ===");
        System.out.println("1. List All Courses");
        System.out.println("2. Add New Course");
        System.out.println("3. List All Students");
        System.out.println("4. Create New Student");
        System.out.println("5. Register Student to Course");
        System.out.println("6. Drop Student from Course");
        System.out.println("7. View Student Courses");
        System.out.println("8. Calculate Student Tuition");
        System.out.println("9. Update Course Capacity");
        System.out.println("10. Exit");
        System.out.print("Select an option: ");
    }


    private void preLoadData() {
        System.out.println("Pre loading system data...");
        try {
            Instructor kocatekin = new Instructor("Tuğberk Kocatekin");
            Instructor emrah = new Instructor("Emrah");
            Instructor sevgi = new Instructor("Sevgi");

            allInstructors.add(kocatekin);
            allInstructors.add(emrah);
            allInstructors.add(sevgi);

            Course oop = new Course("OOP", kocatekin, 4, 3);
            Course phys101 = new Course("PHYS101 Physics", emrah, 10, 4);
            Course math101 = new Course("MATH101 Calculus", sevgi, 20, 4);

            catalog.addCourse(oop);
            catalog.addCourse(phys101);
            catalog.addCourse(math101);

            Student selin = new Student("Selin");
            Student ahmet = new Student("Ahmet");
            Student murat = new Student("Murat");
            Student mehmet = new GraduateStudent("Mehmet");

            allStudents.add(selin);
            allStudents.add(ahmet);
            allStudents.add(murat);
            allStudents.add(mehmet);

            selin.joinCourse(oop, registration);
            ahmet.joinCourse(oop, registration);
            murat.joinCourse(oop, registration);
            mehmet.joinCourse(phys101, registration);
            mehmet.joinCourse(math101, registration);

        } catch (Exception e) {
            System.out.println("Error seeding data: " + e.getMessage());
        }
    }
    private void listCourses() {}
    private void addCourse() throws Exception {}
    private void listAllStudents() {}
    private void createStudent() {}
    private void registerStudent() throws Exception {}
    private void dropStudent() throws Exception {}
    private void viewStudentCourses() throws Exception {}
    private void calculateTuition() throws Exception {}
    private void updateCourseCapacity() throws Exception {}


    private Student selectStudent() { return null; }
}
