package lab4;

import lab4.example.FakeProblemFactory;
import lab4.example.PreferencesExample;

public class Main {

    public static void main(String[] args) {
        System.out.println("Student preferences:");
        System.out.println(PreferencesExample.getStudentPreferences());

        System.out.println("School preferences:");
        System.out.println(PreferencesExample.getSchoolPreferences());
        
        Problem problem = FakeProblemFactory.getProblem();
        System.out.println(problem);
    }
}
