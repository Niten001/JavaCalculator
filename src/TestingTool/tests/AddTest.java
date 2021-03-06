package testingtool;

import java.util.*;
import calculator.*;

public class AddTest {
    private UI uiCalculator;
    private TestingTool testingTool;

    public AddTest(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
        this.testingTool = new TestingTool(this.uiCalculator);
    }

    //  Test Specific Algebraic Values
    public Map<String, String[][]> testAlgebraicValues() {
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

        Map<String, String[][]> output = new HashMap<String, String[][]>();
        String[][] results = new String[testedValues.length][5];

        for (int i = 0; i < testedValues.length; i++) {
            this.testingTool.enterCalculatorInput(testedValues[i][0]);
            this.uiCalculator.butAdd.doClick();
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
        
        output.put("Addition function specific values", results);
        return output;
    }

    //  Test Commutativity
    public Map<String, String[][]> testCommutativity(int numAttempts) {
        Map<String, String[][]> output = new HashMap<String, String[][]>();
        String[][] results = new String[numAttempts][5];
        Random rand = new Random();

        for(int i = 0; i < numAttempts; i++) {
            int a = rand.nextInt();
            int b = rand.nextInt();

            // Do a + b
            this.testingTool.enterCalculatorInput(a);
            uiCalculator.butAdd.doClick();
            this.testingTool.enterCalculatorInput(b);
            uiCalculator.butEqual.doClick();
            double output1 = this.testingTool.getCalculatorOutput();
            uiCalculator.butCancel.doClick();

            // Do b + a
            this.testingTool.enterCalculatorInput(b);
            uiCalculator.butAdd.doClick();
            this.testingTool.enterCalculatorInput(a);
            uiCalculator.butEqual.doClick();
            double output2 = this.testingTool.getCalculatorOutput();

            //  Commutativity relation: a + b = b + a
            results[i] = this.testingTool.insertValues(
                a,
                b,
                output1,
                output2,
                this.testingTool.checkFuzzyEqual(output1, output2)
            );
        }
        
        output.put("Addition function commutativity", results);
        return output;
    }

    public Map<String, String[][]> testCommutativity() {
        return testCommutativity(20);
    }

    //  Test Associativity
    public Map<String, String[][]> testAssociativity(int numAttempts) {
        Map<String, String[][]> output = new HashMap<String, String[][]>();
        String[][] results = new String[numAttempts][5];
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
            results[i] = this.testingTool.insertValues(
                a,
                b,
                c,
                output1,
                output2,
                this.testingTool.checkFuzzyEqual(output1, output2)
            );
        }

        output.put("Addition function associativity", results);
        return output;
    }

    public Map<String, String[][]> testAssociativity() {
        return testAssociativity(20);
    }

    //  Test All
    public Map<String, String[][]> testAll() {
        Map<String, String[][]> output = new HashMap<String, String[][]>();

        output.putAll(this.testAlgebraicValues());
        output.putAll(this.testCommutativity());
        output.putAll(this.testAssociativity());

        return output;
    }
}
