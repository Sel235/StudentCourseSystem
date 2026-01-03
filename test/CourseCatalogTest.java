import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CourseCatalogTest {
    private CourseCatalog catalog;
    private Instructor instructor;

    @BeforeEach
    void setUp() {
        catalog = new CourseCatalog();
        instructor = new Instructor("Tuğberk Kocatekin");
    }

    @Test
    void testAddCourseAndFind() throws Exception {
        Course c = new Course("OOP", instructor, 30, 3);
        //
        catalog.addCourse(c);

        Course found = catalog.findCourse("oop");
        assertNotNull(found);
        assertEquals(c, found);
    }

    @Test
    void testPreventDuplicateCourses() throws Exception {
        Course c1 = new Course("Math101", instructor, 30, 3);
        catalog.addCourse(c1);

        Course c2 = new Course("Math101", instructor, 30, 3);

        Exception exception = assertThrows(Exception.class, () -> {
            catalog.addCourse(c2);
        });

        assertTrue(exception.getMessage().contains("already exists"));
    }
}