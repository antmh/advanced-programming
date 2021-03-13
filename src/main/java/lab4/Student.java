package lab4;

public class Student {
    private String firstName;
    private String lastName;

    Student() {
        firstName = "";
        lastName = "";
    }

    Student(String firstName, String lastName) {
        validateName(firstName);
        validateName(lastName);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void setLastName(String lastName) {
        validateName(lastName);
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        validateName(firstName);
        this.firstName = firstName;
    }

    private void validateName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
    }

    @Override
    public String toString() {
        return "Student [firstName=" + firstName + ", lastName=" + lastName + "]";
    }
}
