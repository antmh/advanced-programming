package lab4.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.javafaker.Faker;

import lab4.Problem;
import lab4.School;
import lab4.Student;

public class FakeProblemFactory {
    private static Faker faker;

    public static Problem getProblem() {
        faker = new Faker();
        Problem problem = new Problem();
        List<Student> students = getStudents();
        List<School> schools = getSchools();
        for (var student : students) {
            problem.addStudent(student);
            Collections.shuffle(schools);
            for (var school : schools) {
                if (faker.bool().bool()) {
                    problem.addStudentPreference(student, school);
                }
            }
        }
        for (var school : schools) {
            problem.addSchool(school);
            Collections.shuffle(students);
            for (var student : students) {
                if (faker.bool().bool()) {
                    problem.addSchoolPreference(school, student);
                }
            }
        }
        return problem;
    }

    private static List<Student> getStudents() {
        int studentsNo = faker.number().numberBetween(1, 20);
        List<Student> students = new ArrayList<>(studentsNo);
        for (int i = 0; i < studentsNo; ++i) {
            var name = faker.name();
            students.add(new Student(name.firstName(), name.lastName()));
        }
        return students;
    }
    
    private static List<School> getSchools() {
        int schoolsNo = faker.number().numberBetween(1, 5);
        List<School> schools = new ArrayList<>(schoolsNo);
        for (int i = 0; i < schoolsNo; ++i) {
            var university = faker.university().name();
            var capacity = faker.number().numberBetween(0, 10);
            schools.add(new School(university, capacity));
        }
        return schools;
    }
}
