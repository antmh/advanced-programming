package lab4;

import lab4.algorithm.Algorithm;
import lab4.algorithm.ScoreBasedAlgorithm;
import lab4.example.FakeProblemFactory;
import lab4.example.PreferencesExample;

public class Main {

    public static void main(String[] args) {
        System.out.println("Student preferences:");
        System.out.println(PreferencesExample.getStudentPreferences());

        System.out.println("School preferences:");
        System.out.println(PreferencesExample.getSchoolPreferences());
        
        Problem problem = FakeProblemFactory.getProblem();
        System.out.println("Fake problem:");
        System.out.println(problem);
        Algorithm algorithm = new ScoreBasedAlgorithm();
        System.out.println("Fake problem solution:");
        System.out.println(algorithm.solve(problem));
    }
}
