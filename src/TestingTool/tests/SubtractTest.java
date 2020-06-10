package testingtool;

import java.util.*;
import javacalculator.*;

public class SubtractTest {
    private UI uiCalculator;
    private TestingTool testingTool;

    public SubtractTest(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
        this.testingTool = new TestingTool(this.uiCalculator);
    }

    //  Test Specific Algebraic Values
    public boolean[] testAlgebraicValues() {
        //  Array of [Values to be tested, expected results]
        double[][] testedValues = {
            { 1, 0, 1 },
            { 10, 3, 7 },
            { 7, 21, -14 },
            { -6, 3, -9 },
            { 6.7, 8.0, -1.3 },
            { -2, -20, 18 },
            { 0.2, -3.0, 3.2 },
            { 12432, 323, 12109 },
            { 5, -3, 8 }
        };

        boolean[] numPassed = new boolean[testedValues.length];

        for (int i = 0; i < testedValues.length; i++) {
            this.testingTool.enterCalculatorInput(testedValues[i][0]);
            this.uiCalculator.butMinus.doClick();
            this.testingTool.enterCalculatorInput(testedValues[i][1]);
            this.uiCalculator.butEqual.doClick();
            numPassed[i] = this.testingTool.checkFuzzyEqual(this.testingTool.getCalculatorOutput(), testedValues[i][2]);
            this.uiCalculator.butCancel.doClick();
        }

        return numPassed;
    }

    //  Test Anticommutativity
    public boolean[] testAnticommutativity(int numAttempts) {
        boolean[] numPassed = new boolean[numAttempts];
        Random rand = new Random();

        for(int i = 0; i < numAttempts; i++) {
            int a = rand.nextInt();
            int b = rand.nextInt();

            // Do a - b
            this.testingTool.enterCalculatorInput(a);
            uiCalculator.butMinus.doClick();
            this.testingTool.enterCalculatorInput(b);
            uiCalculator.butEqual.doClick();
            double output1 = this.testingTool.getCalculatorOutput();
            uiCalculator.butCancel.doClick();

            // Do -(b-a)
            this.testingTool.enterCalculatorInput(b);
            uiCalculator.butMinus.doClick();
            this.testingTool.enterCalculatorInput(a);
            uiCalculator.butEqual.doClick();
            double output2 = -1 * this.testingTool.getCalculatorOutput();

            // Anticommutativity relation: a - b = -(b - a)
            numPassed[i] = this.testingTool.checkFuzzyEqual(output1, output2);
        }

        return numPassed;
    }

    public boolean[] testAnticommutativity() {
        return testAnticommutativity(20);
    }

    //  Test Identity
    public boolean[] testIdentity(int numAttempts) {
        boolean[] numPassed = new boolean[numAttempts];
        Random rand = new Random();

        for(int i = 0; i < numAttempts; i++) {
            int a = rand.nextInt();

            // Do a - a
            this.testingTool.enterCalculatorInput(a);
            uiCalculator.butMinus.doClick();
            this.testingTool.enterCalculatorInput(a);
            uiCalculator.butEqual.doClick();
            double output = this.testingTool.getCalculatorOutput();
            uiCalculator.butCancel.doClick();

            //  Identity relation: a - a = 0
            numPassed[i] = this.testingTool.checkFuzzyEqual(output, 0);
        }

        return numPassed;
    }

    public boolean[] testIdentity() {
        return testIdentity(20);
    }

    //Test the SUT's calculator result with our tool's calculator resut (N-version programming)
    public boolean[] testNProgramming(int numAttempts) {
        boolean[] numPassed = new boolean[numAttempts];
        Random rand = new Random();

        for(int i = 0; i < numAttempts; i++) {
            int a = rand.nextInt();
            int b = rand.nextInt();

            //Software under test result
            this.testingTool.enterCalculatorInput(a);
            uiCalculator.butMinus.doClick();
            this.testingTool.enterCalculatorInput(b);
            uiCalculator.butEqual.doClick();
            double sutResult = this.testingTool.getCalculatorOutput();
            uiCalculator.butCancel.doClick();

            //Our tool's result
            double toolResult = this.testingTool.getToolCalculator().subtract(a, b);

            numPassed[i] = this.testingTool.checkFuzzyEqual(sutResult, toolResult);
        }

        return numPassed;
    }

    public boolean[] testNProgramming() {
        return testNProgramming(20);
    }

    //  Test All
    public Map<String, boolean[]> testAll() {
        Map<String, boolean[]> output = new HashMap<String, boolean[]>();

        output.put("Subtraction function specific values", this.testAlgebraicValues());
        output.put("Subtraction function anticommutativity", this.testAnticommutativity());
        output.put("Subtraction function identity", this.testIdentity());
        output.put("Subtraction function n-version programming test", this.testNProgramming());

        return output;
    }
}
