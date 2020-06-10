package testingtool;

import java.util.*;
import javacalculator.*;

public class TestingTool {
    private UI uiCalculator;

    private AddTest addTest;
    private SubtractTest subtractTest;
    private PowerTest powerTest;
    private MultiplyTest multiplyTest;
    private DivideTest divideTest;
    private LogTest logTest;
    private SinTest sinTest;
    private CosTest cosTest;
    private TanTest tanTest;
    private TrigTest trigTest;
    private RootTest rootTest;
    private InverseTest inverseTest;

    private final double e = 0.000001;  //  e = 1x10^-6

    public TestingTool(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
    }

    public void runTests() {
        //  We could potentially make this less "repetettive" if we make a parent class with
        //  these functions templated, that the test classes then extend.

        //  Run all tests for the Add function
            this.addTest = new AddTest(this.uiCalculator);
            Map<String, boolean[]> addTests = this.addTest.testAll();
            for (Map.Entry<String, boolean[]> test : addTests.entrySet()) {
                logTestResults(test.getKey(), test.getValue());
            }
            System.out.println();

        //  Run all tests for the Subtract function
            this.subtractTest = new SubtractTest(this.uiCalculator);
            Map<String, boolean[]> subtractTests = this.subtractTest.testAll();
            for (Map.Entry<String, boolean[]> test : subtractTests.entrySet()) {
                logTestResults(test.getKey(), test.getValue());
            }
            System.out.println();

        //  Run all tests for the Power function
            this.powerTest = new PowerTest(this.uiCalculator);
            Map<String, boolean[]> powerTests = this.powerTest.testAll();
            for (Map.Entry<String, boolean[]> test : powerTests.entrySet()) {
                logTestResults(test.getKey(), test.getValue());
            }
            System.out.println();

        //  Run all tests for the Multiply function
            this.multiplyTest = new MultiplyTest(this.uiCalculator);
            Map<String, boolean[]> multiplyTests = this.multiplyTest.testAll();
            for (Map.Entry<String, boolean[]> test : multiplyTests.entrySet()) {
                logTestResults(test.getKey(), test.getValue());
            }
            System.out.println();

        //  Run all tests for the Divide function
            /*
            this.divideTest = new DivideTest(this.uiCalculator);
            Map<String, boolean[]> divideTests = this.divideTest.testAll();
            for (Map.Entry<String, boolean[]> test : divideTests.entrySet()) {
                logTestResults(test.getKey(), test.getValue());
            }
            System.out.println();
            */

        //  Run all tests for the Logarithm function
            this.logTest = new LogTest(this.uiCalculator);
            Map<String, boolean[]> logTests = this.logTest.testAll();
            for (Map.Entry<String, boolean[]> test : logTests.entrySet()) {
                logTestResults(test.getKey(), test.getValue());
            }
            System.out.println();

        //  Run all tests for the Sine function
            this.sinTest = new SinTest(this.uiCalculator);
            Map<String, boolean[]> sinTests = this.sinTest.testAll();
            for (Map.Entry<String, boolean[]> test : sinTests.entrySet()) {
                logTestResults(test.getKey(), test.getValue());
            }
            System.out.println();

        //  Run all tests for the Cosine function
            this.cosTest = new CosTest(this.uiCalculator);
            Map<String, boolean[]> cosTests = this.cosTest.testAll();
            for (Map.Entry<String, boolean[]> test : cosTests.entrySet()) {
                logTestResults(test.getKey(), test.getValue());
            }
            System.out.println();

        //  Run all tests for the Tangent function
            this.tanTest = new TanTest(this.uiCalculator);
            Map<String, boolean[]> tanTests = this.tanTest.testAll();
            for (Map.Entry<String, boolean[]> test : tanTests.entrySet()) {
                logTestResults(test.getKey(), test.getValue());
            }
            System.out.println();

        //  Run all tests for the combined Trigonometric functions
            this.trigTest = new TrigTest(this.uiCalculator);
            Map<String, boolean[]> trigTests = this.trigTest.testAll();
            for (Map.Entry<String, boolean[]> test : trigTests.entrySet()) {
                logTestResults(test.getKey(), test.getValue());
            }
            System.out.println();

            //  Run all tests for the combined Trigonometric functions
            this.rootTest = new RootTest(this.uiCalculator);
            Map<String, boolean[]> rootTests = this.rootTest.testAll();
            for (Map.Entry<String, boolean[]> test : rootTests.entrySet()) {
                logTestResults(test.getKey(), test.getValue());
            }
            System.out.println();

            //  Run all tests for the combined Trigonometric functions
            this.inverseTest = new InverseTest(this.uiCalculator);
            Map<String, boolean[]> inverseTests = this.inverseTest.testAll();
            for (Map.Entry<String, boolean[]> test : inverseTests.entrySet()) {
                logTestResults(test.getKey(), test.getValue());
            }
            System.out.println();
    }

    private void logTestResults(String testName, boolean[] testResults) {
        int numPassed = 0;

        for (boolean test : testResults) {
            if (test) { numPassed++; }
        }

        System.out.println("The test of " + testName + " passed " + numPassed + "/" + testResults.length + " tests.");
    }

    //  Helper functions
        //  Enters a value into the calculator
            public void enterCalculatorInput(double num) {
                uiCalculator.text.setText(String.valueOf(num));
            }

        //  Returns whatever is currently displayed in the calculator output
            public double getCalculatorOutput() {
                return Double.valueOf(uiCalculator.text.getText());
            }

        //  Returns true if the two numbers are within the range e
            public boolean checkFuzzyEqual(double a, double b) {
                return ((a - b < e) && (a - b > -e));
            }
}
