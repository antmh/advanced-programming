package com.github.antmh.laboratorul1;

/**
 * @author Antonio Mihăeș
 */
public class Main {

    int digitsSum(int x) {
        int result = 0;

        while (x != 0) {
            result += x % 10;
            x /= 10;
        }

        return result;
    }

    void printLanguages() {
        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};

        int n = (int) (1_000_000 * Math.random());
        n *= 3;
        n += 0b10101;
        n += 0xFF;
        n *= 6;
        while (n > 9) {
            n = digitsSum(n);
        }

        System.out.println("This semester I will learn " + languages[n]);
    }

    public void compulsory() {
        System.out.println("Hello World!");
        printLanguages();
    }

    public static void main(String[] args) {
        new Main().compulsory();
    }
}
