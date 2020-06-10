package testingtool;

import java.util.*;
import calculator.*;

public class DivideTest {
    private UI uiCalculator;
    private TestingTool testingTool;

    public DivideTest(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
        this.testingTool = new TestingTool(this.uiCalculator);
    }

    //  Test Specific Algebraic Values
    public Map<String, String[][]> testAlgebraicValues() {
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

        Map<String, String[][]> output = new HashMap<String, String[][]>();
        String[][] results = new String[testedValues.length][5];

        for (int i = 0; i < testedValues.length; i++) {
            this.testingTool.enterCalculatorInput(testedValues[i][0]);
            this.uiCalculator.butDivide.doClick();
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

        output.put("Divide function specific values", results);
        return output;
    }

    //  Test Inverse Identity
    public Map<String, String[][]> testInverseIdentity(int numAttempts) {
        Map<String, String[][]> output = new HashMap<String, String[][]>();
        String[][] results = new String[numAttempts][5];
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
            results[i] = this.testingTool.insertValues(
                a,
                b,
                output1 * output2,
                1,
                this.testingTool.checkFuzzyEqual(output1 * output2, 1)
            );
        }

        output.put("Divide function inverse identity", results);
        return output;
    }

    public Map<String, String[][]> testInverseIdentity() {
        return testInverseIdentity(20);
    }

    //  Test associativity
    public Map<String, String[][]> testAssociativity(int numAttempts) {
        Map<String, String[][]> output = new HashMap<String, String[][]>();
        String[][] results = new String[numAttempts][5];
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
            results[i] = this.testingTool.insertValues(
                a,
                b,
                c,
                output1,
                output2,
                this.testingTool.checkFuzzyEqual(output1, output2)
            );
        }

        output.put("Divide function associativity", results);
        return output;
    }

    public Map<String, String[][]> testAssociativity() {
        return testAssociativity(20);
    }

    //  Test All
        public Map<String, String[][]> testAll() {
            Map<String, String[][]> output = new HashMap<String, String[][]>();

            output.putAll(this.testAlgebraicValues());
            output.putAll(this.testInverseIdentity());
            output.putAll(this.testAssociativity());

            return output;
        }
}
