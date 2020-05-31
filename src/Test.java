package javacalculator;

import java.util.*;

public class Test {
    public static void runTests(UI uiCalculator) {
        test1(uiCalculator);
    }

    // Tests
    private static void test1(UI uiCalculator) {
        Random rand = new Random();
        rand.nextDouble();
        uiCalculator.text.setText(String.valueOf(rand.nextDouble()));
        uiCalculator.butAdd.doClick();
        uiCalculator.text.setText(String.valueOf(rand.nextDouble()));
        uiCalculator.butEqual.doClick();
        System.out.println(uiCalculator.text.getText());
    }
}