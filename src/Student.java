public class Student implements Comparable{

    private int enrollmentId;
    private String firstName;
    private String lastName;

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Student(int enrollmentId, String firstName, String lastName) {
        this.enrollmentId = enrollmentId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Student() {
    }

    public void printDetails(){
        System.out.println(this.getEnrollmentId());
        System.out.println(this.getFirstName());
        System.out.println(this.getLastName());
    }

    @Override
    public int compareTo(Object o) {
        Student otherStudent = (Student) o;
        return this.firstName.compareTo(otherStudent.getFirstName());
    }
}
