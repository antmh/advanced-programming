package lab4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
    private Map<School, Set<Student>> data;

    public Solution() {
        data = new HashMap<>();
    }

    public void addSchool(School school) {
        validateSchool(school);
        data.put(school, new HashSet<>());
    }

    public void addStudentToSchool(School school, Student student) {
        validateSchool(school);
        validateStudent(student);
        if (!data.containsKey(school)) {
            throw new IllegalArgumentException("School is not part of the solution");
        }
        data.get(school).add(student);
    }

    public Set<Student> getStudentsOfSchool(School school) {
        validateSchool(school);
        if (!data.containsKey(school)) {
            throw new IllegalArgumentException("School is not part of the solution");
        }
        return data.get(school);
    }

    public boolean hasSchool(School school) {
        validateSchool(school);
        return data.containsKey(school);
    }

    public boolean hasStudent(Student student) {
        validateStudent(student);
        return data.values().stream().anyMatch(students -> students.contains(student));
    }

    private void validateSchool(School school) {
        if (school == null) {
            throw new IllegalArgumentException("School cannot be null");
        }
    }

    private void validateStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
    }

    @Override
    public String toString() {
        return "Solution [" + data + "]";
    }
}
