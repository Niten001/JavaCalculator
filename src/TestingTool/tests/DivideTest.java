package testingtool;

import java.util.*;
import javacalculator.*;

public class DivideTest {
    private UI uiCalculator;
    private TestingTool testingTool;

    public DivideTest(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
        this.testingTool = new TestingTool(this.uiCalculator);
    }

    //  Test Specific Algebraic Values
    public boolean[] testAlgebraicValues() {
        //  Array of [Value 1 to be tested, Value 2 to be tested, expected results]
        double[][] testedValues = {
            { 10, 1, 10 },
            { 6, 2, 3 },
            { 100, 10, 10 },
            { -6, 3, -2 },
            { 6.7, 8, 0.8375 },
            { -2, -20, 0.1 },
            { 0.2, -1, -0.2 },
            { 12432, 323, 12432.0/323.0 },
            { 5, -3, 5.0/-3.0 }
        };

        boolean[] numPassed = new boolean[testedValues.length];

        for (int i = 0; i < testedValues.length; i++) {
            this.testingTool.enterCalculatorInput(testedValues[i][0]);
            this.uiCalculator.butDivide.doClick();
            this.testingTool.enterCalculatorInput(testedValues[i][1]);
            this.uiCalculator.butEqual.doClick();
            numPassed[i] = this.testingTool.checkFuzzyEqual(this.testingTool.getCalculatorOutput(), testedValues[i][2]);
            this.uiCalculator.butCancel.doClick();
        }

        return numPassed;
    }

    //  Test Inverse Identity
    public boolean[] testInverseIdentity(int numAttempts) {
        boolean[] numPassed = new boolean[numAttempts];
        Random rand = new Random();

        for(int i = 0; i < numAttempts; i++) {
            int a = rand.nextInt();
            int b = rand.nextInt();

            //Do a/b
            this.testingTool.enterCalculatorInput(a);
            uiCalculator.butDivide.doClick();
            this.testingTool.enterCalculatorInput(b);
            uiCalculator.butEqual.doClick();
            double output1 = this.testingTool.getCalculatorOutput();
            uiCalculator.butCancel.doClick();

            //Do b/a
            this.testingTool.enterCalculatorInput(b);
            uiCalculator.butDivide.doClick();
            this.testingTool.enterCalculatorInput(a);
            uiCalculator.butEqual.doClick();
            double output2 = this.testingTool.getCalculatorOutput();
            uiCalculator.butCancel.doClick();

            //  Inverse identity relation: a/b * b/a = 1
            numPassed[i] = this.testingTool.checkFuzzyEqual(output1 * output2, 1);
        }

        return numPassed;
    }

    public boolean[] testInverseIdentity() {
        return testInverseIdentity(20);
    }

    //  Test associativity
    public boolean[] testAssociativity(int numAttempts) {
        boolean[] numPassed = new boolean[numAttempts];
        Random rand = new Random();

        for(int i = 0; i < numAttempts; i++) {
            int a = rand.nextInt();
            int b = rand.nextInt();
            int c = rand.nextInt();

            //Do a/b
            this.testingTool.enterCalculatorInput(((double)(a)/(double)(b)));
            uiCalculator.butDivide.doClick();
            this.testingTool.enterCalculatorInput(c);
            uiCalculator.butEqual.doClick();
            double output1 = this.testingTool.getCalculatorOutput();
            uiCalculator.butCancel.doClick();

            //Do b/a
            this.testingTool.enterCalculatorInput(a);
            uiCalculator.butDivide.doClick();
            this.testingTool.enterCalculatorInput((double)(b) * (double)(c));
            uiCalculator.butEqual.doClick();
            double output2 = this.testingTool.getCalculatorOutput();
            uiCalculator.butCancel.doClick();

            //  Associativity relation: (a/b)/c = a/(b*c)
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
            double a = rand.nextDouble();
            double b = rand.nextDouble();

            //Software under test result
            this.testingTool.enterCalculatorInput(a);
            uiCalculator.butDivide.doClick();
            this.testingTool.enterCalculatorInput(b);
            uiCalculator.butEqual.doClick();
            double sutResult = this.testingTool.getCalculatorOutput();
            uiCalculator.butCancel.doClick();

            //Our tool's result
            double toolResult = this.testingTool.getToolCalculator().divide(a, b);

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

            output.put("Divide function specific values", this.testAlgebraicValues());
            output.put("Divide function inverse identity", this.testInverseIdentity());
            output.put("Divide function associativity", this.testAssociativity());
            output.put("Divide function n-version programming test", this.testNProgramming());

            return output;
        }
}
