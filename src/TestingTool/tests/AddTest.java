package testingtool;

import java.util.*;
import javacalculator.*;

public class AddTest {
    private UI uiCalculator;
    private TestingTool testingTool;

    public AddTest(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
        this.testingTool = new TestingTool(this.uiCalculator);
    }

    //  Test Specific Algebraic Values
    public boolean[] testAlgebraicValues() {
        //  Array of [Value 1 to be tested, Value 2 to be tested, expected result]
        double[][] testedValues = {
            { 1, 0, 1 },
            { 10, 3, 13 },
            { 7, 21, 28 },
            { -6, 3, -3 },
            { 6.7, 8.0, 14.7 },
            { -2, -20, -22 },
            { 0.2, -3.0, -2.8 },
            { 12432, 323, 12755 },
            { 5, -3, 2 }
        };

        boolean[] numPassed = new boolean[testedValues.length];

        for (int i = 0; i < testedValues.length; i++) {
            this.testingTool.enterCalculatorInput(testedValues[i][0]);
            this.uiCalculator.butAdd.doClick();
            this.testingTool.enterCalculatorInput(testedValues[i][1]);
            this.uiCalculator.butEqual.doClick();
            numPassed[i] = this.testingTool.checkFuzzyEqual(this.testingTool.getCalculatorOutput(), testedValues[i][2]);
            this.uiCalculator.butCancel.doClick();
        }

        return numPassed;
    }

    //  Test Commutativity
    public boolean[] testCommutativity(int numAttempts) {
        boolean[] numPassed = new boolean[numAttempts];
        Random rand = new Random();

        for(int i = 0; i < numAttempts; i++) {
            int a = rand.nextInt();
            int b = rand.nextInt();

            //Do a + b
            this.testingTool.enterCalculatorInput(a);
            uiCalculator.butAdd.doClick();
            this.testingTool.enterCalculatorInput(b);
            uiCalculator.butEqual.doClick();
            double output1 = this.testingTool.getCalculatorOutput();
            uiCalculator.butCancel.doClick();

            //Do b + a
            this.testingTool.enterCalculatorInput(b);
            uiCalculator.butAdd.doClick();
            this.testingTool.enterCalculatorInput(a);
            uiCalculator.butEqual.doClick();
            double output2 = this.testingTool.getCalculatorOutput();

            //  Commutativity relation: a + b = b + a
            numPassed[i] = this.testingTool.checkFuzzyEqual(output1, output2);
        }

        return numPassed;
    }

    public boolean[] testCommutativity() {
        return testCommutativity(20);
    }

    //  Test Associativity
    public boolean[] testAssociativity(int numAttempts) {
        boolean[] numPassed = new boolean[numAttempts];
        Random rand = new Random();

        for(int i = 0; i < numAttempts; i++) {
            double a = rand.nextInt();
            double b = rand.nextInt();
            double c = rand.nextInt();

            // Do (a + b) + c
            this.testingTool.enterCalculatorInput((a + b));
            uiCalculator.butAdd.doClick();
            this.testingTool.enterCalculatorInput(c);
            uiCalculator.butEqual.doClick();
            double output1 = this.testingTool.getCalculatorOutput();
            uiCalculator.butCancel.doClick();

            // Do a + (b + c)
            this.testingTool.enterCalculatorInput(a);
            uiCalculator.butAdd.doClick();
            this.testingTool.enterCalculatorInput((b + c));
            uiCalculator.butEqual.doClick();
            double output2 = this.testingTool.getCalculatorOutput();

            //  Associativity relation: (a + b) + c = a + (b + c)
            numPassed[i] = this.testingTool.checkFuzzyEqual(output1, output2);
        }

        return numPassed;
    }

    public boolean[] testAssociativity() {
        return testAssociativity(20);
    }

    //Test the SUT's calculator result with our tool's calculator resut (N-version programming)
    public boolean[] testNProgramming(int numAttempts) {
        boolean[] numPassed = new boolean[numAttempts];
        Random rand = new Random();

        for(int i = 0; i < numAttempts; i++) {
            double a = rand.nextInt();
            double b = rand.nextInt();

            //Software under test result
            this.testingTool.enterCalculatorInput(a);
            uiCalculator.butAdd.doClick();
            this.testingTool.enterCalculatorInput(b);
            uiCalculator.butEqual.doClick();
            double sutResult = this.testingTool.getCalculatorOutput();
            uiCalculator.butCancel.doClick();

            //Our tool's result
            double toolResult = this.testingTool.getToolCalculator().add(a, b);

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

        output.put("Addition function specific values", this.testAlgebraicValues());
        output.put("Addition function commutativity", this.testCommutativity());
        output.put("Addition function associativity", this.testAssociativity());
        output.put("Addition function n-version programming test", this.testNProgramming());

        return output;
    }
}
