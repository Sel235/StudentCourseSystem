import java.util.ArrayList;
import java.util.UUID;

public class Instructor {
    private String fullName;
    private final UUID id;

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
    public ArrayList<Course> getInstructedCourses(CourseCatalog courseCatalog){
        return courseCatalog.getCoursesByInstructor(this);
    }
}