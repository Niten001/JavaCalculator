package testingtool;

import java.util.*;
import calculator.*;

public class InverseTest {
    private UI uiCalculator;
    private TestingTool testingTool;

    protected InverseTest(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
        this.testingTool = new TestingTool(this.uiCalculator);
    }

    //  Test Specific Algebraic Values
        protected Map<String, String[][]> testAlgebraicValues() {
            //  Array of [Values to be tested, expected results]
            double[][] testedValues = {
                { 1, 1 },
                { 2, 1.0/2.0 },
                { 16, 1.0/16.0 },
                { -10, -0.1 },
                { 5, 0.2 },
                { 1234, 1.0/1234.0 },
                { -7.5, -1.0/7.5 },
                { -0.2, -5 },
                { 6.2, 1.0/6.2 }
            };

            Map<String, String[][]> output = new HashMap<String, String[][]>();
            String[][] results = new String[testedValues.length][5];

            for (int i = 0; i < testedValues.length; i++) {
                this.testingTool.enterCalculatorInput(testedValues[i][0]);
                this.uiCalculator.butInverse.doClick();
                results[i] = this.testingTool.insertValues(
                    testedValues[i][0],
                    testedValues[i][1],
                    this.testingTool.getCalculatorOutput(),
                    this.testingTool.checkFuzzyEqual(this.testingTool.getCalculatorOutput(), testedValues[i][1])
                );
                this.uiCalculator.butCancel.doClick();
            }

            output.put("Inverse function specific values", results);
            return output;
        }

    //  Test Identity
        protected Map<String, String[][]> testIdentity(int numAttempts) {
            Map<String, String[][]> output = new HashMap<String, String[][]>();
            String[][] results = new String[numAttempts][5];
            Random rand = new Random();

            for (int i = 0; i < numAttempts; i++) {
                double randomDouble = rand.nextDouble();

                this.testingTool.enterCalculatorInput(randomDouble);
                this.uiCalculator.butInverse.doClick();
                double inverse = this.testingTool.getCalculatorOutput();
                this.uiCalculator.butCancel.doClick();

                // Rule: 1/x * x = 1
                results[i] = this.testingTool.insertValues(
                    randomDouble,
                    randomDouble * inverse,
                    1,
                    this.testingTool.checkFuzzyEqual(randomDouble * inverse, 1)
                );
            }

            output.put("Inverse function identity", results);
            return output;
        }

        protected Map<String, String[][]> testIdentity() {
            return testIdentity(20);
        }

    //  Test Symmetry
        protected Map<String, String[][]> testSymmetry(int numAttempts) {
            Map<String, String[][]> output = new HashMap<String, String[][]>();
            String[][] results = new String[numAttempts][5];
            Random rand = new Random();

            for (int i = 0; i < numAttempts; i++) {
                double randomDouble = rand.nextDouble();

                this.testingTool.enterCalculatorInput(randomDouble);
                this.uiCalculator.butInverse.doClick();
                double a1 = this.testingTool.getCalculatorOutput();
                this.uiCalculator.butCancel.doClick();

                this.testingTool.enterCalculatorInput(a1);
                this.uiCalculator.butInverse.doClick();
                double a2 = this.testingTool.getCalculatorOutput();
                this.uiCalculator.butCancel.doClick();


                // Rule: Symmetry - The inverse of an inverse of x should be x
                results[i] = this.testingTool.insertValues(
                    randomDouble,
                    a2,
                    randomDouble,
                    this.testingTool.checkFuzzyEqual(a2, randomDouble)
                );
            }

            output.put("Inverse function symmetry", results);
            return output;
        }

        protected Map<String, String[][]> testSymmetry() {
            return testSymmetry(20);
        }
    
    //  Test All
        public Map<String, String[][]> testAll() {
            Map<String, String[][]> output = new HashMap<String, String[][]>();

            output.putAll(this.testAlgebraicValues());
            output.putAll(this.testIdentity());
            output.putAll(this.testSymmetry());

            return output;
        }
}