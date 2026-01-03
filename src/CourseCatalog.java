import java.util.ArrayList;
import java.util.List;

/**
 * Manages the collection of all available courses in the system.
 */
public class CourseCatalog {
    private List<Course> courseList;

    public CourseCatalog() {
        this.courseList = new ArrayList<>();
    }

    /**
     * Adds a new course to the catalog.
     * Checks for duplicates based on course name.
     *
     * @param course The course object to add.
     * @throws Exception If the course is null or already exists.
     */
    public void addCourse(Course course) throws Exception {
        if (course == null) {
            throw new Exception("Can't add a null course.");
        }
        for (Course existingCourse : courseList) {
            if (existingCourse.getCourseName().equalsIgnoreCase(course.getCourseName())) {
                throw new Exception("Course '" + course.getCourseName() + "' already exists in the catalog.");
            }
        }
        courseList.add(course);
    }

    /**
     * Retrieves all courses in the catalog.
     * @return A list of courses.
     */
    public List<Course> getAllCourses() {
        return courseList;
    }

    /**
     * Finds a course by its name (case-insensitive).
     * @param courseName The name of the course to find.
     * @return The Course object if found, otherwise null.
     */
    public Course findCourse(String courseName) {
        for (Course course : courseList) {
            if (course.getCourseName().equalsIgnoreCase(courseName)) {
                return course;
            }
        }
        return null;
    }

    public ArrayList<Course> getStudentRegisteredCourses(Student student) {
        ArrayList<Course> registeredCourses = new ArrayList<Course>();
        for (Course course: getAllCourses()){
            if (course.isStudentRegistered(student)){
                registeredCourses.add(course);
            }
        }
        return registeredCourses;
    }

    public ArrayList<Course> getStudentWaitListedCourses(Student student) {
        ArrayList<Course> waitListedCourses = new ArrayList<Course>();
        for (Course course : getAllCourses()){
            if (course.isStudentWaitListed(student)){
                waitListedCourses.add(course);
            }
        }
        return waitListedCourses;
    }

    public ArrayList<Course> getCoursesByInstructor(Instructor instructor) {
        ArrayList<Course> coursesByInstructor = new ArrayList<Course>();
        for (Course course : getAllCourses()){
            if (course.getInstructor() == instructor){
                coursesByInstructor.add(course);
            }
        }
        return coursesByInstructor;
    }
}