
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.UUID;

public class Course {
    private String courseName;
    private final UUID id;
    private Instructor instructor;
    private int capacity;
    private ArrayList<Student> studentsMainList;
    private int credits;
    // Since we will want to remove and get the first item from wait list when a student drops a course from main list
    // linked list can be more efficient.
    private LinkedList<Student> studentsWaitList ;
    public Course(String courseName, Instructor instructor, int capacity, int credits){
        this.courseName = courseName;
        this.id = UUID.randomUUID();
        this.instructor = instructor;
        this.capacity = capacity;
        this.credits = credits;
        this.studentsMainList = new ArrayList<>();
        this.studentsWaitList = new LinkedList<>();
    }
    public void setCourseName(String newCourseName){
        this.courseName = newCourseName;
    }
    public String getCourseName(){
        return courseName;
    }
    public Instructor getInstructor(){
        return instructor;
    }
    public void setInstructor(Instructor newInstructor){
        this.instructor = newInstructor;
    }
    public ArrayList<Student> getStudentsMainList() {
        return studentsMainList;
    }
    public LinkedList<Student> getStudentsWaitList() {
        return studentsWaitList;
    }
    public int getCapacity() {
        return capacity;
    }

    public UUID getId() {
        return id;
    }
    public boolean isStudentRegistered(Student student){
        return studentsMainList.contains(student);
    }
    public boolean isStudentWaitListed(Student student){
        return studentsWaitList.contains(student);
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int newCredits){
        this.credits = newCredits;
    }

    public void setCapacity(int newCapacity) throws Exception{
        if (newCapacity < 0){
            throw new Exception("Capacity can't be negative");
        }
        capacity = newCapacity;
        while (studentsMainList.size() < capacity && !studentsWaitList.isEmpty()){
            Student promotedStudent = studentsWaitList.poll();
            studentsMainList.add(promotedStudent);
        }

        while(studentsMainList.size() > capacity){
            int lastIndex = studentsMainList.size() - 1;
            Student waitListedStudent = studentsMainList.remove(lastIndex);
            studentsWaitList.addFirst(waitListedStudent);
        }
    }
}

