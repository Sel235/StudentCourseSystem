import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents an Instructor (Teacher) in the university system.
 * Holds personal details and can retrieve the list of courses they teach.
 */
public class Instructor {
    private String fullName;
    private final UUID id;

    /**
     * Creates a new Instructor with a unique ID.
     * @param fullName The full name of the instructor.
     */
    public Instructor(String fullName){
        this.fullName = fullName;
        this.id = UUID.randomUUID();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String newFullName){
        this.fullName = newFullName;
    }

    public UUID getId() {
        return id;
    }

    /**
     * Retrieves all courses taught by this instructor.
     *
     * @param courseCatalog The catalog to search within.
     * @return A list of courses assigned to this instructor.
     */
    public ArrayList<Course> getInstructedCourses(CourseCatalog courseCatalog){
        return courseCatalog.getCoursesByInstructor(this);
    }
}