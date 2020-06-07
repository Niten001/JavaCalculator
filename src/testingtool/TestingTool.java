package testingtool;

import java.util.*;
import javacalculator.*;

public class TestingTool {
    private UI uiCalculator;

    private MultiplyTest multiplyTest;
    private DivideTest divideTest;
    private LogTest logTest;
    private SinTest sinTest;
    private CosTest cosTest;
    private TanTest tanTest;

    public TestingTool(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
    }

    public void runTests() {
        //  We could potentially make this less "repetettive" if we make a parent class with 
        //  these functions templated, that the test classes then extend.

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
}
