package javacalculator;

import java.util.*;

public class Test {
    public static void runTests(UI uiCalculator) {
        test1(uiCalculator);
    }

    // Tests
    // Tim -> Cos, Sin, Tan
    private static void test1(UI uiCalculator) {
        Random rand = new Random();
        Double rand1 = rand.nextDouble();
        Double rand2 = rand.nextDouble();

        uiCalculator.text.setText(String.valueOf(rand1));
        uiCalculator.butAdd.doClick();
        uiCalculator.text.setText(String.valueOf(rand2));
        uiCalculator.butEqual.doClick();

        System.out.println(rand1);
        System.out.println(rand2);
        System.out.println(uiCalculator.text.getText());
    }
}