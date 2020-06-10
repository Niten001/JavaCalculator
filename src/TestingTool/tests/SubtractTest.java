package testingtool;

import java.util.*;
import calculator.*;

public class SubtractTest {
    private UI uiCalculator;
    private TestingTool testingTool;

    public SubtractTest(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
        this.testingTool = new TestingTool(this.uiCalculator);
    }

    //  Test Specific Algebraic Values
    public Map<String, String[][]> testAlgebraicValues() {
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

        Map<String, String[][]> output = new HashMap<String, String[][]>();
            String[][] results = new String[testedValues.length][5];

        for (int i = 0; i < testedValues.length; i++) {
            this.testingTool.enterCalculatorInput(testedValues[i][0]);
            this.uiCalculator.butMinus.doClick();
            this.testingTool.enterCalculatorInput(testedValues[i][1]);
            this.uiCalculator.butEqual.doClick();
            results[i] = this.testingTool.insertValues(
                testedValues[i][0],
                testedValues[i][1],
                testedValues[i][2],
                this.testingTool.getCalculatorOutput(),
                this.testingTool.checkFuzzyEqual(this.testingTool.getCalculatorOutput(), testedValues[i][2])
            );
            this.uiCalculator.butCancel.doClick();
        }

        output.put("Subtraction function specific values", results);
        return output;
    }

    //  Test Anticommutativity
    public Map<String, String[][]> testAnticommutativity(int numAttempts) {
        Map<String, String[][]> output = new HashMap<String, String[][]>();
        String[][] results = new String[numAttempts][5];
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
            results[i] = this.testingTool.insertValues(
                a,
                b,
                output1,
                output2,
                this.testingTool.checkFuzzyEqual(output1, output2)
            );
        }

        output.put("Subtraction function anticommutativity", results);
        return output;
    }

    public Map<String, String[][]> testAnticommutativity() {
        return testAnticommutativity(20);
    }

    //  Test Identity
    public Map<String, String[][]> testIdentity(int numAttempts) {
        Map<String, String[][]> output = new HashMap<String, String[][]>();
        String[][] results = new String[numAttempts][5];
        Random rand = new Random();

        for(int i = 0; i < numAttempts; i++) {
            int a = rand.nextInt();

            // Do a - a
            this.testingTool.enterCalculatorInput(a);
            uiCalculator.butMinus.doClick();
            this.testingTool.enterCalculatorInput(a);
            uiCalculator.butEqual.doClick();
            double output1 = this.testingTool.getCalculatorOutput();
            uiCalculator.butCancel.doClick();

            //  Identity relation: a - a = 0
            results[i] = this.testingTool.insertValues(
                a,
                output1,
                0,
                this.testingTool.checkFuzzyEqual(output1, 0)
            );
        }

        output.put("Subtraction function identity", results);
        return output;
    }

    public Map<String, String[][]> testIdentity() {
        return testIdentity(20);
    }

    //  Test All
    public Map<String, String[][]> testAll() {
        Map<String, String[][]> output = new HashMap<String, String[][]>();

        output.putAll(this.testAlgebraicValues());
        output.putAll(this.testAnticommutativity());
        output.putAll(this.testIdentity());

        return output;
    }
}
