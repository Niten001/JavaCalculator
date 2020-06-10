package testingtool;

import java.util.*;
import javacalculator.*;

public class InverseTest {
    private UI uiCalculator;
    private TestingTool testingTool;

    protected InverseTest(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
        this.testingTool = new TestingTool(this.uiCalculator);
    }

    //  Test Specific Algebraic Values
        protected boolean[] testAlgebraicValues() {
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

            boolean[] numPassed = new boolean[testedValues.length];

            for (int i = 0; i < testedValues.length; i++) {
                this.testingTool.enterCalculatorInput(testedValues[i][0]);
                this.uiCalculator.butInverse.doClick();
                numPassed[i] = this.testingTool.checkFuzzyEqual(this.testingTool.getCalculatorOutput(), testedValues[i][1]);
                this.uiCalculator.butCancel.doClick();
            }

            return numPassed;
        }

    //  Test Identity
        protected boolean[] testIdentity(int numAttempts) {
            boolean[] numPassed = new boolean[numAttempts];
            Random rand = new Random();

            for (int i = 0; i < numAttempts; i++) {
                double randomDouble = rand.nextDouble();

                this.testingTool.enterCalculatorInput(randomDouble);
                this.uiCalculator.butInverse.doClick();
                double inverse = this.testingTool.getCalculatorOutput();
                this.uiCalculator.butCancel.doClick();

                // Rule: 1/x * x = 1
                numPassed[i] = this.testingTool.checkFuzzyEqual(randomDouble * inverse, 1);
            }

            return numPassed;
        }

        protected boolean[] testIdentity() {
            return testIdentity(20);
        }

    //  Test Symmetry
        protected boolean[] testSymmetry(int numAttempts) {
            boolean[] numPassed = new boolean[numAttempts];
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
                numPassed[i] = this.testingTool.checkFuzzyEqual(a2, randomDouble);
            }

            return numPassed;
        }

        protected boolean[] testSymmetry() {
            return testSymmetry(20);
        }

        //Test the SUT's calculator result with our tool's calculator resut (N-version programming)
        public boolean[] testNProgramming(int numAttempts) {
            boolean[] numPassed = new boolean[numAttempts];
            Random rand = new Random();

            for(int i = 0; i < numAttempts; i++) {
                double a = rand.nextDouble();
                double b = rand.nextDouble();

                //Software under test result
                this.testingTool.enterCalculatorInput(a);
                uiCalculator.butInverse.doClick();
                double sutResult = this.testingTool.getCalculatorOutput();
                uiCalculator.butCancel.doClick();

                //Our tool's result
                double toolResult = this.testingTool.getToolCalculator().inverse(a);

                numPassed[i] = this.testingTool.checkFuzzyEqual(sutResult, toolResult);
            }

            return numPassed;
        }

        public boolean[] testNProgramming() {
            return testNProgramming(20);
        }

    //  Test All
        public Map<String, boolean[]> testAll() {
            Map<String, boolean[]> output = new HashMap<String, boolean[]>();

            output.put("Inverse function specific values", this.testAlgebraicValues());
            output.put("Inverse function identity", this.testIdentity());
            output.put("Inverse function symmetry", this.testSymmetry());
            output.put("Inverse function n-version programming test", this.testNProgramming());

            return output;
        }
}
