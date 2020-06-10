package testingtool;

import java.util.*;
import javacalculator.*;

public class SinTest {
    private UI uiCalculator;
    private TestingTool testingTool;

    protected SinTest(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
        this.testingTool = new TestingTool(this.uiCalculator);
    }

    //  Test Specific Algebraic Values
        protected boolean[] testAlgebraicValues() {
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

            boolean[] numPassed = new boolean[testedValues.length];

            for (int i = 0; i < testedValues.length; i++) {
                this.testingTool.enterCalculatorInput(testedValues[i][0]);
                this.uiCalculator.butSin.doClick();
                numPassed[i] = this.testingTool.checkFuzzyEqual(this.testingTool.getCalculatorOutput(), testedValues[i][1]);
                this.uiCalculator.butCancel.doClick();
            }

            return numPassed;
        }

    //  Test Parity
        protected boolean[] testParity(int numAttempts) {
            boolean[] numPassed = new boolean[numAttempts];
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

                numPassed[i] = this.testingTool.checkFuzzyEqual(a1, a2);
            }

            return numPassed;
        }

        protected boolean[] testParity() {
            return testParity(20);
        }
    //  Test Periodicity
        protected boolean[] testPeriodicity(int numAttempts) {
            boolean[] numPassed = new boolean[numAttempts];
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

                numPassed[i] = this.testingTool.checkFuzzyEqual(a1, a2);
            }

            return numPassed;
        }

        protected boolean[] testPeriodicity() {
            return testPeriodicity(20);
        }

    //  Test All
        public Map<String, boolean[]> testAll() {
            Map<String, boolean[]> output = new HashMap<String, boolean[]>();

            output.put("Sine function specific values", this.testAlgebraicValues());
            output.put("Sine function parity", this.testParity());
            output.put("Sine function periodicity", this.testPeriodicity());

            return output;
        }
}
