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
}