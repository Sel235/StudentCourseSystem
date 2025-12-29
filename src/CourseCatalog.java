import java.util.ArrayList;
import java.util.List;

public class CourseCatalog {
    private List<Course> courseList;

    public CourseCatalog() {
        this.courseList = new ArrayList<>();
    }

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

    public List<Course> getAllCourses() {
        return courseList;
    }

    public Course findCourse(String courseName) {
        for (Course course : courseList) {
            if (course.getCourseName().equalsIgnoreCase(courseName)) {
                return course;
            }
        }
        return null;
    }
}