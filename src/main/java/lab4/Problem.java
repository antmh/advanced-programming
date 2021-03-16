package lab4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Problem {
    private Map<Student, List<School>> studentPreferences;
    private Map<School, List<Student>> schoolPreferences;

    public Problem() {
        studentPreferences = new HashMap<>();
        schoolPreferences = new HashMap<>();
    }

    public Set<School> getSchools() {
        return Collections.unmodifiableSet(schoolPreferences.keySet());
    }

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(studentPreferences.keySet());
    }

    public List<School> getPreferredSchools(Student student) {
        if (!studentPreferences.containsKey(student)) {
            throw new IllegalArgumentException("Student is not part of the problem");
        }
        return studentPreferences.get(student);
    }

    public List<Student> getPreferredStudents(School school) {
        if (!schoolPreferences.containsKey(school)) {
            throw new IllegalArgumentException("School is not part of the problem");
        }
        return schoolPreferences.get(school);
    }

    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student must not be null");
        }
        if (studentPreferences.containsKey(student)) {
            throw new IllegalArgumentException("Student was already added");
        }
        studentPreferences.put(student, new ArrayList<>());
    }

    public void addStudentPreference(Student student, School school) {
        if (school == null) {
            throw new IllegalArgumentException("School must not be null");
        }
        if (student == null) {
            throw new IllegalArgumentException("Student must not be null");
        }
        if (!studentPreferences.containsKey(student)) {
            throw new IllegalArgumentException("Student was not added");
        }
        studentPreferences.get(student).add(school);
    }

    public void addSchool(School school) {
        if (school == null) {
            throw new IllegalArgumentException("School must not be null");
        }
        if (schoolPreferences.containsKey(school)) {
            throw new IllegalArgumentException("School was already added");
        }
        schoolPreferences.put(school, new ArrayList<>());
    }

    public void addSchoolPreference(School school, Student student) {
        if (school == null) {
            throw new IllegalArgumentException("School must not be null");
        }
        if (student == null) {
            throw new IllegalArgumentException("Student must not be null");
        }
        if (!schoolPreferences.containsKey(school)) {
            throw new IllegalArgumentException("student was not added");
        }
        schoolPreferences.get(school).add(student);
    }

    public void displayStudentsPreferring(List<School> schools) {
        studentPreferences.keySet().stream().filter(student -> studentPreferences.get(student).containsAll(schools))
                .forEach(student -> System.out.println(student));
    }

    public void displaySchoolsPreferring(Student student) {
        schoolPreferences.keySet().stream().filter(school -> schoolPreferences.get(school).indexOf(student) == 0)
                .forEach(school -> System.out.println(school));
    }

    @Override
    public String toString() {
        return "Problem [studentPreferences=" + studentPreferences + ", schoolPreferences=" + schoolPreferences + "]";
    }
}
