import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    private CourseCatalog catalog;
    private Registration registration;
    private Course mathCourse;
    private Course csCourse;

    @BeforeEach
    void setUp() throws Exception {
        catalog = new CourseCatalog();
        registration = new Registration(catalog);
        Instructor instructor = new Instructor("Prof. Jones");

        mathCourse = new Course("Math", instructor, 10, 4);
        csCourse = new Course("CS", instructor, 10, 3);

        catalog.addCourse(mathCourse);
        catalog.addCourse(csCourse);
    }

    @Test
    void testUndergraduateTuition() throws Exception {
        Student undergrad = new Student("Regular Student");


        undergrad.joinCourse(mathCourse, registration);
        undergrad.joinCourse(csCourse, registration);

        double expectedTuition = (4 * 1000.0) + (3 * 1000.0); // 7000.0
        assertEquals(expectedTuition, undergrad.calculateTuition(catalog));
    }

    @Test
    void testGraduateTuitionPolymorphism() throws Exception {
        Student gradStudent = new GraduateStudent("Grad Student");

        gradStudent.joinCourse(mathCourse, registration);
        gradStudent.joinCourse(csCourse, registration);

        double baseTuition = (4 * 1000.0) + (3 * 1000.0);
        double expectedTuition = baseTuition * 0.6;

        assertEquals(expectedTuition, gradStudent.calculateTuition(catalog), 0.01);
    }
}
