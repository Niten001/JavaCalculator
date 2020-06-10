package testingtool;

import java.util.*;
import javacalculator.*;

public class PowerTest {
    private UI uiCalculator;
    private TestingTool testingTool;

    public PowerTest(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
        this.testingTool = new TestingTool(this.uiCalculator);
    }

    //  Test Specific Algebraic Values
    public boolean[] testAlgebraicValues() {
        //  Array of [Value 1 to be tested, Value 2 to be tested, expected results]
        double[][] testedValues = {
            { 1, 3, Math.pow(1,3) },
            { 10, 3, Math.pow(10,3) },
            { 7, 0, Math.pow(7,0) },
            { -6, 3, Math.pow(-6,3) },
            { 6.7, 8, Math.pow(6.7, 8) },
            { -2, 15, Math.pow(-2,15) },
            { 0.2, 6, Math.pow(0.2, 6) },
            { 1243, 3, Math.pow(1243, 3) },
            { 5, 3, Math.pow(5, 3) }
        };

        boolean[] numPassed = new boolean[testedValues.length];

        for (int i = 0; i < testedValues.length; i++) {
            this.testingTool.enterCalculatorInput(testedValues[i][0]);
            this.uiCalculator.butPower.doClick();
            this.testingTool.enterCalculatorInput(testedValues[i][1]);
            this.uiCalculator.butEqual.doClick();
            numPassed[i] = this.testingTool.checkFuzzyEqual(this.testingTool.getCalculatorOutput(), testedValues[i][2]);
            this.uiCalculator.butCancel.doClick();
        }

        return numPassed;
    }

    //  Test Addition Identity
    public boolean[] testAdditionIdentity(int numAttempts) {
        boolean[] numPassed = new boolean[numAttempts];
        Random rand = new Random();

        for(int i = 0; i < numAttempts; i++) {
            int a = rand.nextInt(100);
            int power_m = rand.nextInt(10);
            int power_n = rand.nextInt(10);

            //Do a^m
            this.testingTool.enterCalculatorInput(a);
            uiCalculator.butPower.doClick();
            this.testingTool.enterCalculatorInput(power_m);
            uiCalculator.butEqual.doClick();
            double output1 = this.testingTool.getCalculatorOutput();
            uiCalculator.butCancel.doClick();

            //Do a^n
            this.testingTool.enterCalculatorInput(a);
            uiCalculator.butPower.doClick();
            this.testingTool.enterCalculatorInput(power_n);
            uiCalculator.butEqual.doClick();
            double output2 = this.testingTool.getCalculatorOutput();

            //Do a^(m+n)
            this.testingTool.enterCalculatorInput(a);
            uiCalculator.butPower.doClick();
            this.testingTool.enterCalculatorInput(power_m + power_n);
            uiCalculator.butEqual.doClick();
            double output3 = this.testingTool.getCalculatorOutput();
            uiCalculator.butCancel.doClick();

            //  Addition identity relation: a^(m+n) = a^m * a^n
            numPassed[i] = this.testingTool.checkFuzzyEqual(output1 * output2, output3);
        }

        return numPassed;
    }

    public boolean[] testAdditionIdentity() {
        return testAdditionIdentity(20);
    }

    //  Test Multiply Identity
    public boolean[] testMultiplyIdentity(int numAttempts) {
        boolean[] numPassed = new boolean[numAttempts];
        Random rand = new Random();

        for(int i = 0; i < numAttempts; i++) {
            int a = rand.nextInt(100);
            int power_m = rand.nextInt(10);
            int power_n = rand.nextInt(10);

            //Do (a^m)^n
            this.testingTool.enterCalculatorInput(a);
            uiCalculator.butPower.doClick();
            this.testingTool.enterCalculatorInput(power_m);
            uiCalculator.butPower.doClick();
            this.testingTool.enterCalculatorInput(power_n);
            uiCalculator.butEqual.doClick();
            double output1 = this.testingTool.getCalculatorOutput();
            uiCalculator.butCancel.doClick();

            //Do a^(m*n)
            this.testingTool.enterCalculatorInput(a);
            uiCalculator.butPower.doClick();
            this.testingTool.enterCalculatorInput(power_m * power_n);
            uiCalculator.butEqual.doClick();
            double output2 = this.testingTool.getCalculatorOutput();
            uiCalculator.butCancel.doClick();

            //  Multiply identity relation: (a^m)^n = a^(m*n)
            numPassed[i] = this.testingTool.checkFuzzyEqual(output1, output2);
        }

        return numPassed;
    }

    public boolean[] testMultiplyIdentity() {
        return testMultiplyIdentity(20);
    }

    //  Test All
    public Map<String, boolean[]> testAll() {
        Map<String, boolean[]> output = new HashMap<String, boolean[]>();

        output.put("Power function specific values", this.testAlgebraicValues());
        output.put("Power function addition identity", this.testAdditionIdentity());
        output.put("Power function multiply identity", this.testMultiplyIdentity());

        return output;
    }
}
