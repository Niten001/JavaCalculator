package testingtool;

import java.io.*;
import java.util.*;
import calculator.*;

public class TestingTool {
    private UI uiCalculator;

    private AddTest addTest;
    private SubtractTest subtractTest;
    private MultiplyTest multiplyTest;
    private DivideTest divideTest;
    private SinTest sinTest;
    private CosTest cosTest;
    private TanTest tanTest;
    private TrigTest trigTest;
    private RootTest rootTest;
    private SquareTest squareTest;
    private PowerTest powerTest;
    private InverseTest inverseTest;
    private RateTest rateTest;
    private LogTest logTest;
    private AbsTest absTest;
    private BinaryTest binTest;

    private final double e = 0.000001;  //  e = 1x10^-6

    public TestingTool(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
    }

    public void runTests() {
        try {
            File output = new File("output.txt");
            output.createNewFile();
            FileWriter writer = new FileWriter("output.txt");
            writer.write("The full test results for the testing tool of JavaCalculator.\n");
            writer.write("The test results are given in the form:\n");
            writer.write("INPUT1    INPUT2(Optional)    EXPECTED_VALUE    ACTUAL_VALUE    PASS/FAIL\n");
            writer.write("------------------------------\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //  We could potentially make this less "repetettive" if we make a parent class with
        //  these functions templated, that the test classes then extend.

        //  Run all tests for the Add function
            this.addTest = new AddTest(this.uiCalculator);
            Map<String, String[][]> addTests = this.addTest.testAll();
            for (Map.Entry<String, String[][]> test : addTests.entrySet()) {
                logTestResults(test.getKey(), test.getValue());
                printTestResults(test.getKey(), test.getValue());
            }
            System.out.println();

        //  Run all tests for the Subtract function
            this.subtractTest = new SubtractTest(this.uiCalculator);
            Map<String, String[][]> subtractTests = this.subtractTest.testAll();
            for (Map.Entry<String, String[][]> test : subtractTests.entrySet()) {
                logTestResults(test.getKey(), test.getValue());
                printTestResults(test.getKey(), test.getValue());
            }
            System.out.println();

        //  Run all tests for the Multiply function
            this.multiplyTest = new MultiplyTest(this.uiCalculator);
            Map<String, String[][]> multiplyTests = this.multiplyTest.testAll();
            for (Map.Entry<String, String[][]> test : multiplyTests.entrySet()) {
                logTestResults(test.getKey(), test.getValue());
                printTestResults(test.getKey(), test.getValue());
            }
            System.out.println();

        //  Run all tests for the Divide function
            this.divideTest = new DivideTest(this.uiCalculator);
            Map<String, String[][]> divideTests = this.divideTest.testAll();
            for (Map.Entry<String, String[][]> test : divideTests.entrySet()) {
                logTestResults(test.getKey(), test.getValue());
                printTestResults(test.getKey(), test.getValue());
            }
            System.out.println();

        //  Run all tests for the Sine function
            this.sinTest = new SinTest(this.uiCalculator);
            Map<String, String[][]> sinTests = this.sinTest.testAll();
            for (Map.Entry<String, String[][]> test : sinTests.entrySet()) {
                logTestResults(test.getKey(), test.getValue());
                printTestResults(test.getKey(), test.getValue());
            }
            System.out.println();

        //  Run all tests for the Cosine function
            this.cosTest = new CosTest(this.uiCalculator);
            Map<String, String[][]> cosTests = this.cosTest.testAll();
            for (Map.Entry<String, String[][]> test : cosTests.entrySet()) {
                logTestResults(test.getKey(), test.getValue());
                printTestResults(test.getKey(), test.getValue());
            }
            System.out.println();

        //  Run all tests for the Tangent function
            this.tanTest = new TanTest(this.uiCalculator);
            Map<String, String[][]> tanTests = this.tanTest.testAll();
            for (Map.Entry<String, String[][]> test : tanTests.entrySet()) {
                logTestResults(test.getKey(), test.getValue());
                printTestResults(test.getKey(), test.getValue());
            }
            System.out.println();

        //  Run all tests for the combined Trigonometric functions
            this.trigTest = new TrigTest(this.uiCalculator);
            Map<String, String[][]> trigTests = this.trigTest.testAll();
            for (Map.Entry<String, String[][]> test : trigTests.entrySet()) {
                logTestResults(test.getKey(), test.getValue());
                printTestResults(test.getKey(), test.getValue());
            }
            System.out.println();

        //  Run all tests for the Root functions
            this.rootTest = new RootTest(this.uiCalculator);
            Map<String, String[][]> rootTests = this.rootTest.testAll();
            for (Map.Entry<String, String[][]> test : rootTests.entrySet()) {
                logTestResults(test.getKey(), test.getValue());
                printTestResults(test.getKey(), test.getValue());
            }
            System.out.println();

        //  Run all tests for the Square functions
            this.squareTest = new SquareTest(this.uiCalculator);
            Map<String, String[][]> squareTests = this.squareTest.testAll();
            for (Map.Entry<String, String[][]> test : squareTests.entrySet()) {
                logTestResults(test.getKey(), test.getValue());
                printTestResults(test.getKey(), test.getValue());
            }
            System.out.println();

        //  Run all tests for the Power function
            this.powerTest = new PowerTest(this.uiCalculator);
            Map<String, String[][]> powerTests = this.powerTest.testAll();
            for (Map.Entry<String, String[][]> test : powerTests.entrySet()) {
                logTestResults(test.getKey(), test.getValue());
                printTestResults(test.getKey(), test.getValue());
            }
            System.out.println();

        //  Run all tests for the Inverse functions
            this.inverseTest = new InverseTest(this.uiCalculator);
            Map<String, String[][]> inverseTests = this.inverseTest.testAll();
            for (Map.Entry<String, String[][]> test : inverseTests.entrySet()) {
                logTestResults(test.getKey(), test.getValue());
                printTestResults(test.getKey(), test.getValue());
            }
            System.out.println();

        //  Run all tests for the Rate function
            this.rateTest = new RateTest(this.uiCalculator);
            Map<String, String[][]> rateTests = this.rateTest.testAll();
            for (Map.Entry<String, String[][]> test : rateTests.entrySet()) {
                logTestResults(test.getKey(), test.getValue());
                printTestResults(test.getKey(), test.getValue());
            }
            System.out.println();

        //  Run all tests for the Logarithm function
            this.logTest = new LogTest(this.uiCalculator);
            Map<String, String[][]> logTests = this.logTest.testAll();
            for (Map.Entry<String, String[][]> test : logTests.entrySet()) {
                logTestResults(test.getKey(), test.getValue());
                printTestResults(test.getKey(), test.getValue());
            }
            System.out.println();
        
        //  Run all tests for the Absolute Value function
            this.absTest = new AbsTest(this.uiCalculator);
            Map<String, String[][]> absTests = this.absTest.testAll();
            for (Map.Entry<String, String[][]> test : absTests.entrySet()) {
                logTestResults(test.getKey(), test.getValue());
                printTestResults(test.getKey(), test.getValue());
            }
            System.out.println();

        //  Run all tests for the Binary function
            this.binTest = new BinaryTest(this.uiCalculator);
            Map<String, String[][]> binTests = this.binTest.testAll();
            for (Map.Entry<String, String[][]> test : binTests.entrySet()) {
                logTestResults(test.getKey(), test.getValue());
                printTestResults(test.getKey(), test.getValue());
            }
            System.out.println();
    }

    private void logTestResults(String testName, String[][] testResults) {
        int numPassed = 0;

        for (String[] test : testResults) {
            if (test[5] == "PASS") { numPassed++; }
        }

        System.out.println("The test of " + testName + " passed " + numPassed + "/" + testResults.length + " tests.");
    }

    private void printTestResults(String testName, String[][] testResults) {
        int numPassed = 0;

        for (String[] test : testResults) {
            if (test[5] == "PASS") { numPassed++; }
        }

        try {
            FileWriter writer = new FileWriter("output.txt", true);
            writer.write("The test of " + testName + " passed " + numPassed + "/" + testResults.length + " tests.\n");

            for (String[] test : testResults) {
                writer.write(test[0] + "    " + ((test[1] == null) ? "" : (test[1] + "    ")) + ((test[2] == null) ? "" : (test[2] + "    ")) + test[3] + "    " + test[4] + "    " + test[5] + "\n");
            }

            writer.write("\n");

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
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
        
        //  Places the output of the test function into the appropriate format
            public String[] insertValues(double input1, double input2, double input3, double expected, double actual, boolean d) {
                String[] output = new String[6];

                output[0] = String.valueOf(input1);
                output[1] = String.valueOf(input2);
                output[2] = String.valueOf(input2);
                output[3] = String.valueOf(expected);
                output[4] = String.valueOf(actual);
                if (d) {
                    output[5] = "PASS";
                } else {
                    output[5] = "FAIL";
                }

                return output;
            }

            public String[] insertValues(double input1, double input2, double expected, double actual, boolean d) {
                String[] output = new String[6];

                output[0] = String.valueOf(input1);
                output[1] = String.valueOf(input2);
                output[3] = String.valueOf(expected);
                output[4] = String.valueOf(actual);
                if (d) {
                    output[5] = "PASS";
                } else {
                    output[5] = "FAIL";
                }

                return output;
            }

            public String[] insertValues(double input1, double expected, double actual, boolean d) {
                String[] output = new String[6];

                output[0] = String.valueOf(input1);
                output[3] = String.valueOf(expected);
                output[4] = String.valueOf(actual);
                if (d) {
                    output[5] = "PASS";
                } else {
                    output[5] = "FAIL";
                }

                return output;
            }

            public String[] insertValues(double input1, String expected, String actual, boolean d) {
                String[] output = new String[6];

                output[0] = String.valueOf(input1);
                output[3] = expected;
                output[4] = actual;
                if (d) {
                    output[5] = "PASS";
                } else {
                    output[5] = "FAIL";
                }

                return output;
            }
}
