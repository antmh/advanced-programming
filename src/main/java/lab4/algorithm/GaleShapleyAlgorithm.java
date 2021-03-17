package lab4.algorithm;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;

import lab4.Problem;
import lab4.School;
import lab4.Solution;
import lab4.Student;

public class GaleShapleyAlgorithm implements Algorithm {
    private Problem problem;
    private Solution solution;
    private Map<Student, Queue<School>> schoolsToTry;

    @Override
    public Solution solve(Problem problem) {
        this.problem = problem;
        solution = new Solution();
        schoolsToTry = new HashMap<>();
        for (var student : problem.getStudents()) {
            schoolsToTry.put(student, new ArrayDeque<>());
            var schoolList = schoolsToTry.get(student);
            for (var school : problem.getStudentPreferences(student).getAll()) {
                if (problem.getSchoolPreferences(school).contains(student)) {
                    schoolList.add(school);
                }
            }
        }

        while (true) {
            var optionalStudent = leftOutStudentWithSchoolsToTry();
            if (optionalStudent.isEmpty()) {
                break;
            }
            var student = optionalStudent.get();
            var school = schoolsToTry.get(student).remove();
            if (!solution.hasSchool(school)) {
                if (school.getCapacity() > 0) {
                    solution.addSchool(school);
                    solution.addStudentToSchool(school, student);
                }
            } else {
                if (solution.getStudentsOfSchool(school).size() < school.getCapacity()) {
                    solution.addStudentToSchool(school, student);
                } else {
                    Student leastPreferredStudent = leastPreferredStudentOfSchool(school);
                    var preferences = problem.getSchoolPreferences(school);
                    if (preferences.getPriorityOf(leastPreferredStudent) > preferences.getPriorityOf(student)) {
                        solution.getStudentsOfSchool(school).remove(leastPreferredStudent);
                        solution.addStudentToSchool(school, student);
                    }
                }
            }
        }
        return solution;
    }

    private Optional<Student> leftOutStudentWithSchoolsToTry() {
        for (var student : schoolsToTry.keySet()) {
            if (!solution.hasStudent(student) && !schoolsToTry.get(student).isEmpty()) {
                return Optional.of(student);
            }
        }
        return Optional.empty();
    }

    private Student leastPreferredStudentOfSchool(School school) {
        var preferences = problem.getSchoolPreferences(school);
        Student result = null;
        for (var student : solution.getStudentsOfSchool(school)) {
            if (result == null || preferences.getPriorityOf(student) > preferences.getPriorityOf(result)) {
                result = student;
            }
        }
        return result;
    }
}
