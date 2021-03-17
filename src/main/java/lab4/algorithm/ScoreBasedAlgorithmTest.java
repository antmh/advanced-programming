package lab4.algorithm;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import lab4.Problem;
import lab4.School;
import lab4.Student;

class ScoreBasedAlgorithmTest {
    @Test
    void studentWithNoPreferences() {
        var problem = new Problem();
        var school = new School("a", 1);
        problem.addSchool(school);
        problem.addStudent(new Student("b", "b", 100));
        Algorithm algorithm = new ScoreBasedAlgorithm();
        var solution = algorithm.solve(problem);
        assertEquals(0, solution.getStudentsOfSchool(school).size());
    }
    
    @Test
    void schoolAtCapacity() {
        var problem = new Problem();
        var school = new School("a", 0);
        problem.addSchool(school);
        var student = new Student("b", "b", 100);
        problem.addStudent(student);
        problem.addStudentPreference(student, school, 0);
        Algorithm algorithm = new ScoreBasedAlgorithm();
        var solution = algorithm.solve(problem);
        assertEquals(0, solution.getStudentsOfSchool(school).size());
    }
    
    @Test
    void elligibleStudent() {
        var problem = new Problem();
        var school = new School("a", 1);
        problem.addSchool(school);
        var student = new Student("b", "b", 0);
        problem.addStudent(student);
        problem.addStudentPreference(student, school, 0);
        Algorithm algorithm = new ScoreBasedAlgorithm();
        var solution = algorithm.solve(problem);
        var students = solution.getStudentsOfSchool(school);
        assertEquals(1, students.size());
        assertTrue(students.contains(student));
    }
    
    @Test
    void oneOutOfTwoElligibleStudents() {
        var problem = new Problem();
        var school = new School("a", 1);
        problem.addSchool(school);
        var betterStudent = new Student("b", "b", 51);
        problem.addStudent(betterStudent);
        problem.addStudentPreference(betterStudent, school, 0);
        var worseStudent = new Student("c", "c", 50);
        problem.addStudent(worseStudent);
        problem.addStudentPreference(worseStudent, school, 0);
        Algorithm algorithm = new ScoreBasedAlgorithm();
        var solution = algorithm.solve(problem);
        var students = solution.getStudentsOfSchool(school);
        assertEquals(1, students.size());
        assertTrue(students.contains(betterStudent));
    }
    
    @Test
    void equallyGoodStudents() {
        var problem = new Problem();
        var school = new School("a", 1);
        problem.addSchool(school);
        var firstStudent = new Student("b", "b", 50);
        problem.addStudent(firstStudent);
        problem.addStudentPreference(firstStudent, school, 0);
        var secondStudent = new Student("c", "c", 50);
        problem.addStudent(secondStudent);
        problem.addStudentPreference(secondStudent, school, 0);
        Algorithm algorithm = new ScoreBasedAlgorithm();
        var solution = algorithm.solve(problem);
        var students = solution.getStudentsOfSchool(school);
        assertEquals(1, students.size());
        assertTrue(students.contains(firstStudent) || students.contains(secondStudent));
    }
    
    @Test
    void studentWithSecondOption() {
        var problem = new Problem();
        var firstSchool = new School("a", 0);
        problem.addSchool(firstSchool);
        var secondSchool = new School("a", 1);
        problem.addSchool(secondSchool);
        var student = new Student("b", "b", 100);
        problem.addStudent(student);
        problem.addStudentPreference(student, firstSchool, 0);
        problem.addStudentPreference(student, secondSchool, 1);
        Algorithm algorithm = new ScoreBasedAlgorithm();
        var solution = algorithm.solve(problem);
        assertEquals(0, solution.getStudentsOfSchool(firstSchool).size());
        var secondSchoolStudents = solution.getStudentsOfSchool(secondSchool);
        assertEquals(1, secondSchoolStudents.size());
        assertTrue(secondSchoolStudents.contains(student));
    }
    
    @Test
    void betterStudentThanOneWithSecondOption() {
        var problem = new Problem();
        var firstSchool = new School("a", 1);
        problem.addSchool(firstSchool);
        var secondSchool = new School("b", 1);
        problem.addSchool(secondSchool);
        var betterStudent = new Student("c", "c", 51);
        problem.addStudent(betterStudent);
        problem.addStudentPreference(betterStudent, firstSchool, 0);
        problem.addStudentPreference(betterStudent, secondSchool, 1);
        var worseStudent = new Student("d", "d", 50);
        problem.addStudent(worseStudent);
        problem.addStudentPreference(worseStudent, firstSchool, 0);
        problem.addStudentPreference(worseStudent, secondSchool, 1);
        Algorithm algorithm = new ScoreBasedAlgorithm();
        var solution = algorithm.solve(problem);
        var firstSchoolStudents = solution.getStudentsOfSchool(firstSchool);
        assertEquals(1, firstSchoolStudents.size());
        assertTrue(firstSchoolStudents.contains(betterStudent));
        var secondSchoolStudents = solution.getStudentsOfSchool(secondSchool);
        assertEquals(1, secondSchoolStudents.size());
        assertTrue(secondSchoolStudents.contains(worseStudent));
    }
}
