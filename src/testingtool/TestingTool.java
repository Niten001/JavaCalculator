package testingtool;

import java.util.*;
import javacalculator.*;

public class TestingTool {

    public final int NUM_TESTS = 100;

    private CalculatorTest calcTest;
    private UI uiCalculator;

    public TestingTool(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
        this.calcTest = new CalculatorTest(uiCalculator);
        this.runTests();
    }

    private void runTests() {
        //Test multiply functionality and then log the results
        int numPassedMultiply = calcTest.testMultiply(NUM_TESTS, RelationTransform.PERMUTE, RelationTransform.EQUAL);
        this.logTestResults("Multiply", numPassedMultiply, NUM_TESTS - numPassedMultiply);
        this.reset();
        //Test division functionality and then log the results
        int numPassedDivide = calcTest.testDivide(NUM_TESTS, null, RelationTransform.INEQUALITY);
        this.logTestResults("Divide", numPassedDivide, NUM_TESTS - numPassedDivide);
        this.reset();
        //Test division functionality and then log the results
        int numPassedLogarithm = calcTest.testLogarithm(NUM_TESTS, null, RelationTransform.INEQUALITY);
        this.logTestResults("Logarithm", numPassedLogarithm, NUM_TESTS - numPassedLogarithm);
        this.reset();
    }

    /**
    Log the results of a test
    **/
    private void logTestResults(String testName, int numPassed, int numFailed) {
        int totalTests = numPassed + numFailed;
        System.out.println("Completed test for " + testName + " - Passed: " + numPassed + ", Failed: " + numFailed + ", Total: " + totalTests);
    }

    /**
    Resets the calculator. Must be done at the end of every test!
    **/
    public void reset() {
        this.uiCalculator.butCancel.doClick();
    }
}
