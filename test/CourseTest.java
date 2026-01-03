import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CourseTest {
    private Course course;
    private Student s1, s2, s3, s4;

    @BeforeEach
    void setUp() {
        course = new Course("Test Course", new Instructor("Prof X"), 2, 3);
        s1 = new Student("S1");
        s2 = new Student("S2");
        s3 = new Student("S3");
        s4 = new Student("S4");

        course.getStudentsMainList().add(s1);
        course.getStudentsMainList().add(s2);
        course.getStudentsWaitList().add(s3);
        course.getStudentsWaitList().add(s4);
    }

    @Test
    void testIncreaseCapacityPromotesWaitlist() throws Exception {
        course.setCapacity(3);

        assertEquals(3, course.getStudentsMainList().size());
        assertTrue(course.getStudentsMainList().contains(s3));

        assertEquals(1, course.getStudentsWaitList().size());
        assertTrue(course.getStudentsWaitList().contains(s4));
    }

    @Test
    void testDecreaseCapacityMovesToWaitlist() throws Exception {
        course.setCapacity(1);

        assertEquals(1, course.getStudentsMainList().size());
        assertTrue(course.getStudentsMainList().contains(s1)); // S1 stays

        assertEquals(3, course.getStudentsWaitList().size());
        assertEquals(s2, course.getStudentsWaitList().getFirst(), " bumped student should be at front of waitlist");
    }

    @Test
    void testNegativeCapacity() {
        Exception exception = assertThrows(Exception.class, () -> {
            course.setCapacity(-1);
        });
        assertEquals("Capacity can't be negative", exception.getMessage());
    }
}
