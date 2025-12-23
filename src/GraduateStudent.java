public class GraduateStudent extends Student{
    private static final double GRAD_CREDIT_DISCOUNT_FACTOR = 0.6;
    public GraduateStudent(String fullName) {
        super(fullName);
    }
    @Override
    public double calculateTuition(CourseCatalog courseCatalog) {
        double baseTuition = super.calculateTuition(courseCatalog);
        return baseTuition * GRAD_CREDIT_DISCOUNT_FACTOR;
    }
}