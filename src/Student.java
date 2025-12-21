import java.util.UUID;

public class Student {
    protected static final double ONE_CREDIT_FEE = 1000.0;
    private String fullName;
    private final UUID id;

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
}