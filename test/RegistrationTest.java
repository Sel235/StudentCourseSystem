import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RegistrationTest {
    private CourseCatalog catalog;
    private Registration registration;
    private Instructor instructor;
    private Course course;
    private Student selin;
    private Student ahmet;
    private Student murat;

    @BeforeEach
    void setUp() throws Exception {
        catalog = new CourseCatalog();
        registration = new Registration(catalog);
        instructor = new Instructor("Tuğberk Kocatekin");


        course = new Course("OOP", instructor, 2, 3);
        catalog.addCourse(course);

        selin = new Student("Selin");
        ahmet = new Student("Ahmet");
        murat = new Student("Murat");
    }

    @Test
    void testRegisterSuccess() throws Exception {

        registration.registerStudentToCourse(selin, course);

        assertTrue(course.getStudentsMainList().contains(selin), "Student should be in the main list");
        assertEquals(1, course.getStudentsMainList().size());
    }

    @Test
    void testWaitlistLogic() throws Exception {

        registration.registerStudentToCourse(selin, course);
        registration.registerStudentToCourse(ahmet, course);


        registration.registerStudentToCourse(murat, course);

        assertTrue(course.getStudentsMainList().contains(selin));
        assertTrue(course.getStudentsMainList().contains(ahmet));
        assertFalse(course.getStudentsMainList().contains(murat));


        assertTrue(course.getStudentsWaitList().contains(murat));
        assertEquals(1, course.getStudentsWaitList().size());
    }

    @Test
    void testDropAndPromote() throws Exception {
        registration.registerStudentToCourse(selin, course);
        registration.registerStudentToCourse(ahmet, course);
        registration.registerStudentToCourse(murat, course);


        registration.dropStudentFromCourse(ahmet, course);


        assertFalse(course.getStudentsMainList().contains(ahmet));


        assertTrue(course.getStudentsMainList().contains(murat), "Waitlisted student should be promoted");
        assertTrue(course.getStudentsWaitList().isEmpty(), "Waitlist should be empty after promotion");
    }

    @Test
    void testPreventDuplicateRegistration() throws Exception {
        registration.registerStudentToCourse(selin, course);


        Exception exception = assertThrows(Exception.class, () -> {
            registration.registerStudentToCourse(selin, course);
        });

        assertEquals("Student already joined", exception.getMessage());
    }
}
