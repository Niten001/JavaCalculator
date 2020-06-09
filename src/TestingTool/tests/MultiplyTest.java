package testingtool;

import java.util.*;
import javacalculator.*;

public class MultiplyTest {
    private UI uiCalculator;
    private TestingTool testingTool;

    public MultiplyTest(UI uiCalculator) {
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
            { 6.7, 8 },
            { -2, -20 },
            { 0.2, -3 },
            { 12432, 323 },
            { 5, -3 }
        };

        double[] testedAnswers = {0,30,147,-18,53.6,40,-0.6,4015536,-15};

        boolean[] numPassed = new boolean[testedValues.length];

        for (int i = 0; i < testedValues.length; i++) {
            this.testingTool.enterCalculatorInput(testedValues[i][0]);
            this.uiCalculator.butMultiply.doClick();
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

            //Do a*b
            this.testingTool.enterCalculatorInput(a);
            uiCalculator.butMultiply.doClick();
            this.testingTool.enterCalculatorInput(b);
            uiCalculator.butEqual.doClick();
            double output1 = this.testingTool.getCalculatorOutput();
            uiCalculator.butCancel.doClick();

            //Do b*a
            this.testingTool.enterCalculatorInput(b);
            uiCalculator.butMultiply.doClick();
            this.testingTool.enterCalculatorInput(a);
            uiCalculator.butEqual.doClick();
            double output2 = this.testingTool.getCalculatorOutput();

            //  Commutativity relation: a * b = b * a
            numPassed[i] = this.testingTool.checkFuzzyEqual(output1, output2);
        }

        return numPassed;
    }

    public boolean[] testCommutativity() {
        return testCommutativity(20);
    }

    //  Test Commutativity
    public boolean[] testAssociativity(int numAttempts) {
        boolean[] numPassed = new boolean[numAttempts];
        Random rand = new Random();

        for(int i = 0; i < numAttempts; i++) {
            int a = rand.nextInt();
            int b = rand.nextInt();
            int c = rand.nextInt();

            //Do (a*b)*c
            this.testingTool.enterCalculatorInput(a * b);
            uiCalculator.butMultiply.doClick();
            this.testingTool.enterCalculatorInput(b);
            uiCalculator.butEqual.doClick();
            double output1 = this.testingTool.getCalculatorOutput();
            uiCalculator.butCancel.doClick();

            //Do a*(b*c)
            this.testingTool.enterCalculatorInput(a);
            uiCalculator.butMultiply.doClick();
            this.testingTool.enterCalculatorInput(b*c);
            uiCalculator.butEqual.doClick();
            double output2 = this.testingTool.getCalculatorOutput();

            //  Commutativity relation: a * b = b * a
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

        output.put("Multiply function specific values", this.testAlgebraicValues());
        output.put("Multiply function commutativity", this.testCommutativity());
        output.put("Multiply function associativity", this.testCommutativity());

        return output;
    }
}
