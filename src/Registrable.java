public interface Registrable {
    void joinCourse(Course course, Registration registration) throws Exception;
    void dropCourse(Course course, Registration registration) throws Exception;
}
