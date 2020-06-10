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
        //  Array of [Values to be tested, expected results]
        double[][] testedValues = {
            { 1, 0 },
            { 10, 3 },
            { 7, 21 },
            { -6, 3 },
            { 6.7, 8.0 },
            { -2, -20 },
            { 0.2, -3.0 },
            { 12432, 323 },
            { 5, -3 }
        };

        double[] testedAnswers = {1,13,28,-3,14.7,-22,-2.8,12755,2};

        boolean[] numPassed = new boolean[testedValues.length];

        for (int i = 0; i < testedValues.length; i++) {
            this.testingTool.enterCalculatorInput(testedValues[i][0]);
            this.uiCalculator.butAdd.doClick();
            this.testingTool.enterCalculatorInput(testedValues[i][1]);
            this.uiCalculator.butEqual.doClick();
            numPassed[i] = this.testingTool.checkFuzzyEqual(this.testingTool.getCalculatorOutput(), testedAnswers[i]);
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
            int a = rand.nextInt();
            int b = rand.nextInt();
            int c = rand.nextInt();

            //Do (a + b) + c
            this.testingTool.enterCalculatorInput((a + b));
            uiCalculator.butAdd.doClick();
            this.testingTool.enterCalculatorInput(c);
            uiCalculator.butEqual.doClick();
            double output1 = this.testingTool.getCalculatorOutput();
            uiCalculator.butCancel.doClick();

            //Do a + (b + c)
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

    //  Test All
    public Map<String, boolean[]> testAll() {
        Map<String, boolean[]> output = new HashMap<String, boolean[]>();

        output.put("Addition function specific values", this.testAlgebraicValues());
        output.put("Addition function commutativity", this.testCommutativity());
        output.put("Addition function associativity", this.testAssociativity());

        return output;
    }
}
