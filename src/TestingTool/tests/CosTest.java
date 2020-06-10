package testingtool;

import java.util.*;
import javacalculator.*;

public class CosTest {
    private UI uiCalculator;
    private TestingTool testingTool;

    public CosTest(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
        this.testingTool = new TestingTool(this.uiCalculator);
    }

    //  Test Specific Algebraic Values
        public boolean[] testAlgebraicValues() {
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

            boolean[] numPassed = new boolean[testedValues.length];

            for (int i = 0; i < testedValues.length; i++) {
                this.testingTool.enterCalculatorInput(testedValues[i][0]);
                this.uiCalculator.butCos.doClick();
                numPassed[i] = this.testingTool.checkFuzzyEqual(this.testingTool.getCalculatorOutput(), testedValues[i][1]);
                this.uiCalculator.butCancel.doClick();
            }

            return numPassed;
        }

    //  Test Parity
        public boolean[] testParity(int numAttempts) {
            boolean[] numPassed = new boolean[numAttempts];
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
                numPassed[i] = this.testingTool.checkFuzzyEqual(a1, a2);
            }

            return numPassed;
        }

        public boolean[] testParity() {
            return testParity(20);
        }
    //  Test Periodicity
        public boolean[] testPeriodicity(int numAttempts) {
            boolean[] numPassed = new boolean[numAttempts];
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
                numPassed[i] = this.testingTool.checkFuzzyEqual(a1, a2);
            }

            return numPassed;
        }

        public boolean[] testPeriodicity() {
            return testPeriodicity(20);
        }
    //  Test All
        public Map<String, boolean[]> testAll() {
            Map<String, boolean[]> output = new HashMap<String, boolean[]>();

            output.put("Cosine function specific values", this.testAlgebraicValues());
            output.put("Cosine function parity", this.testParity());
            output.put("Cosine function periodicity", this.testPeriodicity());

            return output;
        }
}
