import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents a student in the system.
 * Implements {@link Registrable} interface to handle course operations.
 */
public class Student implements Registrable{
    /** The fee charged per credit unit. */
    protected static final double ONE_CREDIT_FEE = 1000.0;

    private String fullName;
    private final UUID id;

    /**
     * Creates a new Student with a unique ID.
     * @param fullName The full name of the student.
     */
    public Student(String fullName){
        this.fullName = fullName;
        this.id = UUID.randomUUID();
    }

    public String getFullName(){
        return fullName;
    }

    public void setFullName(String newFullName){
        this.fullName = newFullName;
    }

    public UUID getId(){
        return id;
    }

    /**
     * Calculates the total tuition fee based on registered courses.
     * formula: Total Credits * ONE_CREDIT_FEE.
     *
     * @param courseCatalog The catalog required to fetch course details (credits).
     * @return The calculated tuition amount.
     */
    public double calculateTuition(CourseCatalog courseCatalog) {
        double sum = 0.0;
        for (Course course : getRegisteredCourses(courseCatalog)){
            sum += course.getCredits() * ONE_CREDIT_FEE;
        }
        return sum;
    }

    @Override
    public void joinCourse(Course course, Registration registration) throws Exception {
        registration.registerStudentToCourse(this,course);
    }

    @Override
    public void dropCourse(Course course, Registration registration) throws Exception {
        registration.dropStudentFromCourse(this,course);
    }

    public ArrayList<Course> getRegisteredCourses(CourseCatalog courseCatalog){
        return courseCatalog.getStudentRegisteredCourses(this);
    }

    public ArrayList<Course> getWaitListedCourses(CourseCatalog courseCatalog){
        return courseCatalog.getStudentWaitListedCourses(this);
    }

    public ArrayList<Course> getAppliedCourses(CourseCatalog courseCatalog){
        ArrayList<Course> registeredCourses = getRegisteredCourses(courseCatalog);
        ArrayList<Course> waitListedCourses = getWaitListedCourses(courseCatalog);
        ArrayList<Course> appliedCourses = new ArrayList<Course>(registeredCourses);
        appliedCourses.addAll(waitListedCourses);
        return appliedCourses;
    }
}