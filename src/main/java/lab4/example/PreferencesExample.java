package lab4.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lab4.School;
import lab4.Student;

public class PreferencesExample {
    private static Map<Student, List<School>> studentPreferences;
    private static Map<School, List<Student>> schoolPreferences;

    static {
        List<Student> students = IntStream.rangeClosed(1, 3)
                .mapToObj(i -> new Student("firstName" + i, "lastName" + i, i))
                .sorted(Comparator.comparing(Student::getFirstName).reversed())
                .collect(Collectors.toCollection(LinkedList::new));

        Set<School> schools = IntStream.rangeClosed(1, 2).mapToObj(i -> new School("school" + i, students.size()))
                .collect(Collectors.toCollection(TreeSet::new));

        studentPreferences = new HashMap<>();
        for (var student : students) {
            List<School> preferences = new ArrayList<>(schools);
            Collections.shuffle(preferences);
            studentPreferences.put(student, preferences);
        }

        schoolPreferences = new TreeMap<>();
        for (var school : schools) {
            List<Student> preferences = new LinkedList<>(students);
            Collections.shuffle(preferences);
            schoolPreferences.put(school, preferences);
        }
    }

    public static Map<School, List<Student>> getSchoolPreferences() {
        return schoolPreferences;
    }

    public static Map<Student, List<School>> getStudentPreferences() {
        return studentPreferences;
    }
}
