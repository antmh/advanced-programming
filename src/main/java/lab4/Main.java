package lab4;

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

public class Main {

    public static void main(String[] args) {
        List<Student> students = IntStream.rangeClosed(1, 3).mapToObj(i -> new Student("firstName" + i, "lastName" + i))
                .sorted(Comparator.comparing(Student::getFirstName).reversed())
                .collect(Collectors.toCollection(LinkedList::new));

        Set<School> schools = IntStream.rangeClosed(1, 2).mapToObj(i -> new School("school" + i))
                .collect(Collectors.toCollection(TreeSet::new));

        Map<Student, List<School>> studentPreferences = new HashMap<>();
        for (var student : students) {
            List<School> preferences = new ArrayList<>(schools);
            Collections.shuffle(preferences);
            studentPreferences.put(student, preferences);
        }

        Map<School, List<Student>> schoolPreferences = new TreeMap<>();
        for (var school : schools) {
            List<Student> preferences = new LinkedList<>(students);
            Collections.shuffle(preferences);
            schoolPreferences.put(school, preferences);
        }

        System.out.println("Student preferences:");
        System.out.println(studentPreferences);

        System.out.println("School preferences:");
        System.out.println(schoolPreferences);
    }
}
