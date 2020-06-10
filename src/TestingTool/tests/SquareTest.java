package testingtool;

import java.util.*;
import javacalculator.*;

public class SquareTest {
    private UI uiCalculator;
    private TestingTool testingTool;

    protected SquareTest(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
        this.testingTool = new TestingTool(this.uiCalculator);
    }

    //  Test Specific Algebraic Values
        protected boolean[] testAlgebraicValues() {
            //  Array of [Values to be tested, expected results]
            double[][] testedValues = {
                { 2, 4 },
                { 4, 16 },
                { -5.78, Math.pow(-5.78, 2) },
                { -17, Math.pow(-17, 2) },
                { 0, Math.pow(0, 2) },
                { -7, Math.pow(-7, 2) },
                { 10, Math.pow(10, 2) },
                { 632, Math.pow(632, 2) },
                { 4973, Math.pow(4973, 2) }
            };

            boolean[] numPassed = new boolean[testedValues.length];

            for (int i = 0; i < testedValues.length; i++) {
                this.testingTool.enterCalculatorInput(testedValues[i][0]);
                this.uiCalculator.butSquare.doClick();
                numPassed[i] = this.testingTool.checkFuzzyEqual(this.testingTool.getCalculatorOutput(), testedValues[i][1]);
                this.uiCalculator.butCancel.doClick();
            }

            return numPassed;
        }

    //  Test Positive Square
        protected boolean[] testPositiveSquare(int numAttempts) {
            boolean[] numPassed = new boolean[numAttempts];
            Random rand = new Random();

            for (int i = 0; i < numAttempts; i++) {
                int randomNum = rand.nextInt();

                this.testingTool.enterCalculatorInput(randomNum);
                this.uiCalculator.butSquare.doClick();
                double square = this.testingTool.getCalculatorOutput();
                this.uiCalculator.butCancel.doClick();

                // Rule: A square of a number should always be positive
                numPassed[i] = (square >= 0);
            }

            return numPassed;
        }

        protected boolean[] testPositiveSquare() {
            return testPositiveSquare(20);
        }

    //  Test Identity
        protected boolean[] testIdentity(int numAttempts) {
            boolean[] numPassed = new boolean[numAttempts];
            Random rand = new Random();

            for (int i = 0; i < numAttempts; i++) {
                int randomNum = rand.nextInt();

                this.testingTool.enterCalculatorInput(randomNum);
                this.uiCalculator.butSquare.doClick();
                double square1 = this.testingTool.getCalculatorOutput();
                this.uiCalculator.butCancel.doClick();

                this.testingTool.enterCalculatorInput(-1 * randomNum);
                this.uiCalculator.butSquare.doClick();
                double square2 = this.testingTool.getCalculatorOutput();
                this.uiCalculator.butCancel.doClick();

                // Rule: Property of squares - (x)^2 = (-x)^2
                numPassed[i] = this.testingTool.checkFuzzyEqual(square1, square2);
            }

            return numPassed;
        }

        protected boolean[] testIdentity() {
            return testIdentity(20);
        }

        //Test the SUT's calculator result with our tool's calculator resut (N-version programming)
        public boolean[] testNProgramming(int numAttempts) {
            boolean[] numPassed = new boolean[numAttempts];
            Random rand = new Random();

            for(int i = 0; i < numAttempts; i++) {
                double a = rand.nextInt();

                //Software under test result
                this.testingTool.enterCalculatorInput(a);
                uiCalculator.butSquare.doClick();
                double sutResult = this.testingTool.getCalculatorOutput();
                uiCalculator.butCancel.doClick();

                //Our tool's result
                double toolResult = this.testingTool.getToolCalculator().square(a);

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

            output.put("Square function specific values", this.testAlgebraicValues());
            output.put("Square function positive square", this.testPositiveSquare());
            output.put("Square function identity", this.testIdentity());
            output.put("Square function n-version programming test", this.testNProgramming());

            return output;
        }
}
