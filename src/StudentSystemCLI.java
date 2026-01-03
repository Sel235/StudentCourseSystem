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
    private void listCourses() {
        System.out.println("\n--- Course List ---");
        if (catalog.getAllCourses().isEmpty()) {
            System.out.println("No courses available.");
        } else {
            for (Course c : catalog.getAllCourses()) {
                System.out.printf(" - %s (Credits: %d, Capacity: %d/%d, Waitlist: %d)%n",
                        c.getCourseName(), c.getCredits(),
                        c.getStudentsMainList().size(), c.getCapacity(),
                        c.getStudentsWaitList().size());
            }
        }
    }

    private void addCourse() throws Exception {
        System.out.print("Enter Course Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Instructor Name: ");
        String instructorName = scanner.nextLine();

        Instructor instructor = null;
        for (Instructor i : allInstructors) {
            if (i.getFullName().equalsIgnoreCase(instructorName)) {
                instructor = i;
                break;
            }
        }
        if (instructor == null) {
            instructor = new Instructor(instructorName);
            allInstructors.add(instructor);
        }

        System.out.print("Enter Capacity: ");
        int capacity = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Credits: ");
        int credits = Integer.parseInt(scanner.nextLine());

        Course newCourse = new Course(name, instructor, capacity, credits);
        catalog.addCourse(newCourse);
        System.out.println("Course '" + name + "' added successfully.");
    }

    private void createStudent() {
        System.out.print("Enter Student Full Name: ");
        String fullName = scanner.nextLine();
        System.out.print("Is this a Graduate Student? (y/n): ");
        String answer = scanner.nextLine();

        Student newStudent;
        if (answer.equalsIgnoreCase("y")) {
            newStudent = new GraduateStudent(fullName);
        } else {
            newStudent = new Student(fullName);
        }

        allStudents.add(newStudent);
        System.out.println("Student " + fullName + " created");
    }

    private void listAllStudents() {
        System.out.println("\n--- Student List ---");
        if (allStudents.isEmpty()) {
            System.out.println("No students.");
            return;
        }
        for (int i = 0; i < allStudents.size(); i++) {
            Student student = allStudents.get(i);
            System.out.println("[" + i + "] " + student.getFullName());
        }
    }

    private Student selectStudent() {
        if (allStudents.isEmpty()) {
            System.out.println("No students exist. Please create one first.");
            return null;
        }
        listAllStudents();
        System.out.print("Enter Student Index: ");
        try {
            int index = Integer.parseInt(scanner.nextLine());
            if (index >= 0 && index < allStudents.size()) {
                return allStudents.get(index);
            } else {
                System.out.println("Invalid index.");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return null;
        }
    }
    private void registerStudent() throws Exception {
        Student student = selectStudent();
        if (student == null) {
            System.out.println("Student not found! Please create the student first (Option 4).");
            return;
        }
        System.out.print("Enter Course Name to Join: ");
        String courseName = scanner.nextLine();
        Course course = catalog.findCourse(courseName);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        student.joinCourse(course, registration);
    }

    private void dropStudent() throws Exception {
        Student student = selectStudent();
        if (student == null) return;

        System.out.print("Enter Course Name to Drop: ");
        String courseName = scanner.nextLine();
        Course course = catalog.findCourse(courseName);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        student.dropCourse(course, registration);
        System.out.println("Dropped " + student.getFullName() + " from " + courseName);
    }

    private void viewStudentCourses() throws Exception {
        Student student = selectStudent();
        if (student == null) return;
        System.out.println("\n--- Courses for " + student.getFullName() + " ---");
        ArrayList<Course> registered = student.getRegisteredCourses(catalog);
        if (registered.isEmpty()) {
            System.out.println("Registered: None");
        } else {
            System.out.println("Registered:");
            for (Course c : registered) {
                System.out.println("  - " + c.getCourseName() + " (" + c.getCredits() + " credits)");
            }
        }
        ArrayList<Course> waitlisted = student.getWaitListedCourses(catalog);
        if (!waitlisted.isEmpty()) {
            System.out.println("Waitlisted:");
            for (Course c : waitlisted) {
                System.out.println("  - " + c.getCourseName() + " (Waiting)");
            }
        }
    }

    private void calculateTuition() throws Exception{
        Student student = selectStudent();
        if (student == null) return;
        double tuition = student.calculateTuition(catalog);
        System.out.println("Tuition for " + student.getFullName() + ": " + tuition);
    }

    private void updateCourseCapacity() throws Exception {
        System.out.print("Enter Course Name: ");
        String courseName = scanner.nextLine();
        Course course = catalog.findCourse(courseName);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        System.out.println("Current Capacity: " + course.getCapacity());
        System.out.println("Current Applied: " + course.getStudentsMainList().size() + " Registered, " +
                course.getStudentsWaitList().size() + " Waitlisted.");

        System.out.print("Enter New Capacity: ");
        try {
            int newCapacity = Integer.parseInt(scanner.nextLine());
            registration.updateCourseCapacity(courseName, newCapacity);
            System.out.println("Capacity updated successfully.");
            System.out.println("New Status: " + course.getStudentsMainList().size() + "/" + course.getCapacity() +
                    " Registered, " + course.getStudentsWaitList().size() + " Waitlisted.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format.");
        }
    }

}
