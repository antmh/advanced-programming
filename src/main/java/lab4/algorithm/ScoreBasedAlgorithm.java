package lab4.algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import lab4.Problem;
import lab4.Solution;
import lab4.Student;

public class ScoreBasedAlgorithm implements Algorithm {

    @Override
    public Solution solve(Problem problem) {
        List<Student> studentsByScore = problem.getStudents().stream()
                .sorted(Comparator.comparing(Student::getScore).reversed())
                .collect(Collectors.toCollection(ArrayList::new));
        var solution = new Solution();
        for (var school : problem.getSchools()) {
            solution.addSchool(school);
        }
        for (var student : studentsByScore) {
            for (var preferredSchool : problem.getPreferredSchools(student)) {
                if (preferredSchool.getCapacity() > solution.getStudentsOfSchool(preferredSchool).size()) {
                    solution.addStudentToSchool(preferredSchool, student);
                    break;
                }
            }
        }
        return solution;
    }

}
