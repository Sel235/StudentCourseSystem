public class Registration {
    private final CourseCatalog courseCatalog;

    public Registration(CourseCatalog courseCatalog){
        this.courseCatalog = courseCatalog;
    }

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

    public void updateCourseCapacity(String courseName, int newCapacity) throws Exception {
        Course course = courseCatalog.findCourse(courseName);
        if (course != null) {
            course.setCapacity(newCapacity);
        }
    }
}