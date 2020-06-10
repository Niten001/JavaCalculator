package testingtool;

import java.util.*;
import calculator.*;

public class CosTest {
    private UI uiCalculator;
    private TestingTool testingTool;

    public CosTest(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
        this.testingTool = new TestingTool(this.uiCalculator);
    }

    //  Test Specific Algebraic Values
        public Map<String, String[][]> testAlgebraicValues() {
            //  Array of [Values to be tested, expected results]
            double[][] testedValues = {
                { 0, 1 },
                { Math.PI/12.0, (Math.sqrt(6) + Math.sqrt(2))/4.0 },
                { Math.PI/8.0, Math.sqrt(2 + Math.sqrt(2))/2.0 },
                { Math.PI/6.0, Math.sqrt(3)/2.0 },
                { Math.PI/4.0, Math.sqrt(2)/2.0 },
                { Math.PI/3.0, 1.0/2.0 },
                { (3*Math.PI)/8.0, Math.sqrt(2 - Math.sqrt(2))/2.0 },
                { (5*Math.PI)/12.0, (Math.sqrt(6) - Math.sqrt(2))/4.0 },
                { Math.PI/2.0, 0 }
            };

            Map<String, String[][]> output = new HashMap<String, String[][]>();
            String[][] results = new String[testedValues.length][5];

            for (int i = 0; i < testedValues.length; i++) {
                this.testingTool.enterCalculatorInput(testedValues[i][0]);
                this.uiCalculator.butCos.doClick();
                results[i] = this.testingTool.insertValues(
                    testedValues[i][0],
                    testedValues[i][1],
                    this.testingTool.getCalculatorOutput(),
                    this.testingTool.checkFuzzyEqual(this.testingTool.getCalculatorOutput(), testedValues[i][1])
                );
                this.uiCalculator.butCancel.doClick();
            }

            output.put("Cosine function specific values", results);
            return output;
        }

    //  Test Parity
        public Map<String, String[][]> testParity(int numAttempts) {
            Map<String, String[][]> output = new HashMap<String, String[][]>();
            String[][] results = new String[numAttempts][5];
            Random rand = new Random();

            for (int i = 0; i < numAttempts; i++) {
                double randomDouble = rand.nextDouble();

                this.testingTool.enterCalculatorInput(randomDouble);
                this.uiCalculator.butCos.doClick();
                double a1 = this.testingTool.getCalculatorOutput();
                this.uiCalculator.butCancel.doClick();

                this.testingTool.enterCalculatorInput(-1 * randomDouble);
                this.uiCalculator.butCos.doClick();
                double a2 = this.testingTool.getCalculatorOutput();
                this.uiCalculator.butCancel.doClick();

                // Rule: cos(x) = cos(-x)
                results[i] = this.testingTool.insertValues(
                    randomDouble,
                    a1,
                    a2,
                    this.testingTool.checkFuzzyEqual(a1, a2)
                );
            }

            output.put("Cosine function parity", results);
            return output;
        }

        public Map<String, String[][]> testParity() {
            return testParity(20);
        }
    //  Test Periodicity
        public Map<String, String[][]> testPeriodicity(int numAttempts) {
            Map<String, String[][]> output = new HashMap<String, String[][]>();
            String[][] results = new String[numAttempts][5];
            Random rand = new Random();

            for (int i = 0; i < numAttempts; i++) {
                double randomDouble = rand.nextDouble();
                int randomInt = rand.nextInt();

                this.testingTool.enterCalculatorInput(randomDouble);
                this.uiCalculator.butCos.doClick();
                double a1 = this.testingTool.getCalculatorOutput();
                this.uiCalculator.butCancel.doClick();

                this.testingTool.enterCalculatorInput(randomDouble + (2.0 * (double)(randomInt) * Math.PI));
                this.uiCalculator.butCos.doClick();
                double a2 = this.testingTool.getCalculatorOutput();
                this.uiCalculator.butCancel.doClick();

                // Rule: cos(x) = cos(x + 2πk) where k ∈ Z
                results[i] = this.testingTool.insertValues(
                    randomDouble,
                    randomInt,
                    a1,
                    a2,
                    this.testingTool.checkFuzzyEqual(a1, a2)
                );
            }

            output.put("Cosine function periodicity", results);
            return output;
        }

        public Map<String, String[][]> testPeriodicity() {
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
