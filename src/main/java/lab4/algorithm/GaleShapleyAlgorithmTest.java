package lab4.algorithm;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import lab4.Problem;
import lab4.School;
import lab4.Student;

class GaleShapleyAlgorithmTest {
    @Test
    void perfectMatch() {
        var problem = new Problem();
        var students = new Student[10];
        var schools = new School[10];
        for (int i = 0; i < 10; ++i) {
            students[i] = new Student("s" + i, "s" + i);
            problem.addStudent(students[i]);
            schools[i] = new School("s" + i, 10);
            problem.addSchool(schools[i]);
        }
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; ++j) {
                problem.addSchoolPreference(schools[i], students[j], i == j ? 0 : 1);
                problem.addStudentPreference(students[i], schools[j], i == j ? 0 : 1);
            }
        }
        Algorithm algorithm = new GaleShapleyAlgorithm();
        var solution = algorithm.solve(problem);
        for (int i = 0; i < 10; ++i) {
            assertTrue(solution.getStudentsOfSchool(schools[i]).contains(students[i]));
        }
    }
    
    @Test
    void studentWithTwoBackups() {
        var problem = new Problem();
        var student = new Student("a", "a");
        problem.addStudent(student);
        var firstChoice = new School("b", 0);
        problem.addSchool(firstChoice);
        problem.addSchoolPreference(firstChoice, student, 0);
        problem.addStudentPreference(student, firstChoice, 0);
        var backups = new School[2];
        for (int i = 0; i < 2; ++i) {
            backups[i] = new School("c" + i, 1);
            problem.addSchool(backups[i]);
            problem.addSchoolPreference(backups[i], student, 0);
            problem.addStudentPreference(student, backups[i], 1);
        }
        Algorithm algorithm = new GaleShapleyAlgorithm();
        var solution = algorithm.solve(problem);
        assertFalse(solution.hasSchool(firstChoice));
        assertTrue(solution.hasSchool(backups[0]) ^ solution.hasSchool(backups[1]));
        if (solution.hasSchool(backups[0])) {
            assertTrue(solution.getStudentsOfSchool(backups[0]).contains(student));
        } else {
            assertTrue(solution.getStudentsOfSchool(backups[1]).contains(student));
        }
    }

    @Test
    void studentWithNoPreferences() {
        var problem = new Problem();
        var school = new School("a", 1);
        problem.addSchool(school);
        var student = new Student("b", "b");
        problem.addStudent(student);
        problem.addSchoolPreference(school, student, 0);
        Algorithm algorithm = new GaleShapleyAlgorithm();
        var solution = algorithm.solve(problem);
        assertFalse(solution.hasSchool(school));
    }

    @Test
    void schoolAtCapacity() {
        var problem = new Problem();
        var school = new School("a", 0);
        problem.addSchool(school);
        var student = new Student("b", "b");
        problem.addStudent(student);
        problem.addStudentPreference(student, school, 0);
        problem.addSchoolPreference(school, student, 0);
        Algorithm algorithm = new GaleShapleyAlgorithm();
        var solution = algorithm.solve(problem);
        assertFalse(solution.hasSchool(school));
    }

    @Test
    void eligibleStudent() {
        var problem = new Problem();
        var school = new School("a", 1);
        problem.addSchool(school);
        var student = new Student("b", "b", 0);
        problem.addStudent(student);
        problem.addStudentPreference(student, school, 0);
        problem.addSchoolPreference(school, student, 0);
        Algorithm algorithm = new GaleShapleyAlgorithm();
        var solution = algorithm.solve(problem);
        var students = solution.getStudentsOfSchool(school);
        assertEquals(1, students.size());
        assertTrue(students.contains(student));
    }

    @Test
    void oneOutOfTwoEligibleStudents() {
        var problem = new Problem();
        var school = new School("a", 1);
        problem.addSchool(school);
        var betterStudent = new Student("b", "b");
        problem.addStudent(betterStudent);
        problem.addStudentPreference(betterStudent, school, 0);
        var worseStudent = new Student("c", "c");
        problem.addStudent(worseStudent);
        problem.addStudentPreference(worseStudent, school, 0);
        problem.addSchoolPreference(school, betterStudent, 0);
        problem.addSchoolPreference(school, worseStudent, 1);
        Algorithm algorithm = new GaleShapleyAlgorithm();
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
        var firstStudent = new Student("b", "b");
        problem.addStudent(firstStudent);
        problem.addStudentPreference(firstStudent, school, 0);
        var secondStudent = new Student("c", "c");
        problem.addStudent(secondStudent);
        problem.addStudentPreference(secondStudent, school, 0);
        problem.addSchoolPreference(school, firstStudent, 0);
        problem.addSchoolPreference(school, secondStudent, 0);
        Algorithm algorithm = new GaleShapleyAlgorithm();
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
        var student = new Student("b", "b");
        problem.addStudent(student);
        problem.addStudentPreference(student, firstSchool, 0);
        problem.addStudentPreference(student, secondSchool, 1);
        problem.addSchoolPreference(firstSchool, student, 0);
        problem.addSchoolPreference(secondSchool, student, 0);
        Algorithm algorithm = new GaleShapleyAlgorithm();
        var solution = algorithm.solve(problem);
        assertFalse(solution.hasSchool(firstSchool));
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
        var betterStudent = new Student("c", "c");
        problem.addStudent(betterStudent);
        problem.addStudentPreference(betterStudent, firstSchool, 0);
        problem.addStudentPreference(betterStudent, secondSchool, 1);
        problem.addSchoolPreference(firstSchool, betterStudent, 0);
        problem.addSchoolPreference(secondSchool, betterStudent, 0);
        var worseStudent = new Student("d", "d");
        problem.addStudent(worseStudent);
        problem.addStudentPreference(worseStudent, firstSchool, 0);
        problem.addStudentPreference(worseStudent, secondSchool, 1);
        problem.addSchoolPreference(firstSchool, worseStudent, 1);
        problem.addSchoolPreference(secondSchool, worseStudent, 1);
        Algorithm algorithm = new GaleShapleyAlgorithm();
        var solution = algorithm.solve(problem);
        var firstSchoolStudents = solution.getStudentsOfSchool(firstSchool);
        assertEquals(1, firstSchoolStudents.size());
        assertTrue(firstSchoolStudents.contains(betterStudent));
        var secondSchoolStudents = solution.getStudentsOfSchool(secondSchool);
        assertEquals(1, secondSchoolStudents.size());
        assertTrue(secondSchoolStudents.contains(worseStudent));
    }
}
