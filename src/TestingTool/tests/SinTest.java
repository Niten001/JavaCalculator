package testingtool;

import java.util.*;
import calculator.*;

public class SinTest {
    private UI uiCalculator;
    private TestingTool testingTool;

    protected SinTest(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
        this.testingTool = new TestingTool(this.uiCalculator);
    }

    //  Test Specific Algebraic Values
        protected Map<String, String[][]> testAlgebraicValues() {
            //  Array of [Values to be tested, expected results]
            double[][] testedValues = {
                { 0, 0 },
                { Math.PI/12.0, (Math.sqrt(6) - Math.sqrt(2))/4.0 },
                { Math.PI/8.0, Math.sqrt(2 - Math.sqrt(2))/2.0 },
                { Math.PI/6.0, 1.0/2.0 },
                { Math.PI/4.0, Math.sqrt(2)/2.0 },
                { Math.PI/3.0, Math.sqrt(3)/2.0 },
                { (3*Math.PI)/8.0, Math.sqrt(2 + Math.sqrt(2))/2.0 },
                { (5*Math.PI)/12.0, (Math.sqrt(6) + Math.sqrt(2))/4.0 },
                { Math.PI/2.0, 1 }
            };

            Map<String, String[][]> output = new HashMap<String, String[][]>();
            String[][] results = new String[testedValues.length][5];

            for (int i = 0; i < testedValues.length; i++) {
                this.testingTool.enterCalculatorInput(testedValues[i][0]);
                this.uiCalculator.butSin.doClick();
                results[i] = this.testingTool.insertValues(
                    testedValues[i][0],
                    testedValues[i][1],
                    this.testingTool.getCalculatorOutput(),
                    this.testingTool.checkFuzzyEqual(this.testingTool.getCalculatorOutput(), testedValues[i][1])
                );
                this.uiCalculator.butCancel.doClick();
            }

            output.put("Sine function specific values", results);
            return output;
        }

    //  Test Parity
        protected Map<String, String[][]> testParity(int numAttempts) {
            Map<String, String[][]> output = new HashMap<String, String[][]>();
            String[][] results = new String[numAttempts][5];
            Random rand = new Random();

            for (int i = 0; i < numAttempts; i++) {
                double randomDouble = rand.nextDouble();

                this.testingTool.enterCalculatorInput(-1 * randomDouble);
                this.uiCalculator.butSin.doClick();
                double a1 = this.testingTool.getCalculatorOutput();
                this.uiCalculator.butCancel.doClick();

                this.testingTool.enterCalculatorInput(randomDouble);
                this.uiCalculator.butSin.doClick();
                double a2 = -1 * this.testingTool.getCalculatorOutput();
                this.uiCalculator.butCancel.doClick();

                // Rule: sin(-x) = -sin(x)
                results[i] = this.testingTool.insertValues(
                    randomDouble,
                    a1,
                    a2,
                    this.testingTool.checkFuzzyEqual(a1, a2)
                );
            }

            output.put("Sine function parity", results);
            return output;
        }

        protected Map<String, String[][]> testParity() {
            return testParity(20);
        }
    //  Test Periodicity
        protected Map<String, String[][]> testPeriodicity(int numAttempts) {
            Map<String, String[][]> output = new HashMap<String, String[][]>();
            String[][] results = new String[numAttempts][5];
            Random rand = new Random();

            for (int i = 0; i < numAttempts; i++) {
                double randomDouble = rand.nextDouble();
                int randomInt = rand.nextInt();

                this.testingTool.enterCalculatorInput(randomDouble);
                this.uiCalculator.butSin.doClick();
                double a1 = this.testingTool.getCalculatorOutput();
                this.uiCalculator.butCancel.doClick();

                this.testingTool.enterCalculatorInput(randomDouble + (2.0 * (double)(randomInt) * Math.PI));
                this.uiCalculator.butSin.doClick();
                double a2 = this.testingTool.getCalculatorOutput();
                this.uiCalculator.butCancel.doClick();

                // Rule: sin(x) = sin(x + 2πk) where k ∈ Z
                results[i] = this.testingTool.insertValues(
                    randomDouble,
                    randomInt,
                    a1,
                    a2,
                    this.testingTool.checkFuzzyEqual(a1, a2)
                );
            }

            output.put("Sine function periodicity", results);
            return output;
        }

        protected Map<String, String[][]> testPeriodicity() {
            return testPeriodicity(20);
        }

    //  Test All
        public Map<String, String[][]> testAll() {
            Map<String, String[][]> output = new HashMap<String, String[][]>();

            output.putAll(this.testAlgebraicValues());
            output.putAll(this.testParity());
            output.putAll(this.testPeriodicity());

            return output;
        }
}
