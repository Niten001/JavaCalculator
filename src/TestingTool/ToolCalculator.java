package testingtool;

import java.util.*;
import javacalculator.*;

/**
Our Tool Calculator that can be used to compare results against a Software Under Test (SUT) calculator.
These functions make use of Java's internal Math library
**/

public class ToolCalculator {

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        return a / b;
    }

    public double inverse(double a) {
        return 1/a;
    }

    public double square(double a) {
        return a * a;
    }

    public double power(double a, int pow) {
        for(int i = 0; i < pow; i++) {
            a *= a;
        }
        return a;
    }

    public double squareRoot(double a) {
        return Math.sqrt(a);
    }

    public double log(double a) {
        return Math.log10(a);
    }

    public double rate(double a) {
        return a/100;
    }

    public double sin(double a) {
        return Math.sin(a);
    }

    public double cos(double a) {
        return Math.cos(a);
    }

    public double tan(double a) {
        return Math.tan(a);
    }

    public double abs(double a) {
        return Math.abs(a);
    }

    public String binary(int a) {
        return Integer.toBinaryString(a);
    }
}
