/**
 * Represents a Graduate Student.
 * Graduate students receive a discount on tuition fees.
 */
public class GraduateStudent extends Student{
    private static final double GRAD_CREDIT_DISCOUNT_FACTOR = 0.6;

    /**
     * Creates a new GraduateStudent.
     * @param fullName The full name of the student.
     */
    public GraduateStudent(String fullName) {
        super(fullName);
    }

    /**
     * Calculates tuition with a graduate discount applied.
     * Overrides the base {@link Student#calculateTuition(CourseCatalog)} method.
     *
     * @param courseCatalog The catalog containing course details.
     * @return The discounted tuition fee.
     */
    @Override
    public double calculateTuition(CourseCatalog courseCatalog) {
        double baseTuition = super.calculateTuition(courseCatalog);
        return baseTuition * GRAD_CREDIT_DISCOUNT_FACTOR;
    }
}