package lab4;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Problem {
    private Map<Student, Preferences<School>> studentPreferences;
    private Map<School, Preferences<Student>> schoolPreferences;

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

    public Preferences<School> getStudentPreferences(Student student) {
        if (!studentPreferences.containsKey(student)) {
            throw new IllegalArgumentException("Student is not part of the problem");
        }
        return studentPreferences.get(student);
    }

    public Preferences<Student> getSchoolPreferences(School school) {
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
        studentPreferences.put(student, new Preferences<>());
    }

    public void addStudentPreference(Student student, School school, int priority) {
        if (school == null) {
            throw new IllegalArgumentException("School must not be null");
        }
        if (student == null) {
            throw new IllegalArgumentException("Student must not be null");
        }
        if (!studentPreferences.containsKey(student)) {
            throw new IllegalArgumentException("Student was not added");
        }
        studentPreferences.get(student).add(school, priority);
    }

    public void addSchool(School school) {
        if (school == null) {
            throw new IllegalArgumentException("School must not be null");
        }
        if (schoolPreferences.containsKey(school)) {
            throw new IllegalArgumentException("School was already added");
        }
        schoolPreferences.put(school, new Preferences<>());
    }

    public void addSchoolPreference(School school, Student student, int priority) {
        if (school == null) {
            throw new IllegalArgumentException("School must not be null");
        }
        if (student == null) {
            throw new IllegalArgumentException("Student must not be null");
        }
        if (!schoolPreferences.containsKey(school)) {
            throw new IllegalArgumentException("student was not added");
        }
        schoolPreferences.get(school).add(student, priority);
    }

    public void displayStudentsPreferring(List<School> schools) {
        studentPreferences.keySet().stream()
                .filter(student -> studentPreferences.get(student).getAll().containsAll(schools))
                .forEach(student -> System.out.println(student));
    }

    public void displaySchoolsPreferring(Student student) {
        schoolPreferences.keySet().stream().filter(school -> {
            var preferences = schoolPreferences.get(school);
            return !preferences.isEmpty() && preferences.getPriorityOf(student) == preferences.getHighestPriority();
        }).forEach(school -> System.out.println(school));
    }

    @Override
    public String toString() {
        return "Problem [studentPreferences=" + studentPreferences + ", schoolPreferences=" + schoolPreferences + "]";
    }
}
