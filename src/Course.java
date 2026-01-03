import java.util.ArrayList;
import java.util.LinkedList;
import java.util.UUID;

/**
 * Represents a course offered by the university.
 * Maintains a main list of registered students and a waitlist for overflow.
 */
public class Course {
    private String courseName;
    private final UUID id;
    private Instructor instructor;
    private int capacity;
    private ArrayList<Student> studentsMainList;
    private int credits;

    // Linked list is used for efficient FIFO operations on the waitlist.
    private LinkedList<Student> studentsWaitList;

    public Course(String courseName, Instructor instructor, int capacity, int credits){
        this.courseName = courseName;
        this.id = UUID.randomUUID();
        this.instructor = instructor;
        this.capacity = capacity;
        this.credits = credits;
        this.studentsMainList = new ArrayList<>();
        this.studentsWaitList = new LinkedList<>();
    }

    // Getters and Setters with basic docs omitted for brevity, but standard Javadoc applies here too.

    public void setCourseName(String newCourseName){ this.courseName = newCourseName; }
    public String getCourseName(){ return courseName; }
    public Instructor getInstructor(){ return instructor; }
    public void setInstructor(Instructor newInstructor){ this.instructor = newInstructor; }
    public ArrayList<Student> getStudentsMainList() { return studentsMainList; }
    public LinkedList<Student> getStudentsWaitList() { return studentsWaitList; }
    public int getCapacity() { return capacity; }
    public UUID getId() { return id; }
    public int getCredits() { return credits; }
    public void setCredits(int newCredits){ this.credits = newCredits; }

    public boolean isStudentRegistered(Student student){
        return studentsMainList.contains(student);
    }

    public boolean isStudentWaitListed(Student student){
        return studentsWaitList.contains(student);
    }

    /**
     * Updates the course capacity.
     * If capacity is increased, students from the waitlist are automatically promoted.
     * If capacity is decreased, the last registered students are moved to the waitlist.
     *
     * @param newCapacity The new capacity (must be non-negative).
     * @throws Exception If new capacity is negative.
     */
    public void setCapacity(int newCapacity) throws Exception{
        if (newCapacity < 0){
            throw new Exception("Capacity can't be negative");
        }
        capacity = newCapacity;

        // Promote from waitlist if spots opened up
        while (studentsMainList.size() < capacity && !studentsWaitList.isEmpty()){
            Student promotedStudent = studentsWaitList.poll();
            studentsMainList.add(promotedStudent);
        }

        // Move to waitlist if capacity shrank
        while(studentsMainList.size() > capacity){
            int lastIndex = studentsMainList.size() - 1;
            Student waitListedStudent = studentsMainList.remove(lastIndex);
            studentsWaitList.addFirst(waitListedStudent);
        }
    }
}
