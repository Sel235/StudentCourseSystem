/**
 * Manages the registration process for students and courses.
 * Handles logic for adding students to courses, managing waitlists, and dropping courses.
 */
public class Registration {
    private final CourseCatalog courseCatalog;

    /**
     * Constructs a Registration system with a specific course catalog.
     * @param courseCatalog The catalog containing available courses.
     */
    public Registration(CourseCatalog courseCatalog){
        this.courseCatalog = courseCatalog;
    }

    /**
     * Registers a student to a specific course.
     * If the course is full, the student is added to the waitlist.
     *
     * @param student The student attempting to register.
     * @param course The course to join.
     * @throws Exception If the course is null, doesn't exist, or the student is already registered/waitlisted.
     */
    public void registerStudentToCourse(Student student, Course course) throws Exception{
        if (course == null){
            throw new RuntimeException("Entered null course");
        }
        if (!courseCatalog.getAllCourses().contains(course)){
            throw new Exception("Course doesn't exist");
        }
        if (course.getStudentsMainList().contains(student) || course.getStudentsWaitList().contains(student)){
            throw new Exception("Student already joined");
        }
        if (course.getStudentsMainList().size() >= course.getCapacity()){
            course.getStudentsWaitList().add(student);
            return;
        }
        course.getStudentsMainList().add(student);
    }

    /**
     * Drops a student from a course.
     * If there are students in the waitlist, the next student is automatically promoted to the main list.
     *
     * @param student The student to be removed.
     * @param course The course to drop.
     * @throws Exception If the student is not found in the course or waitlist.
     */
    public void dropStudentFromCourse(Student student, Course course) throws Exception{
        if (course == null){
            throw new RuntimeException("Entered null course");
        }
        if (!courseCatalog.getAllCourses().contains(course)){
            throw new Exception("Course doesn't exist");
        }
        boolean isInMainList = course.getStudentsMainList().contains(student);
        boolean isInWaitList = course.getStudentsWaitList().contains(student);
        if (!isInMainList && !isInWaitList){
            throw new Exception("Student never joined the course");
        }
        if (isInMainList){
            course.getStudentsMainList().remove(student);
            if (!course.getStudentsWaitList().isEmpty()){
                course.getStudentsMainList().add(course.getStudentsWaitList().poll());
            }
            return;
        }
        course.getStudentsWaitList().remove(student);
    }

    /**
     * Updates the capacity of a course and reorganizes students if necessary.
     *
     * @param courseName The name of the course.
     * @param newCapacity The new capacity value.
     * @throws Exception If the course is not found or capacity logic fails.
     */
    public void updateCourseCapacity(String courseName, int newCapacity) throws Exception {
        Course course = courseCatalog.findCourse(courseName);
        if (course != null) {
            course.setCapacity(newCapacity);
        }
    }
}