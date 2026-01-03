/**
 * Interface defining the contract for entities that can register for courses.
 * Any class implementing this (like Student) must define how to join and drop courses.
 */
public interface Registrable {

    /**
     * Joins a specific course using the registration system.
     * * @param course The course to join.
     * @param registration The registration controller handling the logic.
     * @throws Exception If registration fails (e.g., course full, already registered).
     */
    void joinCourse(Course course, Registration registration) throws Exception;

    /**
     * Drops a specific course using the registration system.
     * * @param course The course to drop.
     * @param registration The registration controller handling the logic.
     * @throws Exception If the drop operation fails.
     */
    void dropCourse(Course course, Registration registration) throws Exception;
}