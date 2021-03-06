package testingtool;

import java.util.*;
import calculator.*;

public class TanTest {
    private UI uiCalculator;
    private TestingTool testingTool;

    public TanTest(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
        this.testingTool = new TestingTool(this.uiCalculator);
    }

    //  Test Specific Algebraic Values
        public Map<String, String[][]> testAlgebraicValues() {
            //  Array of [Values to be tested, expected results]
            double[][] testedValues = {
                { 0, 0 },
                { Math.PI/12.0, 2.0 - Math.sqrt(3) },
                { Math.PI/8.0, Math.sqrt(2) - 1.0 },
                { Math.PI/6.0, Math.sqrt(3)/3.0 },
                { Math.PI/4.0, 1 },
                { Math.PI/3.0, Math.sqrt(3) },
                { (3.0*Math.PI)/8.0, Math.sqrt(2) + 1.0 },
                { (5.0*Math.PI)/12.0, 2.0 + Math.sqrt(3) },
                { Math.PI/2.0, Double.POSITIVE_INFINITY }
            };

            Map<String, String[][]> output = new HashMap<String, String[][]>();
            String[][] results = new String[testedValues.length][5];

            for (int i = 0; i < testedValues.length; i++) {
                this.testingTool.enterCalculatorInput(testedValues[i][0]);
                this.uiCalculator.butTan.doClick();
                results[i] = this.testingTool.insertValues(
                    testedValues[i][0],
                    testedValues[i][1],
                    this.testingTool.getCalculatorOutput(),
                    this.testingTool.checkFuzzyEqual(this.testingTool.getCalculatorOutput(), testedValues[i][1])
                );
                this.uiCalculator.butCancel.doClick();
            }

            output.put("Tangent function specific values", results);
            return output;
        }

    //  Test Parity
        public Map<String, String[][]> testParity(int numAttempts) {
            Map<String, String[][]> output = new HashMap<String, String[][]>();
            String[][] results = new String[numAttempts][5];
            Random rand = new Random();

            for (int i = 0; i < numAttempts; i++) {
                double randomDouble = rand.nextDouble();

                this.testingTool.enterCalculatorInput(-1 * randomDouble);
                this.uiCalculator.butTan.doClick();
                double a1 = this.testingTool.getCalculatorOutput();
                this.uiCalculator.butCancel.doClick();

                this.testingTool.enterCalculatorInput(randomDouble);
                this.uiCalculator.butTan.doClick();
                double a2 = -1 * this.testingTool.getCalculatorOutput();
                this.uiCalculator.butCancel.doClick();

                // Rule: tan(-x) = -tan(x)
                results[i] = this.testingTool.insertValues(
                    randomDouble,
                    a1,
                    a2,
                    this.testingTool.checkFuzzyEqual(a1, a2)
                );
            }

            output.put("Tangent function parity", results);
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
                this.uiCalculator.butTan.doClick();
                double a1 = this.testingTool.getCalculatorOutput();
                this.uiCalculator.butCancel.doClick();

                this.testingTool.enterCalculatorInput(randomDouble + (double)(randomInt) * Math.PI);
                this.uiCalculator.butTan.doClick();
                double a2 = this.testingTool.getCalculatorOutput();
                this.uiCalculator.butCancel.doClick();

                // Rule: tan(x) = tan(x + πk) where k ∈ Z
                results[i] = this.testingTool.insertValues(
                    randomDouble,
                    randomInt,
                    a1,
                    a2,
                    this.testingTool.checkFuzzyEqual(a1, a2)
                );
            }

            output.put("Tangent function periodicity", results);
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
